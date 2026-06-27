package javax.microedition.lcdui;
public class Image {
    public static Image createImage(int w, int h) { return new Image(); }
    public static Image createImage(byte[] data, int off, int len) { return new Image(); }
    public static Image createRGBImage(int[] rgb, int w, int h, boolean pr) { return new Image(); }
    public static Image createImage(String name) { return new Image(); }
    public int getWidth() { return 0; }
    public int getHeight() { return 0; }
    public int[] getRGB(int[] rgb, int off, int scanlen, int x, int y, int w, int h) { return null; }
    public Graphics getGraphics() { return null; }
}
