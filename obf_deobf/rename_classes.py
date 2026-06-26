import zipfile
import struct
import os

# Mapping: old_name -> new_name
rename_map = {
    'a': 'GraphicsEngine',
    'b': 'GameController',
    'c': 'GameData',
    'd': 'GameConfig',
    'e': 'SensorHandler',
    'f': 'AudioManager',
    'g': 'GameCanvas',
}

input_jar = '../240x320-rus-zombie-infection.jar'
output_jar = 'deobfuscated.jar'

# Build reverse map for quick lookup during bytecode parsing
new_name_map = {k.encode('utf-8'): v.encode('utf-8') for k, v in rename_map.items()}

with zipfile.ZipFile(input_jar, 'r') as zin, zipfile.ZipFile(output_jar, 'w', zipfile.ZIP_DEFLATED) as zout:
    for item in zin.namelist():
        data = zin.read(item)
        if item.endswith('.class'):
            # Parse class file and rename classes
            data = rename_classes_in_bytes(data, new_name_map)
        zout.writestr(item, data)

print(f"Created {output_jar}")

def rename_classes_in_bytes(data, new_name_map):
    if len(data) < 10:
        return data
    
    # Check magic
    magic = struct.unpack('>I', data[:4])[0]
    if magic != 0xCAFEBABE:
        return data
    
    minor_version, major_version = struct.unpack('>HH', data[4:8])
    # Java 1.1 = major 45, minor 3
    
    constant_pool_count = struct.unpack('>H', data[8:10])[0]
    
    # We need to parse the constant pool to find CONSTANT_Class entries
    # and point their name_index to new CONSTANT_Utf8 entries
    
    offset = 10
    constants = [None] * constant_pool_count  # 1-indexed
    utf8_indices = {}  # map from old utf8 index -> new utf8 index (if renamed)
    
    i = 1
    while i < constant_pool_count:
        if offset + 1 > len(data):
            break
        tag = data[offset]
        offset += 1
        
        if tag == 7:  # CONSTANT_Class
            name_index = struct.unpack('>H', data[offset:offset+2])[0]
            offset += 2
            constants[i] = ('class', name_index)
        elif tag == 9:  # CONSTANT_Fieldref
            class_index, name_and_type_index = struct.unpack('>HH', data[offset:offset+4])
            offset += 4
            constants[i] = ('field', class_index, name_and_type_index)
        elif tag == 10:  # CONSTANT_Methodref
            class_index, name_and_type_index = struct.unpack('>HH', data[offset:offset+4])
            offset += 4
            constants[i] = ('method', class_index, name_and_type_index)
        elif tag == 11:  # CONSTANT_InterfaceMethodref
            class_index, name_and_type_index = struct.unpack('>HH', data[offset:offset+4])
            offset += 4
            constants[i] = ('imethod', class_index, name_and_type_index)
        elif tag == 8:  # CONSTANT_String
            string_index = struct.unpack('>H', data[offset:offset+2])[0]
            offset += 2
            constants[i] = ('string', string_index)
        elif tag == 3:  # CONSTANT_Integer
            value = struct.unpack('>i', data[offset:offset+4])[0]
            offset += 4
            constants[i] = ('int', value)
        elif tag == 4:  # CONSTANT_Float
            value = struct.unpack('>f', data[offset:offset+4])[0]
            offset += 4
            constants[i] = ('float', value)
        elif tag == 5:  # CONSTANT_Long
            offset += 8
            constants[i] = ('long', None)
            constants[i+1] = ('long_hi', None)  # takes two slots
            i += 1
        elif tag == 6:  # CONSTANT_Double
            offset += 8
            constants[i] = ('double', None)
            constants[i+1] = ('double_hi', None)  # takes two slots
            i += 1
        elif tag == 12:  # CONSTANT_NameAndType
            name_index, descriptor_index = struct.unpack('>HH', data[offset:offset+4])
            offset += 4
            constants[i] = ('nat', name_index, descriptor_index)
        elif tag == 1:  # CONSTANT_Utf8
            length = struct.unpack('>H', data[offset:offset+2])[0]
            offset += 2
            utf8_bytes = data[offset:offset+length]
            offset += length
            constants[i] = ('utf8', utf8_bytes)
        elif tag == 15:  # CONSTANT_MethodHandle
            offset += 3
            constants[i] = ('mh', None)
        elif tag == 16:  # CONSTANT_MethodType
            offset += 2
            constants[i] = ('mt', None)
        elif tag == 18:  # CONSTANT_InvokeDynamic
            offset += 4
            constants[i] = ('id', None)
        else:
            # Unknown tag - skip or fail
            return data
        
        i += 1
    
    # Now find all CONSTANT_Class entries and see if they reference a renamed class
    renamed_utf8_map = {}  # old_utf8_index -> new_utf8_index
    
    for idx, c in enumerate(constants):
        if c is None or len(c) == 0:
            continue
        if c[0] == 'class':
            name_index = c[1]
            if name_index < len(constants) and constants[name_index] is not None and constants[name_index][0] == 'utf8':
                old_name = constants[name_index][1]
                if old_name in new_name_map:
                    # We need to add a new CONSTANT_Utf8 entry with the new name
                    new_name = new_name_map[old_name]
                    # Check if we already added this new name to the pool
                    if new_name not in [v[1] for v in constants if v and v[0] == 'utf8' and v[1] == new_name]:
                        # Find the first None slot in constants
                        new_idx = None
                        for j in range(1, len(constants)):
                            if constants[j] is None:
                                new_idx = j
                                break
                        if new_idx is None:
                            # Need to expand - shouldn't happen for simple renames
                            pass
                        else:
                            constants[new_idx] = ('utf8', new_name)
                            renamed_utf8_map[name_index] = new_idx
    
    # If nothing to rename, return original
    if not renamed_utf8_map:
        return data
    
    # Now rebuild the class file with updated references
    # We need to know which constant pool entries changed size
    # CONSTANT_Utf8 size depends on the string length
    
    # Calculate sizes of all constants
    def const_size(entry):
        if entry is None:
            return 0
        tag = entry[0]
        if tag == 'utf8':
            return 1 + 2 + len(entry[1])
        elif tag == 'class' or tag == 'string' or tag == 'mh' or tag == 'mt':
            return 1 + 2
        elif tag in ('field', 'method', 'imethod', 'nat'):
            return 1 + 4
        elif tag in ('int', 'float'):
            return 1 + 4
        elif tag == 'long_hi' or tag == 'double_hi':
            return 0  # already counted
        elif tag == 'long' or tag == 'double':
            return 1 + 8
        elif tag == 'id':
            return 1 + 4
        return 0
    
    # First pass: calculate original sizes and new sizes
    original_cp_size = 2  # count field
    new_cp_size = 2
    
    for idx in range(1, constant_pool_count):
        entry = constants[idx]
        original_cp_size += const_size(entry)
        if entry and entry[0] == 'utf8' and idx in [v for v in renamed_utf8_map.values()]:
            # This is a newly added UTF8 with a potentially different length
            new_cp_size += const_size(entry)
        elif entry and entry[0] == 'utf8' and any(k == idx for k in renamed_utf8_map):
            # This is an old UTF8 that was replaced
            # Its size might have changed, but we're removing it
            original_cp_size += const_size(entry)
        elif entry is not None:
            new_cp_size += const_size(entry)
        
        # Handle long/double double-slot
        if entry and entry[0] in ('long', 'double'):
            if idx + 1 < constant_pool_count:
                idx += 1  # skip next
    
    # Hmm, this is getting complex. Let me do a simpler rebuild by iterating constants in order
    # and writing the new binary.
    
