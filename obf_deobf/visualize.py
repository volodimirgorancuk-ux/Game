#!/usr/bin/env python3
"""Visualize Gameloft tile maps and sprites to PNG"""
import struct, os, sys
sys.path.insert(0, os.path.dirname(__file__))
from png_writer import write_png, write_palette_png

# Gameloft palette decoding from GraphicsEngine
PALETTE_MAGIC = {
    -30584: 0,   # 32-bit RGBA
    17476: 1,    # 16-bit RGB 4444 expanded
    25861: 2,    # 16-bit RGB 565 expanded
    25840: 3,    # 8-bit indexed
    10225: 4,    # RLE indexed
    22258: 5,    # RLE indexed v2
    5632: 6,     # 4-bit planar
    2048: 7,     # 6-bit planar
    1024: 8,     # 2-bit
    512: 9,      # 1-bit
    22018: 10,   # raw 8-bit
}

def decode_palette(data, offset, count):
    """Decode palette entry at given offset"""
    magic = struct.unpack_from('<H', data, offset)[0]
    if magic == 0xFFFF:
        # Skip marker
        return None, offset + 2
    
    mode = PALETTE_MAGIC.get(magic, -1)
    
    if mode == 0:  # 32-bit RGBA
        palette = []
        for i in range(count):
            r, g, b, a = data[offset+2:offset+6]
            palette.append((r, g, b))
            offset += 4
        return palette, offset + 2
    
    elif mode == 2:  # 16-bit RGB 565
        palette = []
        for i in range(count):
            val = struct.unpack_from('<H', data, offset)[0]
            r = (val >> 8) & 0xF8
            g = (val >> 3) & 0xFC
            b = (val << 3) & 0xF8
            palette.append((r, g, b))
            offset += 2
        return palette, offset + 2
    
    elif mode == 3:  # 8-bit indexed
        palette = []
        for i in range(count):
            val = data[offset + 2 + i]
            r = (val >> 5) * 8
            g = ((val >> 2) & 0x07) * 8
            b = (val & 0x03) * 0x55
            palette.append((r, g, b))
        return palette, offset + 2 + count
    
    return None, offset + 2

def render_tilemap_8bit(filename, width, height, tile_size, data, palette):
    """Render 8-bit indexed tile map to RGB PNG"""
    rgb = []
    tiles_per_row = width // tile_size
    for y in range(height):
        for x in range(width):
            tile_idx = (y // tile_size) * tiles_per_row + (x // tile_size)
            if tile_idx < len(data):
                color_idx = data[tile_idx] % len(palette)
                rgb.extend(palette[color_idx])
            else:
                rgb.extend((0, 0, 0))
    base = os.path.splitext(filename)[0]
    write_palette_png(f"{base}_{width}x{height}.png", width, height, data, palette)

def guess_tile_dims(size):
    """Guess common tilemap dimensions"""
    for w, h in [(240, 320), (320, 240), (120, 160), (160, 120), (80, 100), (100, 80)]:
        if size == w * h:
            return w, h
        if size == w * h * 2:
            return w, h
    return None, None

if __name__ == '__main__':
    import sys
    if len(sys.argv) < 2:
        print("Usage: visualize.py <file.bin> [width height tile_size]")
        sys.exit(1)
    
    path = sys.argv[1]
    with open(path, 'rb') as f:
        data = f.read()
    
    # Default grayscale palette
    palette = [(i, i, i) for i in range(256)]
    
    if len(sys.argv) >= 4:
        w, h = int(sys.argv[2]), int(sys.argv[3])
    else:
        w, h = guess_tile_dims(len(data))
        if w is None:
            print(f"Cannot guess dimensions for {len(data)} bytes")
            sys.exit(1)
    
    tile_size = int(sys.argv[4]) if len(sys.argv) >= 5 else 8
    
    if len(data) >= w * h:
        render_tilemap_8bit(path, w, h, tile_size, data[:w*h], palette)
        print(f"Rendered {w}x{h} tilemap")
    else:
        print(f"Data too short: {len(data)} < {w*h}")
