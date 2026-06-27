package javax.microedition.media;
public class VolumeControl implements Control {
    public static final int LEVEL_MUTED = 0;
    public static final int LEVEL_MAX = 100;
    public int getLevel() { return 0; }
    public boolean isMuted() { return false; }
    public int setLevel(int level) { return 0; }
    public void setMute(boolean mute) {}
}
