#!/usr/bin/env python3
"""
Analyze duplicate field names in Java sources.
Outputs a report of all duplicate names by type.
Does NOT modify files.
"""
import re, os, sys
from collections import defaultdict

SKIP = {'if', 'do', 'for', 'int', 'var', 'new', 'try', 'null', 'this', 'super', 'void', 'byte', 'char', 'long', 'short', 'true', 'false', 'switch', 'case', 'break', 'continue', 'return', 'public', 'private', 'protected', 'static', 'final', 'class', 'interface', 'extends', 'implements', 'import', 'package', 'throw', 'throws', 'catch', 'finally', 'while', 'else', 'default', 'instanceof'}

def find_fields(path):
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    # Remove strings and comments
    content = re.sub(r'"([^"\\]|\\.)*"', '""', content)
    content = re.sub(r'//.*$', '', content, flags=re.MULTILINE)
    content = re.sub(r'/\*.*?\*/', '', content, flags=re.DOTALL)
    
    lines = content.split('\n')
    fields = defaultdict(list)
    # Pattern: [modifiers] Type name [= ...];
    decl = re.compile(r'(?:(?:public|private|protected|static|final|transient|volatile)\s+)*\s*(\w[\w\[\].]*(?:<[^>]+>)?)\s+([a-z_][a-z0-9_]*)\s*[;=]')
    for i, line in enumerate(lines, 1):
        for m in decl.finditer(line):
            ftype = m.group(1)
            fname = m.group(2)
            if fname in SKIP or len(fname) > 2:
                continue
            # Skip if it looks like method (has parens right after)
            rest = line[m.end():]
            if '(' in rest[:20]:
                continue
            fields[fname].append((i, ftype))
    return fields

def main():
    base = sys.argv[1] if len(sys.argv) > 1 else 'fully_deobfuscated_src'
    report = []
    for fname in sorted(os.listdir(base)):
        if not fname.endswith('.java'):
            continue
        path = os.path.join(base, fname)
        fields = find_fields(path)
        dups = {k: v for k, v in fields.items() if len(v) > 1}
        if dups:
            report.append((fname, dups))
    
    if not report:
        print('No duplicate short field names found.')
        return
    
    print(f'Found duplicate field names in {len(report)} files:\n')
    total = 0
    for fname, dups in report:
        print(f'=== {fname} ===')
        for name, occurrences in sorted(dups.items()):
            types = [t for _, t in occurrences]
            unique_types = list(dict.fromkeys(types))  # preserve order
            print(f'  {name}: {len(occurrences)} declarations, types: {unique_types}')
            total += len(occurrences) - 1
        print()
    print(f'Total extra duplicate declarations: {total}')

if __name__ == '__main__':
    main()
