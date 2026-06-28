#!/usr/bin/env python3
"""
Conservative fix for common CFR decompilation artifacts.
Only fixes clearly broken syntax, does not remove random lines.
"""
import re, os, sys

# 1. Fix broken catch blocks: }/* catch (Exception exception) */ {} -> } catch (Exception exception) {}
CATCH_PATTERN = re.compile(r'\}\s*/\*\s*catch\s*\(([^)]+)\)\s*\*/\s*\{\s*\}')

# 2. Fix var_do/varDo -> stateCounter
VAR_DO_PATTERN = re.compile(r'\bvar_do\b', re.IGNORECASE)

# 3. Remove cfr_ignored_* variables (standalone assignments)
CFR_IGNORED_ASSIGN = re.compile(r'^\s*(?:(?:String|int|boolean|byte|short|long|float|double)\s+cfr_ignored_\d+\s*=\s*[^;]+;)\s*$', re.MULTILINE)

# 4. Remove lbl markers that are alone on a line (not inside methods as targets)
LBL_LINE = re.compile(r'^\s*lbl\d+:\s*$', re.MULTILINE)

# 5. Remove // N sources comments
SOURCES_COMMENT = re.compile(r'//\s*\d+\s+sources?\s*$', re.MULTILINE)

def fix_file(path):
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    original = content
    
    content = CATCH_PATTERN.sub(lambda m: '} catch (' + m.group(1) + ') {}', content)
    content = VAR_DO_PATTERN.sub('stateCounter', content)
    content = CFR_IGNORED_ASSIGN.sub('', content)
    content = LBL_LINE.sub('', content)
    content = SOURCES_COMMENT.sub('', content)
    
    if content != original:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
    return False

def main():
    base = sys.argv[1] if len(sys.argv) > 1 else 'fully_deobfuscated_src'
    if not os.path.isdir(base):
        print('Directory not found:', base)
        return
    count = 0
    for fname in sorted(os.listdir(base)):
        if not fname.endswith('.java'):
            continue
        path = os.path.join(base, fname)
        if fix_file(path):
            print('Fixed:', fname)
            count += 1
    print(f'Total: {count} files updated')

if __name__ == '__main__':
    main()
