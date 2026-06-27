import re, json

def find_methods(content):
    methods = []
    sig_pattern = re.compile(
        r'^\s*(?:public|private|protected|static|final|abstract|\s)*\s*'
        r'(?:static\s+)?(?:final\s+)?'
        r'(\w+)\s+(\w+)\s*\(([^)]*)\)\s*\{',
        re.MULTILINE
    )
    matches = list(sig_pattern.finditer(content))
    for m in matches:
        return_type = m.group(1)
        name = m.group(2)
        params = m.group(3)
        start = m.end()
        depth = 1
        pos = start
        while depth > 0 and pos < len(content):
            if content[pos] == '{': depth += 1
            elif content[pos] == '}': depth -= 1
            pos += 1
        body = content[start:pos-1]
        methods.append({
            'name': name, 'return_type': return_type, 'params': params,
            'body': body,
            'strings': list(set(re.findall(r'"([^"\\]*(?:\\.[^"\\]*)*)"', body))),
            'calls': extract_calls(body)
        })
    return methods

def extract_calls(text):
    calls = set()
    for m in re.finditer(r'\.([A-Za-z_]\w*)\s*\(', text):
        calls.add(m.group(1))
    for m in re.finditer(r'\b([A-Z][A-Za-z0-9_]*)\.([A-Za-z_]\w*)\s*\(', text):
        calls.add(f"{m.group(1)}.{m.group(2)}")
    return list(calls)

def infer_name(method, caller_context, callee_context):
    name = method['name']
    ret = method['return_type']
    params = method['params']
    body = method['body'].lower()
    strings = [s.lower() for s in method['strings']]
    callers = caller_context.get(name, [])
    callees = callee_context.get(name, [])
    
    # KNOWN STATE MACHINE HANDLERS
    STATE_HANDLERS = {
        'v': 'updateMainMenu', 'x': 'updateOptions', 'F': 'updateLoading',
        'V': 'updateCutscene', 'z': 'updateInGame', 'bK': 'updatePaused',
        'B': 'updateGameOver', 'cd': 'updateTutorial', 'cj': 'updateSubState',
        'dH': 'updateContinue', 'dA': 'updateVictory', 'eh': 'updateCredits',
        'fE': 'updateLevelSelect', 'aO': 'updateCharacterSelect',
        'E': 'updatePausedSubmenu', 'G': 'updateConfirmDialog'
    }
    RENDER_HANDLERS = {
        'b': 'drawMainMenu', 'c': 'drawOptions', 'h': 'drawLoading',
        'l': 'drawCutscene', 'j': 'drawInGame', 'e': 'drawGameOver',
        'P': 'drawTutorial', 'R': 'drawSubState', 'ar': 'drawContinue',
        'ao': 'drawVictory', 'ax': 'drawCredits', 'n': 'drawLevelSelect',
        'q': 'drawCharacterSelect', 'g': 'drawPausedSubmenu', 'i': 'drawConfirmDialog'
    }
    
    if name in STATE_HANDLERS:
        return STATE_HANDLERS[name]
    if name in RENDER_HANDLERS:
        return RENDER_HANDLERS[name]
    
    # If this method is called from specific state handlers, inherit their domain
    caller_bases = set()
    for caller in callers:
        base = caller.split('::')[0] if '::' in caller else caller
        caller_bases.add(base)
    
    # Determine domain from callers
    domain = None
    for caller in callers:
        cl = caller.lower()
        if 'updatemaingame' in cl or 'updatezombie' in cl or 'updateingame' in cl:
            domain = 'gameplay'
            break
        if 'updatecharacter' in cl:
            domain = 'character'
            break
        if 'updatelevel' in cl or 'loadinglevel' in cl:
            domain = 'level'
            break
        if 'drawhud' in cl or 'drawui' in cl:
            domain = 'hud'
            break
        if 'audio' in cl:
            domain = 'audio'
            break
        if 'enemy' in cl or 'npc' in cl:
            domain = 'enemy'
            break
        if 'particle' in cl:
            domain = 'particle'
            break
        if 'sensor' in cl:
            domain = 'sensor'
            break
        if 'network' in cl or 'url' in cl:
            domain = 'network'
            break
    
    # If domain known, generate specific name
    if domain:
        if 'graphics' in params.lower():
            if ret == 'void':
                return f'{domain}Render'
            return f'{domain}Calc'
        if ret == 'void':
            return f'{domain}Update'
        if ret == 'boolean':
            return f'is{domain.capitalize()}'
        if ret == 'int':
            return f'get{domain.capitalize()}'
    
    # Domain from strings
    string_domain = None
    for s in strings:
        sl = s.lower()
        if 'enemy' in sl or 'zombie' in sl or 'npc' in sl:
            string_domain = 'enemy'
        elif 'audio' in sl or 'sound' in sl or 'music' in sl:
            string_domain = 'audio'
        elif 'particle' in sl:
            string_domain = 'particle'
        elif 'weapon' in sl or 'bullet' in sl:
            string_domain = 'weapon'
        elif 'level' in sl or 'map' in sl or 'tile' in sl:
            string_domain = 'level'
        elif 'hud' in sl or 'ui' in sl or 'menu' in sl:
            string_domain = 'hud'
        elif 'input' in sl or 'key' in sl or 'touch' in sl:
            string_domain = 'input'
        elif 'network' in sl or 'url' in sl:
            string_domain = 'network'
    
    if string_domain:
        if ret == 'void':
            return f'{string_domain}Method'
        if ret == 'boolean':
            return f'is{string_domain.capitalize()}'
        if ret == 'int':
            return f'get{string_domain.capitalize()}'
        return f'{string_domain}Calc'
    
    # Fallbacks based on signature uniqueness
    if ret == 'void' and params == '':
        return f'update_{name}'
    if ret == 'void' and params == 'boolean bl':
        return f'setFlag_{name}'
    if ret == 'void' and 'int' in params and 'graphics' in params.lower():
        return f'draw_{name}'
    if ret == 'boolean' and 'int' in params:
        return f'check_{name}'
    if ret == 'int' and 'int' in params:
        return f'calc_{name}'
    if ret == 'void':
        return f'update_{name}'
    if ret == 'boolean':
        return f'check_{name}'
    if ret == 'int':
        return f'calc_{name}'
    
    return name

