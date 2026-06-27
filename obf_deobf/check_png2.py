import struct
f = open('obf_deobf/decoded/tile_verif_00.png', 'rb')
data = f.read()
print('File length:', len(data))
print('First 32 bytes:', data[:32].hex())
print('Bytes 32-64:', data[32:64].hex())
