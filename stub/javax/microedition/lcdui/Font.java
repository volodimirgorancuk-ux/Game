package javax.microedition.lcdui;
public class Font {
    public static final int FACE_SYSTEM = 0;
    public static final int FACE_MONOSPACE = 1;
    public static final int FACE_PROPORTIONAL = 2;
    public static final int SIZE_SMALL = 1;
    public static final int SIZE_MEDIUM = 2;
    public static final int SIZE_LARGE = 3;
    public static final int STYLE_PLAIN = 0;
    public static final int STYLE_BOLD = 1;
    public static final int STYLE_ITALIC = 2;
    public static final int STYLE_UNDERLINED = 4;
    public static Font getDefaultFont() { return null; }
    public int getHeight() { return 0; }
    public int getBaselinePosition() { return 0; }
    public int charWidth(char ch) { return 0; }
    public int charsWidth(char[] ch, int offset, int length) { return 0; }
    public int stringWidth(String str) { return 0; }
    public void drawChar(Graphics g, char ch, int x, int y, int anchor) {}
    public void drawChars(Graphics g, char[] ch, int offset, int length, int x, int y, int anchor) {}
    public void drawString(Graphics g, String str, int x, int y, int anchor) {}
    public int substringWidth(String str, int offset, int len) { return 0; }
}
