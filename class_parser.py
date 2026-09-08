#!/usr/bin/env python3
"""Java class file disassembler - focuses on method signatures and bytecode."""
import struct, sys

class ClassFile:
    def __init__(self, data):
        self.data = data
        self.pos = 8  # skip magic(4) + minor(2) + major(2)
        self.cp = {}
        
    def parse_cp(self):
        cp_count = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
        i = 1
        while i < cp_count:
            tag = self.data[self.pos]; self.pos += 1
            if tag == 7:  # Class
                idx = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                self.cp[i] = ('Class', idx)
            elif tag == 8:  # String
                idx = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                self.cp[i] = ('String', idx)
            elif tag == 9:  # Fieldref
                cl = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                nm = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                self.cp[i] = ('Fieldref', cl, nm)
            elif tag == 10:  # Methodref
                cl = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                nm = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                self.cp[i] = ('Methodref', cl, nm)
            elif tag == 11:  # InterfaceMethodref
                cl = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                nm = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                self.cp[i] = ('InterfaceMethodref', cl, nm)
            elif tag == 12:  # NameAndType
                nm = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                sig = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                self.cp[i] = ('NameAndType', nm, sig)
            elif tag == 3:  # Integer
                val = struct.unpack_from('>i', self.data, self.pos)[0]; self.pos += 4
                self.cp[i] = ('Integer', val)
            elif tag == 4:  # Float
                val = struct.unpack_from('>f', self.data, self.pos)[0]; self.pos += 4
                self.cp[i] = ('Float', val)
            elif tag == 5:  # Long
                val = struct.unpack_from('>q', self.data, self.pos)[0]; self.pos += 8
                self.cp[i] = ('Long', val)
                i += 1
            elif tag == 6:  # Double
                val = struct.unpack_from('>d', self.data, self.pos)[0]; self.pos += 8
                self.cp[i] = ('Double', val)
                i += 1
            elif tag == 1:  # Utf8
                length = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                s = self.data[self.pos:self.pos+length].decode('utf-8', errors='replace'); self.pos += length
                self.cp[i] = ('Utf8', s)
            elif tag == 15:  # MethodHandle
                kind = self.data[self.pos]; self.pos += 1
                idx = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                self.cp[i] = ('MethodHandle', kind, idx)
            elif tag == 16:  # MethodType
                idx = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                self.cp[i] = ('MethodType', idx)
            elif tag == 18:  # InvokeDynamic
                idx = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
                self.cp[i] = ('InvokeDynamic', idx)
            else:
                print(f"  Unknown tag {tag} at cp[{i}], pos={self.pos}")
                self.pos += 2  # try to skip
                self.cp[i] = ('Unknown', tag)
            i += 1

    def gname(self, idx):
        e = self.cp.get(idx)
        if not e:
            return f"#{idx}?"
        if e[0] == 'Class':
            inner = self.cp.get(e[1])
            if inner and inner[0] == 'Utf8':
                return inner[1].replace('/', '.')
            return '?'
        elif e[0] == 'Utf8':
            return e[1]
        elif e[0] == 'String':
            inner = self.cp.get(e[1])
            return inner[1] if inner and inner[0] == 'Utf8' else '?'
        return str(e)

    def mname(self, idx):
        e = self.cp.get(idx)
        if not e:
            return f"#{idx}?"
        if e[0] in ('Methodref', 'Fieldref', 'InterfaceMethodref'):
            cls_parts = self.gname(e[1]).split('.')
            cls = cls_parts[-1] if cls_parts else '?'
            nt = self.cp.get(e[2])
            n = s = '?'
            if nt and nt[0] == 'NameAndType':
                nn = self.cp.get(nt[1])
                ss = self.cp.get(nt[2])
                n = nn[1] if nn and nn[0] == 'Utf8' else '?'
                s = ss[1] if ss and ss[0] == 'Utf8' else '?'
            return f"{cls}.{n}{s}"
        elif e[0] == 'NameAndType':
            n = self.cp.get(e[1])
            s = self.cp.get(e[2])
            n = n[1] if n and n[0] == 'Utf8' else '?'
            s = s[1] if s and s[0] == 'Utf8' else '?'
            return f"{n}{s}"
        return str(e)

    def get_int(self):
        v = struct.unpack_from('>I', self.data, self.pos)[0]; self.pos += 4
        return v

    def get_short(self):
        v = struct.unpack_from('>H', self.data, self.pos)[0]; self.pos += 2
        return v

    def get_bytes(self, n):
        v = self.data[self.pos:self.pos+n]; self.pos += n
        return v

