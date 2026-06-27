import re, json, hashlib

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

def get_caller_domain(callers_lower):
    for c in callers_lower:
        if 'menu' in c or 'main' in c: return 'menu'
        if 'option' in c or 'setting' in c: return 'options'
        if 'load' in c or 'init' in c: return 'loading'
        if 'cutscene' in c or 'cinematic' in c: return 'cutscene'
        if 'game' in c or 'ingame' in c or 'updatez' in c: return 'gameplay'
        if 'paus' in c: return 'pause'
        if 'gameover' in c or 'dead' in c: return 'gameover'
        if 'victory' in c or 'win' in c: return 'victory'
        if 'continue' in c: return 'continue'
        if 'credit' in c: return 'credits'
        if 'level' in c: return 'level'
        if 'character' in c or 'select' in c: return 'charselect'
        if 'audio' in c: return 'audio'
        if 'enemy' in c or 'npc' in c or 'zombie' in c: return 'enemy'
        if 'particle' in c: return 'particle'
        if 'hud' in c or 'ui' in c: return 'hud'
        if 'input' in c or 'key' in c or 'touch' in c: return 'input'
        if 'sensor' in c: return 'sensor'
        if 'network' in c or 'url' in c: return 'network'
    return None

def infer_name(method, caller_map, key_prefix):
    name = method['name']
    ret = method['return_type']
    params = method['params']
    body = method['body'].lower()
    strings = [s.lower() for s in method['strings']]
    
    full_key = key_prefix + name + "(" + params[:50] + ")"
    callers_lower = [c.lower() for c in caller_map.get(full_key, [])]
    caller_domain = get_caller_domain(callers_lower)
    
    sig_hash = hashlib.md5(f"{name}({params})".encode()).hexdigest()[:4]
    
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
    
    if 'graphics' in params.lower():
        if ret == 'void':
            if 'font' in strings or 'text' in strings:
                return f'drawText_{sig_hash}'
            if 'weapon' in strings or 'hud' in strings:
                return f'drawWeapon_{sig_hash}'
            if 'health' in strings or 'hp' in strings:
                return f'drawHealth_{sig_hash}'
            if 'enemy' in strings or 'zombie' in strings:
                return f'drawEnemy_{sig_hash}'
            if 'particle' in strings:
                return f'drawParticle_{sig_hash}'
            if 'background' in strings or 'tile' in strings:
                return f'drawBackground_{sig_hash}'
            if 'sprite' in strings:
                return f'drawSprite_{sig_hash}'
            if 'foreground' in body:
                return f'drawForeground_{sig_hash}'
            if 'hud' in body or 'score' in body:
                return f'drawHUD_{sig_hash}'
            return f'draw_{sig_hash}'
        return f'calcGraphics_{sig_hash}'
    
    if 'audio' in strings or any('audio' in c for c in callers_lower):
        if 'play' in name.lower() or 'start' in name.lower():
            return 'playSound'
        if 'stop' in name.lower():
            return 'stopSound'
        if 'volume' in strings:
            return 'setVolume'
        if 'midi' in strings or 'wav' in strings:
            return 'playMusic' if 'music' in strings else 'playSFX'
        if 'audiomanager[]' in params.lower():
            return 'initAudioChannels'
        if ret == 'int':
            return f'getAudio_{sig_hash}'
        return f'audio_{sig_hash}'
    
    if any(w in strings for w in ['enemy', 'zombie', 'npc', 'spawn']) or \
       any('enemy' in c for c in callers_lower):
        if 'health' in strings:
            return 'getEnemyHealth' if ret == 'int' else 'updateEnemyHealth'
        if 'spawn' in strings or 'spawnarea' in strings:
            return 'spawnEnemy'
        if 'attack' in body or 'damage' in body:
            return 'enemyAttack'
        return f'enemy_{sig_hash}'
    
    if 'particle' in strings or any('particle' in c for c in callers_lower):
        if 'spawn' in name.lower():
            return 'spawnParticle'
        if 'release' in name.lower():
            return 'releaseParticle'
        return f'particle_{sig_hash}'
    
    if any(w in strings for w in ['key', 'touch', 'input', 'pressed']) or \
       any('input' in c for c in callers_lower):
        if 'key' in params.lower():
            return f'handleKey_{sig_hash}'
        if 'touch' in params.lower():
            return f'handleTouch_{sig_hash}'
        return f'input_{sig_hash}'
    
    if any(w in strings for w in ['rms', 'recordstore']) or 'recordstore' in body:
        return 'saveGameData' if 'save' in name.lower() else 'loadGameData'
    
    if any(w in strings for w in ['url', 'http']) or 'platformrequest' in body:
        return 'openURL' if 'open' in name.lower() else 'resolveIGPURL'
    
    if 'sensor' in strings or 'sensormanager' in body:
        return 'onSensorData' if 'data' in name.lower() else 'initSensor'
    
    if any(w in strings for w in ['level', 'map', 'tile']) or any('level' in c for c in callers_lower):
        if 'load' in name.lower():
            return f'loadLevel_{sig_hash}'
        if 'draw' in name.lower():
            return f'drawLevel_{sig_hash}'
        return f'level_{sig_hash}'
    
    if any(w in strings for w in ['menu', 'hud', 'ui', 'score']) or any('ui' in c for c in callers_lower):
        if 'draw' in name.lower() or 'show' in name.lower():
            return f'drawUI_{sig_hash}'
        return f'updateUI_{sig_hash}'
    
    if 'warning' in strings:
        return f'debugLog_{sig_hash}'
    
    if 'drawimage' in body or 'drawregion' in body or 'fillrect' in body:
        return f'render_{sig_hash}'
    
    if 'switch' in body and 'case' in body:
        return f'dispatch_{sig_hash}'
    
    if caller_domain:
        if ret == 'void':
            return f'{caller_domain}Update_{sig_hash}'
        if ret == 'boolean':
            return f'is{caller_domain.capitalize()}_{sig_hash}'
        if ret == 'int':
            return f'get{caller_domain.capitalize()}_{sig_hash}'
        return f'{caller_domain}Calc_{sig_hash}'
    
    if ret == 'boolean':
        return f'check_{sig_hash}'
    if ret == 'int':
        return f'calc_{sig_hash}'
    if ret == 'void':
        return f'update_{sig_hash}'
    
    return f'method_{sig_hash}'

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
caller_map = {}

