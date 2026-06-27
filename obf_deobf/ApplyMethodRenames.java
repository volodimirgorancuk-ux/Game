import org.objectweb.asm.*;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.Remapper;
import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.util.zip.*;

public class ApplyMethodRenames {
    static Map<String, String> methodRenameMap = new HashMap<>();
    
    static class MethodRemapper extends ClassVisitor {
        private final Map<String, String> renameMap;
        private String currentClassName;
        
        public MethodRemapper(ClassVisitor cv, Map<String, String> renameMap) {
            super(Opcodes.ASM9, cv);
            this.renameMap = renameMap;
        }
        
        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            this.currentClassName = name.replace('/', '.');
            super.visit(version, access, name, signature, superName, interfaces);
        }
        
        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            String key = currentClassName + "." + name + "(" + descriptor + ")";
            String newName = renameMap.get(key);
            if (newName != null && !newName.equals(name) && !newName.contains(" ") && !newName.contains("GraphicsEngine") && !newName.contains("GameData")) {
                name = newName;
            }
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
        
        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
            return super.visitField(access, name, descriptor, signature, value);
        }
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java ApplyMethodRenames <input.jar> <mapping.json> [output.jar]");
            System.exit(1);
        }
        
        String input = args[0];
        String mappingFile = args[1];
        String output = args.length > 2 ? args[2] : "fully_renamed.jar";
        
        // Load mapping: simple key -> value parser
        BufferedReader br = new BufferedReader(new FileReader(mappingFile));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        br.close();
        String json = sb.toString().replaceAll("\\s", "");
        
        // Extract "key": "value" pairs
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("\"([^\"]+)\"\\s*:\\s*\"([^\"]*)\"").matcher(json);
        while (m.find()) {
            String key = m.group(1);
            String suggested = m.group(2);
            if (suggested.matches("[a-zA-Z_][a-zA-Z0-9_]*") && !suggested.equals("void")) {
                methodRenameMap.put(key, suggested);
            }
        }
        System.out.println("Loaded " + methodRenameMap.size() + " method mappings");
        
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
                ClassVisitor cv = new MethodRemapper(cw, methodRenameMap);
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
        System.out.println("Output: " + output + " (" + count + " classes)");
    }
}
