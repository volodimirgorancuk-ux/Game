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

# Load existing comprehensive mapping
with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/comprehensive_mapping.json', 'r') as f:
    mapping = json.load(f)

# Update GameCanvas methods with InGame-specific mappings
# Based on analysis of state 4 (InGame) at lines 1081-1280 and render at 11652+
ingame_updates = {
    # Main update/render for InGame
    'GameCanvas.z()': 'updateInGame',
    'GameCanvas.z(Graphics)': 'renderInGame',
    
    # Sub-state render methods
    'GameCanvas.c(Graphics,boolean)': 'renderHUD',
    'GameCanvas.A(Graphics)': 'renderPlayer',
    'GameCanvas.d(Graphics,boolean)': 'renderTarget',
    'GameCanvas.S(Graphics)': 'renderWorld',
    
    # Game loop core
    'GameCanvas.B()': 'updateGameLoop',
    'GameCanvas.A()': 'updateCamera',
    'GameCanvas.bc()': 'updateEntities',
    'GameCanvas.bu()': 'updateProjectiles',
    'GameCanvas.cx()': 'updateCollisions',
    'GameCanvas.dd()': 'updateEffects',
    'GameCanvas.bA()': 'handleInput',
    'GameCanvas.q()': 'handleSpecialInput',
    
    # Camera/level
    'GameCanvas.ba()': 'updateCameraTarget',
    'GameCanvas.bb()': 'initLevelEntities',
    'GameCanvas.aZ()': 'clearLevelEntities',
    'GameCanvas.Z(int)': 'loadLevel',
    
    # HUD helpers
    'GameCanvas.a(int,int,int,int,int,GraphicsEngine[],GraphicsEngine,int,int,int,int,int,int,int,int,int)': 'drawWeaponHUD',
    'GameCanvas.b(Graphics,int)': 'drawCrosshair',
    'GameCanvas.a(Graphics,int,int,int)': 'drawDamageIndicator',
    
    # Entity management
    'GameCanvas.a(GameData)': 'spawnEntity',
    'GameCanvas.b(GameData)': 'despawnEntity',
    
    # Health/damage system
    'GameCanvas.b(int,int,int,int,int,int,boolean)': 'spawnEnemy',
    'GameCanvas.a(boolean,boolean)': 'triggerDamageFlash',
    'GameCanvas.f()': 'isPlayerDead',
    'GameCanvas.y()': 'isPlayerDying',
    
    # Input/combat
    'GameCanvas.l(int,int)': 'getEntityProperty',
    'GameCanvas.c(int)': 'playFootstep',
    
    # Particle effects
    'GameCanvas.z()': 'updateParticles',
    'GameCanvas.br()': 'updateParticleBursts',
    'GameCanvas.bm()': 'updateParticleTrails',
    
    # Screen transitions
    'GameCanvas.aT': 'letterboxHeight',
}

# Merge with existing mapping
for key, val in ingame_updates.items():
    if key not in mapping:
        mapping[key] = val

# Write updated mapping
with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/comprehensive_mapping.json', 'w') as f:
    json.dump(mapping, f, indent=2)

print(f"Updated comprehensive_mapping.json with {len(mapping)} entries")
print(f"Added {len(ingame_updates)} InGame-specific mappings")

# Also update the human-readable mapping_methods.txt
with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/mapping_methods.txt', 'a') as f:
    f.write("\n\n# ============ INGAME STATE (State 4) ============\n")
    f.write("# GameCanvas InGame methods\n")
    for key, val in sorted(ingame_updates.items()):
        f.write(f"GameCanvas.{key.split('.', 1)[1].split('(')[0]} -> {val}\n")

print("Updated mapping_methods.txt")
