import re, os, json

def analyze_java_file(filepath):
    with open(filepath, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()
    
    methods = []
    current_method = None
    method_body = []
    in_method = False
    brace_depth = 0
    
    lines = content.split('\n')
    i = 0
    while i < len(lines):
        line = lines[i]
        stripped = line.strip()
        
        # Match method signature (with any access modifier, static, return type, name, params)
        m = re.match(r'^(public|private|protected|static|\s)*\s*(?:static\s+)?(\w+)\s+(\w+)\s*\(([^)]*)\)\s*\{?\s*$', stripped)
        if m and not stripped.startswith('//') and not stripped.startswith('/*'):
            # Could be a method start
            # Check if it looks like a method (return type is not a control flow keyword)
            return_type = m.group(2)
            name = m.group(3)
            params = m.group(4)
            if return_type not in ('if', 'while', 'for', 'switch', 'catch', 'synchronized'):
                # Save previous method
                if current_method:
                    current_method['body'] = '\n'.join(method_body)
                    current_method['strings'] = extract_strings(current_method['body'])
                    current_method['called_methods'] = extract_called_methods(current_method['body'], current_method['name'])
                    methods.append(current_method)
                
                current_method = {
                    'name': name,
                    'return_type': return_type,
                    'params': params,
                    'line': i + 1,
                    'body': '',
                    'strings': [],
                    'called_methods': []
                }
                method_body = []
                in_method = True
                brace_depth = stripped.count('{') - stripped.count('}')
                i += 1
                continue
        
        if in_method:
            method_body.append(line)
            brace_depth += line.count('{') - line.count('}')
            if brace_depth <= 0 and '{' not in ''.join(method_body[:3]):
                # Method with no braces (single line or abstract)
                in_method = False
            elif brace_depth <= 0:
                in_method = False
        
        i += 1
    
    # Save last method
    if current_method:
        current_method['body'] = '\n'.join(method_body)
        current_method['strings'] = extract_strings(current_method['body'])
        current_method['called_methods'] = extract_called_methods(current_method['body'], current_method['name'])
        methods.append(current_method)
    
    return methods

def extract_strings(text):
    strings = re.findall(r'"([^"\\]*(?:\\.[^"\\]*)*)"', text)
    return list(set(strings))

def extract_called_methods(text, current_name):
    # Find calls like ClassName.method() or obj.method() or this.method()
    calls = set()
    # Method calls with dot notation
    for m in re.finditer(r'\.(\w+)\s*\(', text):
        calls.add(m.group(1))
    # Static calls like ClassName.method()
    for m in re.finditer(r'(\w+)\.(\w+)\s*\(', text):
        calls.add(f"{m.group(1)}.{m.group(2)}")
    return list(calls)

def infer_semantic_name(method, class_name):
    name = method['name']
    return_type = method['return_type']
    params = method['params']
    strings = method['strings']
    called = method['called_methods']
    
    sig = f"{return_type} {name}({params})"
    hints = []
    
    # String-based hints
    for s in strings:
        sl = s.lower()
        if 'warning' in sl:
            hints.append('warning/log')
        if 'draw' in sl or 'image' in sl:
            hints.append('draw/render')
        if 'audio' in sl or 'player' in sl or 'volume' in sl or 'sound' in sl or 'midi' in sl or 'wav' in sl or 'amr' in sl:
            hints.append('audio')
        if 'enemy' in sl or 'zombie' in sl or 'npc' in sl or 'spawn' in sl or 'health' in sl:
            hints.append('enemy/npc')
        if 'url' in sl or 'http' in sl or 'web' in sl or 'browser' in sl:
            hints.append('network/url')
        if 'rms' in sl or 'record' in sl or 'store' in sl:
            hints.append('persistence')
        if 'touch' in sl or 'key' in sl or 'input' in sl or 'click' in sl:
            hints.append('input')
        if 'particle' in sl or 'focus' in sl:
            hints.append('particles')
        if 'sensor' in sl or 'accelerometer' in sl:
            hints.append('sensor')
        if 'font' in sl or 'text' in sl or 'string' in sl:
            hints.append('text/font')
        if 'weapon' in sl or 'bullet' in sl or 'shot' in sl or 'ammo' in sl:
            hints.append('weapon')
        if 'menu' in sl or 'hud' in sl or 'ui' in sl or 'score' in sl or 'health' in sl:
            hints.append('ui')
        if 'level' in sl or 'map' in sl or 'tile' in sl or 'sprite' in sl:
            hints.append('level/map')
        if 'start' in sl or 'init' in sl or 'load' in sl:
            hints.append('initialization')
    
    # Call-based hints
    for c in called:
        cl = c.lower()
        if 'drawimage' in cl or 'fillrect' in cl or 'drawstring' in cl or 'drawregion' in cl:
            hints.append('draw/render')
        if 'createplayer' in cl or 'start' in cl and 'player' in cl:
            hints.append('audio')
        if 'sensormanager' in cl or 'accelerometer' in cl:
            hints.append('sensor')
        if 'recordstore' in cl or 'rms' in cl:
            hints.append('persistence')
        if 'platformrequest' in cl:
            hints.append('network/url')
    
    # Signature-based hints
    if return_type == 'boolean' and 'image' in params.lower():
        hints.append('image-check')
    if return_type == 'void' and 'graphics' in params.lower():
        hints.append('render')
    if return_type == 'int' and 'audio' in params.lower():
        hints.append('audio-index')
    
    # Conflict resolution for GameCanvas vs GameController signature ambiguity
    # Methods that take AudioManager[] are likely audio init
    if 'AudioManager[]'.lower() in params.lower() or 'audioManager[]'.lower() in params.lower():
        hints.append('audio-array')
    
    return list(set(hints))

# Main analysis
gamecanvas_path = '/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/decompiled/GameCanvas.java'
methods = analyze_java_file(gamecanvas_path)

print(f"Total methods found in GameCanvas: {len(methods)}")
print("\n=== Methods with semantic hints ===\n")

for m in methods:
    hints = infer_semantic_name(m, 'GameCanvas')
    if hints:
        params_short = m['params'][:60] + '...' if len(m['params']) > 60 else m['params']
        print(f"  {m['return_type']:12s} {m['name']}({params_short[:40]})")
        print(f"    Line: {m['line']}")
        print(f"    Strings: {m['strings'][:5]}")
        print(f"    Hints: {hints}")
        print()

# Save to JSON
with open('/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/obf_deobf/gamecanvas_analysis.json', 'w') as f:
    json.dump(methods, f, indent=2, default=str)

print(f"\nSaved analysis to gamecanvas_analysis.json")
