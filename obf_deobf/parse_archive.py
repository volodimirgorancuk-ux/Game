#!/usr/bin/env python3
"""
Gameloft archive parser.
Handles two known formats:
1. Standard: count(1) + pad(1) + size(4) + offsets(count*4) + chunk_data
2. Raw: data is raw chunk without offset table (count=0)

For files with count=0, treats entire file as a single chunk.
"""
import sys, os

def parse_gameloft_archive(path):
    data = open(path, 'rb').read()
    if len(data) < 8:
        return [], data
    
    count = data[0]
    # Try standard format: bytes 1-4 = file size
    size = int.from_bytes(data[1:5], 'little')
    
    if count > 0 and (1 + 1 + 4 + count*4) <= len(data):
        # Parse offset table
        off_start = 6
        offsets = [int.from_bytes(data[off_start+i*4:off_start+i*4+4], 'little') 
                   for i in range(count)]
        # Check if sorted and valid
        if offsets == sorted(offsets) and all(o <= len(data) for o in offsets):
            chunks = []
            for i, off in enumerate(offsets):
                end = offsets[i+1] if i+1 < len(offsets) else len(data)
                chunk = data[off:end]
                chunks.append((i, off, end, chunk))
            return chunks, data
    
    # Fallback: single chunk (raw data)
    return [(0, 0, len(data), data)], data

if __name__ == '__main__':
    import glob
    for path in sorted(glob.glob('obf_deobf/decoded/*.archive')):
        chunks, _ = parse_gameloft_archive(path)
        print(f'{path}: {len(chunks)} chunks')
        for idx, off, end, chunk in chunks[:3]:
            print(f'  chunk{idx}: offset={off} size={len(chunk)} head={chunk[:8].hex()}')
        if len(chunks) > 3:
            print(f'  ... and {len(chunks)-3} more')
        print()
