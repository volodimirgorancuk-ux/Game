import re, json

with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/smart_mapping_v3.json', 'r') as f:
    smart = json.load(f)

with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/comprehensive_mapping.json', 'r') as f:
    comp = json.load(f)

# Parse mappings from mapping_methods.txt (format: "suggested -> obfuscated")
manual_by_name = {}
with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/mapping_methods.txt', 'r') as f:
    for line in f:
        line = line.strip()
        if not line or line.startswith('#'):
            continue
        if ' -> ' in line:
            parts = line.split(' -> ')
            if len(parts) == 2:
                suggested = parts[0].strip()
                obf_name = parts[1].strip()
                manual_by_name[obf_name] = suggested

print(f"Smart: {len(smart)}")
print(f"Comprehensive: {len(comp)}")
print(f"Manual by name: {len(manual_by_name)}")

# Start with smart
final = {}
for k, v in smart.items():
    final[k] = v['suggested']

# Apply comprehensive overrides (30 matches)
comp_applied = 0
for k, v in comp.items():
    if k in final:
        if final[k] != v:
            comp_applied += 1
        final[k] = v
print(f"Comprehensive applied: {comp_applied}")

# Apply manual name-based mappings
manual_applied = 0
for obf_name, suggested in manual_by_name.items():
    # Skip non-identifier names
    if not re.match(r'^[A-Za-z_]\w*$', suggested):
        continue
    for existing_key in list(final.keys()):
        base = existing_key.split('(')[0].split('.')[-1]
        if base == obf_name:
            final[existing_key] = suggested
            manual_applied += 1

print(f"Manual applied: {manual_applied}")
print(f"Final count: {len(final)}")

cats = {}
for v in final.values():
    cat = v.split('_')[0] if '_' in v else v
    cats[cat] = cats.get(cat, 0) + 1

print("\nTop categories:")
for c, n in sorted(cats.items(), key=lambda x: x[1], reverse=True)[:15]:
    print(f"  {c}: {n}")

with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/final_mapping.json', 'w') as f:
    json.dump(final, f, indent=2)

print("\nSaved final_mapping.json")
