package javax.microedition.lcdui;
public class Display {
    public static Display getDisplay(javax.microedition.midlet.MIDlet m) { return null; }
    public void setCurrent(Displayable d) {}
    public Displayable getCurrent() { return null; }
    public void callSerially(Runnable r) {}
    public boolean setCurrentItem(Item item) { return false; }
}
