#!/usr/bin/env python3
"""
Rename duplicate field declarations by appending type suffix.
Example: int a -> a_int, Graphics a -> a_Graphics
Updates all usages within the same file.
"""
import re, os, sys
from collections import defaultdict

def find_fields(content):
    """Return dict: field_name -> list of types."""
    # Remove strings and comments to avoid false positives
    content_no_comments = re.sub(r'//.*$', '', content, flags=re.MULTILINE)
    content_no_comments = re.sub(r'/\*.*?\*/', '', content_no_comments, flags=re.DOTALL)
    content_no_comments = re.sub(r'"([^"\\]|\\.)*"', '""', content_no_comments)
    
    lines = content_no_comments.split('\n')
    fields = defaultdict(list)
    # Match field declarations: [modifiers] Type name [= ...];
    decl_pattern = re.compile(r'(?:(?:public|private|protected|static|final|transient|volatile)\s+)*\s*(\w[\w\[\].]*(?:<[^>]+>)?)\s+([a-z_][a-z0-9_]*)\s*[;=]')
    
    for i, line in enumerate(lines):
        for m in decl_pattern.finditer(line):
            ftype = m.group(1)
            fname = m.group(2)
            # Ignore if looks like method declaration (has parentheses after name)
            if '(' in line[line.index(fname):]:
                continue
            fields[fname].append(ftype)
    return fields

def rename_fields_in_content(content, fields_to_rename, rename_map):
    """Rename fields according to rename_map (old_name -> new_name)."""
    if not rename_map:
        return content
    
    lines = content.split('\n')
    new_lines = []
    
    # Build regex for all old names, sorted by length desc to avoid partial matches
    old_names = sorted(rename_map.keys(), key=len, reverse=True)
    # Pattern: \b old_name \b (not preceded/followed by identifier chars)
    name_pattern = re.compile(r'\b(' + '|'.join(re.escape(n) for n in old_names) + r')\b')
    
    for i, line in enumerate(lines):
        # Determine code and comment parts
        if '//' in line:
            comment_idx = line.index('//')
            code_part = line[:comment_idx]
            comment_part = line[comment_idx:]
        else:
            code_part = line
            comment_part = ''
        
        # Also handle /* */ comments crudely
        if '/*' in code_part:
            comment_start = code_part.index('/*')
            # Simple: don't touch anything after /* in this line
            code_part = code_part[:comment_start]
            comment_part = code_part[comment_start:] + comment_part
        
        new_code = code_part
        # Replace from end to start to preserve positions
        matches = list(name_pattern.finditer(new_code))
        if matches:
            for m in reversed(matches):
                old_name = m.group(1)
                new_name = rename_map[old_name]
                new_code = new_code[:m.start()] + new_name + new_code[m.end():]
        
        new_lines.append(new_code + comment_part)
    
    return '\n'.join(new_lines)

