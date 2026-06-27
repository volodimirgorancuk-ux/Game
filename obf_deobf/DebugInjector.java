import org.objectweb.asm.*;
import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.util.zip.*;

public class DebugInjector {
    static class DebugClassVisitor extends ClassVisitor {
        public DebugClassVisitor(ClassVisitor cv) {
            super(Opcodes.ASM9, cv);
        }
        
        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
            if (mv == null) return null;
            
            // Inject debug into renderWorld()V
            if (name.equals("renderWorld") && descriptor.equals("()V")) {
                return new DebugAdapter(mv, "DEBUG renderWorld() called");
            }
            // Inject debug into triggerDamageFlash(Ljava/lang/String;)V
            if (name.equals("triggerDamageFlash") && descriptor.equals("(Ljava/lang/String;)V")) {
                return new DebugAdapter(mv, "DEBUG triggerDamageFlash(String) called");
            }
            // Inject debug into spawnEnemy(LGameCanvas$GraphicsEngine;III)V
            if (name.equals("spawnEnemy") && descriptor.equals("(LGraphicsEngine;III)V")) {
                return new DebugAdapter(mv, "DEBUG spawnEnemy() called");
            }
            return mv;
        }
    }
    
    static class DebugAdapter extends MethodVisitor {
        private final String msg;
        
        public DebugAdapter(MethodVisitor mv, String msg) {
            super(Opcodes.ASM9, mv);
            this.msg = msg;
        }
        
        @Override
        public void visitCode() {
            // System.out.println("DEBUG ...");
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn(msg);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            super.visitCode();
        }
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java DebugInjector <input.jar> <output.jar>");
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
            if (entryName.endsWith(".class")) {
                ClassReader cr = new ClassReader(data);
                ClassWriter cw = new ClassWriter(0);
                ClassVisitor cv = new DebugClassVisitor(cw);
                cr.accept(cv, 0);
                byte[] newBytes = cw.toByteArray();
                
                JarEntry newEntry = new JarEntry(entryName);
                outJar.putNextEntry(newEntry);
                outJar.write(newBytes);
                outJar.closeEntry();
                count++;
            } else {
                JarEntry newEntry = new JarEntry(entryName);
                outJar.putNextEntry(newEntry);
                outJar.write(data);
                outJar.closeEntry();
            }
        }
        inJar.close();
        outJar.close();
        System.out.println("Injected debug into " + count + " classes -> " + output);
    }
}
