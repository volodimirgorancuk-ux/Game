#!/usr/bin/env python3
import struct, zipfile

z = zipfile.ZipFile('fully_method_renamed.jar', 'r')
data = z.read('GraphicsEngine.class')

offset = 8
pool = [None]
i = 0
while offset < len(data):
    tag = data[offset]; offset += 1
    i += 1
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

print(f'Pool size: {len(pool)}')
print(f'Offset after pool: {offset}')
print(f'Remaining: {len(data) - offset}')
