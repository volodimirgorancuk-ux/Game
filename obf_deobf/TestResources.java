import java.lang.reflect.Method;

public class TestResources {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Testing resource loading ===");
        
        // Load the game classes
        ClassLoader cl = new java.net.URLClassLoader(new java.net.URL[] {
            new java.io.File("/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/build/package/ZombieInfection_original.jar").toURI().toURL()
        });
        
        // We need J2ME stubs
        ClassLoader stubCl = new java.net.URLClassLoader(new java.net.URL[] {
            new java.io.File("/workspace/2e169882-ef71-42c0-8852-da4051cfb2af/sessions/agent_5f22a82b-5cc7-42ab-877e-bccd78a79285/stub/javax/microedition/lcdui/Canvas.class").getParentFile().toURI().toURL()
        }, cl);
        
        Class gameCanvas = Class.forName("GameCanvas", true, stubCl);
        
        System.out.println("GameCanvas loaded: " + gameCanvas);
        System.out.println("Methods:");
        for (Method m : gameCanvas.getDeclaredMethods()) {
            if (m.getName().contains("renderWorld") || m.getName().contains("triggerDamageFlash") || m.getName().contains("spawnEnemy")) {
                System.out.println("  " + m.getName() + m.getParameterTypes());
            }
        }
        
        // Try to get the resource array
        java.lang.reflect.Field resourceField = gameCanvas.getDeclaredField("a");
        resourceField.setAccessible(true);
        String[] resources = (String[]) resourceField.get(null);
        System.out.println("\nResources:");
        for (int i = 0; i < resources.length; i++) {
            System.out.println("  [" + i + "] = " + resources[i]);
        }
    }
}
