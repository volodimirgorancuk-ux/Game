package javax.microedition.media;
import java.io.InputStream;
public class Player {
    public static final int UNLOADED = 0;
    public static final int CLOSED = 1;
    public static final int STARTED = 4;
    public void realize() throws Exception {}
    public void prefetch() throws Exception {}
    public void start() throws Exception {}
    public void stop() throws Exception {}
    public void setLoopCount(int count) {}
    public long getTime() { return 0; }
    public void setMediaTime(long now) throws Exception {}
    public long getDuration() { return 0; }
    public String getContentType() { return null; }
    public int getState() { return 0; }
    public Control getControl(String type) { return null; }
    public void close() throws Exception {}
}
