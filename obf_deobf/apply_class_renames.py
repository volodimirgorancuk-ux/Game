#!/usr/bin/env python3
"""
Apply class renames to decompiled sources.
"""
import re, os, sys

# Class rename mapping
CLASS_RENAMES = {
    'a': 'GraphicsEngine',
    'b': 'GameController', 
    'c': 'GameData',
    'd': 'GameConfig',
    'e': 'SensorHandler',
    'f': 'AudioManager',
    'g': 'GameCanvas',
}

# Method renames from comprehensive_mapping.json
METHOD_RENAMES = {
    'GameCanvas.run': 'gameLoop',
    'GameCanvas.t': 'startGame',
    'GameCanvas.u': 'stopGame',
    'GameCanvas.s': 'setPaused',
    'GameCanvas.d': 'setState',
    'GameCanvas.f': 'setRunning',
    'GameCanvas.l': 'hasFlag',
    'GameCanvas.a(Graphics)': 'clearScreen',
    'GameCanvas.e(int,int)': 'createOffscreenBuffer',
    'GameCanvas.j(Graphics)': 'drawBuffer',
    'GameCanvas.H()': 'destroyBuffer',
    'GameCanvas.I()': 'resetClipBuffer',
    'GameCanvas.v()': 'updateMainMenu',
    'GameCanvas.x()': 'updateOptions',
    'GameCanvas.F()': 'updateLoading',
    'GameCanvas.V()': 'updateCutscene',
    'GameCanvas.z()': 'updateInGame',
}

def rename_classes_in_file(path):
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    
    original = content
    
    # Build class-specific method name patterns
    for old, new in CLASS_RENAMES.items():
        # class declaration (handles: class Foo {)
        content = re.sub(r'\bclass\s+' + re.escape(old) + r'\s*\{', f'class {new} {{', content)
        # extends/implements
        content = re.sub(r'\bextends\s+' + re.escape(old) + r'\b', f'extends {new}', content)
        content = re.sub(r'\bimplements\s+' + re.escape(old) + r'\b', f'implements {new}', content)
        # new expressions
        content = re.sub(r'\bnew\s+' + re.escape(old) + r'\b', f'new {new}', content)
        # type declarations: Type name; or Type name = ...
        content = re.sub(r'\b' + re.escape(old) + r'\s+([a-zA-Z_]\w*)\s*[;=]', f'{new} $1', content)
        # array types: Type[]
        content = re.sub(r'\b' + re.escape(old) + r'\[\]', f'{new}[]', content)
        # method calls on variable: obj.old(
        content = re.sub(r'\.' + re.escape(old) + r'\(', f'.{new}(', content)
        # cast expressions: (old) expr
        content = re.sub(r'\(\s*' + re.escape(old) + r'\s*\)', f'({new})', content)
        # instanceof
        content = re.sub(r'\binstanceof\s+' + re.escape(old) + r'\b', f'instanceof {new}', content)
        # Constructor calls: old(...) - but be careful not to match `old.method()` etc.
        # Match when old is followed by ( and args)
        content = re.sub(r'\b' + re.escape(old) + r'\(', f'{new}(', content)
    
    # Rename simple field names like a(), b() to their semantic names if we have them
    # This is more complex because we need to handle overloaded methods
    
    if content != original:
        with open(path, 'w', encoding='utf-8') as f:
            f.write(content)
        return True
    return False

def main():
    base = sys.argv[1] if len(sys.argv) > 1 else 'decompiled_rename'
    count = 0
    for fname in sorted(os.listdir(base)):
        if not fname.endswith('.java'):
            continue
        path = os.path.join(base, fname)
        if rename_classes_in_file(path):
            print('Renamed classes in:', fname)
            count += 1
    print(f'Total: {count} files updated')

if __name__ == '__main__':
    main()
