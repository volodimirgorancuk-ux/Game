import re, json

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

def categorize(method):
    cats = set()
    body = method['body'].lower()
    strings = [s.lower() for s in method['strings']]
    params = method['params'].lower()
    
    for s in strings:
        if 'warning' in s: cats.add('warning')
        if any(w in s for w in ['enemy', 'zombie', 'npc', 'spawn', 'health', 'hp', 'damage', 'dead']):
            cats.add('enemy')
        if any(w in s for w in ['weapon', 'bullet', 'shot', 'gun', 'katana', 'ammo']):
            cats.add('weapon')
        if 'particle' in s: cats.add('particles')
        if any(w in s for w in ['audio', 'player', 'volume', 'sound', 'music', 'midi', 'wav', 'amr', 'tone-seq']):
            cats.add('audio')
        if any(w in s for w in ['url', 'http', 'web', 'browser']):
            cats.add('network')
        if any(w in s for w in ['rms', 'recordstore']):
            cats.add('persistence')
        if any(w in s for w in ['sensor', 'accelerometer']):
            cats.add('sensor')
        if any(w in s for w in ['touch', 'key', 'input', 'click', 'pressed', 'pointer']):
            cats.add('input')
        if any(w in s for w in ['menu', 'hud', 'ui', 'score']):
            cats.add('ui')
        if any(w in s for w in ['level', 'map', 'tile', 'sprite', 'scene', 'mission']):
            cats.add('level')
        if any(w in s for w in ['draw', 'image', 'fillrect', 'drawstring', 'drawregion']):
            cats.add('render')
        if any(w in s for w in ['font', 'text', 'string']):
            cats.add('text')
        if any(w in s for w in ['start', 'init', 'load', 'create', 'setup']):
            cats.add('init')
        if any(w in s for w in ['stop', 'destroy', 'end', 'exit', 'release', 'pause']):
            cats.add('cleanup')
    
    if 'audiomanager[]' in params or 'audioManagerArray' in params:
        cats.add('audio-array')
    if 'graphicsengine' in params:
        cats.add('graphics-engine')
    if 'gamedata' in params:
        cats.add('game-data')
    
    if any(w in body for w in ['drawimage', 'drawregion', 'fillrect', 'drawstring', 'graphics.']):
        cats.add('render')
    if 'createplayer' in body or 'player.realize' in body:
        cats.add('audio')
    if 'sensormanager' in body or 'accelerometer' in body:
        cats.add('sensor')
    if 'recordstore' in body or 'openrecordstore' in body:
        cats.add('persistence')
    if 'platformrequest' in body:
        cats.add('network')
    if method['name'] == 'run':
        cats.add('main-loop')
    
    return list(cats)

def suggest(method, cats):
    name = method['name']
    ret = method['return_type']
    params = method['params'].lower()
    strings = method['strings']
    
    if name == 'run': return 'gameLoop'
    if 'commandaction' in name.lower(): return 'onCommand'
    if 'dataReceived' in name: return 'onSensorData'
    
    if 'audio' in cats:
        if 'play' in name.lower() or 'start' in name.lower():
            return 'playSound'
        if 'stop' in name.lower():
            return 'stopSound'
        if 'audiomanager[]' in params:
            return 'initAudioChannels'
        if ret == 'int':
            return 'getAudioChannel'
        return 'updateAudio'
    
    if 'enemy' in cats:
        if 'health' in cats:
            return 'getEnemyHealth' if ret == 'int' else 'updateEnemyHealth'
        if any('spawn' in s.lower() for s in strings):
            return 'spawnEnemy'
        return 'updateEnemy'
    
    if 'particles' in cats:
        if any('release' in s.lower() or 'free' in s.lower() for s in strings):
            return 'releaseParticle'
        if any('position' in s.lower() or 'set' in s.lower() for s in strings):
            return 'setParticlePos'
        return 'updateParticles'
    
    if 'render' in cats and 'graphics' in params:
        if 'text' in cats:
            return 'drawText'
        if 'ui' in cats or 'menu' in cats:
            return 'drawUI'
        if 'weapon' in cats:
            return 'drawWeaponHUD'
        if 'health' in cats:
            return 'drawHealthBar'
        return 'render'
    
    if 'input' in cats:
        if 'key' in params or any('pressed' in s.lower() for s in strings):
            return 'handleKey'
        if 'touch' in cats or 'pointer' in params:
            return 'handleTouch'
    
    if 'network' in cats:
        if 'platformrequest' in params:
            return 'openURL'
        return 'resolveIGPURL'
    
    if 'sensor' in cats:
        return 'initSensor' if 'init' in cats else 'onSensorData'
    
    if 'persistence' in cats:
        return 'saveRecord' if 'save' in name.lower() else 'loadRecord'
    
    return None

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

for cls, path in files.items():
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    methods = find_methods(content)
    for m in methods:
        m['class'] = cls
        m['cats'] = categorize(m)
        m['suggested'] = suggest(m, m['cats'])
    all_methods[cls] = methods
    print(f"{cls}: {len(methods)} methods")
    for m in methods:
        key = f"{cls}.{m['name']}"
        callgraph[key] = {
            'calls': m['calls'],
            'categories': m['cats'],
            'strings': m['strings'][:3]
        }

print("\n=== SUGGESTED METHOD MAPPINGS ===\n")
for cls, methods in all_methods.items():
    suggestions = [(m, m['suggested'], m['cats']) for m in methods if m['suggested'] and m['suggested'] != m['name']]
    if suggestions:
        print(f"\n--- {cls} ({len(suggestions)} suggestions) ---")
        for m, sug, cats in suggestions[:15]:
            print(f"  {m['return_type']:10s} {m['name']}({m['params'][:40]})")
            print(f"    -> {sug}  [{', '.join(cats)}]")
            if m['strings']:
                print(f"    Strings: {m['strings'][:2]}")
        if len(suggestions) > 15:
            print(f"  ... and {len(suggestions)-15} more")

print("\n=== CROSS-CLASS CALLS (sample) ===")
count = 0
for key, info in callgraph.items():
    for call in info['calls']:
        if '.' in call and not call.startswith('java.') and not call.startswith('javax.'):
            print(f"  {key} -> {call}")
            count += 1
            if count >= 30:
                break
    if count >= 30:
        break

with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/callgraph.json', 'w') as f:
    json.dump(callgraph, f, indent=2)
print("\nSaved callgraph.json")