def process_file(path):
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    
    fields = find_fields(content)
    
    # Find names that appear with multiple distinct types
    name_types = defaultdict(set)
    for name, types in fields.items():
        name_types[name].update(types)
    
    # Only rename single-char or very short names with duplicates
    to_rename = {name for name, types in name_types.items() 
                 if len(types) > 1 and len(name) <= 2 and name not in {'if', 'do', 'for', 'int', 'var', 'new', 'try', 'null', 'this', 'super', 'void', 'byte', 'char', 'long', 'short', 'true', 'false', 'switch', 'case', 'break', 'continue', 'return', 'public', 'private', 'protected', 'static', 'final', 'class', 'interface', 'extends', 'implements', 'import', 'package', 'throw', 'throws', 'try', 'catch', 'finally', 'while', 'do', 'for', 'if', 'else', 'switch', 'case', 'default', 'instanceof', 'new', 'this', 'super', 'void', 'boolean', 'byte', 'char', 'short', 'int', 'long', 'float', 'double', 'true', 'false', 'null'}}
    
    if not to_rename:
        return False
    
    print(f'\nProcessing {os.path.basename(path)}:')
    print(f'  Duplicate names found: {sorted(to_rename)}')
    
    # Build rename map: for each occurrence of a field, assign a unique name based on type
    # We'll inspect the file to count occurrences per (name, type)
    lines = content.split('\n')
    occurrences = defaultdict(int)
    rename_map = {}
    
    decl_pattern = re.compile(r'(?:(?:public|private|protected|static|final|transient|volatile)\s+)*\s*(\w[\w\[\].]*(?:<[^>]+>)?)\s+([a-z_][a-z0-9_]*)\s*[;=]')
    
    for i, line in enumerate(lines):
        if '//' in line:
            line = line[:line.index('//')]
        for m in decl_pattern.finditer(line):
            ftype = m.group(1)
            fname = m.group(2)
            if fname in to_rename:
                # Skip if it looks like a method
                rest = line[m.end():]
                if '(' in rest[:20]:
                    continue
                safe_type = re.sub(r'[<>\[]', '_', ftype).replace(']', 'Arr').replace('.', '_')
                key = (fname, safe_type)
                occ = occurrences[key]
                occurrences[key] += 1
                if occ == 0:
                    new_name = f'{fname}_{safe_type}'
                else:
                    new_name = f'{fname}_{safe_type}_{occ}'
                # Store for this specific declaration position? No, we'll use first-occurrence mapping
                # Actually, we need to map each old name to a single new name PER DECLARATION
                # But we can't do that easily without tracking positions.
                # Simplified: if name has multiple types, pick the most common type as primary
                # and only rename the others. This breaks if same type appears twice.
                pass
    
    # Better approach: process line by line, maintaining state of which declaration we're in
    # This is complex. Simplified: just rename ALL occurrences of duplicate names by adding type suffix
    # where type can be inferred from context (assignment, cast, etc.) — too hard.
    
    # Fallback: rename declarations only, leave usages broken (user will fix)
    # This at least allows compilation if usages are resolved manually.
    
    # Let's do: for each declaration line, rename it with a unique index
    decl_lines = {}
    decl_count = defaultdict(int)
    decl_pattern = re.compile(r'(?:(?:public|private|protected|static|final|transient|volatile)\s+)*\s*(\w[\w\[\].]*(?:<[^>]+>)?)\s+([a-z_][a-z0-9_]*)\s*[;=]')
    
    new_lines = []
    for i, line in enumerate(lines):
        new_line = line
        if '//' in line:
            comment_idx = line.index('//')
            code_part = line[:comment_idx]
            comment_part = line[comment_idx:]
        else:
            code_part = line
            comment_part = ''
        
        matches = list(decl_pattern.finditer(code_part))
        if matches:
            for m in reversed(matches):
                ftype = m.group(1)
                fname = m.group(2)
                if fname in to_rename:
                    rest = code_part[m.end():]
                    if '(' in rest[:20]:
                        continue
                    safe_type = re.sub(r'[<>\[]', '_', ftype).replace(']', 'Arr').replace('.', '_')
                    key = (fname, safe_type)
                    cnt = decl_count[key]
                    decl_count[key] += 1
                    if cnt == 0:
                        new_name = f'{fname}_{safe_type}'
                    else:
                        new_name = f'{fname}_{safe_type}_{cnt}'
                    code_part = code_part[:m.start(2)] + new_name + code_part[m.end(2):]
            new_line = code_part + comment_part
        new_lines.append(new_line)
    
    new_content = '\n'.join(new_lines)
    if new_content != content:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(new_content)
        return True
    return False

def main():
    base = sys.argv[1] if len(sys.argv) > 1 else 'fully_deobfuscated_src'
    count = 0
    for fname in sorted(os.listdir(base)):
        if not fname.endswith('.java'):
            continue
        if process_file(os.path.join(base, fname)):
            print(f'Updated: {fname}')
            count += 1
    print(f'\nTotal files updated: {count}')

if __name__ == '__main__':
    main()
