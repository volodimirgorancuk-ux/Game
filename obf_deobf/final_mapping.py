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

gc = find_methods(open(base + 'GameCanvas.java', 'r', encoding='utf-8', errors='replace').read())
gc_methods_by_name = {m['name']: m for m in gc}

gm = find_methods(open(base + 'GameController.java', 'r', encoding='utf-8', errors='replace').read())
gm_methods_by_name = {m['name']: m for m in gm}

am = find_methods(open(base + 'AudioManager.java', 'r', encoding='utf-8', errors='replace').read())
am_methods_by_name = {m['name']: m for m in am}

ge = find_methods(open(base + 'GraphicsEngine.java', 'r', encoding='utf-8', errors='replace').read())
ge_methods_by_name = {m['name']: m for m in ge}

sh = find_methods(open(base + 'SensorHandler.java', 'r', encoding='utf-8', errors='replace').read())
sh_methods_by_name = {m['name']: m for m in sh}

# Manual mapping based on analysis
manual_map = {
    # GameCanvas core
    'GameCanvas.run': 'gameLoop',
    'GameCanvas.t': 'startGame',
    'GameCanvas.u': 'stopGame',
    'GameCanvas.s': 'setPaused',
    'GameCanvas.d': 'setState',
    'GameCanvas.f': 'setRunning',
    'GameCanvas.l': 'hasFlag',
    'GameCanvas.a': 'clearScreen',
    'GameCanvas.e': 'createOffscreenBuffer',
    'GameCanvas.j': 'drawBuffer',
    'GameCanvas.H': 'destroyBuffer',
    'GameCanvas.I': 'resetClipBuffer',
    'GameCanvas.J': 'initTimers',
    'GameCanvas.K': 'resetTimers',
    # State machine
    'GameCanvas.v': 'updateMainMenu',
    'GameCanvas.x': 'updateOptions',
    'GameCanvas.F': 'updateLoading',
    'GameCanvas.V': 'updateCutscene',
    'GameCanvas.z': 'updateInGame',
    'GameCanvas.bK': 'updatePaused',
    'GameCanvas.B': 'updateGameOver',
    'GameCanvas.dH': 'updateVictory',
    'GameCanvas.dA': 'updateContinue',
    'GameCanvas.eh': 'updateCredits',
    'GameCanvas.fE': 'updateLevelSelect',
    'GameCanvas.aO': 'updateCharacterSelect',
    'GameCanvas.cd': 'updateTutorial',
    'GameCanvas.E': 'updatePausedSubmenu',
    'GameCanvas.G': 'updateConfirmDialog',
    'GameCanvas.b': 'drawForeground',
    'GameCanvas.c': 'drawBackground',
    'GameCanvas.g': 'drawSprites',
    'GameCanvas.h': 'updateParticles',
    'GameCanvas.f': 'drawHUD',
    # Input
    'GameCanvas.p': 'handleWeaponSelect',
    'GameCanvas.q': 'handleSpecialInput',
    'GameCanvas.m': 'setTile',
    'GameCanvas.n': 'setLayer',
    # Particles
    'GameCanvas.ax': 'releaseParticleFocus',
    'GameCanvas.ay': 'updateParticleSystem',
    'GameCanvas.k': 'stopParticles',
    'GameCanvas.az': 'spawnParticle',
    # Init
    'GameCanvas.aw': 'initAudio',
    'GameCanvas.af': 'initSprites',
    'GameCanvas.ah': 'initLevel',
    'GameCanvas.ai': 'drawUI',
    'GameCanvas.aj': 'playSound',
    # Enemy
    'GameCanvas.b(int,int,int,boolean)': 'spawnEnemy',
    'GameCanvas.ck': 'spawnWave',
    # AudioManager
    'AudioManager.j(AudioManager)': 'getEnemyTotalHealth',
    'AudioManager.k(AudioManager)': 'getEnemyCurrentHealth',
    'AudioManager.c(AudioManager,AudioManager)': 'updateNPCTarget',
    # GameController
    'GameController.b()': 'loadIGPData',
    'GameController.c()': 'isOnline',
    'GameController.g()': 'openURL',
    'GameController.commandAction(Command,Displayable)': 'onCommand',
    # SensorHandler
    'SensorHandler.a()': 'initSensor',
    'SensorHandler.dataReceived(SensorConnection,Data[],boolean)': 'onSensorData',
}

# Write ProGuard-style mapping
proguard_lines = []
proguard_lines.append("# GameCanvas (GameCanvas -> g)")
proguard_lines.append("GameCanvas -> g:")

for key, val in manual_map.items():
    if key.startswith('GameCanvas.'):
        method_name = key.split('.', 1)[1]
        proguard_lines.append(f"    {val} -> {method_name}")

proguard_lines.append("\n# GameController (GameController -> b)")
proguard_lines.append("GameController -> b:")
for key, val in manual_map.items():
    if key.startswith('GameController.'):
        parts = key.split('.', 1)
        sig = parts[1] if len(parts) > 1 else ''
        # Handle different formats
        if '(' in sig:
            method_name = sig.split('(')[0]
            proguard_lines.append(f"    {val} -> {method_name}")
        else:
            proguard_lines.append(f"    {val} -> {sig}")

proguard_lines.append("\n# AudioManager (AudioManager -> f)")
proguard_lines.append("AudioManager -> f:")
for key, val in manual_map.items():
    if key.startswith('AudioManager.'):
        sig = key.split('.', 1)[1]
        if '(' in sig:
            method_name = sig.split('(')[0]
            proguard_lines.append(f"    {val} -> {method_name}")
        else:
            proguard_lines.append(f"    {val} -> {sig}")

proguard_lines.append("\n# GraphicsEngine (GraphicsEngine -> a)")
proguard_lines.append("GraphicsEngine -> a:")

proguard_lines.append("\n# SensorHandler (SensorHandler -> e)")
proguard_lines.append("SensorHandler -> e:")
for key, val in manual_map.items():
    if key.startswith('SensorHandler.'):
        sig = key.split('.', 1)[1]
        if '(' in sig:
            method_name = sig.split('(')[0]
            proguard_lines.append(f"    {val} -> {method_name}")
        else:
            proguard_lines.append(f"    {val} -> {sig}")

with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/mapping_methods.txt', 'w') as f:
    f.write('\n'.join(proguard_lines))

print("Generated mapping_methods.txt")
print(f"Total manual mappings: {len(manual_map)}")
