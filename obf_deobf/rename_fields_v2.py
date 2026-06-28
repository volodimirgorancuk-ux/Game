#!/usr/bin/env python3
"""
Rename duplicate class-level fields in Java sources.
For each name with multiple types, pick the most common type as canonical.
WARNING: This is a best-effort rename; semantic correctness depends on the decompiler output.
"""
import re, os, sys
from collections import defaultdict

KEYWORDS = {
    'abstract', 'assert', 'boolean', 'break', 'byte', 'case', 'catch', 'char', 'class',
    'const', 'continue', 'default', 'do', 'double', 'else', 'enum', 'extends', 'final',
    'finally', 'float', 'for', 'goto', 'if', 'implements', 'import', 'instanceof',
    'int', 'interface', 'long', 'native', 'new', 'package', 'private', 'protected',
    'public', 'return', 'short', 'static', 'strictfp', 'super', 'switch', 'synchronized',
    'this', 'throw', 'throws', 'transient', 'try', 'void', 'volatile', 'while', 'true',
    'false', 'null', 'var', 'yield', 'record', 'void',
}

def find_class_sections(content):
    """Split content into top-level type declarations and their bodies."""
    # Simple approach: find all top-level class/interface/enum declarations
    # Return list of (header, body) for each
    results = []
    i = 0
    while i < len(content):
        # Skip to next class declaration
        m = re.search(r'\b(class|interface|enum)\s+(\w+)', content[i:])
        if not m:
            break
        class_start = i + m.start()
        # Find the opening brace of this class
        brace_start = content.index('{', class_start)
        # Find matching closing brace
        depth = 0
        j = brace_start
        while j < len(content):
            if content[j] == '{':
                depth += 1
            elif content[j] == '}':
                depth -= 1
                if depth == 0:
                    break
            j += 1
        header = content[:brace_start+1]
        body = content[brace_start+1:j]
        results.append((header, body))
        content = content[j:]  # Continue with rest
        i = 0
    return results

def find_fields_in_body(body):
    """
    Find class-level field declarations.
    Returns list of (declaration_line_index, original_line, new_name_or_None).
    We only consider lines that are direct children of the class (not inside methods).
    Crude heuristic: if line contains only field-like syntax and no control flow.
    """
    lines = body.split('\n')
    fields = []
    field_re = re.compile(r'^\s*((?:(?:public|private|protected|static|final|transient|volatile)\s+)*)\s*(\w[\w\[\]<>\?\.\s]*)\s+([a-z_][a-z0-9_]*)\s*[;=]')
    
    inside_method = False
    brace_depth = 0
    
    for line_idx, line in enumerate(lines):
        stripped = line.strip()
        if not stripped or stripped.startswith('//') or stripped.startswith('/*') or stripped.startswith('*'):
            continue
        
        # Track if inside method (crude)
        if re.search(r'\b(public|private|protected)\b', line) and '(' in line and '{' in line:
            # Could be method declaration with body on same line
            pass
        elif re.search(r'\b(public|private|protected)\s+\w+.*\(', line) and not stripped.endswith(';'):
            inside_method = True
        
        if inside_method:
            brace_depth += line.count('{') - line.count('}')
            if brace_depth <= 0 and line.count('{') == 0:
                inside_method = False
            continue
        
        m = field_re.match(line)
        if m:
            ftype = m.group(2).strip()
            fname = m.group(3)
            if fname not in KEYWORDS and len(fname) <= 2:
                fields.append((line_idx, line, fname, ftype))
    
    return fields

def process_file(path):
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    
    original = content
    sections = find_class_sections(content)
    
    for header, body in sections:
        fields = find_fields_in_body(body)
        if not fields:
            continue
        
        # Group by name
        name_entries = defaultdict(list)
        for line_idx, line, fname, ftype in fields:
            name_entries[fname].append((line_idx, line, ftype))
        
        dups = {k: v for k, v in name_entries.items() if len(v) > 1}
        if not dups:
            continue
        
        # Build rename map: old_name -> new_name (canonical)
        rename_map = {}
        for fname, entries in dups.items():
            # Count types
            type_counts = defaultdict(int)
            for _, _, ftype in entries:
                safe_type = re.sub(r'[\[\]<>]', '', ftype).replace('.', '_').replace('?', '')
                if not safe_type:
                    safe_type = 'Unknown'
                type_counts[safe_type] += 1
            best_type = max(type_counts.items(), key=lambda x: x[1])[0]
            rename_map[fname] = f'{fname}_{best_type}'
        
        # Replace in body
        lines = body.split('\n')
        new_lines = []
        for line in lines:
            # Skip comments
            if '//' in line:
                code, comment = line.split('//', 1)
                comment = '//' + comment
            else:
                code = line
                comment = ''
            
            for old, new in rename_map.items():
                code = re.sub(r'\b' + re.escape(old) + r'\b', new, code)
            new_lines.append(code + comment)
        new_body = '\n'.join(new_lines)
        
        content = content.replace(body, new_body)
    
    if content != original:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
    return False

def main():
    base = sys.argv[1] if len(sys.argv) > 1 else 'fully_deobfuscated_src'
    count = 0
    for fname in sorted(os.listdir(base)):
        if not fname.endswith('.java'):
            continue
        if process_file(os.path.join(base, fname)):
            print('Renamed:', fname)
            count += 1
    print(f'Total: {count} files updated')

if __name__ == '__main__':
    main()