OPCODE_NAMES = {
    0x00:'nop',0x01:'aconst_null',0x02:'aconst_pair',0x03:'iconst_m1',0x04:'iconst_0',
    0x05:'iconst_1',0x06:'iconst_2',0x07:'iconst_3',0x08:'iconst_4',0x09:'iconst_5',
    0x0a:'lconst_0',0x0b:'lconst_1',0x0c:'fconst_0',0x0d:'fconst_1',0x0e:'fconst_2',
    0x0f:'dconst_0',0x10:'bipush',0x11:'sipush',0x12:'ldc',0x13:'ldc_w',0x14:'ldc2_w',
    0x15:'iload',0x16:'lload',0x17:'fload',0x18:'dload',0x19:'aload',
    0x1a:'iload_0',0x1b:'iload_1',0x1c:'iload_2',0x1d:'iload_3',
    0x1e:'lload_0',0x1f:'lload_1',0x20:'lload_2',0x21:'lload_3',
    0x22:'fload_0',0x23:'fload_1',0x24:'fload_2',0x25:'fload_3',
    0x26:'dload_0',0x27:'dload_1',0x28:'dload_2',0x29:'dload_3',
    0x2a:'aload_0',0x2b:'aload_1',0x2c:'aload_2',0x2d:'aload_3',
    0x2e:'iaload',0x2f:'laload',0x30:'faload',0x31:'daload',0x32:'aaload',
    0x33:'baload',0x34:'caload',0x35:'saload',
    0x36:'istore',0x37:'lstore',0x38:'fstore',0x39:'dstore',0x3a:'astore',
    0x3b:'istore_0',0x3c:'istore_1',0x3d:'istore_2',0x3e:'istore_3',
    0x3f:'lstore_0',0x40:'lstore_1',0x41:'lstore_2',0x42:'lstore_3',
    0x43:'fstore_0',0x44:'fstore_1',0x45:'fstore_2',0x46:'fstore_3',
    0x47:'dstore_0',0x48:'dstore_1',0x49:'dstore_2',0x4a:'dstore_3',
    0x4b:'astore_0',0x4c:'astore_1',0x4d:'astore_2',0x4e:'astore_3',
    0x4f:'iastore',0x50:'lastore',0x51:'fastore',0x52:'dastore',0x53:'aastore',
    0x54:'bastore',0x55:'castore',0x56:'sastore',0x57:'pop',0x58:'pop2',
    0x59:'dup',0x5a:'dup_x1',0x5b:'dup_x2',0x5c:'dup2',0x5d:'dup2_x1',0x5e:'dup2_x2',
    0x5f:'swap',0x60:'iadd',0x61:'ladd',0x62:'fadd',0x63:'dadd',0x64:'isub',
    0x65:'lsub',0x66:'fsub',0x67:'dsub',0x68:'imul',0x69:'lmul',0x6a:'fmul',
    0x6b:'dmul',0x6c:'idiv',0x6d:'ldiv',0x6e:'fdiv',0x6f:'ddiv',0x70:'irem',
    0x71:'lrem',0x72:'frem',0x73:'drem',0x74:'ineg',0x75:'lneg',0x76:'fneg',
    0x77:'dneg',0x78:'ishl',0x79:'lshl',0x7a:'ishr',0x7b:'lshr',0x7c:'iushr',
    0x7d:'lushr',0x7e:'iand',0x7f:'land',0x80:'ior',0x81:'lor',0x82:'ixor',
    0x83:'lxor',0x84:'iinc',0x85:'i2l',0x86:'i2f',0x87:'i2d',0x88:'l2i',
    0x89:'l2f',0x8a:'l2d',0x8b:'f2i',0x8c:'f2l',0x8d:'f2d',0x8e:'d2i',
    0x8f:'d2l',0x90:'d2f',0x91:'i2b',0x92:'i2c',0x93:'i2s',0x94:'lcmp',
    0x95:'fcmpl',0x96:'fcmpg',0x97:'dcmpl',0x98:'dcmpg',0x99:'ifeq',0x9a:'ifne',
    0x9b:'iflt',0x9c:'ifge',0x9d:'ifgt',0x9e:'ifle',0x9f:'if_icmpeq',0xa0:'if_icmpne',
    0xa1:'if_icmplt',0xa2:'if_icmpge',0xa3:'if_icmpgt',0xa4:'if_icmple',
    0xa5:'if_acmpeq',0xa6:'if_acmpne',0xa7:'goto',0xa8:'jsr',0xa9:'ret',
    0xaa:'tableswitch',0xab:'lookupswitch',0xac:'ireturn',0xad:'lreturn',
    0xae:'freturn',0xaf:'dreturn',0xb0:'areturn',0xb1:'return',
    0xb2:'getstatic',0xb3:'putstatic',0xb4:'getfield',0xb5:'putfield',
    0xb6:'invokevirtual',0xb7:'invokespecial',0xb8:'invokestatic',
    0xb9:'invokeinterface',0xba:'invokedynamic',0xbb:'new',0xbc:'newarray',
    0xbd:'anewarray',0xbe:'arraylength',0xbf:'athrow',0xc0:'checkcast',
    0xc1:'instanceof',0xc2:'monitorenter',0xc3:'monitorexit',0xc4:'wide',
    0xc5:'multianewarray',0xc6:'ifnull',0xc7:'ifnonnull',0xc8:'goto_w',0xc9:'jsr_w',
}

