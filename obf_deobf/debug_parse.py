#!/usr/bin/env python3
import struct, zipfile

z = zipfile.ZipFile('fully_method_renamed.jar', 'r')
data = z.read('GraphicsEngine.class')

if len(data) < 8:
    print('too short')
    exit()
magic, minor, major = struct.unpack('>IHH', data[:8])
offset = 8
pool = [None]
while offset < len(data):
    tag = data[offset]; offset += 1
    if tag == 1:
        length = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        s = data[offset:offset+length].decode('utf-8', errors='replace'); offset += length
        pool.append(('Utf8', s))
    elif tag in (3,4):
        pool.append((tag, data[offset:offset+4])); offset += 4
    elif tag in (5,6):
        pool.append((tag, data[offset:offset+8])); offset += 8
        pool.append(None)
    elif tag in (7,8,16,19,20):
        pool.append((tag, struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
    elif tag in (9,10,11,12,17,18):
        pool.append((tag, struct.unpack('>HH', data[offset:offset+4]))); offset += 4
    elif tag == 15:
        pool.append(('MethodHandle', data[offset], struct.unpack('>H', data[offset+1:offset+3])[0])); offset += 3
    else:
        print(f'Unknown tag {tag} at offset {offset-1}, breaking')
        break

print(f'After pool: offset={offset}, pool_len={len(pool)}')

if offset + 6 > len(data):
    print('short before access flags')
    exit()
access_flags, this_class, super_class = struct.unpack('>HHH', data[offset:offset+6]); offset += 6
print(f'access=0x{access_flags:04x} this={this_class} super={super_class}')

if offset + 2 > len(data):
    print('short before interfaces')
    exit()
iface_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2

if offset + 2 > len(data):
    print('short before fields')
    exit()
field_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
print(f'fields={field_count}')

for i in range(field_count):
    if offset + 8 > len(data):
        print(f'short field {i}')
        break
    f_access, f_name_idx, f_desc_idx = struct.unpack('>HHH', data[offset:offset+6]); offset += 6
    attrs_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    for _ in range(attrs_count):
        if offset + 6 > len(data):
            break
        attr_name_idx, attr_len = struct.unpack('>HI', data[offset:offset+6]); offset += 6
        offset += attr_len

if offset + 2 > len(data):
    print('short before methods')
    exit()
method_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
print(f'methods={method_count}')

for i in range(method_count):
    if offset + 6 > len(data):
        print(f'short method {i}')
        break
    m_access, m_name_idx, m_desc_idx = struct.unpack('>HHH', data[offset:offset+6]); offset += 6
    attrs_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    for _ in range(attrs_count):
        if offset + 6 > len(data):
            break
        attr_name_idx, attr_len = struct.unpack('>HI', data[offset:offset+6]); offset += 6
        offset += attr_len

print(f'Done. Final offset={offset}, data len={len(data)}')
