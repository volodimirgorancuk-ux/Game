/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.midlet.MIDlet
 *  javax.microedition.rms.RecordStore
 */
import java.io.InputStream;
import java.io.Serializable;
import java.util.Hashtable;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class GameController
implements Runnable,
CommandListener {
    public static String a = "1.9";
    public static String b = "IGP-Signature=" + a;
    private static String c = "";
    private static String d;
    private static boolean c;
    private static Font a;
    private static int a;
    private static int b;
    private static int c;
    private static int d;
    private static int e;
    private static int f;
    private static int g;
    private static int h;
    private static int i;
    private static int j;
    private static int k;
    private static int l;
    private static int m;
    private static int n;
    private static int o;
    private static int p;
    private static int q;
    private static int r;
    private static int s;
    private static int t;
    private static int u;
    private static int v;
    private static int w;
    private static int x;
    private static int[] a;
    private static byte[][] a;
    private static int y;
    private static byte[] a;
    private static int z;
    private static int A;
    private static int B;
    private static int C;
    private static int D;
    private static Image[] a;
    private static int E;
    private static int[] b;
    private static int F;
    private static int G;
    private static boolean d;
    private static final String[] b;
    private static String e;
    private static boolean e;
    public static String[] a;
    private static String[] c;
    private static String[] d;
    private static String[] e;
    private static int H;
    private static int I;
    private static String[] f;
    private static short[] a;
    private static int J;
    private static int K;
    private static int L;
    private static boolean f;
    private static boolean g;
    private static boolean h;
    private static boolean i;
    private static long a;
    private static boolean j;
    private static MIDlet a;
    private static Canvas a;
    private static String f;
    private static boolean k;
    private static CommandListener a;
    public static GameController a;
    private static boolean l;
    private static String g;
    private static boolean m;
    private static int M;
    private static int N;
    private static int O;
    private static int P;
    private static int Q;
    private static int R;
    private static Image[] b;
    private static Image[] c;
    private static Image a;
    private static Image b;
    private static Image c;
    private static Image[][] a;
    private static String[][] a;
    private static int[][] a;
    private static int[][] b;
    private static int[] c;
    private static int S;
    private static int T;
    private static int U;
    private static int V;
    private static int W;
    private static int X;
    private static int Y;
    private static int Z;
    private static int aa;
    private static int ab;
    private static int ac;
    private static String[] g;
    private static boolean[] a;
    private static int ad;
    private static boolean n;
    private static byte a;
    private static boolean o;
    private static int ae;
    private static int af;
    private static int ag;
    private static int ah;
    private static int ai;
    private static int aj;
    private static Image d;
    private static Image e;
    private static String h;
    private static String i;
    private static String j;
    private static int ak;
    private static int al;
    private static int am;
    private static int an;
    private static Command a;
    private static Command b;
    private static int ao;
    private static boolean p;
    private static boolean q;
    private static String k;
    private static Hashtable a;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static String r;
    private static String s;
    private static String t;
    private static String u;
    private static int ap;
    public static boolean a;
    public static boolean b;

    private static boolean render_4() {
        GameController.method_0();
        try {
            InputStream inputStream = "GraphicsEngine".getClass().getResourceAsStream("/dataIGP");
            x = inputStream.read() & 0xFF;
            a = new int[x += (inputStream.read() & 0xFF) << 8];
            int n = 0;
            while (n < x) {
                GameController.a[n] = inputStream.read() & 0xFF;
                int n2 = n;
                a[n2] = a[n2] + ((inputStream.read() & 0xFF) << 8);
                int n3 = n;
                a[n3] = a[n3] + ((inputStream.read() & 0xFF) << 16);
                int n4 = n++;
                a[n4] = a[n4] + ((inputStream.read() & 0xFF) << 24);
            }
            inputStream.close();
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    private static void method_0() {
        a = null;
        a = null;
        x = 0;
        System.gc();
    }

    private static byte[] method_0(int n) {
        if (n < 0 || n >= x - 1) {
            return null;
        }
        int n2 = a[n + 1] - a[n];
        if (n2 == 0) {
            return null;
        }
        if (a != null) {
            return a[n];
        }
        byte[] byArray = null;
        try {
            int n3;
            InputStream inputStream = null;
            inputStream = "GraphicsEngine".getClass().getResourceAsStream("/dataIGP");
            inputStream.skip(2 + 4 * x + a[n]);
            byArray = new byte[n2];
            int n4 = n3 = byArray.length;
            while (n4 > 0) {
                n4 = n3 - inputStream.read(byArray);
            }
            inputStream.close();
        }
        catch (Exception exception) {}
        return byArray;
    }

    private static int method_0(byte[] byArray) {
        return (byArray[y++] & 0xFF) + ((byArray[y++] & 0xFF) << 8);
    }

    private static Image method_0(byte[] byArray) {
        int n = GameController.method_0(byArray);
        Image image = GameController.method_0(byArray, y, n);
        y += n;
        return image;
    }

    private static Image method_0(byte[] byArray, int n, int n2) {
        if (GameConfig.d) {
            byte[] byArray2 = new byte[n2];
            System.arraycopy(byArray, n, byArray2, 0, n2);
            byArray = byArray2;
            n = 0;
        }
        return Image.createImage((byte[])byArray, (int)n, (int)n2);
    }

    private static String method_0(int n) {
        return "" + f[n];
    }

    private static int method_0(int n, int n2) {
        if (ap != 0) {
            if (n2 == A || n2 == B) {
                int n3 = a[n * 6 + n2] & 0xFF;
                int n4 = a[n * 6 + n2 + 1] & 0xFF;
                int n5 = 0;
                n5 = 0 | (n4 & 0xFF) << 8;
                return n5 |= n3 & 0xFF;
            }
            int n6 = a[n * 6 + n2] & 0xFF;
            return n6;
        }
        return a[(n << 2) + n2] & 0xFF;
    }

    private static void method_0(int n, Graphics graphics, int n2, int n3, int n4) {
        GameController.method_0(GameController.method_0(n), graphics, ak, n2, n3, n4);
    }

    private static void method_0(String string, Graphics graphics, int n, int n2, int n3, int n4) {
        GameController.method_0(string, graphics, n2, n3, n4);
    }

    /*
     * Unable to fully structure code
     */
    private static void method_0(String var0, Graphics var1_1, int var2_2, int var3_3, int var4_4) {
        block17: {
            block20: {
                block19: {
                    block18: {
                        var5_5 = var0;
                        GameController.b[0] = 0;
                        GameController.G = 0;
                        var6_6 = 0;
                        var9_7 = false;
                        var10_8 = false;
                        var11_9 = var5_5.length();
                        var12_10 = 0;
                        if (GameController.ak <= 176 && (GameController.O == 0 || GameController.O == 1 || GameController.O == 2)) {
                            var12_10 += 2;
                        }
                        for (var8_11 = 0; var8_11 < var11_9; ++var8_11) {
                            var7_12 = var5_5.charAt(var8_11);
                            if (var7_12 == '\n' && var6_6 < 10 || var7_12 == '\\' && var5_5.charAt(++var8_11) == 'N') {
                                v0 = var6_6;
                                GameController.b[v0] = GameController.b[v0] - -1;
                                if (GameController.b[var6_6] > GameController.G) {
                                    GameController.G = GameController.b[var6_6];
                                }
                                v1 = GameController.b;
                                v2 = ++var6_6;
                                v3 = 0;
                            } else {
                                if (var7_12 == '\u0000' || var7_12 == '\u0001') continue;
                                v4 = var6_6;
                                v2 = v4;
                                v1 = GameController.b;
                                v3 = GameController.b[v4] + (GameController.method_0(var7_12, GameController.C) + -1);
                            }
                            v1[v2] = v3;
                        }
                        v5 = var6_6;
                        GameController.b[v5] = GameController.b[v5] - -1;
                        if (GameController.b[var6_6] > GameController.G) {
                            GameController.G = GameController.b[var6_6];
                        }
                        GameController.F = (var6_6 + 1) * GameController.z + var6_6 * (0 + var12_10);
                        if (GameController.d) break block17;
                        var3_3 += (0 + var12_10) * var6_6 / 2;
                        var6_6 = 0;
                        if ((var4_4 & 32) == 0) break block18;
                        v6 = var3_3;
                        v7 = GameController.F;
                        break block19;
                    }
                    if ((var4_4 & 2) == 0) break block20;
                    v6 = var3_3;
                    v7 = GameController.F >> 1;
                }
                var3_3 = v6 - v7;
            }
            var13_13 = var2_2;
            var9_7 = true;
            var10_8 = false;
            GameController.render_4(var1_1, 0, 0, GameController.ak, GameController.al);
            for (var8_11 = 0; var8_11 < var11_9; ++var8_11) {
                block21: {
                    block24: {
                        block23: {
                            block22: {
                                var7_12 = var5_5.charAt(var8_11);
                                if (!var9_7) break block21;
                                var13_13 = var2_2;
                                if ((var4_4 & 8) == 0) break block22;
                                v8 = var13_13;
                                v9 = GameController.b[var6_6];
                                break block23;
                            }
                            if ((var4_4 & 1) == 0) break block24;
                            v8 = var13_13;
                            v9 = GameController.b[var6_6] >> 1;
                        }
                        var13_13 = v8 - v9;
                    }
                    var9_7 = false;
                }
                if (var7_12 == '\n' && var6_6 < 10 || var7_12 == '\\' && var5_5.charAt(++var8_11) == 'N') {
                    var3_3 += GameController.z + 0 + var12_10 - 2;
                    ++var6_6;
                    var9_7 = true;
                    var10_8 = true;
                    continue;
                }
                if (!var10_8) ** GOTO lbl-1000
                var10_8 = false;
                var14_14 = var5_5.charAt(var8_11 - 2);
                if (var14_14 == ' ') {
                    var13_13 -= GameController.method_0(var14_14, GameController.C) + -1 >> 1;
                }
                if (var7_12 == ' ') {
                    v10 = var13_13;
                    v11 = GameController.method_0(var7_12, GameController.C) + -1 >> 1;
                } else lbl-1000:
                // 2 sources

                {
                    GameController.render_4(var1_1, var13_13, var3_3, GameController.method_0(var7_12, GameController.C), GameController.method_0(var7_12, GameController.D));
                    GameController.method_0(var1_1, GameController.a[GameController.E], GameController.method_0(var7_12, GameController.A), GameController.method_0(var7_12, GameController.B), GameController.method_0(var7_12, GameController.C), GameController.method_0(var7_12, GameController.D), var13_13, var3_3);
                    v10 = var13_13;
                    v11 = GameController.method_0(var7_12, GameController.C) + -1;
                }
                var13_13 = v10 + v11;
            }
            GameController.render_4(var1_1, 0, 0, GameController.ak, GameController.al);
            GameController.E = 0;
            return;
        }
        GameController.d = false;
    }

    public static void method_0(MIDlet mIDlet, Canvas canvas, int n, int n2) {
        GameController.method_0(mIDlet, canvas, n, n2, null);
    }

    private static void method_0(MIDlet mIDlet, Canvas canvas, int n, int n2, CommandListener commandListener) {
        ak = n;
        al = n2;
        am = ak >> 1;
        an = al >> 1;
        if (GameConfig.a < 0 || GameConfig.a > ak / 2 - ak * 15 / 100) {
            b = 2;
        }
        if (a != null) {
            return;
        }
        if (mIDlet == null) {
            System.out.println("MIDlet instance can't be null");
            return;
        }
        if (canvas == null) {
            System.out.println("Canvas instance can't be null");
            return;
        }
        if (commandListener != null) {
            k = true;
            if (a == null) {
                a = new GameController();
            }
            a = commandListener;
        }
        a = mIDlet;
        a = canvas;
        if (GameConfig.i) {
            GameController.resolveURL_8();
        }
        GameController.render_4();
        String string = b;
        String cfr_ignored_0 = string + "";
    }

    private static boolean method_0(String string, int n) {
        if (string == null) {
            return (n & 1) == 0;
        }
        string = string.trim();
        return !((n & 1) != 0 && string.length() == 0 || (n & 2) != 0 && string.toUpperCase().compareTo("DEL") == 0 || (n & 4) != 0 && (string.toUpperCase().compareTo("NO") == 0 || string.toUpperCase().compareTo("0") == 0));
    }

    private static String method_0(String string, String string2, String string3) {
        String string4 = "";
        try {
            if (string3 != null && string != null && string2 != null) {
                int n = string.indexOf(string2 + "=");
                string3 = string3.trim();
                if (n >= 0 && string3.length() > 0) {
                    int n2 = string.indexOf(";", n += string2.length() + 1);
                    if (n2 < 0) {
                        n2 = string.length();
                    }
                    string4 = string.substring(n, n2);
                    if ((string4 = string4.trim()).length() == 0 || string4.compareTo("0") == 0 || string4.toUpperCase().compareTo("NO") == 0) {
                        string4 = "";
                    } else if (string4.toUpperCase().compareTo("DEL") != 0 && string2.compareTo("OP") != 0) {
                        int n3 = string3.indexOf("XXXX");
                        string4 = string3.substring(0, n3) + string4 + string3.substring(n3 + "XXXX".length());
                    }
                }
            }
        }
        catch (Exception exception) {
            string4 = "";
        }
        return string4;
    }

    private static void method_0(int n, String string, int n2, String string2, String string3) {
        try {
            String string4 = null;
            string4 = e ? GameController.method_0(GameController.method_0(string2), string, string3) : GameController.method_0("URL-" + string);
            boolean bl = GameController.method_0(string4, n2);
            if (bl && (string4.toUpperCase().compareTo("NO") != 0 || string4.toUpperCase().compareTo("0") != 0)) {
                GameController.g[n] = string4;
                GameController.a[n] = true;
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    /*
     * Unable to fully structure code
     */
    private static void method_0(int var0, String[] var1_1, int var2_2, String var3_3) {
        var4_4 = var1_1.length;
        GameController.a[var0] = new String[var4_4];
        GameController.a[var0] = new int[var4_4];
        GameController.b[var0] = new int[var4_4];
        var5_5 = 0;
        if (GameController.c) {
            return;
        }
        var6_6 = "";
        if (GameController.e) {
            try {
                var3_3 = GameController.method_0(var3_3);
                if (var0 != 2) {
                    var6_6 = GameController.e;
                } else if (GameController.j.length() > 0) {
                    var6_6 = GameController.j + "&ctg=XXXX";
                }
            }
            catch (Exception v0) {}
        }
        for (var7_7 = 0; var7_7 < var1_1.length; ++var7_7) {
            try {
                block16: {
                    block14: {
                        block15: {
                            var8_8 = "";
                            if (var0 == 2 || var7_7 != var4_4 - 1) break block14;
                            if (GameController.e) break block15;
                            v1 = GameController.b[var0];
                            ** GOTO lbl35
                        }
                        if (GameController.j.length() > 0) {
                            v2 = GameController.method_0(GameController.method_0("IGP-CATEGORIES"), var1_1[var7_7], GameController.j + "&ctg=XXXX");
                        }
                        break block16;
                    }
                    if (GameController.e) {
                        v2 = var1_1[var7_7].compareTo("GLDT") == 0 ? GameController.method_0(var3_3, var1_1[var7_7], GameController.e) : (var1_1[var7_7].compareTo("CATALOG") == 0 ? GameController.j : GameController.method_0(var3_3, var1_1[var7_7], var6_6));
                    } else {
                        v1 = GameController.b[var0] + "-" + var1_1[var7_7];
lbl35:
                        // 2 sources

                        v2 = var8_8 = GameController.method_0(v1);
                    }
                }
                if (!GameController.method_0(var8_8, 7)) continue;
                GameController.a[var0][var5_5] = var8_8;
                GameController.a[var0][var5_5++] = var7_7;
                GameController.b[var0][var7_7] = var2_2 + var7_7;
                continue;
            }
            catch (Exception v3) {}
        }
        if (var5_5 > 0) {
            GameController.a[4 + var0] = true;
            GameController.c[var0] = var5_5;
        }
    }

    private static String[] method_0(byte[] byArray) {
        String[] stringArray = new String[GameController.method_0(byArray)];
        for (int i = 0; i < stringArray.length; ++i) {
            int n = GameController.method_0(byArray);
            stringArray[i] = new String(byArray, y, n);
            y += n;
        }
        return stringArray;
    }

    private static void method_0(String[] stringArray) {
        int n = (stringArray.length - 1 > 0 ? stringArray.length - 1 : 0) + 4;
        f = ++n;
        g = f + 1;
        h = g + 1;
        i = h + 1;
        j = i + 1;
        k = j + 1;
        l = k + 1;
        m = l + 1;
        GameController.n = m + 1;
        o = GameController.n + 1;
        p = o + 1;
        q = p + 1;
        r = q + 1;
        s = r + 1;
        t = s + 1;
        u = t + c.length;
    }

    private static void render_4() {
        block57: {
            block59: {
                block58: {
                    block50: {
                        int n;
                        String[] stringArray;
                        int n2;
                        try {
                            n2 = GameController.render_4();
                            if (n2 == 0) {
                                m = false;
                                return;
                            }
                            byte[] byArray = GameController.method_0(0);
                            GameController.method_0(byArray);
                            a = GameController.method_0(byArray);
                            stringArray = GameController.method_0(byArray);
                            c = GameController.method_0(byArray);
                            d = GameController.method_0(byArray);
                            e = GameController.method_0(byArray);
                            a = GameController.method_0(byArray) == 1;
                            b = GameController.method_0(byArray) == 1;
                            try {
                                n = GameController.method_0(byArray);
                                c = new String(byArray, y, n);
                                if (c.equals("1.9z")) {
                                    ap = 2;
                                    J = 12;
                                    K = 6;
                                    B = 2;
                                    C = 4;
                                    D = 5;
                                }
                                c.startsWith(a);
                            }
                            catch (Exception exception) {
                                m = false;
                            }
                            GameController.method_0();
                        }
                        catch (Exception exception) {
                            m = false;
                            return;
                        }
                        GameController.method_0(stringArray);
                        H = stringArray.length;
                        I = H + 5;
                        g = new String[9];
                        a = new boolean[9];
                        for (n2 = 0; n2 < a.length; ++n2) {
                            GameController.a[n2] = false;
                        }
                        a = new String[3][];
                        a = new int[3][];
                        b = new int[3][];
                        c = new int[3];
                        if (GameConfig.j) {
                            c = true;
                        } else {
                            try {
                                String string = GameController.method_0("URL-ORANGE");
                                int n3 = Integer.parseInt(string);
                                if (!a && n3 == 1) {
                                    throw new RuntimeException("Trying load Orange France but Resources are not present on dataIGP\nHave you checked include Orange France option at IGPDataConfig.properties file?");
                                }
                                c = a && n3 == 1;
                            }
                            catch (Exception exception) {}
                        }
                        try {
                            e = GameController.method_0("URL-TEMPLATE-GAME").trim();
                            e = true;
                        }
                        catch (Exception exception) {}
                        for (int i = 0; i < H; ++i) {
                            GameController.method_0(i, stringArray[i], 7, "IGP-PROMOS", e);
                        }
                        String string = null;
                        try {
                            string = GameController.method_0("URL-OPERATOR");
                            if (GameController.method_0(string, 7)) {
                                j = string;
                            }
                            String string2 = GameController.method_0("URL-PT");
                            n = 0;
                            int n4 = 0;
                            if (string2.length() > 50) {
                                string2 = null;
                            }
                            if (string2 == null) break block50;
                            h = "";
                            int n5 = string2.length();
                            for (int i = 0; i < n5; ++i) {
                                block51: {
                                    String string3;
                                    StringBuffer stringBuffer;
                                    block53: {
                                        StringBuffer stringBuffer2;
                                        block56: {
                                            char c;
                                            block54: {
                                                block55: {
                                                    block52: {
                                                        c = string2.charAt(i);
                                                        if ((c < ' ' || c > 'z') && c != '\u0082' && c != '\n') break block51;
                                                        if (c == ' ') {
                                                            n4 = i;
                                                        }
                                                        if (i >= n5 - 1 || c != '\\' || string2.charAt(i + 1) != 'n' && string2.charAt(i + 1) != 'N') break block52;
                                                        if (h.length() > 0) {
                                                            if (n != 0) {
                                                                h = h + " ";
                                                            } else {
                                                                h = h + '\n';
                                                                n = 1;
                                                            }
                                                        }
                                                        ++i;
                                                        break block53;
                                                    }
                                                    if (c != '\n') break block54;
                                                    if (h.length() <= 0) break block53;
                                                    if (n == 0) break block55;
                                                    stringBuffer2 = new StringBuffer().append(h).append(" ");
                                                    break block56;
                                                }
                                                h = h + '\n';
                                                n = 1;
                                                break block53;
                                            }
                                            stringBuffer2 = new StringBuffer().append(h).append(c);
                                        }
                                        h = stringBuffer2.toString();
                                    }
                                    if (i != 26 || n != 0) continue;
                                    if (n4 == i) {
                                        stringBuffer = new StringBuffer().append(h);
                                        string3 = "\n";
                                    } else {
                                        stringBuffer = new StringBuffer().append(h.substring(0, n4)).append("\n");
                                        string3 = h.substring(n4 + 1, h.length());
                                    }
                                    h = stringBuffer.append(string3).toString();
                                    n = 1;
                                    continue;
                                }
                                h = null;
                                break;
                            }
                            if (h != null && !GameController.method_0(h = h.toUpperCase(), 7)) {
                                h = null;
                            }
                        }
                        catch (Exception exception) {}
                    }
                    if (!c) {
                        if (e) {
                            if (GameController.method_0(j, 7)) {
                                GameController.method_0(H, "PROMO", 7, "IGP-CATEGORIES", j + "&ctg=XXXX");
                            }
                        } else {
                            String string = GameController.method_0("URL-PROMO");
                            if (string != null) {
                                string.trim();
                                if (GameController.method_0(GameController.method_0("URL-PROMO"), 7)) {
                                    GameController.g[3] = string;
                                    GameController.a[3] = true;
                                }
                            }
                        }
                    }
                    GameController.method_0(0, c, t, "IGP-WN");
                    GameController.method_0(1, d, u, "IGP-BS");
                    if (c) break block57;
                    if (!e) break block58;
                    if (!GameController.method_0(GameController.method_0(GameController.method_0("IGP-CATEGORIES"), "OP", j), 7)) break block57;
                    GameController.g[6] = j;
                    if (!GameController.method_0(j, 7)) break block57;
                    break block59;
                }
                if (!GameController.method_0(j, 7)) break block57;
                GameController.g[6] = j;
            }
            GameController.a[6] = true;
        }
        if (c) {
            try {
                if (GameController.method_0(j, 7)) {
                    GameController.g[7] = j;
                    GameController.a[7] = true;
                }
            }
            catch (Exception exception) {}
        }
        try {
            String string;
            k = null;
            if (!e) {
                string = GameController.method_0("URL-GLIVE").trim();
            } else if (GameController.method_0(j, 7)) {
                string = k = GameController.method_0(GameController.method_0("IGP-CATEGORIES"), "GLIVE", j + "&ctg=XXXX");
            }
            if (GameController.method_0(k, 7) && b) {
                GameController.g[8] = k;
                GameController.a[8] = true;
            } else {
                GameController.g[8] = null;
                GameController.a[8] = false;
            }
        }
        catch (Exception exception) {
            GameController.g[8] = null;
            GameController.a[8] = false;
        }
        ac = GameController.render_4();
        if (ac > 0) {
            m = true;
        }
    }

    public static int method_0() {
        if (!m) {
            return -1;
        }
        if (a[8]) {
            if (GameController.render_4() > 1) {
                return 2;
            }
            return 1;
        }
        if (GameController.render_4() > 0) {
            return 0;
        }
        return -1;
    }

    public static boolean method_0() {
        return GameController.method_0() != -1;
    }

    public static void method_0(String string, int n) {
        block6: {
            int n2;
            block5: {
                block4: {
                    GameController.render_4(string, n);
                    if (GameConfig.b != 0) break block4;
                    n2 = 4 + H + 1 + 1 + 1 + 1 + 1 + 1;
                    break block5;
                }
                R = 5;
                if (GameConfig.b != 1) break block6;
                n2 = R;
            }
            R = n2 + 1;
        }
        GameController.persist_6();
        O = GameController.render_1();
        if (k) {
            a.setCommandListener((CommandListener)a);
        }
    }

    private static void render_4(String string, int n) {
        if (n < 0 || n >= a.length) {
            return;
        }
        aj = n <= a.length ? n : 0;
        i = string;
        a = 0xFF0000;
        Q = -1;
        M = 0;
        P = -1;
        O = 0;
        V = 0;
        W = 0;
        b = new int[10];
        l = true;
        a = Font.getFont((int)0, (int)0, (int)8);
        c = al * 5 / 100;
        d = al / 2;
        e = al * 92 / 100;
        if (GameConfig.c == 2) {
            new Thread(new GameController()).start();
        }
    }

    private static int render_4() {
        int n = 0;
        for (int i = 0; i < a.length; ++i) {
            if (!a[i]) continue;
            ++n;
        }
        return n;
    }

    private static int render_1() {
        for (int i = 0; i < a.length; ++i) {
            if (!a[i]) continue;
            return i;
        }
        return -1;
    }

    private static int render_4(int n, int n2) {
        if (n <= n2 - 1) {
            return n - 1;
        }
        if (n > I + n2 + 1 - 1) {
            return 12;
        }
        if ((n -= n2) < H) {
            return 4;
        }
        if ((n -= H) == 0) {
            return 5;
        }
        return 7 + n - 1;
    }

    private static void method_0(int n) {
        byte[] byArray = null;
        y = 0;
        int n2 = GameController.render_4(n, 5);
        int n3 = n - 1;
        int n4 = n - 5;
        if (n4 > 0 && n < I + 4) {
            if (n == 0 && !a[n4]) {
                return;
            }
            GameController.c[n4] = b[10];
        }
        switch (n2) {
            case -1: {
                b = new Image[w];
                c = new Image[9];
                a = new Image[3][];
                GameController.a[0] = new Image[c.length];
                GameController.a[1] = new Image[d.length];
                GameController.a[2] = new Image[e.length];
                return;
            }
            case 0: {
                GameController.render_4();
                return;
            }
            case 1: {
                int n5;
                int n6;
                byArray = GameController.method_0(n3);
                for (n6 = 0; n6 < aj; ++n6) {
                    n5 = GameController.method_0(byArray);
                    y += n5;
                }
                GameController.method_0(byArray);
                n5 = GameController.method_0(byArray);
                f = new String[n5];
                byte[] byArray2 = new byte[n5];
                System.arraycopy(byArray, y, byArray2, 0, n5);
                y += n5;
                GameController.method_0(byArray);
                int n7 = byArray[y++] & 0xFF | (byArray[y++] & 0xFF) << 8;
                a = new short[n7];
                for (n6 = 0; n6 < n7 - 1; ++n6) {
                    GameController.a[n6] = (short)((byArray[y++] & 0xFF) + ((byArray[y++] & 0xFF) << 8));
                }
                GameController.a[n7 - 1] = (short)n5;
                for (n6 = 0; n6 < n7; ++n6) {
                    Serializable serializable;
                    int n8 = n6 == 0 ? 0 : a[n6 - 1] & 0xFFFF;
                    int n9 = (a[n6] & 0xFFFF) - n8;
                    if (n9 == 0) continue;
                    try {
                        if (GameConfig.e) {
                            GameController.f[n6] = new String(byArray2, n8, n9, "UTF-8");
                            continue;
                        }
                        serializable = new StringBuffer(n9 / 2 + 2);
                        int n10 = n8;
                        while (n10 < n8 + n9) {
                            if ((byArray2[n10] & 0x80) == 0) {
                                ((StringBuffer)serializable).append((char)(byArray2[n10++] & 0xFF));
                            } else if ((byArray2[n10] & 0xE0) == 192) {
                                if (n10 + 1 >= n8 + n9 || (byArray2[n10 + 1] & 0xC0) != 128) {
                                    throw new Exception();
                                }
                                ((StringBuffer)serializable).append((char)((byArray2[n10++] & 0x1F) << 6 | byArray2[n10++] & 0x3F));
                            } else if ((byArray2[n10] & 0xF0) == 224) {
                                if (n10 + 2 >= n8 + n9 || (byArray2[n10 + 1] & 0xC0) != 128 || (byArray2[n10 + 2] & 0xC0) != 128) {
                                    throw new Exception();
                                }
                                ((StringBuffer)serializable).append((char)((byArray2[n10++] & 0xF) << 12 | (byArray2[n10++] & 0x3F) << 6 | byArray2[n10++] & 0x3F));
                            } else {
                                throw new Exception();
                            }
                            GameController.f[n6] = ((StringBuffer)serializable).toString().toUpperCase();
                        }
                        continue;
                    }
                    catch (Exception exception) {
                        serializable = exception;
                        exception.printStackTrace();
                    }
                }
                if (!k) break;
                a = new Command(GameController.method_0(j), 4, 1);
                b = new Command(GameController.method_0(k), 2, 1);
                GameController.method_0(true, true);
                return;
            }
            case 2: {
                Image image;
                int n11;
                Image[] imageArray;
                a = new Image[2];
                byArray = GameController.method_0(n3);
                int n12 = GameController.method_0(byArray);
                y = 0;
                GameController.a[0] = GameController.method_0(byArray);
                if (GameConfig.b) {
                    imageArray = a;
                    n11 = 1;
                    image = GameController.render_4(byArray, 2, n12, 1, 0xFF0000);
                } else {
                    imageArray = a;
                    n11 = 1;
                    image = a[0];
                }
                imageArray[n11] = image;
                int n13 = GameController.method_0(byArray);
                int n14 = GameController.method_0(byArray);
                a = new byte[(n14 + 1) * (4 + ap)];
                int n15 = n13 / (6 + ap);
                for (int i = 0; i < n15; ++i) {
                    int n16 = GameController.method_0(byArray);
                    System.arraycopy(byArray, y, a, n16 *= 4 + ap, 4 + ap);
                    y += 4 + ap;
                }
                z = a[32 * (4 + ap) + D];
                return;
            }
            case 3: {
                byArray = GameController.method_0(n3);
                for (int i = 0; i < w; ++i) {
                    if (!GameConfig.h && (i == 13 || i == 12)) continue;
                    GameController.b[i] = GameController.method_0(byArray);
                }
                if (c == null) break;
                GameController.b[9] = b;
                GameController.b[8] = c;
                return;
            }
            case 12: {
                GameController.method_0();
                return;
            }
            default: {
                int n17 = y;
                int n18 = x;
                y = n17;
                x = n18;
                GameController.method_0(n2, n3, n4);
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    private static void method_0(int var0, int var1_1, int var2_2) {
        var3_3 = null;
        var4_4 = 0;
        var5_5 = 0;
        switch (var0) {
            case 4: 
            case 6: {
                v0 = GameController.c;
                v1 = var2_2;
                v2 = GameController.method_0(GameController.method_0(var1_1));
                ** GOTO lbl59
            }
            case 5: {
                var3_3 = GameController.method_0(var1_1);
                GameController.e = GameController.method_0(var3_3);
                return;
            }
            case 7: {
                var5_5 = GameController.c.length - 1;
            }
            case 8: {
                if (var0 == 8) {
                    var5_5 = GameController.d.length - 1;
                }
                var4_4 = var0 - 7;
                var3_3 = GameController.method_0(var1_1);
                for (var6_6 = 0; var6_6 < var5_5; ++var6_6) {
                    GameController.a[var4_4][var6_6] = GameController.method_0(var3_3);
                }
                v0 = GameController.a[var4_4];
                v1 = var6_6;
                v2 = GameController.b[11];
                ** GOTO lbl59
            }
            case 9: {
                var1_1 = GameController.v = 4 + GameController.H;
                var3_3 = GameController.method_0(var1_1);
                v0 = GameController.c;
                v1 = var2_2;
                v3 = var3_3;
                v4 = 2;
                v5 = var3_3.length - 2;
                v6 = 0xFF3300;
                v7 = 22923;
                ** GOTO lbl58
            }
            case 10: {
                if (!GameController.c) break;
                var7_7 = GameController.x - 2;
                var3_3 = GameController.method_0(var7_7);
                GameController.c[var2_2] = GameController.method_0(var3_3);
                GameController.d = GameController.method_0(var3_3);
                return;
            }
            case 11: {
                var7_8 = GameController.x - 1 - (GameController.a != false ? 2 : 1);
                var3_3 = GameController.method_0(var7_8);
                GameController.a = GameController.method_0(var3_3);
                var3_3 = GameController.method_0(4 + GameController.H);
                v0 = GameController.c;
                v1 = 8;
                v3 = var3_3;
                v4 = 2;
                v5 = var3_3.length - 2;
                v6 = 0xFF3300;
                v7 = 16760064;
lbl58:
                // 2 sources

                v2 = GameController.method_0(v3, v4, v5, v6, v7);
lbl59:
                // 3 sources

                v0[v1] = v2;
            }
        }
    }

    private static void render_4(boolean bl) {
        int n;
        e = null;
        for (n = 0; n < a.length; ++n) {
            if (a[n] == null) continue;
            for (int i = 0; i < a[n].length; ++i) {
                GameController.a[n][i] = null;
            }
        }
        for (n = 0; n < c.length; ++n) {
            GameController.c[n] = null;
        }
        a = null;
        if (bl) {
            GameController.method_0();
            a = null;
            a = null;
            for (n = 0; n < w; ++n) {
                GameController.b[n] = null;
            }
            b = null;
            a = null;
            f = null;
            b = null;
            i = null;
            c = null;
            d = null;
            a = null;
            b = null;
            c = null;
        }
        System.gc();
    }

    public static void method_0(boolean bl) {
        if (bl) {
            if (M == 0 || M == 2) {
                P = M;
                M = 5;
                return;
            }
        } else if (M == 5) {
            M = P;
            Q = -1;
        }
    }

    private static int method_0(byte[] byArray, int n, int n2, String string) {
        for (int i = n; i < n2 - 4; ++i) {
            if ((byArray[i] & 0xFF) != string.charAt(0) || (byArray[i + 1] & 0xFF) != string.charAt(1) || (byArray[i + 2] & 0xFF) != string.charAt(2) || (byArray[i + 3] & 0xFF) != string.charAt(3)) continue;
            return i;
        }
        return -1;
    }

    private static Image method_0(byte[] byArray, int n, int n2, int n3, int n4) {
        int n5;
        int[] nArray = new int[10];
        int[] nArray2 = new int[10];
        int n6 = 0;
        for (n5 = 0; n5 < nArray.length; ++n5) {
            nArray[n5] = -1;
            nArray2[n5] = -1;
        }
        n5 = GameController.method_0(byArray, n, n2, "PLTE");
        int n7 = GameController.method_0(byArray, n, n2, "tRNS");
        Image image = null;
        if (n5 > 0 && n7 > 0) {
            long l;
            int n8;
            int n9;
            int n10;
            int n11;
            int n12 = (byArray[n5 - 4] << 24 & 0xFF000000) + (byArray[n5 - 3] << 16 & 0xFF0000) + (byArray[n5 - 2] << 8 & 0xFF00) + (byArray[n5 - 1] << 0 & 0xFF);
            boolean bl = false;
            for (n11 = 0; n11 < n12 / 3; ++n11) {
                if (byArray[n7 + 4 + n11] == 0) continue;
                n10 = byArray[n5 + 4 + 3 * n11] & 0xFF;
                n9 = byArray[n5 + 4 + 3 * n11 + 1] & 0xFF;
                n8 = byArray[n5 + 4 + 3 * n11 + 2] & 0xFF;
                if (n10 == 255 || n9 == 255 || n8 == 255) {
                    bl = true;
                    break;
                }
                if (n10 != 0 && n9 != 0 && n8 != 0) continue;
                bl = true;
                break;
            }
            if (!bl) {
                n11 = (n3 & 0xFF0000) >> 24 & 0xFF;
                n10 = (n3 & 0xFF0000) >> 16 & 0xFF;
                n9 = (n3 & 0xFF00) >> 8 & 0xFF;
                n8 = n3 & 0xFF;
                n10 = n10 == 255 ? 254 : n10;
                n9 = n9 == 255 ? 254 : n9;
                n8 = n8 == 255 ? 254 : n8;
                n10 = n10 == 0 ? 1 : n10;
                n9 = n9 == 0 ? 1 : n9;
                n8 = n8 == 0 ? 1 : n8;
                n3 = 0 | (n11 & 0xFF) << 24;
                n3 |= (n10 & 0xFF) << 16;
                n3 |= (n9 & 0xFF) << 8;
                n3 |= n8 & 0xFF;
            }
            for (n11 = 0; n11 < n12 / 3; ++n11) {
                if (byArray[n7 + 4 + n11] == 0 || (byArray[n5 + 4 + 3 * n11] & 0xFF) != ((n3 & 0xFF0000) >> 16 & 0xFF) || (byArray[n5 + 4 + 3 * n11 + 1] & 0xFF) != ((n3 & 0xFF00) >> 8 & 0xFF) || (byArray[n5 + 4 + 3 * n11 + 2] & 0xFF) != (n3 & 0xFF)) continue;
                nArray[n6] = n11;
                n10 = GameController.method_0(byArray, n5 + 4 + 3 * n11, 3);
                GameController.method_0(byArray, n5 + 4 + 3 * n11, 3, n4);
                nArray2[n6] = n10;
                ++n6;
            }
            byte[] byArray2 = new byte[n12 + 4];
            System.arraycopy(byArray, n5, byArray2, 0, n12 + 4);
            long[] lArray = new long[256];
            for (n9 = 0; n9 < 256; ++n9) {
                l = n9;
                for (n8 = 0; n8 < 8; ++n8) {
                    l = (l & 1L) == 1L ? 0xEDB88320L ^ l >> 1 : l >> 1;
                }
                lArray[n9] = l;
            }
            l = 0xFFFFFFFFL;
            for (n9 = 0; n9 < byArray2.length; ++n9) {
                l = lArray[(int)(l ^ (long)byArray2[n9]) & 0xFF] ^ l >> 8;
            }
            n9 = GameController.method_0(byArray, n5 + 4 + n12, 4);
            GameController.method_0(byArray, n5 + 4 + n12, 4, (int)(l ^= 0xFFFFFFFFL));
            System.gc();
            if (GameConfig.d) {
                byte[] byArray3 = new byte[n2];
                System.arraycopy(byArray, n, byArray3, 0, n2);
                byArray = byArray3;
                n = 0;
            }
            image = Image.createImage((byte[])byArray, (int)n, (int)n2);
            for (int i = 0; i < n6; ++i) {
                int n13 = nArray[i];
                int n14 = nArray2[i];
                GameController.method_0(byArray, n5 + 4 + 3 * n13, 3, n14);
            }
            GameController.method_0(byArray, n5 + 4 + n12, 4, n9);
        }
        return image;
    }

    private static void method_0(byte[] byArray, int n, int n2, int n3) {
        for (int i = n2 - 1; i >= 0; --i) {
            byArray[n + n2 - 1 - i] = (byte)((n3 & 255 << 8 * i) >> 8 * i);
        }
    }

    private static int method_0(byte[] byArray, int n, int n2) {
        int n3 = 0;
        for (int i = n2 - 1; i >= 0; --i) {
            n3 += byArray[n + n2 - 1 - i] << 8 * i & 255 << 8 * i;
        }
        return n3;
    }

    /*
     * Unable to fully structure code
     */
    public static boolean method_0(int var0) {
        if (!GameController.m) {
            return true;
        }
        if (GameConfig.h && GameController.p) {
            GameController.p = false;
        } else {
            GameController.N = var0;
        }
        switch (GameController.M) {
            case 0: {
                if (GameController.Q >= GameController.R) {
                    GameController.M = 1;
                    GameController.render_1();
                    if (GameConfig.b == 0) {
                        for (var1_1 = 0; var1_1 < GameController.c.length; ++var1_1) {
                            if (GameController.c == null || GameController.c[var1_1] == null) continue;
                            var2_4 = GameController.c[var1_1].getWidth();
                            if (var2_4 > GameController.ak) {
                                throw new RuntimeException("IGP::Page " + var1_1 + " image width bigger than Screen width(" + GameController.ak + ")");
                            }
                            var3_7 = GameController.c[var1_1].getHeight();
                            if (var3_7 <= GameController.al) continue;
                            throw new RuntimeException("IGP::Page " + var1_1 + " image height bigger than Screen height(" + GameController.al + ")");
                        }
                    } else if (GameController.c != null && GameController.c[GameController.O] != null) {
                        var1_2 = GameController.c[GameController.O].getWidth();
                        if (var1_2 > GameController.ak) {
                            throw new RuntimeException("IGP::Page " + GameController.O + " image width bigger than Screen width(" + GameController.ak + ")");
                        }
                        var2_5 = GameController.c[GameController.O].getHeight();
                        if (var2_5 > GameController.al) {
                            throw new RuntimeException("IGP::Page " + GameController.O + " image height bigger than Screen height(" + GameController.al + ")");
                        }
                    }
                } else {
                    GameController.method_0(GameConfig.b != 0 && GameController.Q == 6 ? GameController.I + 5 - 1 : GameController.Q);
                }
                ++GameController.Q;
                break;
            }
            case 1: {
                switch (GameController.N) {
                    case 26: {
                        v0 = 4;
                        ** GOTO lbl85
                    }
                    case 23: {
                        if (GameController.ac <= 1) break;
                        if (GameController.O != 0) ** GOTO lbl43
                        v1 = 8;
                        ** GOTO lbl46
lbl43:
                        // 1 sources

                        v2 = GameController.O;
                        block22: while (true) {
                            v1 = v2 - 1;
                            while (!GameController.a[GameController.O = v1]) {
                                if (GameController.O == 0) {
                                    v1 = 8;
                                    continue;
                                }
                                v2 = GameController.O;
                                continue block22;
                            }
                            break;
                        }
                        GameController.f = true;
                    }
                    case 24: {
                        if (GameController.ac <= 1) break;
                        if (GameController.f) ** GOTO lbl71
                        if (GameController.O != 8) ** GOTO lbl60
                        v3 = 0;
                        ** GOTO lbl63
lbl60:
                        // 1 sources

                        v4 = GameController.O;
                        block24: while (true) {
                            v3 = v4 + 1;
                            while (!GameController.a[GameController.O = v3]) {
                                if (GameController.O == 8) {
                                    v3 = 0;
                                    continue;
                                }
                                v4 = GameController.O;
                                continue block24;
                            }
                            break;
                        }
                        GameController.g = true;
lbl71:
                        // 2 sources

                        GameController.V = 0;
                        GameController.W = 0;
                        GameController.render_1();
                        break;
                    }
                    case 32: {
                        if (!GameController.o || GameController.W >= GameController.T - 1 || ++GameController.W - GameController.V < GameController.U) break;
                        ++GameController.V;
                        break;
                    }
                    case 21: {
                        if (!GameController.o || GameController.W <= 0 || --GameController.W - GameController.V >= 0) break;
                        --GameController.V;
                        break;
                    }
                    case 25: 
                    case 27: {
                        v0 = 6;
lbl85:
                        // 2 sources

                        GameController.M = v0;
                    }
                }
                break;
            }
            case 6: {
                var1_3 = null;
                var1_3 = GameController.g[GameController.O];
                if (GameController.o) {
                    var1_3 = GameController.a[GameController.S][GameController.W];
                }
                if (var1_3 == null || var1_3.length() <= 0) break;
                if (GameConfig.c == 2) {
                    GameController.g = var1_3;
                    break;
                }
                GameController.d = var1_3;
                break;
            }
            case 2: {
                GameController.render_4(false);
                if (GameConfig.b == 1) {
                    GameController.render_4();
                }
                GameController.Q = 5 + GameController.O;
                GameController.method_0(GameController.Q);
                if (GameController.c != null && GameController.c[GameController.O] != null) {
                    var2_6 = GameController.c[GameController.O].getWidth();
                    if (var2_6 > GameController.ak) {
                        throw new RuntimeException("IGP::Page " + GameController.O + " image width bigger than Screen width(" + GameController.ak + ")");
                    }
                    var3_8 = GameController.c[GameController.O].getHeight();
                    if (var3_8 > GameController.al) {
                        throw new RuntimeException("IGP::Page " + GameController.O + " image height bigger than Screen height(" + GameController.al + ")");
                    }
                }
                if (GameConfig.b == 1) {
                    GameController.method_0();
                }
                GameController.M = 1;
                break;
            }
            case 5: {
                break;
            }
            case 3: {
                switch (GameController.N) {
                    case 26: {
                        GameController.M = 1;
                        GameController.f = null;
                        break;
                    }
                    case 25: 
                    case 27: {
                        if (GameConfig.c == 2) {
                            GameController.g = GameController.f;
                            break;
                        }
                        GameController.d = GameController.f;
                    }
                }
                break;
            }
            case 4: {
                GameController.render_4(true);
                if (GameController.k) {
                    GameController.a.setCommandListener(GameController.a);
                    GameController.update_4();
                }
                GameController.l = false;
                return true;
            }
        }
        return false;
    }

    private static void render_1() {
        if (GameConfig.b == 1) {
            M = 2;
        }
        a = 0;
        ad = O;
        W = 0;
        T = 0;
        V = 0;
        o = false;
        boolean bl = n = g[O] != null && g[O].length() > 0 && g[O].compareTo("DEL") != 0;
        if (O == 4) {
            S = 0;
            o = true;
            n = false;
        }
        if (O == 5) {
            S = 1;
            o = true;
            n = false;
        }
        if (O == 6 || O == 7) {
            n = true;
        }
        if (n || o) {
            a = (byte)(a | 1);
        }
        a = (byte)(a | 2);
        int n = ae = GameConfig.h ? l : h;
        if (O == 0 || O == 1 || O == 2) {
            int n2 = ae = GameConfig.h ? m : i;
        }
        if (o) {
            T = c[S];
            int n3 = ae = GameConfig.h ? m : i;
            if (O == 6) {
                GameController.n = true;
                int n4 = ae = GameConfig.h ? l : h;
            }
        }
        if (O == 8) {
            a = (byte)(a | 3);
        }
    }

    /*
     * Unable to fully structure code
     */
    public static void method_0(Graphics var0) {
        if (!GameController.m) {
            return;
        }
        if (GameController.d != null && GameConfig.c == 1) {
            GameController.resolveURL_7();
            return;
        }
        var1_1 = var0;
        GameController.render_4(var0, 0, 0, GameController.ak, GameController.al);
        GameController.render_4(var1_1, 0, 0, GameController.ak, GameController.al);
        switch (GameController.M) {
            case 0: {
                var0.setColor(0);
                var0.fillRect(0, 0, GameController.ak, GameController.al);
                GameController.method_0(var0, GameController.an, GameController.ak * 3 / 4, GameController.Q, GameController.R);
                if (GameController.i == null) break;
                if (GameController.i.trim().equals("")) {
                    return;
                }
                var0.setColor(0xFFFFFF);
                var0.setFont(GameController.a);
                var0.drawString(GameController.i, GameController.am, GameController.an - 5, 33);
                return;
            }
            case 1: {
                GameController.render_1();
                if (GameConfig.j) {
                    var2_2 = 0;
                    v0 = 0;
                } else {
                    var2_2 = 201756;
                    v0 = var3_3 = 35031;
                }
                if (GameController.O == 3) {
                    var2_2 = 201756;
                    var3_3 = 11980248;
                }
                if (GameController.O == 6) {
                    var2_2 = 201756;
                    var3_3 = 11980248;
                }
                if (GameController.O == 7) {
                    var2_2 = 0;
                    var3_3 = 0;
                }
                if (GameController.O == 8) {
                    var1_1.setColor(39423);
                    var1_1.fillRect(0, 0, GameController.ak, GameController.al);
                } else {
                    GameController.method_0(var1_1, 0, 0, GameController.ak, GameController.al, var2_2, var3_3);
                }
                GameController.render_1(var1_1);
                if (!GameController.o) ** GOTO lbl47
                GameController.render_4(var1_1);
                ** GOTO lbl176
lbl47:
                // 1 sources

                if (GameController.O != 7) ** GOTO lbl84
                if (GameConfig.j) {
                    var4_4 = GameController.ak / 2;
                    var5_6 = GameController.al * 8 / 100;
                    v1 = var1_1;
                    v2 = GameController.d;
                    v3 = var4_4;
                    v4 = var5_6;
                    v5 = 17;
                } else {
                    var4_4 = GameController.ak * 9 / 100;
                    var5_6 = GameController.al - GameController.b[8].getHeight() - 5;
                    if (GameConfig.h) {
                        var5_6 -= GameController.b[12].getHeight();
                    }
                    v1 = var1_1;
                    v2 = GameController.d;
                    v3 = GameController.ak - var4_4;
                    v4 = var5_6;
                    v5 = 40;
                }
                GameController.method_0(v1, v2, v3, v4, v5);
                GameController.d = true;
                GameController.method_0(GameController.f, null, 0, 0, 0);
                var6_7 = GameController.F;
                var7_8 = GameController.ai - GameController.c[GameController.O].getHeight();
                var8_9 = Math.abs((var7_8 - var6_7) / 3);
                v6 = var8_9 = var8_9 < 3 ? 3 : var8_9;
                if (!GameConfig.j) ** GOTO lbl80
                v7 = GameController.method_0(GameController.g);
                v8 = var1_1;
                v9 = GameController.ak - (GameController.b[4].getWidth() + 8) * 2;
                v10 = GameController.ak / 2;
                v11 = GameController.al * 60 / 100;
                ** GOTO lbl174
lbl80:
                // 1 sources

                GameController.method_0(GameController.method_0(GameController.f), var1_1, GameController.ak - (GameController.b[4].getWidth() + 8) * 2, GameController.b[4].getWidth() + 8, GameController.c[GameController.O].getHeight() + var8_9, 20);
                GameController.ai = GameController.c[GameController.O].getHeight() + var8_9 + GameController.F + GameController.ag / 2 + var8_9;
                GameController.method_0(var1_1, GameController.c[GameController.O], GameController.am, 2, 17);
                ** GOTO lbl176
lbl84:
                // 1 sources

                if (GameController.O != 3) ** GOTO lbl111
                var4_4 = GameController.al >= 128 ? 1 : 0;
                var5_6 = GameController.h != null && GameController.h.length() > 0 ? 1 : 0;
                var6_7 = var4_4 != 0 ? GameController.b[10].getHeight() : 0;
                var7_8 = GameController.e.getHeight();
                var8_9 = var5_6 != 0 ? GameController.z * 3 : 0;
                var9_10 = var7_8 + var8_9 + (GameController.al - GameController.ai);
                var10_14 = Math.max(0, GameController.al - var9_10);
                var11_18 = var10_14 / 3;
                var11_18 = Math.max(2, var11_18);
                if (var4_4 != 0) {
                    GameController.method_0(var1_1, GameController.b[10], GameController.am, GameController.c, 17);
                }
                var12_21 = var11_18 + var6_7;
                var12_21 = var5_6 != 0 ? var12_21 + GameController.e.getHeight() / 2 : GameController.al / 2;
                GameController.method_0(var1_1, GameController.e, GameController.am - 1, var12_21, 3);
                GameController.method_0(GameController.method_0(GameController.ad), var1_1, GameController.e.getWidth() * 9 / 20, GameController.am + 2, var12_21, 3);
                var12_21 += GameController.e.getHeight() / 2;
                if (var5_6 == 0) ** GOTO lbl176
                GameController.E = 1;
                var13_23 = GameController.ai - var12_21 - GameController.b[8].getHeight();
                v7 = GameController.h;
                v8 = var1_1;
                v9 = GameController.ak;
                v10 = GameController.am;
                v12 = var12_21;
                v13 = var13_23;
                ** GOTO lbl173
lbl111:
                // 1 sources

                if (GameController.O == 6) {
                    var4_4 = GameController.al >= 128 ? 1 : 0;
                    if (var4_4 != 0) {
                        GameController.b[10].getHeight();
                    }
                    var5_6 = 0;
                    var6_7 = GameController.c[GameController.O].getHeight();
                    var7_8 = var6_7 + (GameController.al - GameController.ai);
                    var8_9 = Math.max(0, GameController.al - var7_8);
                    var9_11 = var8_9 / 4;
                    Math.max(2, var9_11);
                    if (var4_4 != 0) {
                        GameController.method_0(var1_1, GameController.b[10], GameController.am, GameController.c, 17);
                    }
                    var10_15 = GameController.al / 2;
                    GameController.method_0(var1_1, GameController.c[GameController.O], GameController.am, var10_15, 3);
                    var11_19 = GameController.E;
                    GameController.E = 1;
                    GameController.method_0(GameController.method_0(GameController.ad), var1_1, GameController.c[GameController.O].getWidth() / 2, GameController.am, var10_15, 3);
                    GameController.E = var11_19;
                } else {
                    if (GameController.O == 8) {
                        var4_4 = GameController.a.getHeight();
                        var5_6 = GameController.c[8].getHeight();
                        var6_7 = 0;
                        GameController.d = true;
                        GameController.method_0(GameController.method_0(GameController.p), var1_1, GameController.ak - 50, GameController.am, 0, 17);
                        var7_8 = GameController.F;
                        GameController.d = true;
                        GameController.method_0(GameController.method_0(GameController.q), var1_1, GameController.ak - 50, GameController.am, 0, 17);
                        var8_9 = GameController.F;
                        var9_12 = GameController.al - var4_4 - GameController.ag - var7_8 - var8_9 - var5_6;
                        var10_16 = var9_12 / 6;
                        GameController.method_0(var1_1, GameController.a, GameController.am, 0, 17);
                        var6_7 = 0 + (var4_4 + var10_16);
                        GameController.E = 0;
                        GameController.method_0(GameController.method_0(GameController.p), var1_1, GameController.ak * 3 / 4, GameController.am, var6_7, 17);
                        GameController.method_0(var1_1, GameController.c[8], GameController.am - 1, var6_7 += var7_8 + var10_16, 17);
                        GameController.method_0(GameController.method_0(GameController.r), var1_1, GameController.ak * 3 / 4, GameController.am, var6_7 + var5_6 / 2, 3);
                        v7 = GameController.method_0(GameController.q);
                        v8 = var1_1;
                        v9 = GameController.ak * 3 / 4;
                        v10 = GameController.am;
                        v11 = (var6_7 += var5_6 + var10_16) - 2;
                        v14 = 17;
                    } else {
                        var4_4 = GameController.c[GameController.O].getHeight();
                        var5_6 = GameController.z * 2;
                        var6_7 = var4_4 + var5_6;
                        var7_8 = Math.max(0, GameController.al - var6_7);
                        var8_9 = var7_8 / 4;
                        var9_13 = var8_9 = Math.max(0, var8_9);
                        var10_17 = var8_9;
                        GameController.method_0(var1_1, GameController.c[GameController.O], GameController.am, var10_17, 17);
                        var11_20 = GameController.k != false ? GameController.ak : GameController.ak - (GameController.b[9].getWidth() + GameController.b[8].getWidth());
                        var12_22 = GameController.al - 2 - (var9_13 += var4_4 + (GameConfig.h != false ? 0 : var8_9)) - (GameConfig.h != false ? GameController.b[12].getHeight() : 0);
                        v7 = GameController.method_0(GameController.ad);
                        v8 = var1_1;
                        v9 = var11_20;
                        v10 = GameController.am;
                        v12 = var9_13;
                        v13 = var12_22;
lbl173:
                        // 2 sources

                        v11 = v12 + v13 / 2;
lbl174:
                        // 2 sources

                        v14 = 3;
                    }
                    GameController.method_0(v7, v8, v9, v10, v11, v14);
                }
lbl176:
                // 5 sources

                if (GameController.ac > 1) {
                    var4_4 = Math.abs((int)(System.currentTimeMillis() / 80L % 8L) - 4);
                    var5_6 = 5;
                    var6_7 = 7;
                    var7_8 = 1;
                    var8_9 = 3;
                    if (GameController.f || GameConfig.h && GameController.ao == 23) {
                        var5_6 = 4;
                        var7_8 = 0;
                        ++GameController.L;
                    }
                    if (GameController.g || GameConfig.h && GameController.ao == 24) {
                        var6_7 = 6;
                        var8_9 = 2;
                        ++GameController.L;
                    }
                    GameController.method_0(var1_1, GameController.b[var5_6], 1 + var4_4, GameController.an, 6);
                    if (!GameConfig.h && !GameConfig.k) {
                        GameController.method_0(var1_1, GameController.b[var7_8], 1 + var4_4 + GameController.J, GameController.an, 6);
                    }
                    GameController.method_0(var1_1, GameController.b[var6_7], GameController.ak - 1 - var4_4, GameController.an, 10);
                    if (!GameConfig.h && !GameConfig.k) {
                        GameController.method_0(var1_1, GameController.b[var8_9], GameController.ak - 1 - var4_4 - (GameController.b[var6_7].getWidth() - GameController.b[var8_9].getWidth()) + GameController.K, GameController.an, 10);
                    }
                    if (GameController.L > 4) {
                        GameController.f = false;
                        GameController.g = false;
                        GameController.L = 0;
                    }
                }
                if (System.currentTimeMillis() % 1000L <= 500L && (GameController.ao != 27 || GameController.q)) break;
                GameController.render_1(var0);
                return;
            }
            case 2: {
                var0.setFont(GameController.a);
                var4_5 = GameController.a.getHeight();
                var0.setColor(255);
                var0.fillRect(0, GameController.an - var4_5 - 5, GameController.ak, var4_5 * 2);
                var0.setColor(0xFFFFFF);
                var0.drawString(GameController.i, GameController.am, GameController.an, 65);
                return;
            }
            case 5: {
                return;
            }
            case 3: {
                GameController.method_0(var0, 0, 0, GameController.ak, GameController.al, 201756, 35031);
                GameController.method_0(GameController.s, var0, GameController.am, GameController.an, 33);
                GameController.render_1(var0);
            }
        }
    }

    private static void render_4(Graphics graphics) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5 = c;
        if (al > 128) {
            GameController.method_0(graphics, c[O], am, c, 17);
            n5 += c[O].getHeight();
        }
        int n6 = n5;
        E = 1;
        GameController.method_0(ad, graphics, am, n6, 17);
        int n7 = Math.max(2 * z, a[S][a[S][0]].getHeight());
        d = true;
        GameController.method_0(ad, null, 0, 0, 0);
        int n8 = n6 + F;
        int n9 = n4 = al * 5 / 100;
        int n10 = (al / 2 - n8) * 2;
        int n11 = al - b[8].getHeight() - 2 - n8;
        U = T;
        if (n10 / n7 >= T) {
            n3 = n8;
            n2 = n10;
        } else {
            if (n11 / n7 < T) {
                U = (n11 - 2 * n9) / n7;
            }
            n3 = n8;
            n2 = n11;
        }
        int n12 = n8 = n3 + n2 / 2 - U * n7 / 2;
        if (U < T) {
            if (V > 0) {
                GameController.method_0(graphics, am, n12 - n9, n4, 65535, true, false);
            }
            if (V + U < T) {
                GameController.method_0(graphics, am, n12 + U * n7 + n9, n4, 65535, true, true);
            }
        }
        n12 = n8 + n7 / 2 + 1;
        int n13 = n = n4 + b[5].getWidth();
        int n14 = ak * 1 / 100;
        int n15 = n13 + a[S][0].getWidth() + n14;
        for (int i = V; i < V + U; ++i) {
            int n16 = a[S][i];
            GameController.method_0(graphics, a[S][n16], n13, n12, 6);
            if (S == 2 || n16 == b[S].length - 1) {
                E = 1;
            }
            GameController.method_0(GameController.method_0(b[S][n16]), graphics, n15, n12, 6);
            n12 += n7;
        }
        X = W - V;
        Y = n13 - 2;
        Z = n8 + n7 * X;
        aa = ak - Y - n + 2;
        ab = n7;
        graphics.setColor(0xFFFFFF);
        graphics.drawRect(Y, Z, aa, ab);
    }

    private static void method_0(Graphics graphics, int n, int n2, int n3, int n4, boolean bl, boolean bl2) {
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10;
        int n11;
        Graphics graphics2;
        int n12;
        int n13;
        int n14;
        int n15;
        int n16;
        int n17;
        int n18;
        Graphics graphics3;
        int n19;
        int n20 = n19 = bl2 ? -1 : 1;
        if (n3 % 2 == 0) {
            --n3;
        }
        graphics.setColor(0xFFFFFF);
        if (bl) {
            graphics3 = graphics;
            n18 = n;
            n17 = n2;
            n16 = n - (n3 >> 1);
            n15 = n2 + n19 * (n3 >> 1);
            n14 = n + (n3 >> 1);
            n13 = n2;
            n12 = n19 * (n3 >> 1);
        } else {
            graphics3 = graphics;
            n18 = n;
            n17 = n2;
            n16 = n - n19 * (n3 >> 1);
            n15 = n2 - (n3 >> 1);
            n14 = n - n19 * (n3 >> 1);
            n13 = n2;
            n12 = n3 >> 1;
        }
        GameController.render_4(graphics3, n18, n17, n16, n15, n14, n13 + n12);
        graphics.setColor(n4);
        if (bl) {
            graphics2 = graphics;
            n11 = n;
            n10 = n2 + n19;
            n9 = n - (n3 >> 1) + 2;
            n8 = n2 + n19 * (n3 >> 1) - n19;
            n7 = n + (n3 >> 1) - 2;
            n6 = n2 + n19 * (n3 >> 1);
            n5 = n19;
        } else {
            graphics2 = graphics;
            n11 = n - n19;
            n10 = n2;
            n9 = n - n19 * (n3 >> 1) + n19;
            n8 = n2 - (n3 >> 1) + 2;
            n7 = n - n19 * (n3 >> 1) + n19;
            n6 = n2 + (n3 >> 1);
            n5 = 2;
        }
        GameController.render_4(graphics2, n11, n10, n9, n8, n7, n6 - n5);
    }

    /*
     * Unable to fully structure code
     */
    private static void render_1() {
        GameController.d = true;
        GameController.method_0(GameController.ae, null, 0, 0, 3);
        var0 = GameController.method_0(32, GameController.C) / 2;
        var1_1 = GameController.method_0(32, GameController.D) / 3;
        GameController.af = GameController.G + var0;
        GameController.ag = GameController.F + var1_1;
        if ((GameController.F + GameController.ag) % 2 != 0) {
            ++GameController.ag;
        }
        GameController.ah = GameController.am - GameController.af / 2;
        switch (GameController.O) {
            case 0: 
            case 1: 
            case 2: 
            case 7: {
                v0 = GameController.d;
                ** GOTO lbl16
            }
            case 3: 
            case 6: 
            case 8: {
                v0 = GameController.e;
lbl16:
                // 2 sources

                GameController.ai = v0 - GameController.ag / 2;
            }
        }
        if (GameConfig.j && GameController.O == 7) {
            GameController.ai = GameController.al * 80 / 100;
        }
    }

    private static void render_1(Graphics graphics) {
        if (n) {
            graphics.setColor(a);
            if (GameConfig.j) {
                graphics.setColor(0xFF7F00);
            }
            if (!q && ao == 27) {
                graphics.setColor(14588928);
                graphics.drawRect(ah - 2, ai - 2, af + 3, ag + 4);
                graphics.drawRect(ah - 1, ai - 1, af + 1, ag + 2);
                graphics.setColor(5767410);
            }
            E = 0;
            graphics.fillRect(ah, ai, af, ag + 1);
            GameController.method_0(ae, graphics, am, ai + (ag >> 1), 3);
        }
    }

    /*
     * Unable to fully structure code
     */
    private static void render_1(Graphics var0) {
        block15: {
            block16: {
                block14: {
                    block11: {
                        block13: {
                            block12: {
                                var1_1 = GameController.b;
                                var2_2 = GameController.al - 2;
                                var3_3 = var1_1;
                                var4_4 = GameController.ak - var1_1;
                                if (GameController.k) {
                                    return;
                                }
                                if (!GameConfig.h) break block11;
                                var5_5 = GameController.b[12];
                                var6_6 = GameController.b[12];
                                if (GameController.ao != 26) break block12;
                                if (GameConfig.a) ** GOTO lbl-1000
                                var5_5 = GameController.b[13];
                                break block13;
                            }
                            if (GameController.ao == 25 && GameController.q) {
                                ** if (!GameConfig.a) goto lbl-1000
lbl-1000:
                                // 1 sources

                                {
                                    var5_5 = GameController.b[13];
                                    ** GOTO lbl21
                                }
                            }
                            break block13;
lbl-1000:
                            // 2 sources

                            {
                                var6_6 = GameController.b[13];
                            }
                        }
                        GameController.method_0(var0, var5_5, var1_1, var2_2, 36);
                        GameController.method_0(var0, var6_6, GameController.ak - var1_1, var2_2, 40);
                        var3_3 += GameController.b[12].getWidth() / 2 - GameController.b[9].getWidth() / 2;
                        var4_4 += -GameController.b[12].getWidth() / 2 + GameController.b[8].getWidth() / 2;
                        var2_2 += -GameController.b[12].getHeight() / 2 + GameController.b[9].getHeight() / 2;
                    }
                    if (!GameConfig.a) break block14;
                    if ((GameController.a & 1) != 0) {
                        GameController.method_0(var0, GameController.b[9], var3_3, var2_2, 36);
                    }
                    if ((GameController.a & 2) == 0) break block15;
                    v0 = var0;
                    v1 = GameController.b;
                    v2 = 8;
                    break block16;
                }
                if ((GameController.a & 2) != 0) {
                    GameController.method_0(var0, GameController.b[8], var3_3, var2_2, 36);
                }
                if ((GameController.a & 1) == 0) break block15;
                v0 = var0;
                v1 = GameController.b;
                v2 = 9;
            }
            GameController.method_0(v0, v1[v2], var4_4, var2_2, 40);
        }
    }

    private static void method_0(boolean bl, boolean bl2) {
        block8: {
            Command command;
            Canvas canvas;
            block9: {
                block7: {
                    if (b != null) {
                        a.removeCommand(b);
                    }
                    if (a != null) {
                        a.removeCommand(a);
                    }
                    if (!GameConfig.a) break block7;
                    if (bl) {
                        a.addCommand(a);
                    }
                    if (!bl2) break block8;
                    canvas = a;
                    command = b;
                    break block9;
                }
                if (bl2) {
                    a.addCommand(b);
                }
                if (!bl) break block8;
                canvas = a;
                command = a;
            }
            canvas.addCommand(command);
        }
    }

    private static void update_4() {
        if (b != null) {
            a.removeCommand(b);
            b = null;
        }
        if (a != null) {
            a.removeCommand(a);
            a = null;
        }
    }

    private static void method_0(Graphics graphics, int n, int n2, int n3, int n4, int n5, int n6) {
        if (GameConfig.c) {
            int n7 = n5 >> 16;
            int n8 = n5 >> 8 & 0xFF;
            int n9 = n5 & 0xFF;
            int n10 = n6 >> 16;
            int n11 = n6 >> 8 & 0xFF;
            int n12 = n6 & 0xFF;
            if (n2 + n4 > al) {
                n4 = al - n2;
            }
            if (n + n3 > ak) {
                n3 = ak - n;
            }
            int n13 = n10 - n7;
            int n14 = n11 - n8;
            int n15 = n12 - n9;
            int n16 = n7;
            int n17 = n8;
            int n18 = n9;
            int n19 = 0;
            for (int i = n2; i < n2 + n4; ++i) {
                int n20;
                if (i < al / 2) {
                    n20 = i;
                } else if (i == al / 2) {
                    n16 = n10;
                    n17 = n11;
                    n18 = n12;
                    n20 = 0;
                } else {
                    n20 = al / 2 - i;
                }
                n19 = n20;
                graphics.setColor(n16 + n13 * n19 / (al / 2), n17 + n14 * n19 / (al / 2), n18 + n15 * n19 / (al / 2));
                graphics.drawLine(n, i, n + n3, i);
            }
        } else {
            graphics.setColor(n6);
            graphics.fillRect(n, n2, n3, n4);
        }
    }

    private static Image render_4(byte[] byArray, int n, int n2, int n3, int n4) {
        long l;
        int n5;
        int n6 = 0;
        for (int i = n; n6 == 0 && i < n + n2; ++i) {
            if ((byArray[i] & 0xFF) != 80 || (byArray[i + 1] & 0xFF) != 76 || (byArray[i + 2] & 0xFF) != 84 || (byArray[i + 3] & 0xFF) != 69) continue;
            n6 = i;
        }
        int n7 = (byArray[n6 - 4] << 24 & 0xFF000000) + (byArray[n6 - 3] << 16 & 0xFF0000) + (byArray[n6 - 2] << 8 & 0xFF00) + (byArray[n6 - 1] << 0 & 0xFF);
        byArray[n6 + 4 + 3 * n3] = (byte)((n4 & 0xFF0000) >> 16);
        byArray[n6 + 4 + 3 * n3 + 1] = (byte)((n4 & 0xFF00) >> 8);
        byArray[n6 + 4 + 3 * n3 + 2] = (byte)(n4 & 0xFF);
        byte[] byArray2 = new byte[n7 + 4];
        System.arraycopy(byArray, n6, byArray2, 0, n7 + 4);
        long[] lArray = new long[256];
        for (n5 = 0; n5 < 256; ++n5) {
            l = n5;
            for (int i = 0; i < 8; ++i) {
                l = (l & 1L) == 1L ? 0xEDB88320L ^ l >> 1 : l >> 1;
            }
            lArray[n5] = l;
        }
        l = 0xFFFFFFFFL;
        for (n5 = 0; n5 < byArray2.length; ++n5) {
            l = lArray[(int)(l ^ (long)byArray2[n5]) & 0xFF] ^ l >> 8;
        }
        byArray[n6 + 4 + n7] = (byte)(((l ^= 0xFFFFFFFFL) & 0xFFFFFFFFFF000000L) >> 24);
        byArray[n6 + 4 + n7 + 1] = (byte)((l & 0xFF0000L) >> 16);
        byArray[n6 + 4 + n7 + 2] = (byte)((l & 0xFF00L) >> 8);
        byArray[n6 + 4 + n7 + 3] = (byte)((l & 0xFFL) >> 0);
        System.gc();
        return GameController.method_0(byArray, n, n2);
    }

    private static void method_0(Graphics graphics, int n, int n2, int n3, int n4) {
        if (n3 > n4) {
            n3 = n4;
        }
        int n5 = (ak - n2) / 2;
        graphics.setColor(0xFFFFFF);
        graphics.drawRect(n5, n, n2, 6);
        int n6 = (n2 - 2 - 2) * n3 / n4 + 1;
        graphics.setColor(0xFF0000);
        graphics.fillRect(n5 + 1 + 1, n + 1 + 1, n6, 3);
    }

    private static void render_4(Graphics graphics, int n, int n2, int n3, int n4, int n5, int n6) {
        graphics.fillTriangle(n, n2, n3, n4, n5, n6);
    }

    private static void method_0(Graphics graphics, Image image, int n, int n2, int n3, int n4, int n5, int n6) {
        graphics.drawRegion(image, n, n2, n3, n4, 0, n5, n6, 20);
    }

    private static void method_0(Graphics graphics, Image image, int n, int n2, int n3) {
        int n4;
        int n5;
        int n6;
        Image image2;
        Graphics graphics2;
        if (GameConfig.l) {
            graphics2 = graphics;
            image2 = image;
            n6 = n;
            n5 = n2;
            n4 = n3;
        } else {
            if ((n3 & 1) != 0) {
                n -= image.getWidth() >> 1;
            }
            if ((n3 & 2) != 0) {
                n2 -= image.getHeight() >> 1;
            }
            if ((n3 & 8) != 0) {
                n -= image.getWidth();
            }
            if ((n3 & 0x20) != 0) {
                n2 -= image.getHeight();
            }
            graphics2 = graphics;
            image2 = image;
            n6 = n;
            n5 = n2;
            n4 = 0;
        }
        graphics2.drawImage(image2, n6, n5, n4);
    }

    private static void render_4(Graphics graphics, int n, int n2, int n3, int n4) {
        n = Math.max(n, 0);
        n2 = Math.max(n2, 0);
        n3 = Math.min(n3, ak);
        n4 = Math.min(n4, al);
        graphics.setClip(n, n2, n3, n4);
    }

    public final void gameLoop() {
        if (GameConfig.c == 2) {
            while (l) {
                try {
                    if (g != null) {
                        d = g;
                        GameController.resolveURL_7();
                        g = null;
                    }
                    Thread.sleep(1000L);
                }
                catch (Exception exception) {}
            }
        }
    }

    private static void persist_6() {
        h = true;
        i = true;
        RecordStore recordStore = null;
        try {
            recordStore = RecordStore.openRecordStore((String)"igp19", (boolean)false);
        }
        catch (Exception exception) {
            try {
                recordStore = RecordStore.openRecordStore((String)"igp19", (boolean)true);
            }
            catch (Exception exception2) {}
        }
        try {
            if (recordStore != null) {
                recordStore.closeRecordStore();
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static boolean render_1() {
        if (m) {
            if (i) {
                return h;
            }
            try {
                RecordStore recordStore = null;
                recordStore = RecordStore.openRecordStore((String)"igp19", (boolean)false);
                recordStore.closeRecordStore();
                h = true;
            }
            catch (Exception exception) {}
            i = true;
        }
        return h;
    }

    private static void resolveURL_7() {
        if (d != null && d.length() > 0) {
            String string = d;
            d = null;
            try {
                a.platformRequest(string);
                Thread.sleep(200L);
            }
            catch (Exception exception) {}
            int n = M = GameConfig.g ? 4 : 1;
            if (GameConfig.f) {
                a.notifyDestroyed();
            }
        }
    }

    public static boolean method_0(Graphics graphics, Image image, int n, int n2, int n3) {
        if (GameController.render_1()) {
            return false;
        }
        if (!GameController.method_0() || image == null || graphics == null) {
            return false;
        }
        if (System.currentTimeMillis() - a > 800L) {
            j = !j;
            a = System.currentTimeMillis();
        }
        if (j) {
            GameController.method_0(graphics, image, n, n2, n3);
            return true;
        }
        return true;
    }

    public final void onCommand(Command command, Displayable displayable) {
        if (k) {
            if (command == a) {
                GameController.method_0(25);
                return;
            }
            if (command == b) {
                GameController.method_0(26);
            }
        }
    }

    private static final void resolveURL_8() {
        a.put("URL-TEMPLATE-GAME", l);
        a.put("URL-OPERATOR", m);
        a.put("URL-PT", n);
        a.put("IGP-PROMOS", o);
        a.put("IGP-WN", p);
        a.put("IGP-BS", q);
        a.put("IGP-CATEGORIES", r);
        a.put("IGP-VERSION", s);
        a.put("URL-ORANGE", t);
        a.put("URL-GLIVE", u);
    }

    private static final String method_0(String string) {
        if (GameConfig.i) {
            String string2 = (String)a.get(string);
            if (string2 != null && string2 != "") {
                return string2;
            }
            return null;
        }
        return a.getAppProperty(string);
    }

    static {
        a = 0xFF0000;
        b = GameConfig.a;
        v = -1;
        w = 14;
        A = 0;
        B = 1;
        C = 2;
        D = 3;
        b = new String[]{"URL-WN", "URL-BS", "URL"};
        a = new String[0];
        J = 6;
        K = 3;
        h = false;
        i = false;
        a = 0L;
        j = true;
        k = false;
        a = null;
        a = null;
        l = false;
        g = null;
        m = false;
        ac = -1;
        ao = 0;
        p = false;
        q = false;
        a = new Hashtable();
        l = "URL-TEMPLATE-GAME-XXX";
        m = "URL-OPERATOR-XXX";
        n = "URL-PT-XXX";
        o = "IGP-PROMOS-XXX";
        p = "IGP-WN-XXX";
        q = "IGP-BS-XXX";
        r = "IGP-CATEGORIES-XXX";
        s = "IGP-VERSION-XXX";
        t = "URL-ORANGE-XXX";
        u = "URL-GLIVE-XXX";
        ap = 0;
        a = false;
        b = false;
    }
}

