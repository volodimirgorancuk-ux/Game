/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.midlet.MIDlet
 */
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class GloftMASS
extends MIDlet {
    public static GameCanvas a;
    public static GloftMASS a;

    public GloftMASS() {
        a = this;
        Display.getDisplay((MIDlet)this);
        a = new GameCanvas();
    }

    public void startApp() {
        a.l();
    }

    public void pauseApp() {
        GameCanvas.k();
    }

    public void destroyApp(boolean bl) {
        a = null;
        this.notifyDestroyed();
    }
}

