package javax.microedition.lcdui;
public class Graphics {
    public void setColor(int color) {}
    public void setColor(int r, int g, int b) {}
    public int getColor() { return 0; }
    public void fillRect(int x, int y, int w, int h) {}
    public void drawRect(int x, int y, int w, int h) {}
    public void fillRoundRect(int x, int y, int w, int h, int rw, int rh) {}
    public void drawRoundRect(int x, int y, int w, int h, int rw, int rh) {}
    public void fillArc(int x, int y, int w, int h, int start, int arc) {}
    public void drawArc(int x, int y, int w, int h, int start, int arc) {}
    public void drawLine(int x1, int y1, int x2, int y2) {}
    public void drawImage(Image img, int x, int y, int anchor) {}
    public void drawRegion(Image src, int x, int y, int w, int h, int sx, int dy, int anchor) {}
    public void drawRGB(int[] rgb, int off, int scanlen, int x, int y, int w, int h, boolean pr) {}
    public void setClip(int x, int y, int w, int h) {}
    public void clipRect(int x, int y, int w, int h) {}
    public int getClipWidth() { return 0; }
    public int getClipHeight() { return 0; }
    public int getClipX() { return 0; }
    public int getClipY() { return 0; }
    public int getDisplayWidth() { return 240; }
    public int getDisplayHeight() { return 320; }
    public void translate(int x, int y) {}
    public Font getFont() { return null; }
}
