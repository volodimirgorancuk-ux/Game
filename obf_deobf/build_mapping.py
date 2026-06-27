import re, json

def extract_strings(text):
    strings = re.findall(r'"([^"\\]*(?:\\.[^"\\]*)*)"', text)
    return list(set(strings))

def suggest_name(method, categories):
    name = method['name']
    ret = method['return_type']
    params = method['params'].lower()
    strings = method['strings']
    
    if name == 'run':
        return 'gameLoop'
    if 'commandaction' in name.lower():
        return 'onCommand'
    if 'dataReceived' in name:
        return 'onSensorDataReceived'
    
    if 'audio' in categories:
        if ret == 'void' and 'play' in name.lower():
            return 'playSound'
        if ret == 'void' and 'stop' in name.lower():
            return 'stopSound'
        if ret == 'int' and 'manager' in params:
            return 'getAudioChannel'
        if 'audiomanager[]' in params:
            return 'initAudioChannels'
    
    if 'enemy' in categories:
        if 'health' in categories:
            return 'getEnemyTotalHealth' if ret == 'int' else 'updateEnemyHealth'
        if 'spawn' in categories or any('spawnarea' in s.lower() for s in strings):
            return 'spawnEnemy'
        if 'npc' in categories:
            return 'updateNPC'
    
    if 'particles' in categories:
        if any('gameparticles' in s.lower() for s in strings):
            return 'updateParticleSystem'
        if 'release' in name.lower() or 'free' in name.lower():
            return 'releaseParticleFocus'
        return 'updateParticles'
    
    if 'render' in categories and 'graphics' in params:
        if 'ui' in categories or 'menu' in categories:
            return 'drawUI'
        if 'text' in categories:
            return 'drawText'
        if 'weapon' in categories:
            return 'drawWeaponHUD'
        if 'health' in categories or 'hp' in categories:
            return 'drawHealthBar'
    
    if 'input' in categories:
        if 'key' in categories or any('pressed' in s.lower() for s in strings):
            return 'handleKeyPress'
        if 'touch' in categories:
            return 'handleTouchInput'
    
    if 'network' in categories or 'url' in categories:
        if 'platformrequest' in params or any('platform' in s.lower() for s in strings):
            return 'openBrowserURL'
        return 'resolveIGPURL'
    
    if 'persistence' in categories:
        if 'save' in name.lower() or 'write' in name.lower():
            return 'saveGameData'
        if 'load' in name.lower() or 'read' in name.lower():
            return 'loadGameData'
    
    if 'sensor' in categories:
        if 'dataReceived' in name:
            return 'onAccelerometerData'
        return 'initSensor'
    
    return None

def categorize_method(method):
    name = method['name']
    return_type = method['return_type']
    params = method['params'].lower()
    strings = [s.lower() for s in method['strings']]
    
    categories = []
    
    for s in strings:
        if 'warning' in s: categories.append('warning/log')
        if any(w in s for w in ['enemy', 'zombie', 'npc', 'spawn', 'health', 'hp', 'damage', 'dead']):
            categories.append('enemy')
        if any(w in s for w in ['weapon', 'bullet', 'shot', 'gun', 'katana', 'ammo']):
            categories.append('weapon')
        if 'particle' in s: categories.append('particles')
        if any(w in s for w in ['audio', 'player', 'volume', 'sound', 'music', 'midi', 'wav', 'amr']):
            categories.append('audio')
        if any(w in s for w in ['url', 'http', 'web', 'browser', 'platformrequest']):
            categories.append('network')
        if any(w in s for w in ['rms', 'recordstore', 'save', 'load']):
            categories.append('persistence')
        if any(w in s for w in ['sensor', 'accelerometer']):
            categories.append('sensor')
        if any(w in s for w in ['touch', 'key', 'input', 'click', 'pressed']):
            categories.append('input')
        if any(w in s for w in ['menu', 'hud', 'ui', 'score', 'health']):
            categories.append('ui')
        if any(w in s for w in ['level', 'map', 'tile', 'sprite', 'scene']):
            categories.append('level')
        if any(w in s for w in ['draw', 'image', 'fillrect', 'drawstring', 'drawregion', 'graphics']):
            categories.append('render')
        if any(w in s for w in ['font', 'text']):
            categories.append('text')
        if any(w in s for w in ['start', 'init', 'load', 'create']):
            categories.append('init')
        if any(w in s for w in ['stop', 'destroy', 'end', 'exit', 'release']):
            categories.append('cleanup')
    
    if 'audiomanager[]' in params: categories.append('audio-array')
    if 'graphicsengine' in params: categories.append('graphics-engine')
    if 'gamedata' in params: categories.append('game-data')
    
    body = method.get('body', '').lower()
    if any(w in body for w in ['drawimage', 'drawregion', 'fillrect', 'drawstring']):
        categories.append('render')
    if 'createplayer' in body or 'player.start' in body:
        categories.append('audio')
    if 'sensormanager' in body or 'accelerometer' in body:
        categories.append('sensor')
    if 'recordstore' in body or 'openrecordstore' in body:
        categories.append('persistence')
    if 'platformrequest' in body:
        categories.append('network')
    
    return list(set(categories))

