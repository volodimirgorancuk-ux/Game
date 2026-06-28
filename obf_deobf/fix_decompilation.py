#!/usr/bin/env python3
"""
Conservative fix for common CFR decompilation artifacts.
Only fixes clearly broken syntax, does not remove random lines.
"""
import re, os, sys

# 1. Remove lines containing ** GOTO (CFR artifact)
GOTO_PATTERN = re.compile(r'^\s*.*\*\*\s*GOTO\s+\w+.*$', re.MULTILINE)

# 2. Remove lbl-XXX style labels (CFR artifact)
LBL_DASH_PATTERN = re.compile(r'^\s*lbl-\d+:\s*$', re.MULTILINE)

# 2b. Remove lbl-XXX that appears after else on same line
LBL_DASH_INLINE = re.compile(r'\}\s*else\s*lbl-\d+:\s*', re.MULTILINE)

# 3. Remove standalone lblXXX: labels
LBL_LINE = re.compile(r'^\s*lbl\d+:\s*$', re.MULTILINE)

# 4. Remove // N sources comments
SOURCES_COMMENT = re.compile(r'//\s*\d+\s+sources?\s*$', re.MULTILINE)

# 5. Fix broken catch blocks created by comment removal
# Pattern: } catch (...) { } catch (...) { } -> keep only first valid catch
# Or orphaned catch blocks
ORPHAN_CATCH = re.compile(r'\}\s*catch\s*\(([^)]+)\)\s*\{\s*\}')

# 6. Remove cfr_ignored_* variables
CFR_IGNORED_ASSIGN = re.compile(r'^\s*(?:(?:String|int|boolean|byte|short|long|float|double)\s+cfr_ignored_\d+\s*=\s*[^;]+;)\s*$', re.MULTILINE)

def fix_file(path):
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    original = content
    
    # Apply fixes in order
    content = GOTO_PATTERN.sub('', content)
    content = LBL_DASH_PATTERN.sub('', content)
    content = LBL_DASH_INLINE.sub('} else {\n', content)
    content = LBL_LINE.sub('', content)
    content = SOURCES_COMMENT.sub('', content)
    content = CFR_IGNORED_ASSIGN.sub('', content)
    
    # Remove orphaned catch blocks (catch without try)
    # These appear as } catch (...) { } at start of line
    content = re.sub(r'^\s*\}?\s*catch\s*\([^)]+\)\s*\{\s*\}', '', content, flags=re.MULTILINE)
    
    # Clean up multiple consecutive blank lines
    content = re.sub(r'\n{3,}', '\n\n', content)
    
    if content != original:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
    return False

def main():
    base = sys.argv[1] if len(sys.argv) > 1 else 'decompiled_cfr_renamed'
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