# Load all decompiled files
base = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/decompiled/'
files = {
    'GameCanvas': base + 'GameCanvas.java',
    'GameController': base + 'GameController.java',
    'AudioManager': base + 'AudioManager.java',
    'GraphicsEngine': base + 'GraphicsEngine.java',
    'SensorHandler': base + 'SensorHandler.java',
}

all_methods = {}
callee_map = {}

print("Analyzing files...")
for cls, path in files.items():
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    methods = find_methods(content)
    all_methods[cls] = {m['name']: m for m in methods}
    for m in methods:
        key = f"{cls}.{m['name']}"
        callee_map[key] = m['calls']

# Reverse map: who calls whom
caller_map = {}
for key, callees in callee_map.items():
    for callee in callees:
        if callee not in caller_map:
            caller_map[callee] = []
        caller_map[callee].append(key)

mapping = {}
for cls, methods in all_methods.items():
    for name, m in methods.items():
        key = f"{cls}.{name}"
        callers = caller_map.get(key, [])
        callees = callee_map.get(key, [])
        sug = infer_name(m, caller_map, callee_map)
        mapping[key] = {
            'obfuscated': name,
            'suggested': sug,
            'params': m['params'][:50],
            'return': m['return_type'],
            'callers': callers[:5],
            'callees': callees[:5],
            'strings': m['strings'][:3]
        }

with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/smart_mapping.json', 'w') as f:
    json.dump(mapping, f, indent=2)

total = len(mapping)
unique = len(set(v['suggested'] for v in mapping.values()))
renamed = sum(1 for v in mapping.values() if v['suggested'] != v['obfuscated'])
print(f"\nTotal methods: {total}")
print(f"Unique suggested names: {unique}")
print(f"Methods renamed: {renamed}")

cats = {}
for v in mapping.values():
    sug = v['suggested']
    cat = sug.split('_')[0] if '_' in sug else sug
    if cat not in ('the', 'a', 'an', 'is', 'get', 'set'):
        cats[cat] = cats.get(cat, 0) + 1

print("\nTop categories:")
for c, n in sorted(cats.items(), key=lambda x: x[1], reverse=True)[:20]:
    print(f"  {c}: {n}")

print("\nSample mappings:")
for key, val in list(mapping.items())[:15]:
    print(f"  {key} ({val['return']} {val['params'][:30]})")
    print(f"    -> {val['suggested']}")
    if val['callers']:
        print(f"    Called by: {val['callers'][:2]}")
    if val['strings']:
        print(f"    Strings: {val['strings'][:2]}")
