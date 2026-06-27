import re, json, os

base = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/'
outdir = base + 'fully_deobfuscated_src/'

with open(base + 'obf_deobf/smart_mapping_v2.json', 'r') as f:
    smart = json.load(f)

print(f"Loaded {len(smart)} signature-inclusive mappings")

class_files = ['GameCanvas.java', 'GameController.java', 'AudioManager.java',
               'GraphicsEngine.java', 'SensorHandler.java', 'GameData.java',
               'GameConfig.java', 'GloftMASS.java']

os.makedirs(outdir, exist_ok=True)

for cls_file in class_files:
    filepath = base + 'decompiled/' + cls_file
    if not os.path.exists(filepath):
        continue
    
    with open(filepath, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    
    class_name = cls_file.replace('.java', '')
    
    sig_pattern = re.compile(
        r'^\s*(?:public|private|protected|static|final|abstract|\s)*\s*'
        r'(?:static\s+)?(?:final\s+)?'
        r'(\w+)\s+(\w+)\s*\(([^)]*)\)\s*\{',
        re.MULTILINE
    )
    matches = list(sig_pattern.finditer(content))
    
    renames = []
    run_renames = []
    for m in matches:
        obf_name = m.group(2)
        params = m.group(3)[:50]
        sig_key = f"{class_name}.{obf_name}({params})"
        
        if sig_key in smart:
            sug = smart[sig_key]['suggested']
            if sug != obf_name:
                renames.append((m.start(), obf_name, sug))
                if obf_name == 'run':
                    run_renames.append((m.start(), obf_name, sug, sig_key))
    
    print(f"  {cls_file}: {len(renames)} renames, run() renames: {run_renames}")
    
    # Apply from end to start to preserve positions
    content_list = list(content)
    for start, old, new in reversed(sorted(renames, key=lambda x: x[0])):
        end = start + len(old)
        if content_list[start:end] == list(old):
            content_list[start:end] = list(new)
    
    content = ''.join(content_list)
    
    # Qualify calls for all renames
    unique_new_names = set(r[2] for r in renames)
    for cls_name in ['GameCanvas', 'GameController', 'AudioManager', 'GraphicsEngine', 
                     'SensorHandler', 'GameData', 'GameConfig', 'GloftMASS']:
        for old, new in [(r[1], r[2]) for r in renames]:
            old_call = f'{cls_name}.{old}('
            new_call = f'{cls_name}.{new}('
            if old_call in content:
                content = content.replace(old_call, new_call)
    
    out_path = outdir + cls_file
    with open(out_path, 'w', encoding='utf-8') as f:
        f.write(content)
    
    print(f"  {cls_file}: {len(renames)} renames")

print(f"\nDone! Fully deobfuscated source in {outdir}")

# Verify
gc_path = outdir + 'GameCanvas.java'
with open(gc_path, 'r') as f:
    gc = f.read()
print(f"\nGameCanvas size: {len(gc)} chars, {gc.count(chr(10))} lines")
keywords = ['gameLoop', 'updateInGame', 'renderInGame', 'updateGameLoop',
            'drawMainMenu', 'updateMainMenu', 'drawLoading', 'updateLoading',
            'drawGameOver', 'updateGameOver', 'drawHUD', 'spawnEnemy',
            'updateParticles', 'initAudio', 'playSound', 'drawOptions',
            'updateOptions', 'drawCredits', 'updateCredits', 'dispatchSwitch']
for kw in keywords:
    print(f"  {kw}: {gc.count(kw)}")
