#!/usr/bin/env python3
"""Debug sprite decoder - print every step."""
import struct

def debug_sprite(path):
    data = open(path, 'rb').read()
    print(f'File: {path}, size: {len(data)}')
    n = 0
    print(f'byte[0] = {data[0]:02x} (version skip)')
    n += 1
    n11 = n + 1; n += 1
    n12 = n + 1; n += 1
    n13 = n + 1; n += 1
    n14 = n + 1; n += 1
    flags = data[n11] | (data[n12] << 8) | (data[n13] << 16) | (data[n14] << 24)
    print(f'flags = 0x{flags:08x} at bytes {n11}-{n14}')
    n = n14 + 1
    print(f'n after flags = {n}')
    n15 = n; n += 1
    n16 = n; n += 1
    n += 1
    frame_count = data[n15] | (data[n16] << 8)
    print(f'frame_count = {frame_count} at bytes {n15}-{n16}')
    print(f'n after frame_count = {n}')
    
    for i in range(min(frame_count, 20)):
        print(f'--- Frame {i} at offset {n} ---')
        if n >= len(data):
            print(f'  OUT OF DATA at frame {i}')
            break
        type_byte = data[n] & 0xFF
        print(f'  type_byte = 0x{type_byte:02x}')
        n += 1
        if type_byte in (255, 254):
            if n + 6 > len(data):
                print(f'  SHORT in ext frame {i}: need 6 bytes, have {len(data)-n}')
                break
            param = data[n] | (data[n+1] << 8) | (data[n+2] << 16) | (data[n+3] << 24); n += 4
            count = data[n] & 0xFF; n += 1
            flag = data[n] & 0xFF; n += 1
            print(f'  EXT: param=0x{param:08x}, count={count}, flag=0x{flag:02x}')
        else:
            if n + 7 > len(data):
                print(f'  SHORT in frame {i}: need 7 bytes, have {len(data)-n}')
                break
            x = data[n] | (data[n+1] << 8); n += 2
            y = data[n] | (data[n+1] << 8); n += 2
            w = data[n] | (data[n+1] << 8); n += 2
            flag = data[n] | (data[n+1] << 8); n += 2
            print(f'  NORMAL: x={x}, y={y}, w={w}, flag=0x{flag:04x}')
    
    print(f'\n--- After frames, n={n}, remaining={len(data)-n} ---')
    if n >= len(data):
        print('No more data for tables')
        return
    
    n8 = data[n] | (data[n+1] << 8); n += 2
    print(f'table2 count = {n8}')
    if n + n8 * 6 > len(data):
        print(f'  SHORT for table2: need {n8*6}, have {len(data)-n}')
        return
    for i in range(min(n8, 10)):
        b0 = data[n]; n += 1
        s0 = data[n] | (data[n+1] << 8); n += 2
        s1 = data[n] | (data[n+1] << 8); n += 2
        b1 = data[n]; n += 1
        print(f'  table2[{i}]: b0=0x{b0:02x}, s0={s0}, s1={s1}, b1=0x{b1:02x}')
    if n8 > 10:
        print(f'  ... and {n8-10} more entries')
    
    print(f'\nAfter table2, n={n}, remaining={len(data)-n}')
    if n >= len(data):
        print('No more data for table3')
        return
    
    n4 = data[n] | (data[n+1] << 8); n += 2
    print(f'table3 count = {n4}')
    if n + n4 * 6 > len(data):
        print(f'  SHORT for table3: need {n4*6}, have {len(data)-n}')
        return
    for i in range(min(n4, 5)):
        b0 = data[n]; n += 1
        s0 = data[n] | (data[n+1] << 8); n += 2
        print(f'  table3[{i}]: b0=0x{b0:02x}, s0={s0}')
    if n4 > 5:
        print(f'  ... and {n4-5} more entries')
    
    print(f'\nAfter table3, n={n}, remaining={len(data)-n}')
    if n + 2 > len(data):
        print('No data for palette format')
        return
    
    n10 = data[n] | (data[n+1] << 8); n += 2
    print(f'palette_format magic = {n10} (0x{n10:04x})')
    if n + 2 > len(data):
        print('No data for palette size')
        return
    colors_per_pal = data[n] & 0xFF; n += 1
    pal_size = data[n] & 0xFF; n += 1
    print(f'colors_per_pal = {colors_per_pal}, pal_size = {pal_size}')
    if pal_size == 0:
        pal_size = 256
    print(f'Expected palette data: {colors_per_pal * pal_size * 2} bytes, remaining={len(data)-n}')

if __name__ == '__main__':
    import sys
    path = sys.argv[1] if len(sys.argv) > 1 else 'obf_deobf/decoded/m10_chunk02.sprite'
    debug_sprite(path)
