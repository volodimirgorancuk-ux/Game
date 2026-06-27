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
        })
    return methods

base = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/decompiled/'

# Load existing analysis
gc = find_methods(open(base + 'GameCanvas.java', 'r', encoding='utf-8', errors='replace').read())
gc_map = {m['name']: m for m in gc}

gm = find_methods(open(base + 'GameController.java', 'r', encoding='utf-8', errors='replace').read())
gm_map = {m['name']: m for m in gm}

am = find_methods(open(base + 'AudioManager.java', 'r', encoding='utf-8', errors='replace').read())
am_map = {m['name']: m for m in am}

ge = find_methods(open(base + 'GraphicsEngine.java', 'r', encoding='utf-8', errors='replace').read())
ge_map = {m['name']: m for m in ge}

sh = find_methods(open(base + 'SensorHandler.java', 'r', encoding='utf-8', errors='replace').read())
sh_map = {m['name']: m for m in sh}

# Build comprehensive mapping
mapping = {}

# ===== GameCanvas =====
# Known from earlier analysis
mapping['GameCanvas.run'] = 'gameLoop'
mapping['GameCanvas.t'] = 'startGame'
mapping['GameCanvas.u'] = 'stopGame'
mapping['GameCanvas.s'] = 'setPaused'
mapping['GameCanvas.d'] = 'setState'
mapping['GameCanvas.f'] = 'setRunning'
mapping['GameCanvas.l'] = 'hasFlag'
mapping['GameCanvas.a(Graphics)'] = 'clearScreen'
mapping['GameCanvas.e(int,int)'] = 'createOffscreenBuffer'
mapping['GameCanvas.j(Graphics)'] = 'drawBuffer'
mapping['GameCanvas.H()'] = 'destroyBuffer'
mapping['GameCanvas.I()'] = 'resetClipBuffer'

# State machine handlers
mapping['GameCanvas.v()'] = 'updateMainMenu'
mapping['GameCanvas.x()'] = 'updateOptions'
mapping['GameCanvas.F()'] = 'updateLoading'
mapping['GameCanvas.V()'] = 'updateCutscene'
mapping['GameCanvas.z()'] = 'updateInGame'
mapping['GameCanvas.bK()'] = 'updatePaused'
mapping['GameCanvas.B()'] = 'updateGameOver'
mapping['GameCanvas.dH()'] = 'updateVictory'
mapping['GameCanvas.dA()'] = 'updateContinue'
mapping['GameCanvas.eh()'] = 'updateCredits'
mapping['GameCanvas.fE()'] = 'updateLevelSelect'
mapping['GameCanvas.aO()'] = 'updateCharacterSelect'
mapping['GameCanvas.cd()'] = 'updateTutorial'
mapping['GameCanvas.E()'] = 'updatePausedSubmenu'
mapping['GameCanvas.G()'] = 'updateConfirmDialog'

# Input handlers
mapping['GameCanvas.p(int)'] = 'handleWeaponSelect'
mapping['GameCanvas.q(int)'] = 'handleSpecialInput'

# Drawing methods
mapping['GameCanvas.f(Graphics)'] = 'drawHUD'
mapping['GameCanvas.g(Graphics)'] = 'drawSprites'
mapping['GameCanvas.h(Graphics)'] = 'drawBackground'
mapping['GameCanvas.i(Graphics)'] = 'drawForeground'

# Particle system
mapping['GameCanvas.ax(int)'] = 'releaseParticleFocus'
mapping['GameCanvas.ay(int)'] = 'updateParticleSystem'
mapping['GameCanvas.c(int,int,int)'] = 'setParticlePosition'
mapping['GameCanvas.k(int)'] = 'stopParticles'
mapping['GameCanvas.az(int)'] = 'spawnParticle'

# Map/sprite loading
mapping['GameCanvas.aw()'] = 'initAudio'
mapping['GameCanvas.af()'] = 'initSprites'
mapping['GameCanvas.ah()'] = 'initLevel'
mapping['GameCanvas.ai()'] = 'drawUI'
mapping['GameCanvas.aj()'] = 'playSound'

# Enemy spawning
mapping['GameCanvas.b(int,int,int,boolean)'] = 'spawnEnemy'
mapping['GameCanvas.ck()'] = 'spawnWave'

# ===== AudioManager =====
mapping['AudioManager.j(AudioManager)'] = 'getEnemyTotalHealth'
mapping['AudioManager.k(AudioManager)'] = 'getEnemyCurrentHealth'
mapping['AudioManager.c(AudioManager,AudioManager)'] = 'updateNPCTarget'

# ===== GameController =====
mapping['GameController.b()'] = 'loadIGPData'
mapping['GameController.c()'] = 'isOnline'
mapping['GameController.g()'] = 'openURL'
mapping['GameController.commandAction(Command,Displayable)'] = 'onCommand'

# ===== SensorHandler =====
mapping['SensorHandler.a()'] = 'initSensor'
mapping['SensorHandler.dataReceived(SensorConnection,Data[],boolean)'] = 'onSensorData'

# ===== Now add more based on strings =====
# GameCanvas - audio related
for m in gc:
    if m['name'] in ['ag', 'ah', 'ai', 'aj', 'ak', 'al', 'am', 'an', 'ao']:
        if m['name'] not in mapping:
            mapping[f"GameCanvas.{m['name']}"] = f'audioMethod_{m["name"]}'

# GameCanvas - sprite/layer related
for m in gc:
    if m['name'] in ['aA', 'aB', 'aC', 'aD', 'aE', 'aF', 'aG', 'aH', 'aI', 'aJ', 'aK', 'aL', 'aM', 'aN', 'aO', 'aP', 'aQ', 'aR', 'aS', 'aT', 'aU', 'aV', 'aW', 'aX', 'aY', 'aZ']:
        if m['name'] not in mapping:
            mapping[f"GameCanvas.{m['name']}"] = f'stateOrRender_{m["name"]}'

# GameCanvas - long[] a = new long[20] related (likely timers)
mapping['GameCanvas.J()'] = 'initTimers'
mapping['GameCanvas.K()'] = 'resetTimers'

# Save comprehensive mapping
with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/comprehensive_mapping.json', 'w') as f:
    json.dump(mapping, f, indent=2)

print(f"\nBuilt comprehensive mapping with {len(mapping)} entries")
print("\n=== KEY MAPPINGS ===\n")
for key, val in sorted(mapping.items())[:40]:
    print(f"  {key} -> {val}")
