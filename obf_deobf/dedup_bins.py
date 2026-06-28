#!/usr/bin/env python3
"""Deduplicate .bin files that are identical to .archive files."""
import os, hashlib, sys

base = 'obf_deobf/decoded'
archive_hashes = {}
for f in os.listdir(base):
    if not (f.endswith('.archive') or f.endswith('.bin')):
        continue
    if f.endswith('.archive') or f.endswith('.bin'):
        path = os.path.join(base, f)
        try:
            h = hashlib.md5(open(path, 'rb').read()).hexdigest()
            archive_hashes.setdefault(h, []).append(f)
        except Exception as e:
            print(f'Skipping {f}: {e}')

removed = 0
for h, files in archive_hashes.items():
    archives = [f for f in files if f.endswith('.archive')]
    bins = [f for f in files if f.endswith('.bin')]
    if not archives or not bins:
        continue
    print(f'Duplicate set (md5={h[:8]}):')
    for a in archives:
        print(f'  archive: {a} ({os.path.getsize(os.path.join(base, a))} bytes)')
    for b in bins:
        sz = os.path.getsize(os.path.join(base, b))
        print(f'  bin:     {b} ({sz} bytes) -> REMOVE')
        os.remove(os.path.join(base, b))
        removed += 1

print(f'\nRemoved {removed} duplicate .bin files')
