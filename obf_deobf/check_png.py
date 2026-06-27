import struct
f = open('obf_deobf/decoded/tile_verif_00.png', 'rb')
sig = f.read(8)
print('Signature:', sig.hex())
chunk_len = struct.unpack('>I', f.read(4))[0]
print('IHDR chunk length:', chunk_len)
chunk_type = f.read(4)
print('Chunk type:', chunk_type)
data = f.read(chunk_len)
print('IHDR data length:', len(data))
if len(data) >= 12:
    w, h, bd, ct = struct.unpack('>IIBB', data[:12])
    print(f'Width: {w}, Height: {h}, bit_depth: {bd}, color_type: {ct}')
else:
    print('Data too short')
