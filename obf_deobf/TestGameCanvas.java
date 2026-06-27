import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class TestGameCanvas {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Test GameCanvas startup ===");
        
        // Simulate resource loading
        // We'll directly call the methods that would be called during game init
        
        // First, let's see what resources exist in the JAR
        java.util.jar.JarFile jar = new java.util.jar.JarFile("/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/build/package/ZombieInfection_original.jar");
        System.out.println("Resources in JAR:");
        java.util.Enumeration<java.util.jar.JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            java.util.jar.JarEntry e = entries.nextElement();
            if (!e.isDirectory() && !e.getName().endsWith(".class")) {
                System.out.println("  " + e.getName() + " (" + e.getSize() + " bytes)");
            }
        }
        jar.close();
        
        System.out.println("\n=== Done ===");
    }
}
