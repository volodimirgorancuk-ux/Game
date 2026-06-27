import re, json, os

base = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/'
outdir = base + 'fully_deobfuscated_src/'

with open(base + 'obf_deobf/final_mapping.json', 'r') as f:
    final_map = json.load(f)

print(f"Loaded {len(final_map)} mappings")

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
    for m in matches:
        obf_name = m.group(2)
        params = m.group(3)[:50]
        sig_key = f"{class_name}.{obf_name}({params})"
        
        if sig_key in final_map:
            sug = final_map[sig_key]
            if sug != obf_name:
                renames.append((m.start(), obf_name, sug))
    
    # Apply renames using string replacement
    rename_dict = {old: sug for _, old, sug in renames}
    new_lines = []
    for line in content.split('\n'):
        new_line = line
        for old, sug in rename_dict.items():
            new_line = re.sub(r'\b' + re.escape(old) + r'\s*\(', sug + '(', new_line)
            for cn in ['GameCanvas', 'GameController', 'AudioManager', 'GraphicsEngine', 
                       'SensorHandler', 'GameData', 'GameConfig', 'GloftMASS']:
                new_line = new_line.replace(f'{cn}.{old}(', f'{cn}.{sug}(')
        new_lines.append(new_line)
    
    content = '\n'.join(new_lines)
    
    out_path = outdir + cls_file
    with open(out_path, 'w', encoding='utf-8') as f:
        f.write(content)
    
    print(f"  {cls_file}: {len(renames)} renames")

print(f"\nDone! {outdir}")
gc_path = outdir + 'GameCanvas.java'
with open(gc_path, 'r') as f:
    gc = f.read()
keywords = ['gameLoop', 'updateInGame', 'drawMainMenu', 'updateMainMenu', 
            'spawnEnemy', 'triggerDamageFlash', 'isPlayerDead', 'createOffscreenBuffer']
for kw in keywords:
    print(f"  {kw}: {gc.count(kw)}")
