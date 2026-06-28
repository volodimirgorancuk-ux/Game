#!/usr/bin/env python3
"""
Minimal Java class-file parser in pure Python.
Reads constant pool, fields, methods from .class files inside a JAR.
"""
import struct, sys, zipfile

def read_jar_classes(jar_path):
    """Yield (class_name, class_data) for each .class in JAR."""
    with zipfile.ZipFile(jar_path, 'r') as z:
        for name in z.namelist():
            if name.endswith('.class'):
                cls_name = name.replace('/', '.').replace('.class', '')
                data = z.read(name)
                yield cls_name, data

def parse_class(data):
    """Return dict with class name, fields, methods."""
    if len(data) < 8:
        return None
    magic, minor, major = struct.unpack('>IHH', data[:8])
    offset = 8

    # constant_pool_count
    if offset + 2 > len(data):
        return None
    pool_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2

    # Parse constant pool entries.
    pool = [None]
    while len(pool) < pool_count and offset < len(data):
        tag = data[offset]; offset += 1
        if tag == 1:  # UTF-8
            length = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
            s = data[offset:offset+length].decode('utf-8', errors='replace'); offset += length
            pool.append(('Utf8', s))
        elif tag == 3:  # Integer
            pool.append(('Integer', struct.unpack('>i', data[offset:offset+4])[0])); offset += 4
        elif tag == 4:  # Float
            pool.append(('Float', struct.unpack('>f', data[offset:offset+4])[0])); offset += 4
        elif tag == 5:  # Long
            val = struct.unpack('>q', data[offset:offset+8])[0]; offset += 8
            pool.append(('Long', val)); pool.append(None)  # takes 2 slots
        elif tag == 6:  # Double
            val = struct.unpack('>d', data[offset:offset+8])[0]; offset += 8
            pool.append(('Double', val)); pool.append(None)  # takes 2 slots
        elif tag == 7:  # Class
            pool.append(('Class', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        elif tag == 8:  # String
            pool.append(('String', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        elif tag in (9, 10, 11):  # Fieldref, Methodref, InterfaceMethodref
            pool.append((tag, struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        elif tag == 12:  # NameAndType
            pool.append((tag, struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        elif tag == 15:  # MethodHandle
            pool.append(('MethodHandle', data[offset], struct.unpack('>H', data[offset+1:offset+3])[0])); offset += 3
        elif tag == 16:  # MethodType
            pool.append(('MethodType', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        elif tag in (17, 18):  # Dynamic, InvokeDynamic
            pool.append((tag, struct.unpack('>HH', data[offset:offset+4]))); offset += 4
        elif tag == 19:  # Module
            pool.append(('Module', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        elif tag == 20:  # Package
            pool.append(('Package', struct.unpack('>H', data[offset:offset+2])[0])); offset += 2
        else:
            return None

    if len(pool) != pool_count:
        return None
    
    # Access flags, this/super class
    if offset + 6 > len(data):
        return None
    access_flags, this_class, super_class = struct.unpack('>HHH', data[offset:offset+6]); offset += 6
    
    # Interfaces
    if offset + 2 > len(data):
        return None
    iface_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    
    # Fields
    if offset + 2 > len(data):
        return None
    field_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    fields = []
    for _ in range(field_count):
        if offset + 8 > len(data):
            break
        f_access, f_name_idx, f_desc_idx = struct.unpack('>HHH', data[offset:offset+6]); offset += 6
        attrs_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        for _ in range(attrs_count):
            if offset + 6 > len(data):
                break
            attr_name_idx, attr_len = struct.unpack('>HI', data[offset:offset+6]); offset += 6
            offset += attr_len
        fname = '<unknown>'
        if f_name_idx < len(pool) and isinstance(pool[f_name_idx], tuple) and pool[f_name_idx][0] == 'Utf8':
            fname = pool[f_name_idx][1]
        fdesc = '<unknown>'
        if f_desc_idx < len(pool) and isinstance(pool[f_desc_idx], tuple) and pool[f_desc_idx][0] == 'Utf8':
            fdesc = pool[f_desc_idx][1]
        fields.append({'name': fname, 'desc': fdesc, 'access': f_access})
    
    # Methods
    if offset + 2 > len(data):
        return None
    method_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
    methods = []
    for _ in range(method_count):
        if offset + 6 > len(data):
            break
        m_access, m_name_idx, m_desc_idx = struct.unpack('>HHH', data[offset:offset+6]); offset += 6
        attrs_count = struct.unpack('>H', data[offset:offset+2])[0]; offset += 2
        for _ in range(attrs_count):
            if offset + 6 > len(data):
                break
            attr_name_idx, attr_len = struct.unpack('>HI', data[offset:offset+6]); offset += 6
            offset += attr_len
        mname = '<unknown>'
        if m_name_idx < len(pool) and isinstance(pool[m_name_idx], tuple) and pool[m_name_idx][0] == 'Utf8':
            mname = pool[m_name_idx][1]
        mdesc = '<unknown>'
        if m_desc_idx < len(pool) and isinstance(pool[m_desc_idx], tuple) and pool[m_desc_idx][0] == 'Utf8':
            mdesc = pool[m_desc_idx][1]
        methods.append({'name': mname, 'desc': mdesc, 'access': m_access})
    
    class_name = '<unknown>'
    if this_class < len(pool) and isinstance(pool[this_class], tuple) and pool[this_class][0] == 'Class':
        ci = pool[this_class][1]
        if ci < len(pool) and isinstance(pool[ci], tuple) and pool[ci][0] == 'Utf8':
            class_name = pool[ci][1]
    super_name = '<unknown>'
    if super_class < len(pool) and isinstance(pool[super_class], tuple) and pool[super_class][0] == 'Class':
        ci = pool[super_class][1]
        if ci < len(pool) and isinstance(pool[ci], tuple) and pool[ci][0] == 'Utf8':
            super_name = pool[ci][1]
    
    return {
        'name': class_name,
        'super': super_name,
        'access': access_flags,
        'fields': fields,
        'methods': methods,
    }

def jvm_type(desc):
    mapping = {
        'I': 'int', 'Z': 'boolean', 'B': 'byte', 'C': 'char',
        'S': 'short', 'J': 'long', 'F': 'float', 'D': 'double',
        'V': 'void',
    }
    if desc in mapping:
        return mapping[desc]
    if desc.startswith('L') and desc.endswith(';'):
        return desc[1:-1].replace('/', '.')
    if desc.startswith('['):
        return jvm_type(desc[1:]) + '[]'
    return desc

def jvm_args(desc):
    result = []
    i = 0
    while i < len(desc):
        c = desc[i]
        if c in 'IBCSJFDZ':
            result.append({'I':'int','B':'byte','C':'char','S':'short','J':'long','F':'float','D':'double','Z':'boolean'}[c])
            i += 1
        elif c == 'L':
            end = desc.index(';', i)
            result.append(desc[i+1:end].replace('/', '.'))
            i = end + 1
        elif c == '[':
            arr = ''
            while i < len(desc) and desc[i] == '[':
                arr += '[]'
                i += 1
            if i < len(desc) and desc[i] == 'L':
                end = desc.index(';', i)
                result.append(desc[i+1:end].replace('/', '.') + arr)
                i = end + 1
            else:
                result.append({'I':'int','B':'byte','C':'char','S':'short','J':'long','F':'float','D':'double'}[desc[i]] + arr)
                i += 1
        else:
            i += 1
    return ', '.join(result)

def desc_to_sig(desc):
    if not desc.startswith('('):
        return desc
    args_end = desc.index(')')
    args_desc = desc[1:args_end]
    return f"({jvm_args(args_desc)}) {jvm_type(desc[args_end+1:])}"

def main():
    jar = sys.argv[1] if len(sys.argv) > 1 else 'fully_method_renamed.jar'
    for cls_name, data in read_jar_classes(jar):
        info = parse_class(data)
        if not info:
            continue
        print(f"\n=== {info['name']} extends {info['super']} ===")
        print(f"  Fields ({len(info['fields'])}):")
        for f in info['fields']:
            print(f"    {f['name']}: {jvm_type(f['desc'])} (access=0x{f['access']:04x})")
        print(f"  Methods ({len(info['methods'])}):")
        for m in info['methods']:
            print(f"    {m['name']}{desc_to_sig(m['desc'])} (access=0x{m['access']:04x})")

if __name__ == '__main__':
    main()