def analyze_java_file(filepath):
    with open(filepath, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    
    methods = []
    current_method = None
    method_body = []
    in_method = False
    brace_depth = 0
    
    lines = content.split('\n')
    i = 0
    while i < len(lines):
        line = lines[i]
        stripped = line.strip()
        
        m = re.match(r'^(public|private|protected|static|\s)*\s*(?:static\s+)?(\w+)\s+(\w+)\s*\(([^)]*)\)\s*\{?\s*$', stripped)
        if m and not stripped.startswith('//') and not stripped.startswith('/*'):
            return_type = m.group(2)
            name = m.group(3)
            params = m.group(4)
            if return_type not in ('if', 'while', 'for', 'switch', 'catch', 'synchronized', 'else'):
                if current_method:
                    current_method['body'] = '\n'.join(method_body)
                    current_method['strings'] = extract_strings(current_method['body'])
                    methods.append(current_method)
                
                current_method = {
                    'name': name,
                    'return_type': return_type,
                    'params': params,
                    'line': i + 1,
                    'body': '',
                    'strings': [],
                }
                method_body = []
                in_method = True
                brace_depth = stripped.count('{') - stripped.count('}')
                i += 1
                continue
        
        if in_method:
            method_body.append(line)
            brace_depth += line.count('{') - line.count('}')
            if brace_depth <= 0 and ('{' not in ''.join(method_body[:5]) or 'abstract' in method_body[0]):
                in_method = False
            elif brace_depth <= 0:
                in_method = False
        
        i += 1
    
    if current_method:
        current_method['body'] = '\n'.join(method_body)
        current_method['strings'] = extract_strings(current_method['body'])
        methods.append(current_method)
    
    return methods

base = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/decompiled/'
files = {
    'GameCanvas': base + 'GameCanvas.java',
    'GameController': base + 'GameController.java',
    'GameData': base + 'GameData.java',
    'GraphicsEngine': base + 'GraphicsEngine.java',
    'AudioManager': base + 'AudioManager.java',
    'SensorHandler': base + 'SensorHandler.java',
}

all_methods = {}
for cls, path in files.items():
    methods = analyze_java_file(path)
    for m in methods:
        m['class'] = cls
        m['categories'] = categorize_method(m)
    all_methods[cls] = methods
    print(f"{cls}: {len(methods)} methods")

mapping = {}
for cls, methods in all_methods.items():
    for m in methods:
        sig = f"{m['return_type']} {m['name']}({m['params'][:50]})"
        categories = m['categories']
        suggested = suggest_name(m, categories)
        if suggested and suggested != m['name']:
            key = f"{cls}.{sig[:70]}"
            mapping[key] = {
                'obfuscated': m['name'],
                'suggested': suggested,
                'categories': categories,
                'strings': m['strings'][:3],
                'line': m['line']
            }

print(f"\n=== TOP SUGGESTIONS (by category count) ===\n")
for key, val in sorted(mapping.items(), key=lambda x: len(x[1]['categories']), reverse=True)[:50]:
    print(f"  {key}")
    print(f"    -> {val['suggested']} [{', '.join(val['categories'])}]")
    if val['strings']:
        print(f"    Strings: {val['strings'][:2]}")
    print()

with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/suggested_mapping.json', 'w') as f:
    json.dump(mapping, f, indent=2, default=str)

print(f"\nSaved {len(mapping)} suggestions to suggested_mapping.json")
