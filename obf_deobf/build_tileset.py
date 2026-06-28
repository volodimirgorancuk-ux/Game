#!/usr/bin/env python3
"""Build tileset_sheet.png by packing decoded tiles/ into a grid."""
import os, sys
sys.path.insert(0, os.path.dirname(__file__))
from png_writer import write_png

TILE_DIR = os.path.join(os.path.dirname(__file__), 'decoded', 'tiles')
OUTPUT = os.path.join(os.path.dirname(__file__), 'decoded', 'tileset_sheet.png')

TILE_W, TILE_H = 16, 16   # as measured for tile_*.png
GRID_COLS, GRID_ROWS = 16, 16   # 256 tiles total

def read_png_pixels(path):
    """Decode PNG (8-bit RGB) and return width, height, flat list of bytes (R,G,B,...)."""
    import zlib, struct
    def read_chunk(f):
        length = struct.unpack('>I', f.read(4))[0]
        chunk_type = f.read(4)
        data = f.read(length)
        crc = f.read(4)
        return chunk_type, data
    with open(path, 'rb') as f:
        sig = f.read(8)
        assert sig == b'\x89PNG\r\n\x1a\n'
        ihdr = None
        idat_data = b''
        while True:
            chunk_type, data = read_chunk(f)
            if chunk_type == b'IHDR':
                ihdr = data
            elif chunk_type == b'IDAT':
                idat_data += data
            elif chunk_type == b'IEND':
                break
        width, height, bit_depth, color_type = struct.unpack('>IIBB', ihdr[:10])
        assert width == TILE_W and height == TILE_H, f'{path} is {width}x{h}, expected {TILE_W}x{TILE_H}'
        raw = zlib.decompress(idat_data)
        pixels = []
        offset = 0
        for y in range(height):
            offset += 1  # skip filter byte
            row = raw[offset:offset+width*3]; offset += width*3
            pixels.extend(row)
        return list(pixels)

def main():
    if not os.path.isdir(TILE_DIR):
        print('Tiles directory not found:', TILE_DIR)
        sys.exit(1)

    sheet_w = GRID_COLS * TILE_W
    sheet_h = GRID_ROWS * TILE_H
    sheet_rgb = [0] * (sheet_w * sheet_h * 3)

    for idx in range(GRID_COLS * GRID_ROWS):
        fname = os.path.join(TILE_DIR, f'tile_{idx:03d}.png')
        if not os.path.exists(fname):
            print(f'Missing tile: tile_{idx:03d}.png')
            continue
        tile_rgb = read_png_pixels(fname)
        # position in sheet
        col = idx % GRID_COLS
        row = idx // GRID_COLS
        for ty in range(TILE_H):
            for tx in range(TILE_W):
                dest_x = col * TILE_W + tx
                dest_y = row * TILE_H + ty
                src_pos = (ty * TILE_W + tx) * 3
                dest_pos = (dest_y * sheet_w + dest_x) * 3
                sheet_rgb[dest_pos:dest_pos+3] = tile_rgb[src_pos:src_pos+3]

    write_png(OUTPUT, sheet_w, sheet_h, sheet_rgb)
    print(f'Wrote {OUTPUT} ({sheet_w}x{sheet_h})')

if __name__ == '__main__':
    main()
