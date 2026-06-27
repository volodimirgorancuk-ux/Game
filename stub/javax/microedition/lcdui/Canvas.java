package javax.microedition.lcdui;
public class Canvas extends Displayable {
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int FIRE = 16;
    public static final int GAME_A = 32;
    public static final int GAME_B = 64;
    public static final int GAME_C = 128;
    public static final int GAME_D = 256;
    public static final int KEY_NUM0 = 512;
    public static final int KEY_NUM1 = 1024;
    public static final int KEY_NUM2 = 2048;
    public static final int KEY_NUM3 = 4096;
    public static final int KEY_NUM4 = 8192;
    public static final int KEY_NUM5 = 16384;
    public static final int KEY_NUM6 = 32768;
    public static final int KEY_NUM7 = 65536;
    public static final int KEY_NUM8 = 131072;
    public static final int KEY_NUM9 = 262144;
    public static final int KEY_STAR = 524288;
    public static final int KEY_POUND = 1048576;
    public static final int SCREEN_DOWN = 2097152;
    public static final int SCREEN_UP = 4194304;
    public Graphics getGraphics() { return null; }
    public int getKeyCode(int gameAction) { return 0; }
    public int getGameAction(int keyCode) { return 0; }
    public String getKeyName(int keyCode) { return null; }
    public int getKeyCode(String character) { return 0; }
    public void keyPressed(int keyCode) {}
    public void keyRepeated(int keyCode) {}
    public void keyReleased(int keyCode) {}
    protected void pointerPressed(int x, int y) {}
    protected void pointerReleased(int x, int y) {}
    protected void pointerDragged(int x, int y) {}
    protected void showNotify() {}
    protected void hideNotify() {}
    public void repaint() {}
    public void repaint(int x, int y, int width, int height) {}
    public void serviceRepaints() {}
    public int getWidth() { return 240; }
    public int getHeight() { return 320; }
    public boolean hasRepeatEvents() { return false; }
    public void setFullScreenMode(boolean mode) {}
    public boolean isFullScreenMode() { return false; }
    protected void sizeChanged(int w, int h) {}
}
