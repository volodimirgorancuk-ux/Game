import re, json, os

def find_methods(content):
    methods = []
    sig_pattern = re.compile(
        r'^\s*(public|private|protected|static|final|abstract|\s)*\s*'
        r'(?:static\s+)?(?:final\s+)?'
        r'(\w+)\s+(\w+)\s*\(([^)]*)\)\s*\{',
        re.MULTILINE
    )
    matches = list(sig_pattern.finditer(content))
    for i, m in enumerate(matches):
        name = m.group(3)
        return_type = m.group(2)
        params = m.group(4)
        start = m.end()
        depth = 1
        pos = start
        while depth > 0 and pos < len(content):
            if content[pos] == '{':
                depth += 1
            elif content[pos] == '}':
                depth -= 1
            pos += 1
        body = content[start:pos-1]
        methods.append({
            'name': name,
            'return_type': return_type,
            'params': params,
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

def categorize_method(method):
    name = method['name']
    return_type = method['return_type']
    params = method['params'].lower()
    body = method['body'].lower()
    strings = [s.lower() for s in method['strings']]
    
    cats = []
    
    # String-based
    for s in strings:
        if 'warning' in s: cats.append('warning')
        if any(w in s for w in ['enemy', 'zombie', 'npc', 'spawn', 'health', 'hp', 'damage']):
            cats.append('enemy')
        if any(w in s for w in ['weapon', 'bullet', 'shot', 'gun', 'katana', 'ammo']):
            cats.append('weapon')
        if 'particle' in s: cats.append('particle')
        if any(w in s for w in ['audio', 'player', 'volume', 'sound', 'music', 'midi', 'wav', 'amr']):
            cats.append('audio')
        if any(w in s for w in ['url', 'http', 'web', 'browser']):
            cats.append('network')
        if any(w in s for w in ['rms', 'recordstore']):
            cats.append('persistence')
        if any(w in s for w in ['sensor', 'accelerometer']):
            cats.append('sensor')
        if any(w in s for w in ['touch', 'key', 'input', 'click', 'pressed']):
            cats.append('input')
        if any(w in s for w in ['menu', 'hud', 'ui', 'score']):
            cats.append('ui')
        if any(w in s for w in ['level', 'map', 'tile', 'sprite']):
            cats.append('level')
        if any(w in s for w in ['draw', 'image', 'fillrect', 'drawstring', 'drawregion']):
            cats.append('render')
        if any(w in s for w in ['font', 'text', 'string']):
            cats.append('text')
    
    # Param-based
    if 'graphics' in params: cats.append('graphics')
    if 'audiomanager' in params: cats.append('audio')
    if 'graphicsengine' in params: cats.append('gfx')
    if 'gamedata' in params: cats.append('entity')
    if 'byte[]' in params: cats.append('bytes')
    
    # Body-based
    if any(w in body for w in ['drawimage', 'drawregion', 'fillrect', 'drawstring']):
        cats.append('render')
    if 'createplayer' in body:
        cats.append('audio')
    if 'recordstore' in body:
        cats.append('persistence')
    if 'platformrequest' in body:
        cats.append('network')
    if 'sensormanager' in body:
        cats.append('sensor')
    
    return list(set(cats))

def generate_name(method, cats, overload_index):
    name = method['name']
    ret = method['return_type']
    params = method['params']
    param_lower = params.lower()
    
    # Special cases
    if name == 'run': return 'gameLoop'
    if 'commandaction' in name.lower(): return 'onCommand'
    if 'dataReceived' in name: return 'onSensorData'
    
    # Base category
    primary = cats[0] if cats else 'unknown'
    
    # Prefix by return type
    prefix = ''
    if ret == 'boolean': prefix = 'is'
    elif ret == 'int': prefix = 'get'
    elif ret == 'void': prefix = ''
    
    # Generate based on primary category and params
    if primary == 'render' or primary == 'graphics':
        if 'graphics' in param_lower:
            if 'weapon' in cats or 'hud' in cats or 'ui' in cats:
                return f'drawHUD_{overload_index}'
            if 'text' in cats or 'font' in cats:
                return f'drawText_{overload_index}'
            if 'background' in cats or 'level' in cats:
                return f'drawBackground_{overload_index}'
            if 'sprite' in cats or 'entity' in cats:
                return f'drawSprite_{overload_index}'
            return f'render_{overload_index}'
    
    if primary == 'audio':
        if 'audiomanager[]' in param_lower or 'audioManager[]' in param_lower:
            return 'initAudioChannels'
        if 'play' in name.lower() or ret == 'void':
            return f'playSound_{overload_index}'
        if 'stop' in name.lower():
            return f'stopSound_{overload_index}'
        if ret == 'int':
            return f'getAudio_{overload_index}'
        return f'audio_{overload_index}'
    
    if primary == 'enemy':
        if 'health' in cats or 'hp' in cats:
            return f'getEnemyHealth_{overload_index}' if ret == 'int' else f'updateEnemyHealth_{overload_index}'
        if 'spawn' in cats:
            return f'spawnEnemy_{overload_index}'
        return f'updateEnemy_{overload_index}'
    
    if primary == 'particle':
        if 'release' in name.lower():
            return 'releaseParticle'
        if 'position' in cats or 'set' in cats:
            return f'setParticlePos_{overload_index}'
        return f'updateParticles_{overload_index}'
    
    if primary == 'input':
        if 'key' in param_lower:
            return f'handleKey_{overload_index}'
        if 'touch' in cats or 'pointer' in param_lower:
            return f'handleTouch_{overload_index}'
        return f'handleInput_{overload_index}'
    
    if primary == 'network':
        if 'platformrequest' in param_lower:
            return 'openURL'
        return f'resolveURL_{overload_index}'
    
    if primary == 'persistence':
        if 'save' in name.lower():
            return f'saveData_{overload_index}'
        if 'load' in name.lower():
            return f'loadData_{overload_index}'
        return f'persist_{overload_index}'
    
    if primary == 'sensor':
        return f'sensor_{overload_index}'
    
    if primary == 'level':
        if 'load' in name.lower() or 'init' in name.lower():
            return f'loadLevel_{overload_index}'
        return f'level_{overload_index}'
    
    if primary == 'text':
        return f'text_{overload_index}'
    
    if primary == 'ui':
        return f'ui_{overload_index}'
    
    if primary == 'warning':
        return f'log_{overload_index}'
    
    # Fallback by return type
    if ret == 'boolean':
        return f'check_{overload_index}'
    if ret == 'int':
        return f'calc_{overload_index}'
    if ret == 'void':
        return f'update_{overload_index}'
    
    return f'method_{overload_index}'

base = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/decompiled/'
files = {
    'GameCanvas': base + 'GameCanvas.java',
    'GameController': base + 'GameController.java',
    'AudioManager': base + 'AudioManager.java',
    'GraphicsEngine': base + 'GraphicsEngine.java',
    'SensorHandler': base + 'SensorHandler.java',
}

# Build overload counters per (class, signature_pattern)
overload_counters = {}
method_mappings = {}

for cls, path in files.items():
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    
    methods = find_methods(content)
    
    for m in methods:
        cats = categorize_method(m)
        
        # Create signature key for overload grouping
        sig_key = f"{cls}:{m['return_type']}:{m['params'][:30]}"
        if sig_key not in overload_counters:
            overload_counters[sig_key] = 0
        else:
            overload_counters[sig_key] += 1
        
        overload_index = overload_counters[sig_key]
        new_name = generate_name(m, cats, overload_index)
        
        key = f"{cls}.{m['name']}({m['params'][:40]})"
        method_mappings[key] = {
            'obfuscated': m['name'],
            'suggested': new_name,
            'categories': cats,
            'overload_index': overload_index,
            'return_type': m['return_type'],
            'params': m['params'][:40]
        }

# Write mapping
with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/auto_method_mapping.json', 'w') as f:
    json.dump(method_mappings, f, indent=2)

# Write ProGuard-style mapping
with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/auto_mapping.txt', 'w') as f:
    f.write("# Auto-generated method mapping\n")
    f.write("# Format: deobfuscated_name -> obfuscated_name\n\n")
    
    current_class = None
    for key, val in method_mappings.items():
        cls = key.split('.')[0]
        if cls != current_class:
            current_class = cls
            f.write(f"\n# {cls}\n")
        
        obf = val['obfuscated']
        sig = val['params']
        suggested = val['suggested']
        
        # Write in ProGuard format
        if suggested != obf:
            f.write(f"    {suggested}({sig}) -> {obf}\n")

# Stats
total = len(method_mappings)
with_names = sum(1 for v in method_mappings.values() if v['suggested'] != v['obfuscated'])
print(f"Total methods: {total}")
print(f"With suggested names: {with_names}")
print(f"Same as obfuscated: {total - with_names}")

# Category distribution
cat_counts = {}
for v in method_mappings.values():
    for c in v['categories']:
        cat_counts[c] = cat_counts.get(c, 0) + 1

print("\nCategory distribution:")
for cat, count in sorted(cat_counts.items(), key=lambda x: x[1], reverse=True)[:15]:
    print(f"  {cat}: {count}")

print(f"\nSaved auto_method_mapping.json and auto_mapping.txt")
