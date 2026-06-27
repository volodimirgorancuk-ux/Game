import org.objectweb.asm.*;
import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.util.zip.*;

public class NullPointerFixer {
    static class SafeConstructorVisitor extends ClassVisitor {
        public SafeConstructorVisitor(ClassVisitor cv) {
            super(Opcodes.ASM9, cv);
        }
        
        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
            if (mv == null) return null;
            
            // Patch GameCanvas constructor to avoid NPE from Display.getDisplay(null)
            if (name.equals("<init>") && descriptor.equals("()V")) {
                return new SafeConstructorAdapter(mv);
            }
            
            return mv;
        }
    }
    
    static class SafeConstructorAdapter extends MethodVisitor {
        public SafeConstructorAdapter(MethodVisitor mv) {
            super(Opcodes.ASM9, mv);
        }
        
        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
            // Replace Display.getDisplay(this) with null-safe version
            if (opcode == Opcodes.INVOKESTATIC && 
                owner.equals("javax/microedition/lcdui/Display") && 
                name.equals("getDisplay") &&
                descriptor.equals("(Ljavax/microedition/midlet/MIDlet;)Ljavax/microedition/lcdui/Display;")) {
                // Skip the call - just pop the argument and ignore result
                super.visitInsn(Opcodes.POP);
                super.visitInsn(Opcodes.ACONST_NULL);
                return;
            }
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java NullPointerFixer <input.jar> <output.jar>");
            System.exit(1);
        }
        
        String input = args[0];
        String output = args[1];
        
        JarFile inJar = new JarFile(input);
        JarOutputStream outJar = new JarOutputStream(new FileOutputStream(output));
        
        Enumeration<JarEntry> entries = inJar.entries();
        int count = 0;
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            InputStream is = inJar.getInputStream(entry);
            byte[] data = is.readAllBytes();
            is.close();
            
            String entryName = entry.getName();
            if (entryName.equals("GameCanvas.class")) {
                ClassReader cr = new ClassReader(data);
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                ClassVisitor cv = new SafeConstructorVisitor(cw);
                cr.accept(cv, 0);
                data = cw.toByteArray();
                count++;
            }
            
            JarEntry newEntry = new JarEntry(entryName);
            outJar.putNextEntry(newEntry);
            outJar.write(data);
            outJar.closeEntry();
        }
        inJar.close();
        outJar.close();
        System.out.println("Patched " + count + " classes -> " + output);
    }
}
