import struct
import zipfile
import os

def parse_class_constant_pool(data):
    if len(data) < 10:
        return []
    magic = struct.unpack('>I', data[:4])[0]
    if magic != 0xCAFEBABE:
        return []
    cp_count = struct.unpack('>H', data[8:10])[0]
    offset = 10
    constants = [None]
    i = 1
    while i < cp_count:
        if offset + 1 > len(data):
            break
        tag = data[offset]; offset += 1
        if tag == 7:
            constants.append(('class', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        elif tag == 9:
            constants.append(('field', struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        elif tag == 10:
            constants.append(('method', struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        elif tag == 11:
            constants.append(('imethod', struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        elif tag == 8:
            constants.append(('string', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        elif tag == 3:
            constants.append(('int', struct.unpack('>i', data[offset:offset+4])[0])); offset += 4
        elif tag == 4:
            constants.append(('float', struct.unpack('>f', data[offset:offset+4])[0])); offset += 4
        elif tag == 5:
            constants.append(('long', struct.unpack('>q', data[offset:offset+8])[0])); offset += 8
            constants.append(None); i += 1
        elif tag == 6:
            constants.append(('double', struct.unpack('>d', data[offset:offset+8])[0])); offset += 8
            constants.append(None); i += 1
        elif tag == 12:
            constants.append(('nat', struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        elif tag == 1:
            length = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
            constants.append(('utf8', data[offset:offset+length])); offset += length
        elif tag == 15:
            constants.append(('mh', struct.unpack('>HB', data[offset:offset+3]))); offset += 3
        elif tag == 16:
            constants.append(('mt', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        elif tag == 18:
            constants.append(('id', struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        else:
            break
        i += 1
    return constants

def resolve_utf8(constants, idx):
    while idx is not None and idx < len(constants) and constants[idx] and constants[idx][0] == 'class':
        idx = constants[idx][1]
    while idx is not None and idx < len(constants) and constants[idx] and constants[idx][0] == 'nat':
        idx = constants[idx][1]
    if idx is not None and idx < len(constants) and constants[idx] and constants[idx][0] == 'utf8':
        return constants[idx][1].decode('utf-8', errors='replace')
    return f'<unk:{idx}>'

def resolve_method(constants, idx):
    if idx is None or idx >= len(constants) or not constants[idx]:
        return f'<unk_method:{idx}>'
    c = constants[idx]
    if c[0] != 'method':
        return f'<not_method:{idx}>'
    class_idx, nat_idx = c[1]
    cls = resolve_utf8(constants, class_idx).replace('/', '.')
    name = resolve_utf8(constants, nat_idx)
    desc = resolve_utf8(constants, nat_idx + 1 if False else nat_idx)
    # Wait: nat is (name_index, descriptor_index)
    nat = constants[nat_idx]
    if nat and nat[0] == 'nat':
        name = resolve_utf8(constants, nat[1])
        desc = resolve_utf8(constants, nat[2])
    return f'{cls}.{name}({desc})'

def analyze_class(data, class_name):
    constants = parse_class_constant_pool(data)
    if not constants:
        return
    
    # Find all method names and their internal names (a, b, etc.)
    methods = []
    fields = []
    
    # interfaces_count
    this_class = struct.unpack('>H', data[6:8])[0]
    super_class = struct.unpack('>H', data[8:10])[0]
    
    # access_flags, this_class, super_class are at 6,8,10 after magic/minor/major
    # Actually standard layout: magic(4), minor(2), major(2), cp_count(2), then cp, then access_flags(2), this_class(2), super_class(2)
    # We need to skip constant pool first
    cp_count = struct.unpack('>H', data[8:10])[0]
    offset = 10
    for _ in range(1, cp_count):
        if offset >= len(data):
            break
        tag = data[offset]; offset += 1
        if tag == 7: offset += 2
        elif tag == 9: offset += 4
        elif tag == 10: offset += 4
        elif tag == 11: offset += 4
        elif tag == 8: offset += 2
        elif tag == 3: offset += 4
        elif tag == 4: offset += 4
        elif tag == 5: offset += 8
        elif tag == 6: offset += 8
        elif tag == 12: offset += 4
        elif tag == 1:
            length = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2 + length
        elif tag == 15: offset += 3
        elif tag == 16: offset += 2
        elif tag == 18: offset += 4
        else:
            break
    
    if offset + 8 > len(data):
        return
    access_flags = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    this_class_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    super_class_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    
    # interfaces_count
    if offset + 2 > len(data):
        return
    interfaces_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    for _ in range(interfaces_count):
        if offset + 2 > len(data): break
        offset += 2
    
    # fields_count
    if offset + 2 > len(data):
        return
    fields_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    for _ in range(fields_count):
        if offset + 6 > len(data): break
        f_access = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        f_name_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        f_desc_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        name = resolve_utf8(constants, f_name_idx)
        desc = resolve_utf8(constants, f_desc_idx)
        fields.append((name, desc))
        # attributes_count
        if offset + 2 > len(data): break
        attrs_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        for _ in range(attrs_count):
            if offset + 4 > len(data): break
            attr_name_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
            attr_len = struct.unpack('>I', data[offset:offset+4])[0]; offset += 4
            offset += attr_len
    
    # methods_count
    if offset + 2 > len(data):
        return
    methods_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    
    for _ in range(methods_count):
        if offset + 6 > len(data): break
        m_access = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        m_name_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        m_desc_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        name = resolve_utf8(constants, m_name_idx)
        desc = resolve_utf8(constants, m_desc_idx)
        methods.append((name, desc, m_access))
        # attributes_count
        if offset + 2 > len(data): break
        attrs_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        for _ in range(attrs_count):
            if offset + 4 > len(data): break
            attr_name_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
            attr_len = struct.unpack('>I', data[offset:offset+4])[0]; offset += 4
            offset += attr_len
    
    return methods, fields

with zipfile.ZipFile('deobfuscated.jar', 'r') as z:
    for name in ['GameCanvas.class', 'GameController.class', 'AudioManager.class', 'GraphicsEngine.class']:
        data = z.read(name)
        result = analyze_class(data, name)
        if result:
            methods, fields = result
            print(f"\n=== {name} ===")
            print(f"Methods ({len(methods)}):")
            for m_name, m_desc, m_flags in methods[:20]:
                print(f"  {m_name}({m_desc}) flags=0x{m_flags:04x}")
            if len(methods) > 20:
                print(f"  ... and {len(methods)-20} more")
            print(f"Fields ({len(fields)}):")
            for f_name, f_desc in fields[:20]:
                print(f"  {f_name}: {f_desc}")
            if len(fields) > 20:
                print(f"  ... and {len(fields)-20} more")
