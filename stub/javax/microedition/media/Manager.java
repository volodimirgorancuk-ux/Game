package javax.microedition.media;
import java.io.InputStream;
public class Manager {
    public static String getSupportedContentType(String filename) { return null; }
    public static Player createPlayer(String locator) throws Exception { return null; }
    public static Player createPlayer(InputStream stream, String type) throws Exception { return null; }
    public static Player createPlayer(byte[] data, int offset, int length, String type) throws Exception { return null; }
}
