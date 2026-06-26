import struct
import zipfile

def parse_cp(data):
    cp_count = struct.unpack('>H', data[8:10])[0]
    offset = 10
    constants = [None]
    i = 1
    while i < cp_count:
        if offset + 1 > len(data): break
        tag = data[offset]; offset += 1
        if tag == 7:
            constants.append(('class', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        elif tag in (9,10,11):
            constants.append((tag, struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        elif tag == 8:
            constants.append(('string', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        elif tag in (3,4):
            constants.append((tag, None)); offset += 4
        elif tag in (5,6):
            constants.append((tag, None)); offset += 8; constants.append(None); i += 1
        elif tag == 12:
            constants.append(('nat', struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        elif tag == 1:
            length = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
            constants.append(('utf8', data[offset:offset+length])); offset += length
        elif tag == 15:
            constants.append(('mh', None)); offset += 3
        elif tag == 16:
            constants.append(('mt', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        elif tag == 18:
            constants.append(('id', struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        else:
            break
        i += 1
    return constants

def resolve_utf8(constants, idx):
    while idx is not None and idx < len(constants) and constants[idx]:
        c = constants[idx]
        if c[0] == 'utf8':
            return c[1].decode('utf-8', errors='replace')
        elif c[0] == 'nat':
            idx = c[1]
        elif c[0] == 'class':
            idx = c[1]
        else:
            break
    return f'<unk:{idx}>'

def resolve_method(constants, idx):
    if idx is None or idx >= len(constants) or not constants[idx]:
        return f'<unk_method:{idx}>'
    c = constants[idx]
    if c[0] not in (10,):  # Methodref
        return f'<not_method:{idx}>'
    class_idx, nat_idx = c[1]
    cls = resolve_utf8(constants, class_idx).replace('/', '.')
    nat = constants[nat_idx]
    if nat and nat[0] == 'nat':
        name = resolve_utf8(constants, nat[1])
        desc = resolve_utf8(constants, nat[2])
    else:
        name = desc = '<error>'
    return f'{cls}.{name}({desc})'

with zipfile.ZipFile('deobfuscated.jar', 'r') as z:
    data = z.read('GameCanvas.class')
    constants = parse_cp(data)
    
    # Parse methods
    cp_count = struct.unpack('>H', data[8:10])[0]
    offset = 10
    for _ in range(1, cp_count):
        if offset >= len(data): break
        tag = data[offset]; offset += 1
        if tag == 7: offset += 2
        elif tag in (9,10,11): offset += 4
        elif tag == 8: offset += 2
        elif tag in (3,4): offset += 4
        elif tag in (5,6): offset += 8
        elif tag == 12: offset += 4
        elif tag == 1:
            length = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2 + length
        elif tag == 15: offset += 3
        elif tag == 16: offset += 2
        elif tag == 18: offset += 4
        else: break
    
    offset += 8  # access_flags, this_class, super_class
    if offset + 2 > len(data): exit(1)
    interfaces_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    offset += 2 * interfaces_count
    offset += 2  # fields_count
    # skip fields
    fields_count = struct.unpack('>H', data[offset-2:offset])[0]
    for _ in range(fields_count):
        offset += 6
        attrs_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        for _ in range(attrs_count):
            offset += 2
            attr_len = struct.unpack('>I', data[offset:offset+4])[0]; offset += 4 + attr_len
    
    methods_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    
    methods = []
    for _ in range(methods_count):
        m_access = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        m_name_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        m_desc_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        name = resolve_utf8(constants, m_name_idx)
        desc = resolve_utf8(constants, m_desc_idx)
        
        attrs_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        has_code = False
        for _ in range(attrs_count):
            attr_name_idx = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
            attr_len = struct.unpack('>I', data[offset:offset+4])[0]; offset += 4
            attr_name = resolve_utf8(constants, attr_name_idx)
            if attr_name == 'Code':
                has_code = True
            offset += attr_len
        
        methods.append((name, desc, m_access, has_code))
    
    # Sort by name and print
    print(f"GameCanvas: {len(methods)} methods")
    print("\nBy signature pattern:")
    
    # Group by first letter and signature shape
    by_desc = {}
    for name, desc, flags, has_code in methods:
        key = f"{name}({desc})"
        if key not in by_desc:
            by_desc[key] = []
        by_desc[key].append(('static' if flags & 0x0008 else 'instance', has_code))
    
    for key in sorted(by_desc.keys()):
        info = by_desc[key]
        static_flag = info[0][0]
        has_code = info[0][1]
        print(f"  {static_flag} {key} code={has_code}")