# 1-byte opcodes (no operands)
ONE_BYTE_OPS = set(range(0xc9+1)) - {0x10,0x11,0x12,0x13,0x14,0x15,0x16,0x17,0x18,0x19,
    0x84,0xb2,0xb3,0xb4,0xb5,0xb6,0xb7,0xb8,0xb9,0xba,0xbb,0xbc,0xbd,0xc5,
    0xa7,0xa8,0x99,0x9a,0x9b,0x9c,0x9d,0x9e,0x9f,0xa0,0xa1,0xa2,0xa3,0xa4,
    0xa5,0xa6,0xc6,0xc7,0xc8,0xc9,0xaa,0xab,0xc4}

def disassemble(cls, code, max_len=None):
    if max_len is None:
        max_len = len(code)
    j = 0
    while j < max_len:
        op = code[j]
        opname = OPCODE_NAMES.get(op, f'unk_{op:02x}')
        if op in ONE_BYTE_OPS:
            print(f'  {j:04x}: {opname}')
            j += 1
        elif op == 0x10:  # bipush
            v = code[j+1]; sv = v - 256 if v >= 128 else v
            print(f'  {j:04x}: bipush {sv}')
            j += 2
        elif op == 0x11:  # sipush
            v = struct.unpack_from('>h', code, j+1)[0]
            print(f'  {j:04x}: sipush {v}')
            j += 3
        elif op == 0x12:  # ldc
            idx = code[j+1]
            print(f'  {j:04x}: ldc #{idx}({cls.gname(idx)})')
            j += 2
        elif op == 0x13:  # ldc_w
            idx = struct.unpack_from('>H', code, j+1)[0]
            print(f'  {j:04x}: ldc_w #{idx}({cls.mname(idx)})')
            j += 3
        elif op == 0x14:  # ldc2_w
            idx = struct.unpack_from('>H', code, j+1)[0]
            print(f'  {j:04x}: ldc2_w #{idx}')
            j += 3
        elif op in (0x19,):  # aload
            print(f'  {j:04x}: aload {code[j+1]}')
            j += 2
        elif op in (0x15,):  # iload
            print(f'  {j:04x}: iload {code[j+1]}')
            j += 2
        elif op in (0x16,):  # lload
            print(f'  {j:04x}: lload {code[j+1]}')
            j += 2
        elif op in (0x17,):  # fload
            print(f'  {j:04x}: fload {code[j+1]}')
            j += 2
        elif op in (0x18,):  # dload
            print(f'  {j:04x}: dload {code[j+1]}')
            j += 2
        elif op in (0x3a,):  # astore
            print(f'  {j:04x}: astore {code[j+1]}')
            j += 2
        elif op in (0x36,):  # istore
            print(f'  {j:04x}: istore {code[j+1]}')
            j += 2
        elif op in (0x37,):  # lstore
            print(f'  {j:04x}: lstore {code[j+1]}')
            j += 2
        elif op in (0x38,):  # fstore
            print(f'  {j:04x}: fstore {code[j+1]}')
            j += 2
        elif op in (0x39,):  # dstore
            print(f'  {j:04x}: dstore {code[j+1]}')
            j += 2
        elif op == 0x84:  # iinc
            v = code[j+1]; c = code[j+2]; sc = c - 256 if c >= 128 else c
            print(f'  {j:04x}: iinc local{v} {sc}')
            j += 3
        elif op in (0xb2,0xb3,0xb4,0xb5):  # getstatic/putstatic/getfield/putfield
            idx = struct.unpack_from('>H', code, j+1)[0]
            print(f'  {j:04x}: {opname} #{idx}({cls.mname(idx)})')
            j += 3
        elif op in (0xb6,0xb7,0xb8):  # invokevirtual/special/static
            idx = struct.unpack_from('>H', code, j+1)[0]
            print(f'  {j:04x}: {opname} #{idx}({cls.mname(idx)})')
            j += 3
        elif op == 0xb9:  # invokeinterface
            idx = struct.unpack_from('>H', code, j+1)[0]
            na = code[j+2]; nl = code[j+3]
            print(f'  {j:04x}: invokeinterface #{idx}({cls.mname(idx)}) nargs={na} nlocals={nl}')
            j += 5
        elif op == 0xba:  # invokedynamic
            idx = struct.unpack_from('>H', code, j+1)[0]
            print(f'  {j:04x}: invokedynamic #{idx}')
            j += 5
        elif op == 0xbb:  # new
            idx = struct.unpack_from('>H', code, j+1)[0]
            print(f'  {j:04x}: new {cls.gname(idx)}')
            j += 3
        elif op == 0xbc:  # newarray
            tp = code[j+1]
            tn = {1:'boolean',2:'char',3:'float',4:'double',5:'byte',6:'short',7:'int',8:'long'}
            print(f'  {j:04x}: newarray {tn.get(tp,tp)}')
            j += 2
        elif op == 0xbd:  # anewarray
            idx = struct.unpack_from('>H', code, j+1)[0]
            print(f'  {j:04x}: anewarray {cls.mname(idx)}')
            j += 3
        elif op == 0xc5:  # multianewarray
            idx = struct.unpack_from('>H', code, j+1)[0]
            nd = code[j+2]
            print(f'  {j:04x}: multianewarray #{idx}({cls.gname(idx)}) dims={nd}')
            j += 4
        elif op in (0x99,0x9a,0x9b,0x9c,0x9d,0x9e,0x9f,0xa0,0xa1,0xa2,0xa3,0xa4,0xa5,0xa6,0xa7,0xa8,0xc6,0xc7):
            off = struct.unpack_from('>h', code, j+1)[0]
            target = j + off
            print(f'  {j:04x}: {opname} -> {target:04x}')
            j += 3
        elif op == 0xaa:  # tableswitch
            pad = (j + 1 + 3) & ~3
            default = struct.unpack_from('>i', code, pad)[0]
            j = pad + 4
            low = struct.unpack_from('>i', code, j)[0]; j += 4
            high = struct.unpack_from('>i', code, j)[0]; j += 4
            print(f'  {j-12:04x}: tableswitch low={low} high={high} default->{default:04x}')
            n = high - low + 1
            for k in range(n):
                o = struct.unpack_from('>i', code, j)[0]; j += 4
                print(f'          [{low+k}] -> {o:04x}')
        elif op == 0xab:  # lookupswitch
            pad = (j + 1 + 3) & ~3
            default = struct.unpack_from('>i', code, pad)[0]
            j = pad + 4
            npairs = struct.unpack_from('>i', code, j)[0]; j += 4
            print(f'  {j-12:04x}: lookupswitch default->{default:04x}')
            for k in range(npairs):
                m = struct.unpack_from('>i', code, j)[0]; j += 4
                o = struct.unpack_from('>i', code, j)[0]; j += 4
                print(f'          [{m}] -> {o:04x}')
        elif op == 0xc4:  # wide
            op2 = code[j+1]
            if op2 in (0x15,0x16,0x17,0x18,0x19,0x36,0x37,0x38,0x39,0x3a,0xa9):
                v = struct.unpack_from('>H', code, j+2)[0]
                print(f'  {j:04x}: wide {OPCODE_NAMES.get(op2,hex(op2))} {v}')
                j += 4
            elif op2 == 0x84:
                v = struct.unpack_from('>H', code, j+2)[0]
                c = struct.unpack_from('>h', code, j+4)[0]
                print(f'  {j:04x}: wide iinc {v} {c}')
                j += 6
            else:
                print(f'  {j:04x}: wide ?')
                j += 2
        elif op == 0xc8:  # goto_w
            off = struct.unpack_from('>i', code, j+1)[0]
            print(f'  {j:04x}: goto_w -> {j+off:04x}')
            j += 5
        elif op == 0xc9:  # jsr_w
            off = struct.unpack_from('>i', code, j+1)[0]
            print(f'  {j:04x}: jsr_w -> {j+off:04x}')
            j += 5
        else:
            print(f'  {j:04x}: {opname}')
            j += 1

