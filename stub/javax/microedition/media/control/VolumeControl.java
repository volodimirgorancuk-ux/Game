package javax.microedition.media.control;
public interface VolumeControl {
    public static final int LEVEL_MUTED = 0;
    public static final int LEVEL_MAX = 100;
    public int getLevel();
    public boolean isMuted();
    public int setLevel(int level);
    public void setMute(boolean mute);
}