print("Building detailed method index...")
for cls, path in files.items():
    with open(path, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    methods = find_methods(content)
    all_methods[cls] = methods
    for m in methods:
        key_prefix = f"{cls}.{m['name']}"
        full_key = f"{key_prefix}({m['params'][:50]})"
        callee_map[full_key] = m['calls']
        for call in m['calls']:
            if call not in caller_map:
                caller_map[call] = []
            caller_map[call].append(full_key)

mapping = {}
for cls, methods in all_methods.items():
    for m in methods:
        key_prefix = f"{cls}.{m['name']}("
        full_key = f"{key_prefix}{m['params'][:50]})"
        sug = infer_name(m, caller_map, key_prefix)
        
        if m['name'] == 'run': sug = 'gameLoop'
        if 'commandaction' in m['name'].lower(): sug = 'onCommand'
        if 'dataReceived' in m['name']: sug = 'onSensorData'
        
        mapping[full_key] = {
            'obfuscated': m['name'],
            'suggested': sug,
            'return': m['return_type'],
            'params': m['params'][:50],
            'callers': caller_map.get(full_key, [])[:5],
            'strings': m['strings'][:3]
        }

with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/smart_mapping_v2.json', 'w') as f:
    json.dump(mapping, f, indent=2)

total = len(mapping)
unique = len(set(v['suggested'] for v in mapping.values()))
renamed = sum(1 for v in mapping.values() if v['suggested'] != v['obfuscated'])

print(f"Total unique method signatures: {total}")
print(f"Unique suggested names: {unique}")
print(f"Methods that would be renamed: {renamed}")

cats = {}
for v in mapping.values():
    sug = v['suggested']
    cat = sug.split('_')[0] if '_' in sug else sug
    cats[cat] = cats.get(cat, 0) + 1

print("\nTop categories:")
for c, n in sorted(cats.items(), key=lambda x: x[1], reverse=True)[:15]:
    print(f"  {c}: {n}")
