import struct, sys, os

def decode_gameloft_graphic(data):
    if len(data) < 8:
        return {'error': 'too short'}
    n = 0
    n += 1  # ++n
    n11 = n + 1; n += 1
    n12 = n + 1; n += 1
    n13 = n + 1; n += 1
    n14 = n + 1; n += 1
    flags = data[n11] | (data[n12] << 8) | (data[n13] << 16) | (data[n14] << 24)
    if n + 4 > len(data):
        return {'flags': flags, 'error': 'short after flags'}
    n15 = n + 1; n += 1
    n16 = n + 1; n += 1
    n += 1
    frame_count = data[n15] | (data[n16] << 8)
    frames = []
    for i in range(frame_count):
        if n >= len(data):
            return {'flags': flags, 'frames': frames, 'error': f'short in frame {i}'}
        type_byte = data[n] & 0xFF; n += 1
        if type_byte in (255, 254):
            if n + 6 > len(data):
                return {'flags': flags, 'frames': frames, 'error': f'short in ext frame {i}'}
            param = data[n] | (data[n+1] << 8) | (data[n+2] << 16) | (data[n+3] << 24); n += 4
            count = data[n] & 0xFF; n += 1
            flag = data[n] & 0xFF; n += 1
            frames.append({'type': type_byte, 'param': param, 'count': count, 'flag': flag})
        else:
            if n + 7 > len(data):
                return {'flags': flags, 'frames': frames, 'error': f'short in frame {i}'}
            x = data[n] | (data[n+1] << 8); n += 2
            y = data[n] | (data[n+1] << 8); n += 2
            w = data[n] | (data[n+1] << 8); n += 2
            flag = data[n] | (data[n+1] << 8); n += 2
            frames.append({'type': 0, 'x': x, 'y': y, 'w': w, 'flag': flag})
    if n >= len(data):
        return {'flags': flags, 'frames': frames, 'error': 'short before table2'}
    n8 = data[n] | (data[n+1] << 8); n += 2
    table2 = []
    for i in range(n8):
        if n + 6 > len(data):
            return {'flags': flags, 'frames': frames, 'error': f'short in table2 {i}'}
        b0 = data[n]; n += 1
        s0 = data[n] | (data[n+1] << 8); n += 2
        s1 = data[n] | (data[n+1] << 8); n += 2
        b1 = data[n]; n += 1
        table2.append((b0, s0, s1, b1))
    if n >= len(data):
        return {'flags': flags, 'frames': frames, 'table2': table2, 'error': 'short before table3'}
    n4 = data[n] | (data[n+1] << 8); n += 2
    table3 = []
    for i in range(n4):
        if n + 6 > len(data):
            return {'flags': flags, 'frames': frames, 'table2': table2, 'error': f'short in table3 {i}'}
        b0 = data[n]; n += 1
        s0 = data[n] | (data[n+1] << 8); n += 2
        table3.append((b0, s0))
    if n + 2 > len(data):
        return {'flags': flags, 'frames': frames, 'table2': table2, 'table3': table3,
                'error': 'short before format'}
    n10 = data[n] | (data[n+1] << 8); n += 2
    if n + 2 > len(data):
        return {'error': 'short before pal size'}
    colors_per_pal = data[n] & 0xFF; n += 1
    pal_size = data[n] & 0xFF; n += 1
    if pal_size == 0:
        pal_size = 256
    palette_format = None
    palettes = []
    if n10 == -30584:
        fmt = 'argb8888'
        for p in range(colors_per_pal):
            col = []
            for k in range(pal_size):
                if n + 4 > len(data):
                    return {'error': f'short in palette {p} color {k}'}
                b = data[n] & 0xFF; n += 1
                g = data[n] & 0xFF; n += 1
                r = data[n] & 0xFF; n += 1
                a = data[n] & 0xFF; n += 1
                col.append((r, g, b, a))
            palettes.append(col)
        palette_format = fmt
    elif n10 == 17476:
        fmt = 'rgb565dup'
        for p in range(colors_per_pal):
            col = []
            for k in range(pal_size):
                if n + 2 > len(data):
                    return {'error': f'short in palette {p} color {k}'}
                val = (data[n] & 0xFF) | ((data[n+1] & 0xFF) << 8); n += 2
                r = ((val >> 11) & 0x1F) * 255 // 31
                g = ((val >> 5) & 0x3F) * 255 // 63
                b = (val & 0x1F) * 255 // 31
                r = (r << 4) | (r & 0xF)
                g = (g << 2) | (g & 0x3)
                b = (b << 4) | (b & 0xF)
                col.append((r, g, b, 255))
            palettes.append(col)
        palette_format = fmt
    elif n10 == 25861:
        fmt = 'rgb565a1'
        for p in range(colors_per_pal):
            col = []
            for k in range(pal_size):
                if n + 2 > len(data):
                    return {'error': f'short in palette {p} color {k}'}
                val = (data[n] & 0xFF) | ((data[n+1] & 0xFF) << 8); n += 2
                r = ((val >> 11) & 0x1F) * 255 // 31
                g = ((val >> 5) & 0x3F) * 255 // 63
                b = (val & 0x1F) * 255 // 31
                a = 0 if val == 0xF81F else 255
                col.append((r, g, b, a))
            palettes.append(col)
        palette_format = fmt
    else:
        fmt = f'unknown_{n10}'
    if n + 2 > len(data):
        return {'flags': flags, 'frames': frames, 'format': palette_format, 'palettes': palettes,
                'error': 'short before magic'}
    magic = data[n] | (data[n+1] << 8); n += 2
    transparency_mask_bits = 0
    if magic == 25840:
        h_bits = pal_size - 1
        j_mask = 1
        while h_bits:
            h_bits >>= 1
            j_mask <<= 1
        j_mask -= 1
        transparency_mask_bits = j_mask
    if n + 2 > len(data):
        return {'flags': flags, 'frames': frames, 'format': palette_format, 'palettes': palettes,
                'magic': magic, 'transparency_mask': transparency_mask_bits,
                'error': 'short before image table'}
    img_chunks = data[n] | (data[n+1] << 8); n += 2
    offsets = []
    sizes = []
    for i in range(img_chunks):
        if n + 4 > len(data):
            return {'error': f'short in image chunk entry {i}'}
        off = data[n] | (data[n+1] << 8); n += 2
        sz = data[n] | (data[n+1] << 8); n += 2
        offsets.append(off)
        sizes.append(sz)
    pixel_data = data[n:]
    return {
        'flags': flags,
        'frames': frames,
        'table2': table2,
        'table3': table3,
        'format': palette_format,
        'palettes': palettes,
        'magic': magic,
        'transparency_mask': transparency_mask_bits,
        'img_chunks': img_chunks,
        'offsets': offsets,
        'sizes': sizes,
        'pixel_data': pixel_data,
        'error': None,
    }

if __name__ == '__main__':
    base = 'obf_deobf/decoded'
    for fname in sorted(os.listdir(base)):
        if not (fname.endswith('.sprite') or fname.endswith('.tile')):
            continue
        path = os.path.join(base, fname)
        data = open(path, 'rb').read()
        res = decode_gameloft_graphic(data)
        err = res.get('error')
        if err:
            print(f'{fname}: {err}')
            continue
        fc = len(res['frames'])
        f0 = res['frames'][0] if res['frames'] else {}
        w = f0.get('w', '?')
        h = f0.get('h', f0.get('flag', '?'))  # note: original stores w then flags as height?
        print(f'{fname}: flags=0x{res["flags"]:08x} frames={fc} format={res["format"]} palettes={len(res["palettes"])} img_chunks={res["img_chunks"]}')
        if 'x' in f0:
            print(f'  frame0: x={f0["x"]} y={f0["y"]} w={f0["w"]} flag=0x{f0["flag"]:04x}')
        if res['palettes']:
            pal = res['palettes'][0]
            print(f'  pal0 first 5 colors: {pal[:5]}')
        print()