def main():
    fn = sys.argv[1]
    show_methods = '--methods' in sys.argv
    data = open(fn, 'rb').read()
    cls = ClassFile(data)
    cls.parse_cp()
    
    magic = struct.unpack_from('>I', data, 0)[0]
    minor = struct.unpack_from('>H', data, 4)[0]
    major = struct.unpack_from('>H', data, 6)[0]
    print(f"Magic: 0x{magic:08x}, Version: {major}.{minor}")
    
    access = cls.get_short()
    this_class = cls.get_short()
    super_class = cls.get_short()
    print(f"This: {cls.gname(this_class)}")
    print(f"Super: {cls.gname(super_class)}")
    
    iface_count = cls.get_short()
    interfaces = []
    for _ in range(iface_count):
        idx = cls.get_short()
        interfaces.append(cls.gname(idx))
    print(f"Interfaces: {interfaces}")
    
    # Fields
    field_count = cls.get_short()
    print(f"\nFields ({field_count}):")
    for _ in range(field_count):
        f_acc = cls.get_short()
        f_name = cls.get_short()
        f_sig = cls.get_short()
        f_attr = cls.get_short()
        fname = cls.cp[f_name][1] if f_name in cls.cp and cls.cp[f_name][0]=='Utf8' else f'#{f_name}'
        fsig = cls.cp[f_sig][1] if f_sig in cls.cp and cls.cp[f_sig][0]=='Utf8' else f'#{f_sig}'
        print(f"  {fname}: {fsig}")
        for _ in range(f_attr):
            an = cls.get_short()
            al = cls.get_int()
            cls.get_bytes(al)
    
    # Methods
    method_count = cls.get_short()
    print(f"\nMethods ({method_count}):")
    for _ in range(method_count):
        m_acc = cls.get_short()
        m_name = cls.get_short()
        m_sig = cls.get_short()
        m_attr = cls.get_short()
        mname = cls.cp[m_name][1] if m_name in cls.cp and cls.cp[m_name][0]=='Utf8' else f'#{m_name}'
        msig = cls.cp[m_sig][1] if m_sig in cls.cp and cls.cp[m_sig][0]=='Utf8' else f'#{m_sig}'
        print(f"\n--- {mname}{msig} (access=0x{m_acc:04x}) ---")
        code_attr = None
        for _ in range(m_attr):
            an = cls.get_short()
            al = cls.get_int()
            attr_data = cls.get_bytes(al)
            if an in cls.cp and cls.cp[an][0]=='Utf8' and cls.cp[an][1]=='Code':
                code_attr = attr_data
        if code_attr:
            max_stack = struct.unpack_from('>H', code_attr, 0)[0]
            max_locals = struct.unpack_from('>H', code_attr, 2)[0]
            code_len = struct.unpack_from('>I', code_attr, 4)[0]
            code = code_attr[8:8+code_len]
            print(f"  max_stack={max_stack}, max_locals={max_locals}, code_len={code_len}")
            if show_methods and '([B' in msig or '([BI' in msig:
                disassemble(cls, code)

if __name__ == '__main__':
    main()
