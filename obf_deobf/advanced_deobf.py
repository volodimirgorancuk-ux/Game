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

def categorize(method):
    name = method['name']
    ret = method['return_type']
    body = method['body'].lower()
    strings = [s.lower() for s in method['strings']]
    params = method['params'].lower()
    
    cats = []
    for s in strings:
        if 'warning' in s: cats.append('warning')
        if any(w in s for w in ['enemy', 'zombie', 'npc', 'spawn', 'health', 'hp', 'damage', 'dead']):
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
        if any(w in s for w in ['start', 'init', 'load', 'create', 'setup']):
            cats.append('init')
        if any(w in s for w in ['stop', 'destroy', 'end', 'exit', 'release', 'pause']):
            cats.append('cleanup')
    
    if 'audiomanager[]' in params: cats.append('audio-array')
    if 'graphicsengine' in params: cats.append('graphics')
    if 'gamedata' in params: cats.append('entity')
    
    body_lower = body.lower()
    if any(w in body_lower for w in ['drawimage', 'drawregion', 'fillrect', 'drawstring', 'graphics.']):
        cats.append('render')
    if 'createplayer' in body_lower or 'player.realize' in body_lower:
        cats.append('audio')
    if 'sensormanager' in body_lower:
        cats.append('sensor')
    if 'recordstore' in body_lower or 'openrecordstore' in body_lower:
        cats.append('persistence')
    if 'platformrequest' in body_lower:
        cats.append('network')
    
    return list(set(cats))

def suggest(method, cats, callers):
    name = method['name']
    ret = method['return_type']
    params = method['params'].lower()
    strings = method['strings']
    body = method['body'].lower()
    
    if name == 'run': return 'gameLoop'
    if 'commandaction' in name.lower(): return 'onCommand'
    if 'dataReceived' in name: return 'onSensorData'
    
    caller_names = [c.lower() for c in callers]
    
    # Audio
    if 'audio' in cats:
        if 'play' in name.lower() or 'start' in name.lower():
            if 'music' in strings: return 'playMusic'
            if 'midi' in strings or 'wav' in strings: return 'playSFX'
            return 'playSound'
        if 'stop' in name.lower():
            return 'stopSound'
        if 'volume' in strings or 'volumecontrol' in strings:
            return 'setVolume'
        if 'loop' in body:
            return 'loopMusic'
        if 'audiomanager[]' in params:
            return 'initAudioChannels'
        if ret == 'int':
            return 'getAudioChannel'
        return 'updateAudio'
    
    # Enemy
    if 'enemy' in cats:
        if 'health' in cats or 'hp' in cats:
            return 'getEnemyHealth' if ret == 'int' else 'updateEnemyHealth'
        if any('spawn' in c.lower() for c in caller_names):
            return 'spawnEnemy'
        if 'attack' in body or 'damage' in body:
            return 'enemyAttack'
        if 'move' in body or 'path' in body:
            return 'updateEnemyAI'
        if 'target' in body or 'follow' in body:
            return 'updateNPCTarget'
        return 'updateEnemy'
    
    # Particle
    if 'particle' in cats:
        if 'spawn' in name.lower() or 'create' in name.lower():
            return 'spawnParticle'
        if 'release' in name.lower() or 'free' in name.lower():
            return 'releaseParticle'
        if 'position' in strings or 'set' in strings:
            return 'setParticlePos'
        return 'updateParticles'
    
    # Render
    if 'render' in cats and 'graphics' in params:
        if 'text' in cats or 'font' in strings:
            return 'drawText'
        if 'ui' in cats or 'menu' in strings:
            return 'drawUI'
        if 'weapon' in cats:
            return 'drawWeaponHUD'
        if 'health' in cats:
            return 'drawHealthBar'
        if 'particle' in cats:
            return 'drawParticleEffect'
        if 'background' in body or 'tilemap' in body:
            return 'drawBackground'
        if 'sprite' in strings or 'drawimage' in body:
            return 'drawSprite'
        if 'enemy' in strings or 'zombie' in strings:
            return 'drawEnemy'
        if 'foreground' in body:
            return 'drawForeground'
        if 'hud' in body or 'score' in body:
            return 'drawHUD'
        return 'render'
    
    # Input
    if 'input' in cats:
        if 'key' in params:
            return 'handleKeyPress'
        if 'touch' in params or 'pointer' in params:
            return 'handleTouch'
        return 'handleInput'
    
    # Network
    if 'network' in cats:
        if 'platformrequest' in params:
            return 'openURL'
        return 'resolveIGPURL'
    
    # Persistence
    if 'persistence' in cats:
        return 'saveGameData' if 'save' in name.lower() else 'loadGameData'
    
    # Sensor
    if 'sensor' in cats:
        return 'initSensor' if 'init' in body else 'onSensorData'
    
    # Level
    if 'level' in cats:
        if 'load' in name.lower() or 'init' in name.lower():
            return 'loadLevel'
        if 'draw' in name.lower():
            return 'drawLevel'
        return 'manageLevel'
    
    # UI
    if 'ui' in cats:
        if 'menu' in strings:
            return 'showMenu'
        if 'dialog' in strings or 'confirm' in strings:
            return 'showDialog'
        return 'updateUI'
    
    # Entity
    if 'entity' in cats or 'gamedata' in params:
        if 'update' in name.lower():
            return 'updateEntity'
        if 'draw' in name.lower() or 'render' in name.lower():
            return 'renderEntity'
        if 'spawn' in name.lower():
            return 'spawnEntity'
        return 'manageEntity'
    
    # Fallback
    if ret == 'boolean':
        return f'check_{name}'
    if ret == 'int':
        return f'calc_{name}'
    if ret == 'void':
        return f'update_{name}'
    
    return name

base = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/decompiled/'
files = {
    'GameCanvas': base + 'GameCanvas.java',
    'GameController': base + 'GameController.java',
    'AudioManager': base + 'AudioManager.java',
    'GraphicsEngine': base + 'GraphicsEngine.java',
    'SensorHandler': base + 'SensorHandler.java',
}

all_methods = {}
callgraph = {}

print("Building call graph...")
for cls, path in files.items():
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    methods = find_methods(content)
    all_methods[cls] = {m['name']: m for m in methods}
    for m in methods:
        key = f"{cls}.{m['name']}"
        callgraph[key] = m['calls']

# Reverse call graph
called_by = {}
for key, callees in callgraph.items():
    for call in callees:
        if call not in called_by:
            called_by[call] = []
        called_by[call].append(key)

new_mapping = {}
for cls, methods in all_methods.items():
    for name, m in methods.items():
        key = f"{cls}.{name}"
        callees = callgraph[key]
        callers = called_by.get(key, [])
        cats = categorize(m)
        sug = suggest(m, cats, callers)
        if sug != name:
            new_mapping[key] = {
                'obfuscated': name,
                'suggested': sug,
                'categories': cats,
                'callers': callers[:5],
                'strings': m['strings'][:2]
            }

with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/advanced_mapping.json', 'w') as f:
    json.dump(new_mapping, f, indent=2)

print(f"Advanced mapping generated: {len(new_mapping)} entries")

cats = {}
for v in new_mapping.values():
    for c in v['categories']:
        cats[c] = cats.get(c, 0) + 1
print("\nTop categories:")
for c, n in sorted(cats.items(), key=lambda x: x[1], reverse=True)[:15]:
    print(f"  {c}: {n}")

print("\nSample high-value mappings:")
for key, val in list(new_mapping.items())[:15]:
    print(f"  {key}")
    print(f"    -> {val['suggested']} [{', '.join(val['categories'])}]")
    if val['callers']:
        print(f"    Callers: {val['callers'][:3]}")
    if val['strings']:
        print(f"    Strings: {val['strings'][:2]}")
