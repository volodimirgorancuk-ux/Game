# Zombie Infection — Progress Update

## Date
2026-06-27

## Major Findings

### 1. Embedded Palette Format (BREAKTHROUGH)
- Palette magic: `17476` (0x4474) found in 30+ sprite/tile files
- Format: 4-4-4 RGB expanded to 8-8-8
- Located after frame/animation headers, before pixel data
- Multiple palettes per file possible (e.g., m4_0_chunk07.tile has 34 palettes at offsets 0x4A4D, 0x60F4, 0xA0D5, ...)
- This explains why palettesAmount.bin was rendering as black — it's NOT the color palette (see below)

### 2. palettesAmount.bin True Purpose
- Contains 31 brightness/contrast tables, NOT RGB colors
- Used for per-room lighting effects (fog, darkness, highlight)
- Reads as byte arrays in GameCanvas.f/GameCanvas.e
- Applies `>> 1` adjustments to graphicsEngine.g (brightness)

### 3. Tilemap Structure (m4_0_chunk03.tile)
- 52795 bytes, maps to 240x220 = 52800 bytes (5-byte padding)
- Contains tile INDICES (0-255), not pixel data
- Format: byte grid where each byte is a tile ID
- Header: `01 00 00 00 00 14 11 00 16 11 ...` (1 frame, 20/17/22 params)

### 4. Tileset Structure (m4_0_chunk07.tile)
- 86199 bytes, contains 256 tiles of 16x16 pixels each (~66KB pixel data)
- Palette: 175 colors (4-4-4 format) at offset 0x4A4D
- Pixel format magic: `22018` (0x5602) at offset 0x4BAF — confirms 8bpp direct palette index
- First tile pixel values: [2, 86, 42, 4, 0, 0, 0, ...] with 28 unique colors per tile
- Tiles arranged in memory as 16x16 grid (16 tiles per row)

### 5. Archive Format Confirmed
- Header: `[count:1][pad:1][size:4][offset_table: count*4]`
- Example: m11_0_chunk00.archive = 11 entries, offsets from 0 to 65602
- Some archives have invalid counts (0) but valid data — likely encrypted or compressed chunks
- Duplication issue: extracted `.bin` files are exact duplicates of `.archive` files

## Assets Progress

| Asset | Status | Output |
|-------|--------|--------|
| Tilemap (240x220) | Extracted | `tilemap_256pal.png` (rendered with embedded palette) |
| Tileset (256x16x16) | Extracted | `tiles/` directory with 256 individual tile PNGs |
| Level composite | Partial | `level_composite.png` generation too slow for pure Python renderer |
| Palettes (embedded) | 30+ files decoded | `palettes_embedded/*.png` swatches |
| Sprites (m8, m10, m13) | Header only | Need full pixel decoder |
| m13 MIDI | Extracted | `m13_2_music.mid` |

## Code Files Created/Updated

- `obf_deobf/decode_graphic.py` — Gameloft graphic decoder (frames, embedded palettes, pixel formats)
- `obf_deobf/check_png.py` / `check_png2.py` — PNG inspection tools
- `obf_deobf/decoded/tiles/` — 256 extracted 16x16 tiles
- `obf_deobf/decoded/palettes_embedded/` — palette swatches from sprite/tile files

## Known Issues

1. **Archive duplication**: Every chunk extracts to both `.archive` and `.bin` with identical content. Need deduplication in extraction script.
2. **Pure Python PNG speed**: Writing full 3840x3520 level image takes >2 minutes. Need chunked/tiled rendering or faster approach.
3. **PalettesAmount.bin colors are 0**: The file loads correctly but all 31 palettes read as black. True palettes are embedded in sprite files.
4. **Archive counts**: Some `.archive` files have count=0 but non-zero data (e.g., m10_chunk05.archive: count=0, size=25). These may be raw data without an offset table.
5. **Sprite decoder**: `decode_gameloft_graphic.py` still throws `IndexError` on some files due to incomplete frame table parsing.

## Next Steps

1. Inspect individual tiles (`tiles/tile_000.png` to `tile_255.png`) to identify wall/floor/door patterns
2. Create a manual tilemap editor or annotation tool for level 1
3. Fix archive extraction deduplication
4. Continue sprite decoding for m8_chunk01.sprite (player/character sprites)
5. Investigate m4_0_chunk03.tile exact header structure — is it really 1 frame of 240x220 tile indices?
6. Test tilemap + tileset mapping: does index 0x01 in tilemap match `tile_001.png` from tileset?
7. Run existing tilemap_256pal.png against tileset to visually validate level layout
