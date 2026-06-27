import re, json, os

base = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/'
outdir = base + 'fully_deobfuscated_src/'

with open(base + 'obf_deobf/smart_mapping.json', 'r') as f:
    smart = json.load(f)
with open(base + 'obf_deobf/comprehensive_mapping.json', 'r') as f:
    comp = json.load(f)

manual_by_sig = {}
for k, v in comp.items():
    cls = k.split('.')[0]
    rest = k.split('.', 1)[1]
    obf = rest.split('(')[0] if '(' in rest else rest
    sig_key = (cls, obf)
    if sig_key not in manual_by_sig:
        manual_by_sig[sig_key] = []
    manual_by_sig[sig_key].append(v)

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
    occurrence = {}
    for m in matches:
        obf = m.group(2)
        key_prefix = f"{class_name}.{obf}"
        occurrence[key_prefix] = occurrence.get(key_prefix, 0) + 1
        occ_num = occurrence[key_prefix]
        
        smart_key = f"{key_prefix}({m.group(3)[:40]})"
        if smart_key in smart:
            suggested = smart[smart_key]['suggested']
        else:
            suggested = obf
        
        sig_key = (class_name, obf)
        manual_list = manual_by_sig.get(sig_key, [])
        if occ_num <= len(manual_list):
            mv = manual_list[occ_num-1]
            if isinstance(mv, dict):
                suggested = mv.get('suggested', mv)
            else:
                suggested = mv
        
        if suggested != obf:
            renames.append((m.start(), obf, suggested))
    
    # Apply renames
    for start, old, new in reversed(sorted(renames, key=lambda x: x[0])):
        # Only replace at exact position
        if content[start:start+len(old)] == old:
            content = content[:start] + new + content[start+len(old):]
    
    # Qualified calls
    for cls_name in ['GameCanvas', 'GameController', 'AudioManager', 'GraphicsEngine', 
                     'SensorHandler', 'GameData', 'GameConfig', 'GloftMASS']:
        for old, new in [(r[1], r[2]) for r in renames]:
            old_call = f'{cls_name}.{old}('
            new_call = f'{cls_name}.{new}('
            content = content.replace(old_call, new_call)
    
    out_path = outdir + cls_file
    with open(out_path, 'w', encoding='utf-8') as f:
        f.write(content)
    
    print(f"  {cls_file}: {len(renames)} renames")

print(f"\nDone! {outdir}")
