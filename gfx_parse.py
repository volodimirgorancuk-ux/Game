#!/usr/bin/env python3
"""Try to parse m* files using the Gameloft GFX format from GameloftGfx.cpp loadData."""
import struct, sys, os

SPRITES_DIR = '/tmp/agent_0b3a0086-9857-4cd3-a54d-432e4c44f289/sprites'

def get_short(data, idx):
    """Read 16-bit LE, advance idx."""
    v = data[idx] | (data[idx+1] << 8)
    return v, idx + 2

def get_int(data, idx):
    """Read 32-bit LE, advance idx."""
    v = data[idx] | (data[idx+1] << 8) | (data[idx+2] << 16) | (data[idx+3] << 24)
    return v, idx + 4

# Palette format constants
ARGB8888 = 0x8888
ARGB4444 = 0x4444
ARGB1555 = 0x5515
RGB565 = 0x6505

# Bit depth constants
BIT_DEPTH_8 = 0x5602
BIT_DEPTH_4 = 0x1600
BIT_DEPTH_2 = 0x0400
BIT_DEPTH_1 = 0x0200
RLE_56F2 = 0x56F2
RLE_27F1 = 0x27F1

def load_data(data, index=0):
    """Parse Gameloft GFX sprite data."""
    version, index = get_short(data, index)
    print(f"  version: 0x{version:04x} ({version})")
    
    sprite_version = 0
    if version == 0x3DF:
        sprite_version = 3
        print("  -> Sprite version 3")
    index += 4  # Skip flags
    
    # Load modules
    num_modules, index = get_short(data, index)
    print(f"  numModules: {num_modules}")
    modules = []
    if num_modules != 0:
        end_index = index + num_modules * 2
        while index < end_index and index < len(data):
            w = data[index]; index += 1
            h = data[index]; index += 1
            modules.append((w, h))
            if index >= len(data):
                break
    
    # Load frame modules
    num_frame_modules, index = get_short(data, index)
    print(f"  numFrameModules: {num_frame_modules}")
    frame_modules = []
    if num_frame_modules != 0 and index < len(data):
        end_index = index + num_frame_modules * 4
        while index < end_index and index < len(data):
            mod_idx = data[index]; index += 1
            x = data[index] - 256 if data[index] >= 128 else data[index]; index += 1
            y = data[index] - 256 if data[index] >= 128 else data[index]; index += 1
            flags = data[index]; index += 1
            frame_modules.append((mod_idx, x, y, flags))
    
    # Load frames
    num_frames, index = get_short(data, index)
    print(f"  numFrames: {num_frames}")
    frames = []
    frame_rects = []
    if num_frames != 0 and index < len(data):
        for _ in range(num_frames):
            nm, index = get_short(data, index)
            fmi, index = get_short(data, index)
            frames.append((nm, fmi))
        for _ in range(num_frames):
            if index + 4 > len(data):
                break
            x = data[index]; index += 1
            y = data[index]; index += 1
            w = data[index]; index += 1
            h = data[index]; index += 1
            frame_rects.append((x, y, w, h))
    
    # Load animation frames
    num_anim_frames, index = get_short(data, index)
    print(f"  numAnimFrames: {num_anim_frames}")
    anim_frames = []
    if num_anim_frames != 0 and index < len(data):
        end_index = index + num_anim_frames * 5
        while index < end_index and index < len(data):
            frame_idx = data[index]; index += 1
            duration = data[index]; index += 1
            xoffs = data[index] - 256 if data[index] >= 128 else data[index]; index += 1
            yoffs = data[index] - 256 if data[index] >= 128 else data[index]; index += 1
            flags = data[index]; index += 1
            anim_frames.append((frame_idx, duration, xoffs, yoffs, flags))
    
    # Load animations
    num_animations, index = get_short(data, index)
    print(f"  numAnimations: {num_animations}")
    animations = []
    if num_animations != 0 and index < len(data):
        for _ in range(num_animations):
            fc, index = get_short(data, index)
            fi, index = get_short(data, index)
            animations.append((fc, fi))
    
    print(f"  index after header: {index}, file_size: {len(data)}")
    
    if num_modules > 0:
        if index + 2 > len(data):
            print(f"  ERROR: not enough data for paletteFormat at index {index}")
            return
        palette_format, index = get_short(data, index)
        pf_name = {ARGB8888:'ARGB8888', ARGB4444:'ARGB4444', ARGB1555:'ARGB1555', RGB565:'RGB565'}.get(palette_format, f'0x{palette_format:04x}')
        print(f"  paletteFormat: {pf_name}")
        
        if index < len(data):
            palette_count = data[index]; index += 1
            palette_size = data[index]; index += 1
            print(f"  paletteCount: {palette_count}, paletteSize: {palette_size}")
            
            # Load palettes
            palettes = []
            for pi in range(palette_count):
                pal = []
                for ci in range(palette_size):
                    if palette_format == ARGB8888:
                        if index + 4 > len(data): break
                        color, index = get_int(data, index)
                        pal.append(color)
                    elif palette_format == ARGB4444:
                        if index + 2 > len(data): break
                        color, index = get_short(data, index)
                        # Expand 4444
                        argb = ((color & 0xF000) << 16) | ((color & 0xF000) << 12) | \
                               ((color & 0xF00) << 12) | ((color & 0xF00) << 8) | \
                               ((color & 0xF0) << 8) | ((color & 0xF0) << 4) | \
                               ((color & 0xF) << 4) | (color & 0xF)
                        pal.append(argb)
                    elif palette_format == ARGB1555:
                        if index + 2 > len(data): break
                        color, index = get_short(data, index)
                        alpha = 0xFF000000
                        if (color & 0x8000) != 0x8000:
                            alpha = 0
                        argb = alpha | ((color & 0x7C00) << 9) | ((color & 0x3E0) << 6) | ((color & 0x1F) << 3)
                        pal.append(argb)
                    elif palette_format == RGB565:
                        if index + 2 > len(data): break
                        color, index = get_short(data, index)
                        alpha = 0xFF000000
                        if color == 0xF81F:
                            alpha = 0
                        argb = alpha | ((color & 0xF800) << 8) | ((color & 0x7E0) << 5) | ((color & 0x1F) << 3)
                        pal.append(argb)
                    else:
                        if index >= len(data): break
                        pal.append(data[index]); index += 1
                palettes.append(pal)
                print(f"  Palette {pi}: {len(pal)} colors, first 5: {[hex(c) for c in pal[:5]]}")
            
            # bitDepth
            if index + 2 <= len(data):
                bit_depth, index = get_short(data, index)
                bd_name = {BIT_DEPTH_8: '8bpp', BIT_DEPTH_4: '4bpp', BIT_DEPTH_2: '2bpp', 
                          BIT_DEPTH_1: '1bpp', RLE_56F2: 'RLE_0x56F2', RLE_27F1: 'RLE_0x27F1'}.get(bit_depth, f'0x{bit_depth:04x}')
                print(f"  bitDepth: {bd_name} (0x{bit_depth:04x})")
            
            # Load module bitmap data
            print(f"  Module bitmap data:")
            for mi in range(len(modules)):
                if index + 2 > len(data): break
                bitmap_size, index = get_short(data, index)
                print(f"    Module {mi} ({modules[mi]}): bitmap_size={bitmap_size}, index={index}")
                if bitmap_size > 0 and bitmap_size < len(data):
                    bitmap_data = data[index:index+bitmap_size]
                    print(f"    First 16 bytes: {bitmap_data[:16].hex()}")
                    index += bitmap_size
                else:
                    if mi < 5:
                        print(f"    First 16 bytes: {data[index:index+16].hex()}")

if __name__ == '__main__':
    fn = sys.argv[1] if len(sys.argv) > 1 else 'm0'
    fpath = os.path.join(SPRITES_DIR, fn)
    data = open(fpath, 'rb').read()
    print(f"File: {fn} ({len(data)} bytes)")
    print(f"First 32 bytes: {data[:32].hex()}")
    load_data(data, 0)
