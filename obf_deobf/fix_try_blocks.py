#!/usr/bin/env python3
"""
Fix broken try blocks in CFR output.
Pattern: try { ... } else { ... } -> remove try if no catch/finally
"""
import re, os, sys

def fix_try_blocks(content):
    """Remove try blocks that have no catch/finally."""
    lines = content.split('\n')
    new_lines = []
    i = 0
    while i < len(lines):
        line = lines[i]
        stripped = line.strip()
        
        # Detect try { at start of block
        if stripped == 'try {' or stripped.startswith('try {') or stripped == 'try{':
            # Find the matching } for this try
            depth = 0
            try_start = i
            try_end = -1
            has_catch = False
            has_finally = False
            
            for j in range(i, len(lines)):
                depth += lines[j].count('{') - lines[j].count('}')
                if depth == 0:
                    try_end = j
                    break
            
            if try_end == -1:
                # Unmatched try, keep as is
                new_lines.append(line)
                i += 1
                continue
            
            # Check if there's a catch or finally after the try block
            for j in range(try_end + 1, min(try_end + 20, len(lines))):
                l = lines[j].strip()
                if l.startswith('} catch') or l.startswith('catch'):
                    has_catch = True
                    break
                if l.startswith('} finally') or l.startswith('finally'):
                    has_finally = True
                    break
                if l.startswith('}') and j > try_end + 1:
                    # Found closing brace of containing block
                    break
            
            if not has_catch and not has_finally:
                # Remove the try { and its closing }, keep the body
                # Find the first non-empty line after try {
                body_start = i + 1
                while body_start < try_end and not lines[body_start].strip():
                    body_start += 1
                
                # Find the last non-empty line before }
                body_end = try_end - 1
                while body_end > i and not lines[body_end].strip():
                    body_end -= 1
                
                # Add the body without try wrapper
                for j in range(body_start, body_end + 1):
                    new_lines.append(lines[j])
                
                i = try_end + 1
                continue
        
        new_lines.append(line)
        i += 1
    
    return '\n'.join(new_lines)

def fix_file(path):
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    original = content
    content = fix_try_blocks(content)
    if content != original:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
    return False

def main():
    base = sys.argv[1] if len(sys.argv) > 1 else 'decompiled_rename'
    if not os.path.isdir(base):
        print('Directory not found:', base)
        return
    count = 0
    for fname in sorted(os.listdir(base)):
        if not fname.endswith('.java'):
            continue
        path = os.path.join(base, fname)
        if fix_file(path):
            print('Fixed try blocks:', fname)
            count += 1
    print(f'Total: {count} files updated')

if __name__ == '__main__':
    main()
