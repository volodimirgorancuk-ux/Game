#!/usr/bin/env python3
"""Brute-force sprite format detection."""
import sys

def try_parse(data, frame_count_offset, frame_count_size, frame_entry_size):
    """Try parsing with given parameters."""
    if frame_count_offset + frame_count_size > len(data) or frame_count_offset < 0:
        return None
    
    if frame_count_size == 1:
        frame_count = data[frame_count_offset]
    elif frame_count_size == 2:
        frame_count = data[frame_count_offset] | (data[frame_count_offset+1] << 8)
    else:
        return None
    
    if frame_count > 1000 or frame_count < 0:
        return None
    
    table_start = frame_count_offset + frame_count_size
    
    if frame_entry_size == 6:
        expected = table_start + frame_count * 6
    elif frame_entry_size == 7:
        expected = table_start + frame_count * 7
    elif frame_entry_size == 8:
        expected = table_start + frame_count * 8
    else:
        return None
    
    if expected > len(data):
        return None
    
    entries = []
    for i in range(frame_count):
        off = table_start + i * frame_entry_size
        if frame_entry_size == 8:
            x = data[off] | (data[off+1] << 8)
            y = data[off+2] | (data[off+3] << 8)
            w = data[off+4] | (data[off+5] << 8)
            flag = data[off+6] | (data[off+7] << 8)
        elif frame_entry_size == 6:
            type_byte = data[off]
            if type_byte in (0xff, 0xfe):
                param = data[off+1] | (data[off+2]<<8) | (data[off+3]<<16) | (data[off+4]<<24)
                count = data[off+5]
                flag = data[off+6]
                x = y = w = None
            else:
                x = data[off] | (data[off+1] << 8)
                y = data[off+2] | (data[off+3] << 8)
                w = data[off+4] | (data[off+5] << 8)
                flag = None
        else:
            return None
        entries.append((x, y, w, flag))
    
    return frame_count, entries

def analyze_file(path):
    data = open(path, 'rb').read()
    print(f"\n=== {path} ({len(data)} bytes) ===")
    
    # Try many combinations
    best = None
    for fc_off in range(0, min(16, len(data))):
        for fc_sz in [1, 2]:
            for entry_sz in [6, 7, 8]:
                result = try_parse(data, fc_off, fc_sz, entry_sz)
                if result is None:
                    continue
                fc, entries = result
                # Heuristic: reasonable frame count, and at least 50% entries have valid-looking values
                valid = 0
                for x, y, w, flag in entries[:min(10, len(entries))]:
                    if x is None:
                        continue
                    # Consider valid if dimensions are reasonable (0-500)
                    if w is not None and 0 <= w <= 500:
                        valid += 1
                    if flag is not None and 0 <= flag <= 0xffff:
                        valid += 1
                score = valid
                if score > 0:
                    print(f"  fc_off={fc_off} fc_sz={fc_sz} entry_sz={entry_sz}: frames={fc}, valid_entries={valid}/10")

if __name__ == '__main__':
    for path in sys.argv[1:]:
        analyze_file(path)
