import org.objectweb.asm.*;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.Remapper;
import java.io.*;
import java.util.*;
import java.util.jar.*;

public class RenameClasses {
    public static Map<String, String> renameMap = new HashMap<>();
    
    static {
        renameMap.put("a", "GraphicsEngine");
        renameMap.put("b", "GameController");
        renameMap.put("c", "GameData");
        renameMap.put("d", "GameConfig");
        renameMap.put("e", "SensorHandler");
        renameMap.put("f", "AudioManager");
        renameMap.put("g", "GameCanvas");
    }
    
    public static class SimpleRemapper extends ClassRemapper {
        public SimpleRemapper(ClassVisitor cv, Map<String, String> renameMap) {
            super(cv, new SimpleMapper(renameMap));
        }
        
        static class SimpleMapper extends Remapper {
            private final Map<String, String> renameMap;
            public SimpleMapper(Map<String, String> renameMap) {
                this.renameMap = renameMap;
            }
            @Override
            public String map(String type) {
                if (renameMap.containsKey(type)) {
                    return renameMap.get(type);
                }
                return type;
            }
            
            @Override
            public Object mapValue(Object value) {
                if (value instanceof String) {
                    return map((String) value);
                }
                return value;
            }
        }
    }
    
    public static String getClassName(byte[] classBytes) {
        // The class is at offset 7 (u2 this_class), after:
        // magic(4), minor(2), major(2), cp_count(2)
        // But we need to skip the constant pool. A simpler way:
        ClassReader cr = new ClassReader(classBytes);
        return cr.getClassName().replace('/', '.');
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java RenameClasses <input.jar> [output.jar]");
            System.exit(1);
        }
        String input = args[0];
        String output = args.length > 1 ? args[1] : "deobfuscated.jar";
        
        JarFile inJar = new JarFile(input);
        JarOutputStream outJar = new JarOutputStream(new FileOutputStream(output));
        
        Enumeration<JarEntry> entries = inJar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            InputStream is = inJar.getInputStream(entry);
            byte[] data = is.readAllBytes();
            is.close();
            
            String entryName = entry.getName();
            if (entryName.endsWith(".class")) {
                ClassReader cr = new ClassReader(data);
                ClassWriter cw = new ClassWriter(0);
                ClassVisitor cv = new SimpleRemapper(cw, renameMap);
                cr.accept(cv, 0);
                byte[] newBytes = cw.toByteArray();
                
                // Determine new class name
                String oldName = cr.getClassName().replace('/', '.');
                String newName = renameMap.getOrDefault(oldName, oldName);
                String newEntryName = newName.replace('.', '/') + ".class";
                
                JarEntry newEntry = new JarEntry(newEntryName);
                outJar.putNextEntry(newEntry);
                outJar.write(newBytes);
                outJar.closeEntry();
            } else {
                JarEntry newEntry = new JarEntry(entryName);
                outJar.putNextEntry(newEntry);
                outJar.write(data);
                outJar.closeEntry();
            }
        }
        inJar.close();
        outJar.close();
        System.out.println("Output: " + output);
    }
}
