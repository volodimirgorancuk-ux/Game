package javax.microedition.lcdui;
public class Command {
    public static final int SCREEN = 1;
    public static final int BACK = 2;
    public static final int OK = 256;
    public static final int CANCEL = 512;
    public static final int HELP = 1024;
    public static final int STOP = 2048;
    public static final int EXIT = 4096;
    public static final int ITEM = 8192;
    public Command(String label, int type) { return; }
    public Command(String label, String type) { return; }
    public int getCommandType() { return 0; }
    public String getLabel() { return null; }
}
