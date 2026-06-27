import re, json, os

def load_mapping(path):
    with open(path, 'r') as f:
        return json.load(f)

def rename_java_file(filepath, mapping, class_rename_map):
    with open(filepath, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    
    # Rename class first
    old_class_name = os.path.basename(filepath).replace('.java', '')
    if old_class_name in class_rename_map:
        new_class_name = class_rename_map[old_class_name]
        content = content.replace(f'class {old_class_name}', f'class {new_class_name}')
        content = content.replace(f'{old_class_name}.', f'{new_class_name}.')
    
    # Build method name mapping from our suggestions
    method_renames = {}
    for key, val in mapping.items():
        # key format: "GameCanvas.a(Graphics)" or "GameCanvas.a"
        parts = key.split('.', 1)
        if len(parts) < 2:
            continue
        cls = parts[0]
        method_sig = parts[1]
        
        # Extract method name (before first paren or as-is if no parens)
        if '(' in method_sig:
            obf_name = method_sig.split('(')[0]
            suggested = val['suggested']
        else:
            obf_name = method_sig
            suggested = val['suggested']
        
        if cls == old_class_name and suggested != obf_name:
            method_renames[obf_name] = suggested
    
    print(f"  {old_class_name}: {len(method_renames)} method renames")
    
    # Apply renames - careful to only rename method declarations and calls
    for obf, sug in method_renames.items():
        # Rename method declarations: "obf(" -> "sug("
        content = re.sub(r'\b' + re.escape(obf) + r'\s*\(', sug + '(', content)
        # Rename method calls: ".obf(" -> ".sug("
        content = re.sub(r'\.' + re.escape(obf) + r'\s*\(', '.' + sug + '(', content)
        # Rename qualified calls: "Class.obf(" -> "Class.sug("
        content = re.sub(r'\b[A-Z]\w*\.' + re.escape(obf) + r'\s*\(', lambda m: m.group(0).replace('.' + obf + '(', '.' + sug + '('), content)
    
    return content

# Load mappings
mapping = load_mapping('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/auto_method_mapping.json')
with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/comprehensive_mapping.json', 'r') as f:
    extra = json.load(f)
# Merge comprehensive into auto
for k, v in extra.items():
    if k not in mapping:
        mapping[k] = {'obfuscated': v, 'suggested': v, 'categories': [], 'overload_index': 0, 'return_type': '', 'params': ''}

class_renames = {
    'GameCanvas': 'GameCanvas',
    'GameController': 'GameController',
    'AudioManager': 'AudioManager',
    'GraphicsEngine': 'GraphicsEngine',
    'SensorHandler': 'SensorHandler',
    'GameData': 'GameData',
    'GameConfig': 'GameConfig',
    'GloftMASS': 'GloftMASS',
}

base = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/decompiled/'
outdir = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/deobfuscated_src/'
os.makedirs(outdir, exist_ok=True)

for cls, path in class_renames.items():
    filepath = base + cls + '.java'
    if not os.path.exists(filepath):
        print(f"  Skipping {cls} (not found)")
        continue
    new_content = rename_java_file(filepath, mapping, class_renames)
    out_path = outdir + cls + '.java'
    with open(out_path, 'w', encoding='utf-8') as f:
        f.write(new_content)
    print(f"  Wrote {out_path}")

print("\nDone! Deobfuscated source in deobfuscated_src/")
