import re, json, os

base = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/'
outdir = base + 'fully_deobfuscated_src/'

# Load all mappings
with open(base + 'obf_deobf/smart_mapping.json', 'r') as f:
    smart = json.load(f)
with open(base + 'obf_deobf/comprehensive_mapping.json', 'r') as f:
    comp = json.load(f)

print(f"Smart mappings: {len(smart)}")
print(f"Comprehensive mappings: {len(comp)}")

# Merge all mappings - smart takes lowest priority, comp higher
final = {}
for k, v in smart.items():
    final[k] = v['suggested']

# Apply comprehensive (overrides smart where manual analysis was done)
for k, v in comp.items():
    if k in final:
        final[k] = v

print(f"Final merged mappings: {len(final)}")

# Now apply to all source files
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
    
    # Build method-specific rename map for this class
    renames = {}
    for key, sug in final.items():
        parts = key.split('.', 1)
        if len(parts) < 2:
            continue
        cls_part = parts[0]
        method_part = parts[1]
        
        if cls_part != class_name:
            continue
        
        # Extract obfuscated name
        if '(' in method_part:
            obf = method_part.split('(')[0]
        else:
            obf = method_part
        
        if sug != obf:
            renames[obf] = sug
    
    # Apply renames - careful about collisions
    content_renamed = content
    for obf, sug in sorted(renames.items(), key=lambda x: -len(x[0])):
        if obf == sug:
            continue
        # Rename declarations: "obf(" -> "sug("
        content_renamed = re.sub(
            r'\b' + re.escape(obf) + r'\s*\(',
            sug + '(',
            content_renamed
        )
        # Rename qualified calls: "ClassName.obf(" -> "ClassName.sug("
        for cls_name in ['GameCanvas', 'GameController', 'AudioManager', 'GraphicsEngine', 
                         'SensorHandler', 'GameData', 'GameConfig', 'GloftMASS']:
            old_call = f'{cls_name}.{obf}('
            new_call = f'{cls_name}.{sug}('
            content_renamed = content_renamed.replace(old_call, new_call)
    
    # Write output
    out_path = outdir + cls_file
    with open(out_path, 'w', encoding='utf-8') as f:
        f.write(content_renamed)
    
    renamed_count = len(renames)
    print(f"  {cls_file}: {renamed_count} renames -> {out_path}")

print(f"\nFully deobfuscated source written to {outdir}")
