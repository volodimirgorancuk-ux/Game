/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class AudioManager {
    public static int a = 0;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    public static int g;
    public static int h;
    public static int i;
    public static int j;
    public static int k;
    public static int l;
    public static int m;
    public static int n;
    public static int o;
    public static boolean a;
    public static int p;
    public static int q;
    public static int r;
    public static int s;
    public static AudioManager a;
    public static int t;
    public static boolean b;
    public static int u;
    public static int v;
    public static int w;
    public static int x;
    public static boolean c;
    public static boolean d;
    public static boolean e;
    public static int y;
    public static int z;
    public static GameData[] a;
    public static int[] a;
    public static byte[][] a;
    public static int[] b;
    public static int[] c;
    public static int A;
    public static int B;
    public static int[] d;
    public static int[][] a;
    public static AudioManager b;
    public static int C;
    public static int D;
    public static boolean f;
    public static boolean g;
    public static int E;
    public static boolean h;
    public static boolean i;
    public static int F;
    public static int G;
    public static int H;
    public static int I;
    public static int[] e;
    public static AudioManager c;
    public static int J;
    public static int[] f;
    public static boolean j;
    public static boolean k;
    public GameData a;
    public int K;
    public int L;
    public int M;
    public int[] g;

    public static void a() {
        a = 0;
        d = 0;
        h = 2304;
        i = 0;
        j = 0;
        k = 0;
        l = 0;
        n = 1;
        o = 1;
        p = 0;
        q = 0;
        e = -1;
        f = -1;
        r = 0;
        s = 0;
        d = false;
        a = null;
    }

    private static void a(GameData gameData) {
        gameData.a();
    }

    public static int a(int n) {
        int n2;
        block16: {
            int n3;
            block13: {
                block15: {
                    block14: {
                        block12: {
                            block11: {
                                int n4;
                                int n5;
                                block10: {
                                    block9: {
                                        int n6;
                                        n2 = 0;
                                        if (((n &= 0x3FDE) & 0x404) != 0) {
                                            n6 = 1028;
                                        } else if ((n & 0x900) != 0) {
                                            n6 = n2 = 2304;
                                        }
                                        if ((n & 0x1010) == 0) break block9;
                                        n5 = n2;
                                        n4 = 4112;
                                        break block10;
                                    }
                                    if ((n & 0x2040) == 0) break block11;
                                    n5 = n2;
                                    n4 = 8256;
                                }
                                n2 = n5 | n4;
                            }
                            if ((n & 2) == 0) break block12;
                            n3 = 5140;
                            break block13;
                        }
                        if ((n & 8) == 0) break block14;
                        n3 = 9284;
                        break block13;
                    }
                    if ((n & 0x80) == 0) break block15;
                    n3 = 6416;
                    break block13;
                }
                if ((n & 0x200) == 0) break block16;
                n3 = 10560;
            }
            n2 = n3;
        }
        return n2;
    }

    private static boolean b() {
        if (GameCanvas.ez != 0) {
            int n = AudioManager.a(GameCanvas.ez);
            if (d == 0) {
                d = n;
                GameCanvas.d(e);
                GameCanvas.d(f);
            } else if (GameCanvas.a(f, 0)) {
                if ((d & n) == d && (n & ~(d & n)) == 0 && ((n & 0x32DA) != 0 && (n & 0xF8E) == 0 || (n & 0x32DA) == 0 && (n & 0xF8E) != 0)) {
                    d = 0;
                    return true;
                }
                d = n;
                GameCanvas.d(e);
                GameCanvas.d(f);
            } else {
                d = 0;
            }
        }
        if (GameCanvas.a(e, 300)) {
            d = 0;
        }
        return false;
    }

    public static void a(AudioManager audioManager, int n) {
        h = n;
        i = ~h;
        AudioManager.a(0, audioManager);
    }

    public static void a(AudioManager audioManager) {
        if (audioManager.g[7] <= 0) {
            GameCanvas.e();
        }
    }

    private static void i() {
        c = 0;
    }

    private static int b(int n, int n2) {
        c += (int)GameCanvas.d;
        if (n2 != 0) {
            b = ~n2;
        }
        if (c >= 0) {
            b = -1;
            c = 0;
        }
        return n & b;
    }

    private static boolean a(AudioManager audioManager, boolean bl, int n) {
        if (b) {
            GameCanvas.b(false);
            if (AudioManager.c()) {
                AudioManager.b(audioManager);
                AudioManager.a(101, audioManager);
            } else if (bl && n != 0) {
                AudioManager.b(audioManager);
            }
            return true;
        }
        return false;
    }

    private static boolean c() {
        boolean bl = false;
        if (SensorHandler.a() && SensorHandler.c()) {
            bl = true;
        }
        r += (int)GameCanvas.d;
        if (GameCanvas.ez != 0) {
            ++s;
        }
        if (s >= 5) {
            r = 0;
            s = 0;
            return true;
        }
        if (bl) {
            r = 0;
            s = 0;
            return true;
        }
        if (r >= 2000) {
            r = 0;
            s = 0;
        }
        return false;
    }

    private static void w(AudioManager audioManager) {
        if (audioManager != null) {
            switch (audioManager.L) {
                case 14: {
                    GameCanvas.b(GameCanvas.d);
                    GameCanvas.l(0);
                    return;
                }
                case 0: 
                case 1: 
                case 2: 
                case 11: {
                    GameCanvas.a(0);
                    return;
                }
                case 16: {
                    GameCanvas.l(audioManager);
                    return;
                }
                case 10: {
                    AudioManager.A(GameCanvas.g[GameCanvas.by][GameCanvas.bz][audioManager.g[8]]);
                }
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    public static boolean a(AudioManager var0) {
        var1_1 = AudioManager.b();
        var2_2 = AudioManager.a(GameCanvas.ez & 16350);
        var3_3 = AudioManager.b(AudioManager.a(GameCanvas.ey & 16350), var2_2);
        if (AudioManager.n(var0)) {
            var1_1 = false;
        }
        AudioManager.z(var0);
        GameCanvas.h();
        GameCanvas.f();
        GameCanvas.h(var0);
        GameCanvas.d();
        v0 = AudioManager.g = AudioManager.j != 0 || AudioManager.k != 0 ? 0 : AudioManager.g + (int)GameCanvas.d;
        if ((AudioManager.p & 4) != 0) {
            GameCanvas.a[17].a();
        }
        var5_4 = AudioManager.a(var0, var1_1, var2_2);
        var6_5 = false;
        block0 : switch (AudioManager.a) {
            case 5: {
                if (AudioManager.m > 0) {
                    if ((AudioManager.m -= (int)GameCanvas.d) > 0) {
                        var6_5 = true;
                    } else {
                        AudioManager.a(0, var0);
                    }
                }
            }
            case 0: {
                if (!var5_4) {
                    GameCanvas.a(var0, AudioManager.h, false, false);
                }
                if (GameCanvas.X) {
                    v1 = 9;
                } else if (GameCanvas.Z) {
                    v1 = GameCanvas.L[1] != -1 ? 12 : 13;
                } else if (var2_2 != 0 && !var1_1) {
                    AudioManager.a(var2_2);
                    v1 = AudioManager.a;
                } else if (var3_3 != 0 || var1_1 && var2_2 != 0) {
                    if (var1_1 && var2_2 != 0) {
                        AudioManager.a(var2_2);
                        AudioManager.i();
                        v1 = 2;
                    } else {
                        AudioManager.a(var3_3);
                        v1 = var6_5 ? 3 : 1;
                    }
                } else {
                    if ((GameCanvas.ez & 16416) != 0 && GameCanvas.d != null) {
                        AudioManager.w(GameCanvas.d);
                        break;
                    }
                    v1 = !AudioManager.b && GameCanvas.a(true) != null && GameCanvas.L[1] != -1 ? 6 : (!AudioManager.b && (GameCanvas.ez & 16416) != 0 && GameCanvas.L[1] != -1 ? 6 : (GameCanvas.W ? 17 : AudioManager.a));
                }
                ** GOTO lbl321
            }
            case 18: {
                v1 = 18;
                ** GOTO lbl321
            }
            case 6: {
                GameCanvas.a(var0, AudioManager.h, true, true);
                if (GameCanvas.b()) {
                    if ((var3_3 & 16350) == 0) break;
                    AudioManager.a(var3_3);
                    v1 = 7;
                } else if (GameCanvas.a(true) != null) {
                    if ((var3_3 & 16350) == 0) {
                        GameCanvas.cu = GameCanvas.h(AudioManager.a(var0, GameCanvas.a(true), true));
                        AudioManager.c(var0);
                    }
                    if ((GameCanvas.ez & 16416) != 0 && GameCanvas.d != null) {
                        AudioManager.w(GameCanvas.d);
                        break;
                    }
                    if (GameCanvas.X) {
                        v1 = 9;
                    } else if (GameCanvas.V) {
                        GameCanvas.cu = GameCanvas.h(AudioManager.a(var0, GameCanvas.a(true), true));
                        v1 = 8;
                    } else if (GameCanvas.W) {
                        v1 = 17;
                    } else if (GameCanvas.Z) {
                        v1 = GameCanvas.L[1] != -1 ? 12 : 13;
                    } else if ((var2_2 & 16350) != 0) {
                        var11_6 = GameCanvas.h(var2_2);
                        if (var1_1 && var2_2 != 0) {
                            AudioManager.a(var2_2);
                            AudioManager.i();
                            v1 = 2;
                        } else {
                            if (var11_6 == GameCanvas.cu || GameCanvas.i(var11_6) == 0) break;
                            AudioManager.a(var2_2);
                            GameCanvas.cu = var11_6;
                            GameCanvas.f(var0);
                            v1 = 6;
                        }
                    } else {
                        if ((var3_3 & 16350) == 0) break;
                        var11_7 = GameCanvas.h(var3_3);
                        if (var11_7 != GameCanvas.cu && GameCanvas.i(var11_7) != 0) {
                            AudioManager.a(var3_3);
                            GameCanvas.cu = var11_7;
                            GameCanvas.f(var0);
                            v1 = 6;
                        } else {
                            AudioManager.a(var3_3);
                            v1 = 7;
                        }
                    }
                } else {
                    if ((GameCanvas.ez & 16416) != 0 && GameCanvas.d != null) {
                        AudioManager.w(GameCanvas.d);
                        break;
                    }
                    if (GameCanvas.X) {
                        v1 = 9;
                    } else if (GameCanvas.V) {
                        v1 = 8;
                    } else if (GameCanvas.W) {
                        v1 = 17;
                    } else if (GameCanvas.Z) {
                        v1 = GameCanvas.L[1] != -1 ? 12 : 13;
                    } else {
                        if ((var2_2 & 16350) == 0) break;
                        AudioManager.a(var2_2);
                        v1 = 1;
                    }
                }
                ** GOTO lbl321
            }
            case 7: {
                GameCanvas.a(var0, AudioManager.h, true, false);
                if (GameCanvas.b()) {
                    if ((var2_2 | var3_3) != 0) {
                        AudioManager.a(var3_3);
                        v1 = 7;
                    } else {
                        v1 = 6;
                    }
                } else if (GameCanvas.a(true) != null) {
                    GameCanvas.cu = GameCanvas.h(AudioManager.a(var0, GameCanvas.a(true), true));
                    AudioManager.c(var0);
                    if (var3_3 != 0) {
                        AudioManager.a(var3_3);
                        v1 = (AudioManager.l += (int)GameCanvas.d) >= 1000 ? 3 : 7;
                    } else if (var1_1) {
                        AudioManager.a(var2_2);
                        v1 = 3;
                    } else {
                        v1 = 6;
                    }
                } else {
                    if (var3_3 != 0) {
                        AudioManager.a(var3_3);
                    }
                    v1 = 1;
                }
                ** GOTO lbl321
            }
            case 8: {
                var7_8 = GameCanvas.l(GameCanvas.L[1], 21);
                GameCanvas.c(false);
                switch (var7_8) {
                    case 4: {
                        var8_9 = GameCanvas.a(true);
                        if ((GameCanvas.ey & 16416) == 0 || GameCanvas.a()) {
                            v1 = 0;
                            break;
                        }
                        GameCanvas.a(var0, var8_9, GameCanvas.L[1]);
                        break block0;
                    }
                    case 6: {
                        if ((GameCanvas.ey & 16416) != 0) {
                            if (!var0.a.a()) break block0;
                            AudioManager.a(7, var0);
                            GameCanvas.L[3] = 2;
                            break block0;
                        }
                        v1 = 0;
                        break;
                    }
                    default: {
                        if (var3_3 != 0) {
                            AudioManager.a(var3_3);
                            v1 = 1;
                            break;
                        }
                        if (!var0.a.a()) break block0;
                        v1 = 6;
                        break;
                    }
                }
                ** GOTO lbl321
            }
            case 9: {
                if (var3_3 != 0 || var1_1 && var2_2 != 0) {
                    if (var1_1 && var2_2 != 0) {
                        AudioManager.a(var2_2);
                        v1 = 2;
                    } else {
                        AudioManager.a(var3_3);
                        v1 = 1;
                    }
                } else {
                    if (!var0.a.a() && GameCanvas.X) break;
                    v1 = 0;
                }
                ** GOTO lbl321
            }
            case 12: {
                if (var3_3 != 0) {
                    AudioManager.a(var3_3);
                    v1 = 1;
                } else {
                    if (!var0.a.a()) break;
                    v1 = 13;
                }
                ** GOTO lbl321
            }
            case 13: {
                if (var3_3 == 0) ** GOTO lbl202
                AudioManager.a(var3_3);
                v1 = 1;
                ** GOTO lbl321
lbl202:
                // 1 sources

                if (!var0.a.a()) break;
                ** GOTO lbl320
            }
            case 1: {
                GameCanvas.c(false);
                if (!var1_1 || var2_2 == 0) ** GOTO lbl211
                AudioManager.a(var2_2);
                AudioManager.i();
                v1 = 2;
                ** GOTO lbl321
lbl211:
                // 1 sources

                if (var3_3 == 0) ** GOTO lbl320
                AudioManager.i();
                AudioManager.a(var3_3);
                v1 = (AudioManager.l += (int)GameCanvas.d) >= 300 || GameCanvas.ct >= 1 ? 3 : 1;
                ** GOTO lbl321
            }
            case 2: {
                GameCanvas.c(false);
                if (var0.a.a()) {
                    if (var3_3 != 0 && (var3_3 & AudioManager.h) != 0) {
                        v1 = 3;
                    } else {
                        if (var3_3 != 0) {
                            AudioManager.a(var3_3);
                        }
                        v1 = 1;
                    }
                } else {
                    v1 = 2;
                }
                ** GOTO lbl321
            }
            case 3: {
                GameCanvas.c(false);
                if ((GameCanvas.ez & 16416) != 0) {
                    if (GameCanvas.d != null) {
                        AudioManager.a(0, var0);
                        AudioManager.w(GameCanvas.d);
                        break;
                    }
                    if (!AudioManager.m(var0)) break;
                    v1 = 14;
                } else if (var1_1 && var2_2 != 0) {
                    AudioManager.a(var2_2);
                    AudioManager.i();
                    v1 = 2;
                } else if (var3_3 == 0 || ((var3_3 | AudioManager.h) & 3332 ^ 3332) == 0 || ((var3_3 | AudioManager.h) & 12368 ^ 12368) == 0) {
                    v1 = 4;
                } else {
                    AudioManager.i();
                    AudioManager.a(var3_3);
                    v1 = 3;
                }
                ** GOTO lbl321
            }
            case 4: {
                GameCanvas.c(false);
                if (var3_3 == 0) {
                    if (var2_2 != 0) {
                        AudioManager.a(var2_2);
                    }
                    if ((GameCanvas.ez & 16416) != 0 && GameCanvas.d != null) {
                        AudioManager.a(0, var0);
                        AudioManager.w(GameCanvas.d);
                        break;
                    }
                    if ((GameCanvas.ez & 16416) != 0 && AudioManager.m(var0)) {
                        v1 = 14;
                    } else if (!AudioManager.b && (GameCanvas.ez & 16416) != 0 && GameCanvas.L[1] != -1) {
                        v1 = 6;
                    } else if (AudioManager.j == 0 && AudioManager.k == 0) {
                        AudioManager.m = 500;
                        v1 = 5;
                    } else {
                        v1 = 4;
                    }
                } else {
                    AudioManager.a(var3_3);
                    if ((AudioManager.h & 13018) != 0) {
                        AudioManager.j = 1474560;
                    }
                    if ((AudioManager.h & 3982) != 0) {
                        AudioManager.k = 1474560;
                    }
                    v1 = 3;
                }
                ** GOTO lbl321
            }
            case 14: {
                if ((AudioManager.v += (int)GameCanvas.d) >= 400) {
                    AudioManager.x(var0);
                }
                if (var0.a.a()) ** GOTO lbl320
                v1 = 14;
                ** GOTO lbl321
            }
            case 11: {
                v1 = 11;
                ** GOTO lbl321
            }
            case 15: {
                if ((GameCanvas.e() || var0.a.a()) && GameCanvas.dZ != 2) {
                    GameCanvas.j(var0);
                }
                if (!var0.a.a()) break;
                ** GOTO lbl320
            }
            case 17: {
                if ((AudioManager.v += (int)GameCanvas.d) >= 100) {
                    GameCanvas.f(var0);
                    AudioManager.y(var0);
                }
                if (!var0.a.a()) break;
                ** GOTO lbl320
            }
            case 100: {
                AudioManager.c(var0);
                GameCanvas.f(var0);
                if (!GameCanvas.a(GameCanvas.a(false, false), true)) ** GOTO lbl305
                GameCanvas.W = true;
                v1 = 17;
                ** GOTO lbl321
lbl305:
                // 1 sources

                if (!AudioManager.c()) break;
                AudioManager.a(101, var0);
                AudioManager.b(var0);
                break;
            }
            case 101: {
                if (!var0.a.a()) break;
                ** GOTO lbl320
            }
            case 666: {
                if (!var0.a.a()) break;
                GameCanvas.a(0);
                GameCanvas.a(20, true);
                break;
            }
            case 16: {
                if ((AudioManager.w += (int)GameCanvas.d) < 800) break;
                AudioManager.w = 0;
lbl320:
                // 7 sources

                v1 = 0;
lbl321:
                // 52 sources

                AudioManager.a(v1, var0);
            }
        }
        var9_10 = (int)GameCanvas.d;
        v2 = var10_11 = AudioManager.a(var0, AudioManager.h, AudioManager.j * var9_10, AudioManager.k * var9_10, 819200 * var9_10 / 1000) == false;
        if (AudioManager.a == 7 || AudioManager.a == 6 || AudioManager.a == 18) {
            AudioManager.a(GameCanvas.c());
        }
        return var10_11;
    }

    public static void b(AudioManager audioManager) {
        a = null;
        t = 0;
        b = false;
        for (int i = 0; i < GameCanvas.cg; ++i) {
            boolean bl;
            AudioManager audioManager2;
            int n;
            if (GameCanvas.e[i].L != 3 || GameCanvas.e[i].g[12] >= 100 || GameCanvas.e[i].g[10] == 5 || (n = GameCanvas.a(audioManager, audioManager2 = GameCanvas.e[i])) >= 1600 || (bl = GameCanvas.a(audioManager.a.a, audioManager.a.b, GameCanvas.e[i].a.a, GameCanvas.e[i].a.b, null, false, 133))) continue;
            AudioManager.i(audioManager2);
            AudioManager.c(audioManager2, 9);
        }
    }

    private static boolean m(AudioManager audioManager) {
        if ((GameCanvas.e & 2) != 0 && !AudioManager.n(audioManager)) {
            GameCanvas.f(audioManager);
            GameCanvas.a(audioManager, h, false, false);
            AudioManager audioManager2 = GameCanvas.a();
            return audioManager.g[18] == 1 && GameCanvas.K[GameCanvas.cu] > 0 && audioManager2 != null && audioManager2.M <= 4096;
        }
        return false;
    }

    private static void x(AudioManager audioManager) {
        for (int i = 0; i < GameCanvas.cg; ++i) {
            int n;
            if (GameCanvas.e[i].L != 3 || GameCanvas.e[i].g[12] >= 100 || GameCanvas.e[i].g[12] == 14 || GameCanvas.e[i].g[12] == 9) continue;
            AudioManager audioManager2 = GameCanvas.e[i];
            int n2 = GameCanvas.h(audioManager2.g[0], 10);
            if (AudioManager.d(audioManager2) || (n = GameCanvas.a(audioManager, audioManager2)) >= 1600 || GameCanvas.a(audioManager.a.a, audioManager.a.b, GameCanvas.e[i].a.a, GameCanvas.e[i].a.b, null, false, 133)) continue;
            if ((n2 == 4 || n2 == 6) && audioManager2.g[12] != 7 && audioManager2.g[12] != 8 && audioManager2.g[12] != 19 && audioManager2.g[12] != 18) {
                AudioManager.f(audioManager2, GameCanvas.c.a.a, GameCanvas.c.a.b, 0);
                AudioManager.c(audioManager2, 18);
                AudioManager.c(audioManager2);
                continue;
            }
            int n3 = AudioManager.a(audioManager, audioManager2, false);
            if ((n3 & h) == 0) continue;
            int[] nArray = GameCanvas.b(audioManager2.a);
            int n4 = (nArray[3] - nArray[1] >> 2) * 3;
            int n5 = AudioManager.b(audioManager2, 1);
            AudioManager.i(audioManager2);
            AudioManager.a(audioManager2, n5, -1, GameCanvas.k[audioManager.g[4]][5], true, 0, -n4, -1, true);
            if (AudioManager.c(audioManager2, n5, -1) > 0) {
                AudioManager.c(audioManager2, 9);
                continue;
            }
            AudioManager.G(audioManager2);
            AudioManager.I(audioManager2);
        }
    }

    public static boolean a() {
        return a != 100 && a != 16 && a != 10 && a != 11 && a != 15 && AudioManager.e(GameCanvas.c) != -1;
    }

    private static void y(AudioManager audioManager) {
        AudioManager audioManager2 = GameCanvas.a(false, false);
        if (GameCanvas.W && audioManager2 != null && GameCanvas.a(audioManager2, false)) {
            AudioManager.i(audioManager2);
            int n = AudioManager.b(audioManager2, 1);
            AudioManager.f(audioManager2, audioManager.a.a, audioManager.a.b, 0);
            AudioManager.a(audioManager2, n, -1, 100, true, 0, 0, -1, true);
            AudioManager.G(audioManager2);
            AudioManager.I(audioManager2);
            GameCanvas.b(14);
        }
        GameCanvas.W = false;
    }

    public static void a(int n, int[] nArray) {
        int n2 = nArray[0] + (nArray[2] - nArray[0] >> 1);
        int n3 = nArray[1] + (nArray[3] - nArray[1] >> 1);
        int n4 = GameCanvas.b(n2);
        int n5 = GameCanvas.b(n3);
        int n6 = GameCanvas.b(n);
        for (int i = -n6; i <= n6; ++i) {
            block5: for (int j = -n6; j <= n6; ++j) {
                AudioManager audioManager = GameCanvas.a(n4 + i, n5 + j);
                if (audioManager == null) continue;
                switch (audioManager.L) {
                    case 3: {
                        AudioManager.E(audioManager);
                        continue block5;
                    }
                    case 9: {
                        AudioManager.k(audioManager);
                    }
                }
            }
        }
    }

    public static void b(AudioManager audioManager, int n) {
        AudioManager.a(audioManager, n, false);
    }

    public static int a(int n, int n2) {
        return n * 30 / 100 * n2;
    }

    private static void a(AudioManager audioManager, int n, boolean bl) {
        if (!d) {
            if ((p & 2) == 0 || bl) {
                GameCanvas.b(audioManager.g[4] == 3 ? 23 : 22);
                int n2 = AudioManager.a(n, GameCanvas.bF);
                audioManager.g[7] = audioManager.g[7] - (n += n2);
                p |= 2;
                u = 0;
                GameCanvas.i();
            }
            GameCanvas.i(n);
        }
    }

    public static void b() {
        if ((p & 2) != 0 && (u += (int)GameCanvas.d) >= 2000) {
            p &= 0xFFFFFFFD;
            u = 0;
        }
    }

    private static boolean a(int n) {
        int n2 = 0;
        if (n != -1) {
            n2 = GameCanvas.b[n][15];
        }
        return n2 >= 3;
    }

    private static boolean n(AudioManager audioManager) {
        int n = audioManager.g[28];
        int n2 = -1;
        if (n > -1) {
            n2 = GameCanvas.l(n, 18);
        }
        return AudioManager.a(n2);
    }

    private static int b(int n) {
        int n2 = GameCanvas.l(GameCanvas.L[1], 18);
        short s = GameCanvas.b[n2][15];
        if (s >= 3) {
            int n3 = (s - 3 + 1) * 10;
            n -= n * n3 / 100;
        }
        return n;
    }

    public static void a(int n, AudioManager audioManager) {
        if (a != n || i != h) {
            switch (n) {
                case 14: 
                case 17: {
                    v = 0;
                    break;
                }
                case 1: 
                case 7: {
                    if (n == a) break;
                    l = 0;
                    break;
                }
                case 100: {
                    r = 0;
                    s = 0;
                    break;
                }
                case 0: {
                    break;
                }
                case 8: {
                    int n2 = GameCanvas.l(GameCanvas.L[1], 12);
                    if (n2 == -1) break;
                    p |= 1;
                    break;
                }
                case 13: {
                    GameCanvas.g();
                }
            }
            a = n;
            AudioManager.c(audioManager);
            if (a == 9) {
                if (GameCanvas.Y) {
                    GameCanvas.j(GameCanvas.b(audioManager));
                    GameCanvas.Y = false;
                }
                GameCanvas.g(audioManager);
            }
            if (a != 4) {
                i = h;
            }
        }
        int n3 = (int)GameCanvas.d;
        boolean bl = audioManager.g[9] == 1 && GameCanvas.b == 0;
        int n4 = AudioManager.b((bl ? 0x104000 : 983040) / 1000);
        int n5 = AudioManager.b((bl ? 1802240 : 1474560) / 1000);
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 16384 * n3 / 1000;
        switch (a) {
            case 18: {
                int n10 = AudioManager.a((GameCanvas.ez | GameCanvas.ey) & 0x3FDE);
                if (!GameCanvas.c() || n10 == 0) break;
                AudioManager.a(n10);
            }
            case 1: 
            case 7: {
                n8 = n4;
                int n11 = n6 = (h & 0x32DA) != 0 ? 983040 * n3 / 1000 : -j;
                if ((h & 0xF8E) != 0) {
                    n7 = 983040 * n3 / 1000;
                    break;
                }
                n7 = -k;
                break;
            }
            case 2: {
                n8 = 2457;
                int n12 = n6 = (h & 0x32DA) != 0 ? 0x1E0000 * n3 / 1000 : -j;
                if ((h & 0xF8E) != 0) {
                    n7 = 0x1E0000 * n3 / 1000;
                    break;
                }
                n7 = -k;
                break;
            }
            case 3: 
            case 14: {
                int n13;
                n8 = n5;
                if ((h & 0x32DA) != 0) {
                    n6 = 1474560 * n3 / 1000;
                    if (n6 < n4) {
                        n13 = n4;
                    }
                } else {
                    n13 = n6 = 0 - j;
                }
                if ((h & 0xF8E) != 0) {
                    n7 = 1474560 * n3 / 1000;
                    if (n7 >= n4) break;
                    n7 = n4;
                    break;
                }
                n7 = 0 - k;
                break;
            }
            case 4: {
                n8 = n5;
                n6 = -n9;
                n7 = -n9;
                break;
            }
            case 10: {
                GameCanvas.b(audioManager, true);
                break;
            }
            case 11: {
                GameCanvas.b(audioManager, false);
                break;
            }
            case 16: {
                int n14 = AudioManager.d(audioManager);
                j = GameCanvas.d(audioManager.g[n14 + 11] * 80) / 1000;
                AudioManager.n = audioManager.g[n14 + 11] < 0 ? -1 : 1;
                k = GameCanvas.d(audioManager.g[n14 + 12] * 80) / 1000;
                o = audioManager.g[n14 + 12] < 0 ? -1 : 1;
                n8 = j > k ? j : k;
                w = 0;
                break;
            }
            default: {
                j = 0;
                k = 0;
            }
        }
        if (d) {
            n8 <<= 1;
        }
        if ((j += n6) < 0) {
            j = 0;
        }
        if (j > n8) {
            j = n8;
        }
        if ((k += n7) < 0) {
            k = 0;
        }
        if (k > n8) {
            k = n8;
        }
    }

    public static void a(int n) {
        block8: {
            int n2;
            block7: {
                block6: {
                    int n3;
                    h = n;
                    if ((h & 0x1010) != 0) {
                        n3 = -1;
                    } else if ((h & 0x2040) != 0) {
                        n3 = AudioManager.n = 1;
                    }
                    if ((h & 0x404) == 0) break block6;
                    n2 = -1;
                    break block7;
                }
                if ((h & 0x900) == 0) break block8;
                n2 = 1;
            }
            o = n2;
        }
    }

    public static int a() {
        return 0;
    }

    public static void c(AudioManager audioManager) {
        AudioManager.a(audioManager, a, GameCanvas.cu, h, audioManager.g[28], audioManager.g[29]);
    }

    private static int c(int n) {
        int n2;
        block6: {
            int n3;
            block3: {
                block5: {
                    block4: {
                        block2: {
                            n2 = 0;
                            if ((n & 0x1010) == 0) break block2;
                            n3 = 2;
                            break block3;
                        }
                        if ((n & 0x2040) == 0) break block4;
                        n3 = 3;
                        break block3;
                    }
                    if ((n & 0x404) == 0) break block5;
                    n3 = 0;
                    break block3;
                }
                if ((n & 0x900) == 0) break block6;
                n3 = 1;
            }
            n2 = n3;
        }
        return n2;
    }

    /*
     * Unable to fully structure code
     */
    public static int a(AudioManager var0, int var1_1, int var2_2, int var3_3, int var4_4, int var5_5) {
        var6_6 = 0;
        var7_7 = true;
        var6_6 = AudioManager.c(var3_3);
        switch (var1_1) {
            case 8: {
                v0 = AudioManager.c ? 116 : 88;
                ** GOTO lbl95
            }
            case 0: 
            case 5: {
                if (var4_4 > -1) {
                    v0 = 64;
                    v1 = GameCanvas.h(var3_3);
                } else {
                    v0 = 56;
                    v1 = GameCanvas.h(var3_3);
                }
                ** GOTO lbl96
            }
            case 1: {
                if (var4_4 > -1) {
                    var6_6 += 72;
                    break;
                }
                var6_6 += 48;
                break;
            }
            case 2: {
                var7_7 = false;
                v0 = 24;
                v1 = GameCanvas.h(var3_3);
                ** GOTO lbl96
            }
            case 14: {
                var7_7 = false;
                v0 = 16;
                v1 = GameCanvas.h(var3_3);
                ** GOTO lbl96
            }
            case 15: {
                var7_7 = false;
                if (GameCanvas.k[var0.g[4]][6] == -1) {
                    var6_6 += 40;
                    break;
                }
                v0 = 16;
                v1 = GameCanvas.h(var3_3);
                ** GOTO lbl96
            }
            case 3: 
            case 4: {
                if (var4_4 > -1) {
                    var6_6 += 76;
                    break;
                }
                var6_6 += 52;
                break;
            }
            case 16: {
                var6_6 += AudioManager.f(var0);
                var7_7 = false;
                break;
            }
            case 100: {
                var7_7 = false;
                if (AudioManager.s == 0) {
                    var6_6 += 8;
                    break;
                }
                var6_6 += 44;
                break;
            }
            case 666: {
                var7_7 = false;
                v2 = 7;
                break;
            }
            case 9: {
                var6_6 += 104;
                break;
            }
            case 12: {
                var6_6 += 108;
                break;
            }
            case 13: {
                var6_6 += 112;
                break;
            }
            case 101: {
                var7_7 = false;
                var6_6 += 12;
                break;
            }
            case 6: {
                v0 = 80;
                ** GOTO lbl95
            }
            case 7: {
                v0 = 96;
                ** GOTO lbl95
            }
            case 10: {
                var7_7 = false;
                var6_6 += 36;
                break;
            }
            case 11: {
                var7_7 = false;
                var6_6 += 32;
                break;
            }
            case 17: {
                var7_7 = false;
                var6_6 += 40;
                break;
            }
            case 18: {
                var7_7 = false;
                v0 = GameCanvas.a(var0, var5_5);
lbl95:
                // 4 sources

                v1 = var2_2;
lbl96:
                // 6 sources

                v2 = var6_6 = v0 + v1;
            }
        }
        if (var7_7) {
            var6_6 += GameCanvas.k(var4_4);
        }
        if (var0 != null && (var6_6 += AudioManager.a()) != var0.a.d && (var0.a.a == null || var6_6 < var0.a.a.h.length)) {
            var0.a.b(var6_6);
        }
        return var6_6;
    }

    public static int a(AudioManager audioManager, AudioManager audioManager2, boolean bl) {
        boolean bl2 = true;
        if (audioManager2 != null && audioManager2.L >= 0 && audioManager2.L < 9) {
            bl2 = false;
        }
        return AudioManager.a(audioManager, audioManager2, bl, bl2);
    }

    private static int a(AudioManager audioManager, AudioManager audioManager2, boolean bl, boolean bl2) {
        int n = 0;
        if (audioManager2 != null && audioManager != null) {
            int n2;
            int n3;
            int n4 = audioManager2.a.a;
            int n5 = audioManager2.a.b;
            if (bl2) {
                int[] nArray = GameCanvas.a(audioManager2);
                n4 = nArray[0] + (nArray[2] - nArray[0] >> 1) << 14;
                n5 = nArray[1] + (nArray[3] - nArray[1] >> 1) << 14;
            }
            if (GameCanvas.c(0, n3 = GameCanvas.b(audioManager.a.a, audioManager.a.b, n4, n5))) {
                n2 = 8256;
            } else if (GameCanvas.c(1, n3)) {
                n2 = 9284;
            } else if (GameCanvas.c(2, n3)) {
                n2 = 1028;
            } else if (GameCanvas.c(3, n3)) {
                n2 = 5140;
            } else if (GameCanvas.c(4, n3)) {
                n2 = 4112;
            } else if (GameCanvas.c(5, n3)) {
                n2 = 6416;
            } else if (GameCanvas.c(6, n3)) {
                n2 = 2304;
            } else if (GameCanvas.c(7, n3)) {
                n2 = n = 10560;
            }
            if (bl) {
                AudioManager.a(n);
            }
        }
        return n;
    }

    private static boolean b(AudioManager audioManager, int n, int n2) {
        AudioManager audioManager2 = GameCanvas.a(n, n2);
        boolean bl = false;
        if (audioManager2 != null && audioManager2 != audioManager) {
            int n3 = GameCanvas.d(n, n2);
            int n4 = GameCanvas.e(n, n2);
            GameCanvas.c(audioManager2);
            GameCanvas.a(audioManager2, 1, true);
            switch (audioManager2.L) {
                case 0: 
                case 1: {
                    int n5 = AudioManager.d(audioManager2);
                    if (AudioManager.e(audioManager2) != 2 && AudioManager.e(audioManager2) != 4 || (audioManager2.g[n5 + 14] & 0x20) != 0 || !AudioManager.j(audioManager2)) break;
                    int n6 = n5 + 14;
                    audioManager2.g[n6] = audioManager2.g[n6] | 0x20;
                    int n7 = audioManager2.g[n5 + 11];
                    int n8 = audioManager2.g[n5 + 12];
                    int n9 = audioManager2.a.a;
                    int n10 = audioManager2.a.b;
                    audioManager2.a.c(GameCanvas.c(GameCanvas.b(audioManager2.a.a())) + 8);
                    audioManager2.a.d(GameCanvas.c(GameCanvas.b(audioManager2.a.b())) + 8);
                    AudioManager.g(audioManager2, GameCanvas.c(GameCanvas.b(audioManager.a.a())) + 8 << 14, GameCanvas.c(GameCanvas.b(audioManager.a.b())) + 8 << 14, 1);
                    audioManager2.a.a = n9;
                    audioManager2.a.b = n10;
                    bl = AudioManager.e(audioManager2, 60);
                    if (!bl) {
                        int n11;
                        AudioManager audioManager3;
                        if (!AudioManager.o(audioManager2)) {
                            audioManager2.a.c(GameCanvas.c(GameCanvas.b(audioManager2.a.a())) + 8);
                            audioManager2.a.d(GameCanvas.c(GameCanvas.b(audioManager2.a.b())) + 8);
                            n7 = audioManager2.g[n5 + 11];
                            n8 = audioManager2.g[n5 + 12];
                            audioManager3 = audioManager2;
                            n11 = 1;
                        } else {
                            audioManager3 = audioManager2;
                            n11 = 0;
                        }
                        AudioManager.d(audioManager3, n11);
                    }
                    audioManager2.g[n5 + 11] = n7;
                    audioManager2.g[n5 + 12] = n8;
                }
            }
            GameCanvas.a(audioManager2, 0, true);
            GameCanvas.a(audioManager2, n3, n4);
        }
        return bl;
    }

    private static boolean o(AudioManager audioManager) {
        int n = audioManager.a.a();
        int n2 = audioManager.a.b();
        boolean bl = false;
        bl = false | AudioManager.b(audioManager, GameCanvas.b(n), GameCanvas.b(n2));
        bl |= AudioManager.b(audioManager, GameCanvas.b(n - 8), GameCanvas.b(n2 + 8));
        bl |= AudioManager.b(audioManager, GameCanvas.b(n + 8), GameCanvas.b(n2 + 8));
        bl |= AudioManager.b(audioManager, GameCanvas.b(n - 8), GameCanvas.b(n2 - 8));
        return bl |= AudioManager.b(audioManager, GameCanvas.b(n + 8), GameCanvas.b(n2 - 8));
    }

    private static boolean a(AudioManager audioManager, int n, int n2, int n3, int n4) {
        int n5;
        int n6;
        int n7 = n2;
        a = false;
        GameData gameData = audioManager.a;
        for (int i = n3; n7 > 0 || i > 0; n7 -= n6, i -= n5) {
            block17: {
                int n8;
                block15: {
                    int n9;
                    block21: {
                        int n10;
                        block23: {
                            GameData gameData2;
                            int n11;
                            block22: {
                                int n12;
                                block18: {
                                    int n13;
                                    block20: {
                                        GameData gameData3;
                                        block19: {
                                            block16: {
                                                n9 = gameData.a >> 14;
                                                n8 = gameData.b >> 14;
                                                n6 = n7 > 131072 ? 131072 : n7;
                                                n5 = i > 131072 ? 131072 : i;
                                                gameData.b += n5 * o;
                                                gameData.a += n6 * AudioManager.n;
                                                if ((n & 0x404) != 0) {
                                                    n8 = GameCanvas.c(GameCanvas.b(n8)) + 8;
                                                }
                                                if ((n & 0x900) != 0) {
                                                    n8 = GameCanvas.c(GameCanvas.b(n8)) + 8;
                                                }
                                                if ((n & 0x1010) != 0) {
                                                    n9 = GameCanvas.c(GameCanvas.b(n9)) + 8;
                                                }
                                                if ((n & 0x2040) != 0) {
                                                    n9 = GameCanvas.c(GameCanvas.b(n9)) + 8;
                                                }
                                                if (!AudioManager.a(gameData.a(), gameData.b())) continue;
                                                AudioManager.o(audioManager);
                                                if ((n & 0x3050) != 0 && !AudioManager.a(gameData.a(), n8)) break block15;
                                                if ((n & 0xD04) == 0 || AudioManager.a(n9, gameData.b())) break block16;
                                                gameData.a = n9 << 14;
                                                break block17;
                                            }
                                            if ((n & 0xD04) == 0) break block18;
                                            n11 = GameCanvas.d(16 - (n9 - 8 - GameCanvas.c(GameCanvas.b(n9 - 8))));
                                            n12 = GameCanvas.d(n9 + 8 - GameCanvas.c(GameCanvas.b(n9 + 8)));
                                            if (n11 == 0) {
                                                n11 = 8;
                                            }
                                            if (AudioManager.a(n9 + n11, gameData.b())) break block19;
                                            gameData3 = gameData;
                                            n13 = (n9 << 14) + n4;
                                            break block20;
                                        }
                                        if (AudioManager.a(n9 - n12, gameData.b())) break block18;
                                        gameData3 = gameData;
                                        n13 = (n9 << 14) - n4;
                                    }
                                    gameData3.a = n13;
                                    gameData.b = n8 << 14;
                                    a = true;
                                }
                                if ((n & 0x3050) == 0) break block21;
                                n11 = GameCanvas.d(n8 + 8 - GameCanvas.c(GameCanvas.b(n8 + 8)));
                                n12 = GameCanvas.d(16 - (n8 - 8 - GameCanvas.c(GameCanvas.b(n8 - 8))));
                                if (AudioManager.a(gameData.a(), n8 + n12)) break block22;
                                gameData.a = n9 << 14;
                                gameData2 = gameData;
                                n10 = (n8 << 14) + n4;
                                break block23;
                            }
                            if (AudioManager.a(gameData.a(), n8 - n11)) break block21;
                            gameData.a = n9 << 14;
                            gameData2 = gameData;
                            n10 = (n8 << 14) - n4;
                        }
                        gameData2.b = n10;
                        a = true;
                    }
                    if (a) break block17;
                    gameData.a = n9 << 14;
                }
                gameData.b = n8 << 14;
            }
            return false;
        }
        return true;
    }

    public static boolean a(int n, int n2) {
        int n3 = 13;
        if (GameCanvas.b != 0) {
            n3 = 5;
        }
        return GameCanvas.b(GameCanvas.b(n), GameCanvas.b(n2), n3) || GameCanvas.b(GameCanvas.b(n - 8), GameCanvas.b(n2 + 7), n3) || GameCanvas.b(GameCanvas.b(n + 7), GameCanvas.b(n2 + 7), n3) || GameCanvas.b(GameCanvas.b(n - 8), GameCanvas.b(n2 - 8), n3) || GameCanvas.b(GameCanvas.b(n + 7), GameCanvas.b(n2 - 8), n3);
    }

    public static boolean a(AudioManager audioManager, AudioManager audioManager2) {
        if (audioManager2 != null && audioManager != null) {
            int n = GameCanvas.b(audioManager2.a.a());
            int n2 = GameCanvas.b(audioManager2.a.b());
            int n3 = 1;
            int n4 = 1;
            if (audioManager2.L == 10) {
                --n;
                --n2;
                n3 = audioManager2.g[2] + 2;
                n4 = audioManager2.g[3] + 2;
            }
            int n5 = audioManager.a.a();
            int n6 = audioManager.a.b();
            for (int i = 0; i < n3; ++i) {
                for (int j = 0; j < n4; ++j) {
                    if (!(n + i == GameCanvas.b(n5) && n2 + j == GameCanvas.b(n6) || n + i == GameCanvas.b(n5 + 8) && n2 + j == GameCanvas.b(n6 + 8) || n + i == GameCanvas.b(n5 - 8) && n2 + j == GameCanvas.b(n6 + 8) || n + i == GameCanvas.b(n5 + 8) && n2 + j == GameCanvas.b(n6 - 8)) && (n + i != GameCanvas.b(n5 - 8) || n2 + j != GameCanvas.b(n6 - 8))) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private static void c(Graphics graphics, AudioManager audioManager) {
        if (a == 10 || a == 11) {
            audioManager.a.a += GameCanvas.ee;
            audioManager.a.b += GameCanvas.ef;
        }
        AudioManager.a(graphics, audioManager, audioManager.a.a(), audioManager.a.b(), true);
        AudioManager.b(graphics, audioManager, true);
        audioManager.a.a.e = audioManager.g[2];
        AudioManager.a(graphics, 0, audioManager.a.a >> 14, audioManager.a.b >> 14);
        audioManager.a.a.a(GameCanvas.a(audioManager));
        audioManager.a.a(graphics);
        AudioManager.a(graphics, audioManager, audioManager.a.a(), audioManager.a.b(), false);
        AudioManager.b(graphics, audioManager, false);
    }

    private static void a(Graphics graphics, AudioManager audioManager, int n, int n2, boolean bl) {
        if ((p & 1) != 0 && (bl && (h & 0x404) != 0 || !bl && (h & 0x404) == 0)) {
            int n3 = GameCanvas.cu + 5;
            GameCanvas.e(16);
            int[] nArray = AudioManager.a(audioManager, false, true);
            GameCanvas.a[15].a(graphics, n3, n - nArray[0], n2 - nArray[1], 0);
            GameCanvas.f(16);
            if ((q += (int)GameCanvas.d) >= 100) {
                q = 0;
                p &= 0xFFFFFFFE;
            }
        }
    }

    private static void b(AudioManager audioManager, AudioManager audioManager2, boolean bl) {
        int n;
        int[] nArray;
        int n2;
        int n3 = AudioManager.d(audioManager);
        if (bl) {
            AudioManager.g(audioManager, audioManager2.a.a, audioManager2.a.b, 0);
            int n4 = n3 + 11;
            audioManager.g[n4] = audioManager.g[n4] * -1;
            int n5 = n3 + 12;
            n2 = n5;
            nArray = audioManager.g;
            n = audioManager.g[n5] * -1;
        } else {
            audioManager.g[n3 + 11] = audioManager2.g[13];
            audioManager.g[n3 + 12] = audioManager2.g[14];
            int n6 = audioManager2.g[13] << 14;
            int n7 = audioManager2.g[14] << 14;
            int n8 = GameCanvas.b(-n7, n6) * 57 >> 14;
            GameCanvas.a();
            GameCanvas.a();
            audioManager.g[n3 + 11] = GameCanvas.e(n8) << 6;
            nArray = audioManager.g;
            n2 = n3 + 12;
            n = -(GameCanvas.f(n8) << 6);
        }
        nArray[n2] = n;
        AudioManager.a(16, audioManager);
    }

    private static void a(int n, int n2) {
        block2: {
            block7: {
                int n3;
                block4: {
                    block6: {
                        block5: {
                            block3: {
                                if ((p & 4) != 0) break block2;
                                p |= 4;
                                if (n <= 8192) break block3;
                                n3 = 8256;
                                break block4;
                            }
                            if (n >= -8192) break block5;
                            n3 = 4112;
                            break block4;
                        }
                        if (n2 <= 8192) break block6;
                        n3 = 2304;
                        break block4;
                    }
                    if (n2 >= -8192) break block7;
                    n3 = 1028;
                }
                x = n3;
            }
            int n4 = AudioManager.c(x);
            GameCanvas.a[17].b(n4);
        }
    }

    private static void b(Graphics graphics, AudioManager audioManager, boolean bl) {
        if (!GameCanvas.N && (p & 4) != 0 && (bl && (x & 0x404) != 0 || !bl && (x & 0x404) == 0)) {
            int[] nArray = GameCanvas.b(audioManager.a);
            int n = nArray[3] - nArray[1];
            GameCanvas.a[17].a = audioManager.a.a;
            GameCanvas.a[17].b = audioManager.a.b - (n >> 1 << 14);
            GameCanvas.a[17].a(graphics);
        }
        if (GameCanvas.a[17].a()) {
            p &= 0xFFFFFFFB;
        }
    }

    private static boolean d() {
        return j != 0 || k != 0;
    }

    private static boolean e() {
        return a == 18 && GameCanvas.m(GameCanvas.M[0], 1) == 1;
    }

    private static void z(AudioManager audioManager) {
        int n;
        if (audioManager.g[7] < 200 && (GameCanvas.ez & 0x80000) != 0 && (n = GameCanvas.c(GameCanvas.bt, 7)) != -1) {
            byte by = GameCanvas.c[GameCanvas.bt][n];
            GameCanvas.c(by, 1, GameCanvas.bt, n);
        }
    }

    public static int[] a(AudioManager audioManager, boolean bl, boolean bl2) {
        int n;
        int n2;
        int n3;
        int[] nArray = GameCanvas.s;
        GameCanvas.s[0] = 0;
        nArray[1] = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        GraphicsEngine graphicsEngine = audioManager.a.a;
        if (bl) {
            n4 = audioManager.a.d;
            n5 = graphicsEngine.b(n4, audioManager.a.e);
            n3 = audioManager.a.e;
        } else {
            n2 = h;
            n = GameCanvas.cu;
            if (audioManager != GameCanvas.c) {
                n = AudioManager.a(audioManager, true);
                n2 = GameCanvas.j(n);
            }
            n4 = AudioManager.a(null, 8, n, n2, audioManager.g[28], audioManager.g[29]);
            n5 = GameCanvas.a(audioManager.a, n4);
            n3 = 0;
        }
        n6 = n3;
        int n7 = graphicsEngine.h[n4] + n6;
        int n8 = graphicsEngine.b[n5] & 0xFF;
        int n9 = GameCanvas.a(graphicsEngine, n4, 0);
        for (n2 = 0; n2 < n8; ++n2) {
            n = graphicsEngine.e[n5] + n2;
            int n10 = graphicsEngine.d[n] & 0xFF;
            int n11 = graphicsEngine.c[n] & 0xFF;
            if (graphicsEngine.k[n11 |= (n10 & 0xC0) << 2] != 1) continue;
            graphicsEngine.b(GameCanvas.r, n5, n2, 0, 0, n9);
            nArray[0] = -(GameCanvas.r[0] + graphicsEngine.i[n7]);
            nArray[1] = -(GameCanvas.r[1] + graphicsEngine.j[n7]);
            return nArray;
        }
        if (bl2) {
            int[] nArray2 = GameCanvas.b(audioManager.a);
            n = nArray2[3] - nArray2[1];
            n -= n >> 2;
            nArray[0] = 0;
            nArray[1] = n;
        } else {
            nArray = null;
        }
        return nArray;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int a(AudioManager audioManager) {
        int n = -1;
        switch (audioManager.g[1]) {
            case 0: {
                return 499;
            }
            case 4: {
                return 210;
            }
            case 9: {
                return 356;
            }
        }
        return n;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static int f(AudioManager audioManager) {
        int n = -1;
        switch (audioManager.g[1]) {
            case 0: {
                return 500;
            }
            case 4: {
                return 211;
            }
            case 9: {
                return 358;
            }
        }
        return n;
    }

    public static void d(AudioManager audioManager) {
        block1: {
            int n;
            block2: {
                AudioManager audioManager2;
                block0: {
                    if (a != 10 && a != 11 || GameCanvas.j == null) break block0;
                    if (GameCanvas.j.a.b <= audioManager.a.b) break block1;
                    AudioManager.u(GameCanvas.j);
                    audioManager2 = audioManager;
                    n = GameCanvas.j.M - 1;
                    break block2;
                }
                audioManager2 = audioManager;
                n = (audioManager.a.b() << 8) + audioManager.a.a();
            }
            audioManager2.M = n;
        }
    }

    private static void a(int n, int[] nArray, int n2, int n3) {
        block10: {
            block9: {
                if (nArray[0] != 1) break block9;
                switch (n) {
                    case 20: {
                        AudioManager.a(nArray, n2, n3);
                        break block10;
                    }
                    case 21: {
                        AudioManager.e(nArray);
                        break block10;
                    }
                    case 22: {
                        AudioManager.b(nArray, n2, n3);
                        break block10;
                    }
                    case 23: {
                        AudioManager.d(nArray, n2, n3);
                        break block10;
                    }
                    case 24: {
                        AudioManager.f(nArray);
                        break block10;
                    }
                    case 25: {
                        AudioManager.e(nArray, n2, n3);
                    }
                    default: {
                        return;
                    }
                }
            }
            if (n == 22) {
                AudioManager.c(nArray, n2, n3);
            }
        }
    }

    public static boolean b(AudioManager audioManager) {
        return audioManager.L == 20 && audioManager.g[0] == 1 && audioManager.g[1] == 1;
    }

    public static void a(int[] nArray) {
        if (nArray[7] == 1) {
            nArray[12] = 0;
        }
    }

    private static void a(int[] nArray, int n, int n2) {
        int n3 = GameCanvas.c.a.a() - 8;
        int n4 = GameCanvas.c.a.b() - 8;
        boolean bl = GameCanvas.a(n3, n4, 16, 16, n, n2, nArray[2], nArray[3]);
        boolean bl2 = nArray[1] == 0;
        if (bl2 && bl) {
            y = nArray[13];
            GameCanvas.b(nArray[6]);
            if (nArray[4] > 0) {
                nArray[4] = nArray[4] - 1;
                if (nArray[4] == 0) {
                    nArray[0] = 0;
                }
            }
        }
        int n5 = nArray[1] = bl ? 1 : 0;
        if (nArray[0] == 1 && nArray[7] == 1) {
            nArray[12] = GameCanvas.c % 6 < 3 ? 1 : 0;
        }
    }

    public static void a(Graphics graphics, int[] nArray, int n, int n2) {
        if (nArray[0] == 1 && nArray[7] == 1 && nArray[12] == 1) {
            graphics.setColor(nArray[8], nArray[9], nArray[10]);
            graphics.fillRect(n, n2, nArray[2], nArray[3]);
        }
    }

    private static void e(int[] nArray) {
        if (GameCanvas.b == 0) {
            nArray[7] = nArray[7] + (int)GameCanvas.d;
            z = nArray[7];
            if (nArray[7] >= nArray[8]) {
                z = -1;
                nArray[0] = 0;
                AudioManager.b(nArray);
                if (nArray[4] >= 0) {
                    GameCanvas.b(nArray[4]);
                }
            }
        }
    }

    private static boolean a(int[] nArray) {
        if (nArray[0] == 1 && (nArray[5] == 1 || nArray[6] == 1)) {
            GameCanvas.a();
            GameCanvas.a(18, -1);
            e = true;
            return true;
        }
        return false;
    }

    public static void b(int[] nArray) {
        if (nArray[0] == 1) {
            nArray[7] = 0;
            if (z != -1) {
                nArray[7] = z;
            }
            nArray[8] = nArray[1] * 1000;
            if (nArray[2] > 0) {
                nArray[8] = nArray[8] + GameCanvas.a() % nArray[2];
            }
            AudioManager.a(nArray);
            return;
        }
        if (GameCanvas.a == 6) {
            z = -1;
        }
        nArray[7] = 0;
        if (nArray[6] == 1 || nArray[5] == 1) {
            GameCanvas.c(GameCanvas.a(2));
            e = false;
        }
    }

    public static void a(Graphics graphics, int[] nArray) {
        int n;
        if (nArray[0] == 1 && nArray[5] == 1 && (n = nArray[8] - nArray[7]) >= 0) {
            int n2 = n / 1000;
            int n3 = (n - n2 * 1000) / 10;
            int n4 = n2 / 60;
            int n5 = n2 - 60 * (n2 / 60);
            GameCanvas.b(0, 0);
            GameCanvas.b(1, 0);
            GameCanvas.b(2, 0);
            GameCanvas.b(3, 0);
            GameCanvas.b(4, 0);
            GameCanvas.b(5, 0);
            GameCanvas.a(GameCanvas.b, null, 0, 11, 0, 240, 320, 0);
            GameCanvas.b(0, n4 / 10);
            GameCanvas.b(1, n4 - n4 / 10 * 10);
            GameCanvas.b(2, n5 / 10);
            GameCanvas.b(3, n5 - n5 / 10 * 10);
            GameCanvas.b(4, n3 / 10);
            GameCanvas.b(5, n3 - n3 / 10 * 10);
            graphics.setColor(0xFF0000);
            byte[] byArray = GameCanvas.a[12].a(15, 0);
            int n6 = byArray[0] & 0xFF;
            int n7 = byArray[1] & 0xFF;
            int n8 = byArray[2] & 0xFF;
            int n9 = byArray[3] & 0xFF;
            graphics.fillRect(n6, n7, n8, n9);
            GameCanvas.b[0].a(0);
            GameCanvas.a(graphics, GameCanvas.b, null, 0, 11, 0, n6 + 10, n7 + 1, 4);
        }
    }

    private static void b(int[] nArray, int n, int n2) {
        int n3 = GameCanvas.c.a.a() - 8;
        int n4 = GameCanvas.c.a.b() - 8;
        boolean bl = GameCanvas.a(n3, n4, 16, 16, n + nArray[11], n2 + nArray[12], nArray[13], nArray[14]);
        boolean bl2 = nArray[1] == 0;
        if (bl2 && bl) {
            AudioManager.a(0, GameCanvas.c);
            int n5 = nArray[8];
            int n6 = nArray[10];
            int n7 = nArray[9];
            if (GameCanvas.g[n5][n7][n6].L == 22) {
                int n8 = GameCanvas.by;
                int n9 = GameCanvas.bz;
                GameCanvas.a(n5, n7, n6);
                GameCanvas.a(n8, n9, false);
                GameCanvas.b(n8, n9, false);
            }
        }
        nArray[1] = bl ? 1 : 0;
        AudioManager.c(nArray, n, n2);
    }

    private static void c(int[] nArray, int n, int n2) {
        boolean bl;
        boolean bl2 = nArray[0] == 1;
        if ((bl2 || nArray[10] == -1) && (bl = GameCanvas.a(GameCanvas.T >> 14, GameCanvas.U >> 14, 240, 320, n, n2, nArray[2], nArray[3]))) {
            GameCanvas.a(true);
        }
    }

    public static void a(Graphics graphics, int[] nArray, int n, int n2, boolean bl) {
        int n3 = GameCanvas.c.a.a() - 8;
        int n4 = GameCanvas.c.a.b() - 8;
        boolean bl2 = GameCanvas.a(n3, n4, 16, 16, n, n2, nArray[2], nArray[3]);
        if (bl && bl2 && GameCanvas.aT == 0) {
            GameCanvas.a(graphics, 0, 46, 240, GameCanvas.b[0].a() + 4);
            GameCanvas.a(graphics, GameCanvas.b, null, nArray[15], nArray[16], 0, 120, 48, 17);
        }
    }

    /*
     * Unable to fully structure code
     */
    private static void b(Graphics var0, int[] var1_1, int var2_2, int var3_3) {
        block6: {
            if (var1_1[0] != 1 && var1_1[10] != -1 || !(var4_4 = GameCanvas.a(0, 0, 240, 320, var2_2, var3_3, var1_1[2], var1_1[3])) || var1_1[13] <= 0 || var1_1[14] <= 0) break block6;
            var5_5 = 0;
            var6_6 = 0;
            var7_7 = 0;
            var8_8 = 0;
            var9_9 = GameCanvas.a(GameCanvas.a[15], 37);
            var10_10 = var9_9[2] - var9_9[0];
            var11_11 = var9_9[3] - var9_9[1];
            var12_12 = 0;
            switch (var1_1[4]) {
                case 0: {
                    var5_5 = var2_2 + var1_1[11];
                    var7_7 = var5_5 + var1_1[13];
                    var6_6 = var8_8 = var3_3 + var1_1[12];
                    v0 = var1_1[13];
                    v1 = var10_10;
                    ** GOTO lbl39
                }
                case 1: {
                    var5_5 = var2_2 + var1_1[11];
                    var7_7 = var5_5 + var1_1[13];
                    var6_6 = var8_8 = var3_3 + var1_1[12] + var1_1[14];
                    v0 = var1_1[13];
                    v1 = var10_10;
                    ** GOTO lbl39
                }
                case 2: {
                    v2 = var2_2;
                    v3 = var1_1;
                    v4 = 11;
                    ** GOTO lbl34
                }
                case 3: {
                    v2 = var2_2 + var1_1[11];
                    v3 = var1_1;
                    v4 = 13;
lbl34:
                    // 2 sources

                    var5_5 = var7_7 = v2 + v3[v4];
                    var6_6 = var3_3 + var1_1[12];
                    var8_8 = var6_6 + var1_1[14];
                    v0 = var1_1[14];
                    v1 = var11_11;
lbl39:
                    // 3 sources

                    var12_12 = v0 / v1;
                }
            }
            GameCanvas.e(8);
            GameCanvas.a(var0, GameCanvas.a[15], 37, var5_5, var6_6, var7_7, var8_8, var12_12);
            GameCanvas.f(8);
        }
    }

    public static int[] a(int[] nArray) {
        int[] nArray2 = new int[nArray.length + 1];
        System.arraycopy(nArray, 0, nArray2, 0, nArray.length);
        nArray2[12] = 1;
        return nArray2;
    }

    private static boolean a(int[] nArray, int n, int n2) {
        int n3 = GameCanvas.b(n);
        int n4 = GameCanvas.b(n2);
        int n5 = GameCanvas.b(nArray[2]) + (nArray[2] % 16 == 0 ? 0 : 1);
        int n6 = GameCanvas.b(nArray[3]) + (nArray[3] % 16 == 0 ? 0 : 1);
        for (int i = 0; i < n5; ++i) {
            for (int j = 0; j < n6; ++j) {
                if (!GameCanvas.b(n3 + i, n4 + j, 4)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean c(AudioManager audioManager) {
        int[] nArray = GameCanvas.g[GameCanvas.by][GameCanvas.bz][audioManager.g[8]].g;
        if (GameCanvas.g[GameCanvas.by][GameCanvas.bz][audioManager.g[8]].g[0] == 1 && nArray[11] == 0) {
            int n;
            int n2 = audioManager.a.a();
            AudioManager audioManager2 = AudioManager.a(n2, n = audioManager.a.b(), nArray[2], nArray[3]);
            boolean bl = audioManager2 != null;
            boolean bl2 = true;
            if (a == 6 || a == 7 || a == 8 || a == 100 || a == 101 || a == 17) {
                bl2 = false;
            }
            if (!bl && bl2) {
                return !AudioManager.a(nArray, n2, n);
            }
        }
        return false;
    }

    public static void a(AudioManager audioManager, int n, int n2) {
        int[] nArray = audioManager.g;
        if (audioManager.g[12] == 0) {
            nArray[12] = 1;
            AudioManager.a(nArray, 7, false, false, n, n2, false);
        }
    }

    private static void A(AudioManager audioManager) {
        boolean bl;
        boolean bl2;
        int n;
        int[] nArray;
        int[] nArray2 = audioManager.g;
        if (audioManager.g[12] == 1) {
            nArray2[12] = 0;
            nArray = nArray2;
            n = 6;
            bl2 = false;
            bl = true;
        } else {
            nArray2[12] = 1;
            nArray = nArray2;
            n = 7;
            bl2 = false;
            bl = false;
        }
        AudioManager.a(nArray, n, bl2, bl, GameCanvas.by, GameCanvas.bz, true);
    }

    public static AudioManager a(int n, int n2, int[] nArray, int n3) {
        int n4;
        int n5;
        int[] nArray2;
        int[] nArray3 = new int[9];
        int[] nArray4 = nArray3;
        nArray3[0] = nArray[4];
        nArray4[1] = nArray[5];
        nArray4[8] = n3;
        if (nArray[0] == 1) {
            nArray4[2] = 0;
            nArray2 = nArray4;
            n5 = 3;
            n4 = 0;
        } else {
            nArray4[2] = GameCanvas.b(nArray[2]) + (nArray[2] % 16 == 0 ? 0 : 1);
            nArray2 = nArray4;
            n5 = 3;
            n4 = GameCanvas.b(nArray[3]) + (nArray[3] % 16 == 0 ? 0 : 1);
        }
        nArray2[n5] = n4;
        nArray4[4] = -1;
        nArray4[5] = nArray[7];
        nArray4[6] = 0;
        return new AudioManager(-1, 10, n, n2, nArray4);
    }

    private static AudioManager a(int n, int n2, int n3, int n4) {
        int n5 = GameCanvas.b(n);
        int n6 = GameCanvas.b(n2);
        int n7 = GameCanvas.b(n3);
        int n8 = GameCanvas.b(n4);
        for (int i = 0; i < n7; ++i) {
            for (int j = 0; j < n8; ++j) {
                AudioManager audioManager = GameCanvas.a(n5 + i, n6 + j);
                if (audioManager == null || audioManager.L >= 9) continue;
                return audioManager;
            }
        }
        return null;
    }

    private static void d(int[] nArray, int n, int n2) {
        block8: {
            int n3;
            int[] nArray2;
            block10: {
                block9: {
                    if (nArray[12] != 1 || AudioManager.a(nArray, n, n2)) break block8;
                    AudioManager audioManager = AudioManager.a(n, n2, nArray[2], nArray[3]);
                    boolean bl = audioManager != null;
                    boolean bl2 = nArray[1] == 0;
                    if (bl2 && bl && (nArray[8] == 0 || 0 >= nArray[9])) {
                        AudioManager.a(nArray, 6, false, true, GameCanvas.by, GameCanvas.bz, true);
                    } else {
                        int n4;
                        int n5;
                        int[] nArray3;
                        AudioManager.a(nArray, 7, false, false, GameCanvas.by, GameCanvas.bz, true);
                        if (bl2 && bl && nArray[8] == 1 && 0 < nArray[9]) {
                            AudioManager.a(0, GameCanvas.c);
                            GameCanvas.b(nArray[10]);
                            nArray3 = nArray;
                            n5 = 1;
                            n4 = 1;
                        } else if (!bl) {
                            nArray3 = nArray;
                            n5 = 1;
                            n4 = nArray3[n5] = 0;
                        }
                    }
                    if (nArray[8] != 1 || 0 >= nArray[9]) break block9;
                    nArray2 = nArray;
                    n3 = 1;
                    break block10;
                }
                if (nArray[5] != 1) break block8;
                nArray2 = nArray;
                n3 = 0;
            }
            AudioManager.a(nArray2, n3, GameCanvas.by, GameCanvas.bz);
        }
    }

    public static void c(int[] nArray) {
        AudioManager.a(nArray, 7, true, false, GameCanvas.by, GameCanvas.bz, false);
        nArray[5] = 1;
        nArray[12] = 1;
    }

    public static void b(AudioManager audioManager, int n, int n2) {
        int[] nArray = audioManager.g;
        AudioManager audioManager2 = GameCanvas.a[n][n2][nArray[4]];
        if (GameCanvas.by == n && GameCanvas.bz == n2) {
            int n3 = audioManager2.g[2];
            int n4 = audioManager2.g[3];
            audioManager2.g[2] = GameCanvas.b(nArray[2]) + (nArray[2] % 16 == 0 ? 0 : 1);
            audioManager2.g[3] = GameCanvas.b(nArray[3]) + (nArray[3] % 16 == 0 ? 0 : 1);
            GameCanvas.d(audioManager2);
            GameCanvas.a(audioManager2, 192);
            audioManager2.a.a = audioManager.a.a;
            audioManager2.a.b = audioManager.a.b;
            GameCanvas.b(audioManager2, 192, false);
            audioManager2.g[2] = n3;
            audioManager2.g[3] = n4;
            AudioManager.a(audioManager.g, audioManager.g[5], n, n2);
            return;
        }
        audioManager2.a.a = audioManager.a.a;
        audioManager2.a.b = audioManager.a.b;
    }

    private static void a(int[] nArray, int n, boolean bl, boolean bl2, int n2, int n3, boolean bl3) {
        AudioManager audioManager = GameCanvas.a[n2][n3][nArray[4]];
        if (audioManager.a.d != nArray[n]) {
            audioManager.a.a(nArray[n], 0);
            if (bl3 && !GameCanvas.A && nArray[11] != 1) {
                GameCanvas.b(5);
            }
        }
        int n4 = audioManager.g[2];
        int n5 = audioManager.g[3];
        audioManager.g[2] = GameCanvas.b(nArray[2]) + (nArray[2] % 16 == 0 ? 0 : 1);
        audioManager.g[3] = GameCanvas.b(nArray[3]) + (nArray[3] % 16 == 0 ? 0 : 1);
        if (bl2) {
            GameCanvas.a(audioManager, 192);
        } else {
            GameCanvas.b(audioManager, 192, false);
        }
        audioManager.g[2] = n4;
        audioManager.g[3] = n5;
        if (bl) {
            GameCanvas.a(audioManager.a);
        }
    }

    /*
     * Unable to fully structure code
     */
    public static void a(int[] var0, int var1_1, int var2_2, int var3_3) {
        block3: {
            var4_4 = GameCanvas.a[var2_2][var3_3][var0[4]];
            var0[5] = var1_1;
            if (var2_2 != GameCanvas.by || var3_3 != GameCanvas.bz) break block3;
            if (var0[5] == 1) {
                var4_4.g[2] = GameCanvas.b(var0[2]) + (var0[2] % 16 == 0 ? 0 : 1);
                var4_4.g[3] = GameCanvas.b(var0[3]) + (var0[3] % 16 == 0 ? 0 : 1);
                GameCanvas.a(var4_4, true);
                return;
            }
            GameCanvas.d(var4_4);
            ** GOTO lbl-1000
        }
        if (var0[5] == 1) {
            var4_4.g[2] = GameCanvas.b(var0[2]) + (var0[2] % 16 == 0 ? 0 : 1);
            v0 = var4_4.g;
            v1 = 3;
            v2 = GameCanvas.b(var0[3]) + (var0[3] % 16 == 0 ? 0 : 1);
        } else lbl-1000:
        // 2 sources

        {
            var4_4.g[2] = 0;
            v0 = var4_4.g;
            v1 = 3;
            v2 = 0;
        }
        v0[v1] = v2;
    }

    private static void f(int[] nArray) {
        if (nArray[0] == 1 && GameCanvas.b == 0) {
            boolean bl = true;
            if (nArray[3] == 1) {
                bl = GameCanvas.c(1, -1, -1, -1, nArray[4], nArray[5], nArray[6], 0);
            }
            if (bl) {
                GameCanvas.b(nArray[2]);
            }
        }
    }

    public static int[] b(int[] nArray) {
        int[] nArray2 = new int[nArray.length + 1];
        System.arraycopy(nArray, 0, nArray2, 0, nArray.length);
        nArray2[14] = 0;
        return nArray2;
    }

    public static void a(int[] nArray, int n, int n2, boolean bl, boolean bl2) {
        if (bl2) {
            int n3 = GameCanvas.b(n + nArray[4]);
            int n4 = GameCanvas.b(n2 + nArray[5]);
            int n5 = GameCanvas.b(nArray[6]);
            int n6 = GameCanvas.b(nArray[7]);
            GameCanvas.a(n3, n4, n5, n6, 16);
            GameCanvas.a(n3 - 1, n4 - 1, n5 + 2, n6 + 2);
        }
        if (nArray[0] == 1) {
            if (nArray[11] == 1) {
                GameCanvas.c.g[11] = 0;
                nArray[10] = GameCanvas.f[nArray[12]][nArray[13]].length;
                GameCanvas.a(1, 0, nArray[14], nArray[10], 0, 33);
                f = nArray;
                return;
            }
            GameCanvas.a(0, 0, nArray[14], nArray[10], 0, 33);
            return;
        }
        GameCanvas.h(0);
        GameCanvas.h(1);
        f = null;
        if (bl && nArray[11] == 1) {
            GameCanvas.c.g[11] = nArray[14];
            GameCanvas.bR = nArray[9];
        }
    }

    private static void e(int[] nArray, int n, int n2) {
        int n3 = GameCanvas.c.a.a() - 8;
        int n4 = GameCanvas.c.a.b() - 8;
        boolean bl = GameCanvas.a(n3, n4, 16, 16, n, n2, nArray[2], nArray[3]);
        boolean bl2 = nArray[1] == 0;
        if (bl2 && bl) {
            int n5 = GameCanvas.b(n + nArray[4]);
            int n6 = GameCanvas.b(n2 + nArray[5]);
            int n7 = GameCanvas.b(nArray[6]);
            int n8 = GameCanvas.b(nArray[7]);
            for (int i = 0; i < n7; ++i) {
                for (int j = 0; j < n8; ++j) {
                    AudioManager audioManager = GameCanvas.a(n5 + i, n6 + j);
                    if (audioManager == null || audioManager.L != 1 || !AudioManager.A(audioManager)) continue;
                    nArray[14] = nArray[14] + 1;
                    audioManager.g[17] = 1;
                    AudioManager.n(audioManager, 8);
                }
            }
        }
        GameCanvas.c(nArray[11] == 1 ? 1 : 0, nArray[14]);
        if (nArray[14] >= nArray[10]) {
            if (nArray[11] == 1) {
                GameCanvas.c.g[11] = nArray[14];
            }
            nArray[1] = 1;
            GameCanvas.b(nArray[9]);
            nArray[0] = 0;
            GameCanvas.h(0);
            GameCanvas.h(1);
            f = null;
        }
    }

    public static void c() {
        a = new GameData[20];
        for (int i = 0; i < 20; ++i) {
            AudioManager.a[i] = new GameData(null, 0, 0, null);
        }
    }

    private static void b(AudioManager audioManager, int n, int n2, int n3) {
        AudioManager.a(audioManager, n, n2, n3, false, 0);
    }

    /*
     * Unable to fully structure code
     */
    private static void a(AudioManager var0, int var1_1, int var2_2, int var3_3, boolean var4_4, int var5_5) {
        block8: {
            var6_6 = -1;
            for (var7_7 = 0; var7_7 < 4; ++var7_7) {
                v0 = (byte)GameCanvas.a(var0.g[21], 255 << var7_7 * 8);
                var8_8 = v0;
                if (v0 != -1) continue;
                var6_6 = var7_7;
                break;
            }
            if (var6_6 < 0) break block8;
            var7_7 = -1;
            for (var8_8 = 0; var8_8 < AudioManager.a.length; ++var8_8) {
                if (AudioManager.a[var8_8].a != null) continue;
                var7_7 = var8_8;
                break;
            }
            if (var7_7 < 0) break block8;
            var0.g[21] = GameCanvas.a(var0.g[21], 255 << var6_6 * 8, var7_7);
            var8_9 = AudioManager.a[var7_7];
            var8_9.a(GameCanvas.a[14]);
            var8_9.a = var0.a.a + (var1_1 << 14);
            var8_9.b = var0.a.b + (var2_2 << 14);
            var0.g[16] = var0.g[16] & -3;
            switch (var4_4 != false ? var5_5 : GameCanvas.cu) {
                case 1: 
                case 2: 
                case 3: {
                    v1 = var8_9;
                    v2 = 0;
                    ** GOTO lbl37
                }
                case 5: 
                case 6: 
                case 7: {
                    var8_9.b(1 + var3_3);
                    var0.g[16] = var0.g[16] | 2;
                    break;
                }
                case 4: {
                    v1 = var8_9;
                    v2 = 2;
                    ** GOTO lbl37
                }
                case 0: {
                    v1 = var8_9;
                    v2 = 3;
lbl37:
                    // 3 sources

                    v1.b(v2 + var3_3);
                }
            }
            var0.g[16] = var0.g[16] | 1;
        }
    }

    private static void c(Graphics graphics, AudioManager audioManager, boolean bl) {
        if ((audioManager.g[16] & 1) != 0 && (bl && (audioManager.g[16] & 2) != 0 || !bl && (audioManager.g[16] & 2) == 0)) {
            for (int i = 0; i < 4; ++i) {
                byte by = (byte)GameCanvas.a(audioManager.g[21], 255 << i * 8);
                if (by < 0) continue;
                GameData gameData = a[by];
                gameData.a -= GameCanvas.T;
                gameData.b -= GameCanvas.U;
                gameData.a(graphics);
                gameData.a += GameCanvas.T;
                gameData.b += GameCanvas.U;
            }
        }
    }

    public static void e(AudioManager audioManager) {
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            byte by = (byte)GameCanvas.a(audioManager.g[21], 255 << i * 8);
            if (by < 0) continue;
            GameData gameData = a[by];
            gameData.a();
            if (gameData.a()) {
                audioManager.g[21] = GameCanvas.a(audioManager.g[21], 255 << i * 8, -1);
                gameData.a((GraphicsEngine)null);
                continue;
            }
            ++n;
        }
        if (n == 0) {
            audioManager.g[16] = audioManager.g[16] & 0xFFFFFFFE;
        }
    }

    public static void d() {
        b = new int[4];
    }

    private static void j() {
        AudioManager.b[0] = Integer.MAX_VALUE;
        AudioManager.b[1] = Integer.MAX_VALUE;
        AudioManager.b[2] = -2147483647;
        AudioManager.b[3] = -2147483647;
    }

    private static boolean f() {
        return b[0] > b[2] || b[1] > b[3];
    }

    private static void a(int n, int n2, int n3, int n4) {
        if (n < b[0]) {
            AudioManager.b[0] = n;
        }
        if (n2 < b[1]) {
            AudioManager.b[1] = n2;
        }
        if (n3 > b[2]) {
            AudioManager.b[2] = n3;
        }
        if (n4 > b[3]) {
            AudioManager.b[3] = n4;
        }
    }

    private static void B(AudioManager audioManager) {
        if ((audioManager.g[12] < 100 || (audioManager.g[16] & 0x20) == 0) && audioManager.g[22] < 0) {
            int n;
            int[] nArray;
            int n2;
            if (audioManager.g[23] <= 0) {
                if (!AudioManager.p(audioManager)) {
                    audioManager.g[22] = GameCanvas.a(audioManager, audioManager.a.a() << 14, audioManager.a.b() << 14, 1, 1, 70, -1);
                    n2 = 16;
                    nArray = audioManager.g;
                    n = audioManager.g[16] | 0x20;
                }
            } else {
                n2 = 23;
                nArray = audioManager.g;
                n = nArray[n2] = audioManager.g[23] - (int)GameCanvas.d;
            }
        }
        if ((audioManager.g[12] >= 100 || (audioManager.g[16] & 0x20) == 0) && audioManager.g[22] >= 0) {
            GameCanvas.a(audioManager.g[22], true, 0, true, -128);
            GameCanvas.k(audioManager.g[22]);
        }
        if (audioManager.g[22] >= 0 && audioManager.g[12] < 100) {
            GameCanvas.c(audioManager.g[22], audioManager.a.a() << 14, audioManager.a.b() << 14);
        }
    }

    public static boolean d(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        return (n == 5 || n == 7) && (audioManager.g[16] & 0x20) != 0;
    }

    private static boolean p(AudioManager audioManager) {
        boolean bl = false;
        int[] nArray = audioManager.a.a();
        int[] nArray2 = null;
        bl = false | GameCanvas.d(audioManager.a.a() / 16, audioManager.a.b() / 16);
        if (GameCanvas.ao && !bl) {
            int[] nArray3 = GameCanvas.d.a();
            nArray2 = nArray3;
            nArray3[0] = nArray3[0] + (GameCanvas.T >> 14);
            nArray2[1] = nArray2[1] + (GameCanvas.U >> 14);
            nArray2[2] = nArray2[2] + (GameCanvas.T >> 14);
            nArray2[3] = nArray2[3] + (GameCanvas.U >> 14);
            bl = GameCanvas.a(nArray[0], nArray[1], nArray[2] - nArray[0], nArray[3] - nArray[1], nArray2[0], nArray2[1], nArray2[2] - nArray2[0], nArray2[3] - nArray2[1], 55);
        }
        for (int i = 0; i < GameCanvas.cg && !bl; ++i) {
            AudioManager audioManager2 = GameCanvas.e[i];
            if (audioManager2.L != 15) continue;
            nArray2 = audioManager2.a.a();
            bl = GameCanvas.a(nArray[0], nArray[1], nArray[2] - nArray[0], nArray[3] - nArray[1], nArray2[0], nArray2[1], nArray2[2] - nArray2[0], nArray2[3] - nArray2[1], 55);
        }
        if (GameCanvas.d() && !bl && !GameCanvas.a(GameCanvas.c.a.a, GameCanvas.c.a.b, audioManager.a.a, audioManager.a.b, null, false, 129)) {
            GameCanvas.a(c);
            bl = GameCanvas.a(nArray[0], nArray[1], nArray[2] - nArray[0], nArray[3] - nArray[1], c[0], c[1], c[2] - c[0], c[3] - c[1], 55);
        }
        return bl;
    }

    private static void C(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        if ((n == 5 || n == 7) && audioManager.g[12] != -1) {
            if (audioManager.g[12] < 100 && AudioManager.p(audioManager) && (audioManager.g[16] & 0x20) != 0) {
                audioManager.g[16] = audioManager.g[16] & 0xFFFFFFDF;
            }
            AudioManager.B(audioManager);
        }
    }

    private static void D(AudioManager audioManager) {
        if ((audioManager.g[12] < 100 || (audioManager.g[16] & 0x20) == 0) && audioManager.g[22] < 0 && audioManager.g[23] > 0) {
            audioManager.g[23] = 0;
        }
    }

    public static void f(AudioManager audioManager) {
        int n;
        int n2;
        int[] nArray;
        audioManager.g[22] = -1;
        if ((audioManager.g[16] & 0x20) == 0) {
            nArray = audioManager.g;
            n2 = 23;
            n = 10000;
        } else {
            nArray = audioManager.g;
            n2 = 23;
            n = 0;
        }
        nArray[n2] = n;
        audioManager.g[16] = audioManager.g[16] & 0xFFFFFFDF;
    }

    private static void d(Graphics graphics, AudioManager audioManager, boolean bl) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        if ((n == 5 || n == 7) && audioManager.g[12] != -1 && audioManager.g[22] >= 0) {
            GraphicsEngine graphicsEngine = audioManager.a.a;
            int n2 = graphicsEngine.b(audioManager.a.d, 0);
            int n3 = graphicsEngine.b(n2);
            int n4 = GameCanvas.a(graphicsEngine, audioManager.a.d, 0);
            int n5 = graphicsEngine.h[audioManager.a.d] + 0;
            int n6 = 0;
            for (int i = 0; i < n3; ++i) {
                int n7 = graphicsEngine.e[n2] + i;
                int n8 = graphicsEngine.d[n7] & 0xFF;
                int n9 = graphicsEngine.c[n7] & 0xFF;
                if (graphicsEngine.k[n9 |= (n8 & 0xC0) << 2] != 1) continue;
                graphicsEngine.b(c, n2, i, 0, 0, n4);
                c[0] = c[0] + graphicsEngine.i[n5];
                c[2] = c[2] + graphicsEngine.i[n5];
                c[1] = c[1] + graphicsEngine.j[n5];
                c[3] = c[3] + graphicsEngine.j[n5];
                if (n6 % 2 == 0 && bl || n6 % 2 == 1 && !bl) {
                    GameCanvas.a(graphics, audioManager.g[22], c[0] << 14, c[1] << 14, GameCanvas.r);
                    AudioManager.a(GameCanvas.r[0], GameCanvas.r[1], GameCanvas.r[2], GameCanvas.r[3]);
                }
                ++n6;
            }
        }
    }

    public static void a(Graphics graphics, AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        if ((n == 5 || n == 7) && audioManager.g[12] != -1 && audioManager.g[12] != 101 && (audioManager.g[16] & 0x20) != 0) {
            int n2;
            int n3;
            if (AudioManager.u(audioManager)) {
                int[] nArray = AudioManager.b(audioManager, true, false);
                n3 = nArray[0] >> 14;
                n2 = nArray[1];
            } else {
                n3 = audioManager.a.a - GameCanvas.T >> 14;
                n2 = audioManager.a.b - GameCanvas.U;
            }
            int n4 = n2 >> 14;
            GraphicsEngine graphicsEngine = audioManager.a.a;
            audioManager.a.a.e = audioManager.g[1];
            graphicsEngine.a(audioManager.g[2]);
            int n5 = graphicsEngine.b(audioManager.a.d, audioManager.a.e);
            int n6 = graphicsEngine.b(n5);
            int n7 = GameCanvas.a(graphicsEngine, audioManager.a.d, audioManager.a.e);
            int n8 = graphicsEngine.h[audioManager.a.d] + audioManager.a.e;
            boolean bl = false;
            for (int i = 0; i < n6; ++i) {
                int n9 = graphicsEngine.e[n5] + i;
                int n10 = graphicsEngine.d[n9] & 0xFF;
                int n11 = graphicsEngine.c[n9] & 0xFF;
                int n12 = graphicsEngine.e[n11 |= (n10 & 0xC0) << 2];
                if (bl) {
                    graphicsEngine.b(c, n5, i, 0, 0, n7);
                    c[0] = c[0] + (n3 + graphicsEngine.i[n8]);
                    c[2] = c[2] + (n3 + graphicsEngine.i[n8]);
                    c[1] = c[1] + (n4 + graphicsEngine.j[n8]);
                    c[3] = c[3] + (n4 + graphicsEngine.j[n8]);
                    GameCanvas.a(graphics, audioManager.a.a(), audioManager.a.b(), c[0] + (c[2] - c[0] >> 1), c[1] + (c[3] - c[1] >> 1), graphicsEngine, 655, 0);
                    return;
                }
                if (graphicsEngine.k[n11] != 1 || n12 != 1) continue;
                bl = true;
            }
        }
    }

    public static void e() {
        a = new byte[10][3];
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a[i].length; ++j) {
                AudioManager.a[i][j] = -1;
            }
        }
    }

    private static int b() {
        int n;
        for (n = 0; n < a.length; ++n) {
            if (a[n][0] != -1) continue;
            return n;
        }
        byte[][] byArray = new byte[a.length + 1][3];
        System.arraycopy(a, 0, byArray, 0, a.length);
        int n2 = a.length;
        a = byArray;
        for (n = 0; n < a[n2].length; ++n) {
            AudioManager.a[n2][n] = -1;
        }
        return n2;
    }

    private static int a(AudioManager audioManager, int n, int n2) {
        int n3 = audioManager.g[30 + n];
        return a[n3][n2];
    }

    private static void c(AudioManager audioManager, int n, int n2, int n3) {
        int n4 = audioManager.g[30 + n];
        AudioManager.a[n4][n2] = (byte)n3;
    }

    private static void b(int n) {
        for (int i = 0; i < a[n].length; ++i) {
            AudioManager.a[n][i] = -1;
        }
    }

    private static int a(int[] nArray) {
        int n = 0;
        int n2 = GameCanvas.h(nArray[0], 33);
        for (int i = 0; i < n2; ++i) {
            int n3 = GameCanvas.d(nArray[0], i, 11);
            if (n3 == 0) {
                n |= GameCanvas.d(nArray[0], i, 7);
                continue;
            }
            for (int j = 0; j < n3; ++j) {
                n |= GameCanvas.d(nArray[0], i, j, 1);
            }
        }
        return n;
    }

    private static void g(int[] nArray) {
        int n = GameCanvas.h(nArray[0], 33);
        for (int i = 0; i < n; ++i) {
            int n2 = GameCanvas.d(nArray[0], i, 11);
            if (n2 == 0) {
                nArray[30 + i] = GameCanvas.d(nArray[0], i, 0);
                continue;
            }
            nArray[30 + i] = AudioManager.b();
            for (int j = 0; j < n2; ++j) {
                AudioManager.a[nArray[30 + i]][j] = (byte)GameCanvas.d(nArray[0], i, j, 0);
            }
        }
        nArray[18] = AudioManager.a(nArray);
    }

    public static int[] c(int[] nArray) {
        int n;
        int n2;
        int[] nArray2;
        int n3;
        if (a == null) {
            a = new int[GameCanvas.d()];
        }
        int[] nArray3 = null;
        if (nArray != null) {
            nArray3 = new int[nArray.length + 16 + GameCanvas.d()];
            System.arraycopy(nArray, 0, nArray3, 0, nArray.length);
            nArray3[0] = GameCanvas.a(GameCanvas.m, nArray3[0]);
        } else {
            nArray3 = new int[30 + GameCanvas.d()];
        }
        for (n3 = 0; n3 < 16; ++n3) {
            nArray3[12 + n3] = 0;
        }
        for (n3 = 0; n3 < 4; ++n3) {
            nArray3[21] = GameCanvas.a(nArray3[21], 255 << n3 * 8, -1);
        }
        int n4 = GameCanvas.h(nArray3[0], 10);
        if (n4 == 5 || n4 == 7) {
            nArray3[23] = -1;
        }
        nArray3[22] = -1;
        if (nArray == null) {
            nArray2 = nArray3;
            n2 = 12;
            n = -1;
        } else {
            nArray2 = nArray3;
            n2 = 12;
            n = 0;
        }
        nArray2[n2] = n;
        AudioManager.g(nArray3);
        return nArray3;
    }

    public static void g(AudioManager audioManager) {
        int n;
        int n2;
        int[] nArray;
        int n3;
        int n4;
        int n5;
        GameCanvas.a(audioManager, 1, false);
        int n6 = GameCanvas.h(audioManager.g[0], 10);
        if ((audioManager.g[16] & 0x800) == 0) {
            n5 = GameCanvas.h(audioManager.g[0], 33);
            for (n4 = 0; n4 < n5; ++n4) {
                n3 = GameCanvas.d(audioManager.g[0], n4, 11);
                if (n3 == 0) continue;
                AudioManager.b(audioManager.g[30 + n4]);
            }
            if (audioManager.g[9] >= 0) {
                GameCanvas.b(audioManager.g[9]);
            }
            if (n6 == 0 || n6 == 3 || n6 == 5 || n6 == 7) {
                nArray = audioManager.g;
                n2 = 2;
                n = 2;
            }
        } else {
            n2 = 16;
            nArray = audioManager.g;
            n = nArray[n2] = audioManager.g[16] & 0xFFFFFFBF;
        }
        if ((audioManager.g[16] & 0x400) == 0 && GameCanvas.a == 6 && GameCanvas.b != 4) {
            n5 = GameCanvas.h(audioManager.g[0], 26);
            n4 = 0;
            n3 = audioManager.a.a();
            int n7 = audioManager.a.b();
            if (n6 != 1) {
                int[] nArray2 = GameCanvas.b(audioManager.a);
                n3 = nArray2[0] + (nArray2[2] - nArray2[0] >> 1);
                if (!GameCanvas.b(n3 / 16, (n7 = nArray2[1]) / 16, 5)) {
                    n4 = 1;
                }
                if (n4 == 0 && GameCanvas.a(GameCanvas.s, nArray2[0] / 16, nArray2[1] / 16, nArray2[2] / 16, nArray2[3] / 16, 5)) {
                    n4 = 1;
                    n3 = GameCanvas.s[0] * 16 + 8;
                    n7 = GameCanvas.s[1] * 16 + 8;
                }
            } else {
                n4 = 1;
            }
            if (n4 != 0) {
                GameCanvas.b(n5, n3 << 14, n7 << 14);
                audioManager.g[16] = audioManager.g[16] | 0x400;
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    private static void g(AudioManager var0, int var1_1) {
        GameCanvas.i(null);
        var2_2 = AudioManager.a(var0.g);
        var3_3 = var0.g[0];
        var4_4 = GameCanvas.h(var0.g[0], 33);
        var0.g[0] = var1_1;
        var0.g[18] = var5_5 = AudioManager.a(var0.g);
        for (var8_6 = 0; var8_6 < var4_4; ++var8_6) {
            var9_7 = GameCanvas.d(var3_3, var8_6, 11);
            if (var9_7 == 0 || ((var10_8 = GameCanvas.d(var3_3, var8_6, 7)) & var5_5) != 0) continue;
            AudioManager.b(var0.g[30 + var8_6]);
        }
        System.arraycopy(var0.g, 30, AudioManager.a, 0, GameCanvas.d());
        var6_9 = GameCanvas.h(var0.g[0], 33);
        for (var8_6 = 0; var8_6 < var6_9; ++var8_6) {
            block10: {
                block9: {
                    var9_7 = GameCanvas.d(var0.g[0], var8_6, 11);
                    var10_8 = GameCanvas.d(var0.g[0], var8_6, 7);
                    if ((var2_2 & var10_8) == 0) break block9;
                    var13_12 = GameCanvas.h(var3_3, 33);
                    for (var14_13 = 0; var14_13 < var13_12; ++var14_13) {
                        var15_14 = GameCanvas.d(var3_3, var14_13, 7);
                        if (var15_14 != var10_8) continue;
                        v0 = var0.g;
                        v1 = 30 + var8_6;
                        v2 = AudioManager.a[var14_13];
                        ** GOTO lbl37
                    }
                    break block10;
                }
                if (var9_7 != 0) {
                    var0.g[30 + var8_6] = AudioManager.b();
                    for (var13_12 = 0; var13_12 < var9_7; ++var13_12) {
                        AudioManager.a[var0.g[30 + var8_6]][var13_12] = (byte)GameCanvas.d(var0.g[0], var8_6, var13_12, 0);
                    }
                } else {
                    v0 = var0.g;
                    v1 = 30 + var8_6;
                    v2 = GameCanvas.d(var0.g[0], var8_6, 0);
lbl37:
                    // 2 sources

                    v0[v1] = v2;
                }
            }
            var11_10 = GameCanvas.d(var0.g[0], var8_6, 5);
            var12_11 = GameCanvas.d(var0.g[0], var8_6, 6);
            if (var9_7 > 0) {
                for (var13_12 = 0; var13_12 < var9_7; ++var13_12) {
                    if (AudioManager.c(var0, var8_6, var13_12) != 0 || var11_10 < 0 || var12_11 < 0) continue;
                    var0.g[18] = var0.g[18] & ~GameCanvas.d(var0.g[0], var8_6, var13_12, 1);
                }
                continue;
            }
            if (AudioManager.c(var0, var8_6, -1) != 0 || var11_10 < 0 || var12_11 < 0) continue;
            var0.g[18] = var0.g[18] & ~var10_8;
        }
        var7_15 = GameCanvas.i(var0.g[0], 34);
        if (var7_15 == -1) {
            var0.g[16] = var0.g[16] & -2049;
            var0.g[16] = var0.g[16] & -4097;
            var0.g[24] = 0;
        }
        GameCanvas.e(var0);
        GameCanvas.i(var0);
    }

    public static void h(AudioManager audioManager) {
        if (audioManager.L == 1) {
            int n;
            boolean bl;
            int n2 = audioManager.g[14];
            int n3 = audioManager.g[2];
            boolean bl2 = bl = audioManager.g[21] == 1;
            if (GameCanvas.f[n2][n3] != -1) {
                n2 = GameCanvas.f[n2][n3];
            }
            if (GameCanvas.v[n3] != -1) {
                n3 = GameCanvas.v[n3];
            }
            int n4 = audioManager.g[1];
            int n5 = AudioManager.d(audioManager);
            int n6 = audioManager.g[n5 + 11];
            int n7 = audioManager.g[n5 + 12];
            int n8 = audioManager.g[20];
            audioManager.L = 3;
            audioManager.g[0] = GameCanvas.w[n4];
            audioManager.g[1] = n3;
            audioManager.g[2] = n2;
            audioManager.g[3] = -120;
            audioManager.g[4] = -120;
            audioManager.g[5] = 240;
            audioManager.g[6] = 240;
            audioManager.g[7] = 0;
            audioManager.g[8] = 0;
            audioManager.g[9] = -1;
            audioManager.g[10] = 0;
            audioManager.g[11] = 0;
            audioManager.g[9] = n8;
            for (n = 0; n < 16; ++n) {
                audioManager.g[12 + n] = 0;
            }
            for (n = 0; n < 4; ++n) {
                audioManager.g[21] = GameCanvas.a(audioManager.g[21], 255 << n * 8, -1);
            }
            audioManager.g[13] = n6;
            audioManager.g[14] = n7;
            if (audioManager.g[13] == 0 && audioManager.g[14] == 0) {
                audioManager.g[13] = 0;
                audioManager.g[14] = 16384;
            }
            audioManager.g[12] = 23;
            audioManager.g[18] = AudioManager.a(audioManager.g);
            GameCanvas.e(audioManager);
            AudioManager.g(audioManager.g);
            AudioManager.c(audioManager);
            audioManager.g[15] = 0;
            if (bl) {
                audioManager.g[16] = audioManager.g[16] | 0x8000;
            }
        }
    }

    public static void a(AudioManager audioManager, int n, int n2, int n3) {
        audioManager.g[0] = n;
        AudioManager.g(audioManager.g);
        audioManager.g[2] = n2;
        audioManager.g[1] = n3;
        audioManager.g[19] = 0;
        audioManager.g[16] = 0;
        audioManager.g[3] = -120;
        audioManager.g[4] = -120;
        audioManager.g[5] = 240;
        audioManager.g[6] = 240;
        audioManager.g[9] = -1;
        audioManager.g[12] = 4;
        GameCanvas.e(audioManager);
        AudioManager.k(audioManager);
        AudioManager.c(audioManager);
    }

    private static void a(AudioManager audioManager, int n, int n2, boolean bl, int n3, int n4) {
        int n5 = GameCanvas.h(audioManager.g[0], 10);
        int n6 = GameCanvas.d(audioManager.g[0], n, 5);
        int n7 = GameCanvas.d(audioManager.g[0], n, 6);
        if (n6 != -1 && n7 != -1) {
            int n8;
            int n9;
            int n10 = audioManager.a.a + (n3 << 14);
            int n11 = audioManager.a.b + (n4 << 14);
            int n12 = audioManager.a.a + (20 + (GameCanvas.a() % 100 - 50) << 14);
            int n13 = audioManager.a.b + (20 + (GameCanvas.a() % 100 - 50) << 14);
            if (GameCanvas.a == 6 && GameCanvas.b == 4 && GameCanvas.cw == 0) {
                n12 += 0x500000;
            }
            int n14 = 819200;
            if (GameCanvas.a == 6 && GameCanvas.b == 4 && GameCanvas.cw == 0) {
                n14 += GameCanvas.cC;
            }
            int n15 = GameCanvas.d(n10 - n12) * 1000 / n14;
            int n16 = GameCanvas.d(n11 - n13) * 1000 / 819200;
            int n17 = GameCanvas.d(n10 - n12) / (n15 + 1);
            int n18 = GameCanvas.d(n11 - n13) / (n16 + 1);
            int n19 = audioManager.g[1];
            int n20 = n9 = audioManager.g[2];
            if (n5 == 0 || n5 == 3 || n5 == 5 || n5 == 7) {
                n20 = 2;
            }
            if (bl) {
                n8 = GameCanvas.d(audioManager.g[0], n, 7);
                if (n2 >= 0) {
                    n8 = GameCanvas.d(audioManager.g[0], n, n2, 1);
                }
                audioManager.g[18] = audioManager.g[18] & ~n8;
            }
            if (!GameCanvas.N || n5 == 2) {
                n8 = GameCanvas.d(audioManager.g[0], n, 8);
                GameCanvas.a(audioManager.a.a, n6, n7, n10 >> 14, n11 >> 14, n17, n18, n12, n13, n19, n9, n8, n20);
            }
        }
    }

    private static void e(Graphics graphics, AudioManager audioManager, boolean bl) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        block18: {
            block19: {
                block16: {
                    block17: {
                        int n6;
                        int n7 = GameCanvas.h(audioManager.g[0], 10);
                        audioManager.a.a.e = audioManager.g[1];
                        audioManager.a.a.a(audioManager.g[2]);
                        AudioManager.j();
                        switch (n7) {
                            case 0: 
                            case 3: 
                            case 5: 
                            case 7: {
                                AudioManager.d(audioManager, bl);
                            }
                        }
                        AudioManager.d(graphics, audioManager, true);
                        if (audioManager.g[10] != 5 && audioManager.g[12] < 100 && (n5 = !(n7 == 1 && audioManager.g[10] == 4 || audioManager.g[12] == 9 && (audioManager.g[12] != 9 || n7 != 4 && n7 != 6 || audioManager.g[12] == 103 || audioManager.g[12] == 104 || audioManager.g[12] == 17)) ? 1 : 0) != 0 && (n6 = GameCanvas.h(audioManager.g[0], 22)) >= 0) {
                            AudioManager.a(graphics, n6, audioManager.a.a >> 14, audioManager.a.b >> 14);
                        }
                        switch (n7) {
                            case 0: 
                            case 3: 
                            case 5: 
                            case 7: {
                                break;
                            }
                            case 1: {
                                break;
                            }
                            case 2: {
                                AudioManager.O(audioManager);
                            }
                        }
                        AudioManager.c(graphics, audioManager, true);
                        GameCanvas.e(32);
                        GameCanvas.b(audioManager.g[18], true);
                        if ((audioManager.g[16] & 0x1000) == 0) break block16;
                        if (audioManager.g[12] != 106) break block17;
                        n4 = audioManager.g[26];
                        n3 = AudioManager.g(audioManager);
                        n2 = audioManager.g[25];
                        n = AudioManager.c(audioManager, 0);
                        break block18;
                    }
                    n4 = audioManager.g[25];
                    n3 = AudioManager.g(audioManager);
                    break block19;
                }
                n4 = 0;
                n3 = 0;
            }
            n2 = 0;
            n = 0;
        }
        GameCanvas.b(n4, n3, n2, n);
        if (audioManager.g[12] != 101) {
            n5 = GameCanvas.h(audioManager.g[0], 34);
            GameCanvas.g(n5 != -1 ? GameCanvas.i(audioManager.g[0], 14) : -1);
            if ((audioManager.g[16] & 0x10) != 0) {
                GameCanvas.b(-1, -1, 15, -1, 9);
            } else if ((audioManager.g[16] & 8) != 0) {
                GameCanvas.b(18, 1, 15, 18, -1);
            } else {
                GameCanvas.b(-1, -1, -1, -1, -1);
            }
        } else {
            GameCanvas.b(-1, -1, -1, -1, -1);
            GameCanvas.g(-1);
        }
        audioManager.a.a(graphics);
        if ((GameCanvas.ag & 0x20) != 0) {
            AudioManager.a(GameCanvas.g(3, 11), GameCanvas.g(3, 12), GameCanvas.g(3, 13), GameCanvas.g(3, 14));
        }
        GameCanvas.f(32);
        AudioManager.c(graphics, audioManager, false);
        AudioManager.d(graphics, audioManager, false);
        AudioManager.d(graphics, audioManager);
    }

    private static void d(Graphics graphics, AudioManager audioManager) {
        if (GameCanvas.g == 2) {
            GraphicsEngine graphicsEngine = audioManager.a.a;
            int n = graphicsEngine.b(audioManager.a.d, audioManager.a.e);
            int n2 = graphicsEngine.b(n);
            int n3 = GameCanvas.a(graphicsEngine, audioManager.a.d, audioManager.a.e);
            int n4 = graphicsEngine.h[audioManager.a.d] + audioManager.a.e;
            graphics.setColor(255);
            graphics.drawLine(audioManager.a.a() - 10, audioManager.a.b(), audioManager.a.a() + 10, audioManager.a.b());
            graphics.drawLine(audioManager.a.a(), audioManager.a.b() - 10, audioManager.a.a(), audioManager.a.b() + 10);
            graphics.setColor(0xFF0000);
            for (int i = 0; i < n2; ++i) {
                graphicsEngine.b(c, n, i, 0, 0, n3);
                c[0] = c[0] + (audioManager.a.a() + graphicsEngine.i[n4]);
                c[2] = c[2] + (audioManager.a.a() + graphicsEngine.i[n4]);
                c[1] = c[1] + (audioManager.a.b() + graphicsEngine.j[n4]);
                c[3] = c[3] + (audioManager.a.b() + graphicsEngine.j[n4]);
                graphics.drawRect(c[0], c[1], c[2] - c[0], c[3] - c[1]);
            }
        }
    }

    public static void f() {
        int n;
        if (a != null) {
            for (n = 0; n < a.length; ++n) {
                AudioManager.a[n] = null;
            }
        }
        a = null;
        d = null;
        int n2 = 0;
        int n3 = 0;
        for (n = 0; n < GameCanvas.T.length; ++n) {
            int n4 = GameCanvas.m(n);
            n2 = Math.max(n2, n4);
            int n5 = GameCanvas.h(n, 33);
            int n6 = 0;
            for (int i = 0; i < n5; ++i) {
                int n7 = GameCanvas.d(n, i, 11);
                if (n7 == 0) {
                    ++n6;
                    continue;
                }
                n6 += n7;
            }
            n3 = Math.max(n6, n3);
        }
        a = new int[n2][4];
        d = new int[n3];
    }

    private static int a(AudioManager audioManager, int n, int n2, boolean bl) {
        int n3;
        int n4;
        GraphicsEngine graphicsEngine = audioManager.a.a;
        int n5 = graphicsEngine.b(audioManager.a.d, audioManager.a.e);
        int n6 = graphicsEngine.b(n5);
        int n7 = audioManager.g[18];
        if (bl) {
            n4 = GameCanvas.a(graphicsEngine, audioManager.a.d, audioManager.a.e);
            n3 = GameCanvas.b(graphicsEngine, audioManager.a.d, audioManager.a.e);
            int n8 = GameCanvas.e(graphicsEngine, audioManager.a.d, audioManager.a.e);
            B = 0;
            A = 0;
            for (int i = 0; i < n6; ++i) {
                int n9 = GameCanvas.c(graphicsEngine, n5, i);
                if (n9 == 1) {
                    AudioManager.d[AudioManager.B++] = GameCanvas.d(graphicsEngine, n5, i);
                    continue;
                }
                graphicsEngine.b(a[A], n5, i, 0, 0, n4);
                int[] nArray = a[A];
                nArray[0] = nArray[0] + n3;
                int[] nArray2 = a[A];
                nArray2[2] = nArray2[2] + n3;
                int[] nArray3 = a[A];
                nArray3[1] = nArray3[1] + n8;
                int[] nArray4 = a[A];
                nArray4[3] = nArray4[3] + n8;
                ++A;
            }
        }
        int n10 = A - 1;
        int n11 = B - 1;
        if (A > 0 && B > 0) {
            for (n4 = n6 - 1; n4 >= 0; --n4) {
                n3 = GameCanvas.c(graphicsEngine, n5, n4);
                if (n3 == 1) {
                    --n11;
                    continue;
                }
                if (n11 < 0 || n10 < 0) continue;
                if ((d[n11] & n7) != 0 && GameCanvas.a(a[n10][0], a[n10][1], a[n10][2], a[n10][3], n, n2)) {
                    return d[n11];
                }
                --n10;
            }
        }
        return 0;
    }

    private static int b(AudioManager audioManager, int n) {
        int n2 = GameCanvas.h(audioManager.g[0], 33);
        for (int i = 0; i < n2; ++i) {
            int n3 = GameCanvas.d(audioManager.g[0], i, 7);
            if ((n3 & n) == 0) continue;
            return i;
        }
        return -1;
    }

    private static int b(AudioManager audioManager, int n, int n2) {
        if (n >= 0) {
            int n3 = GameCanvas.d(audioManager.g[0], n, 11);
            for (int i = 0; i < n3; ++i) {
                int n4 = GameCanvas.d(audioManager.g[0], n, i, 1);
                if ((n4 & n2) == 0) continue;
                return i;
            }
        }
        return -1;
    }

    private static void d(AudioManager audioManager, int n, int n2, int n3) {
        int n4 = GameCanvas.d(audioManager.g[0], n, 11);
        if (n4 == 0) {
            int n5 = 30 + n;
            audioManager.g[n5] = audioManager.g[n5] - n3;
            return;
        }
        if (n2 >= 0) {
            AudioManager.c(audioManager, n, n2, AudioManager.a(audioManager, n, n2) - n3);
        }
    }

    private static int b(AudioManager audioManager, boolean bl) {
        boolean bl2;
        audioManager.g[20] = GameCanvas.a(audioManager.g[20], 255, 1);
        audioManager.g[20] = GameCanvas.a(audioManager.g[20], 0xFFFF00, GameCanvas.bt);
        int n = 4;
        boolean bl3 = bl2 = false;
        block0: while (!bl2) {
            int n2 = GameCanvas.h(audioManager.g[0], 33);
            for (int i = 0; i < n2; ++i) {
                int n3 = GameCanvas.d(audioManager.g[0], i, 7);
                int n4 = GameCanvas.d(audioManager.g[0], i, 1);
                int n5 = GameCanvas.d(audioManager.g[0], i, 12);
                if ((n3 & audioManager.g[19]) == 0) {
                    if (AudioManager.c(audioManager, i, -1) == 0) {
                        audioManager.g[19] = audioManager.g[19] | n3;
                        if (n4 != -1) {
                            AudioManager.g(audioManager, n4);
                            continue block0;
                        }
                        audioManager.g[17] = i;
                        n = (audioManager.g[16] & 0x1000) == 0 || (audioManager.g[25] & n3) != 0 || n5 == -1 ? 100 : 106;
                        bl3 = true;
                        continue block0;
                    }
                } else if (n4 == -1) {
                    n = (audioManager.g[16] & 0x1000) == 0 ? 101 : 107;
                    bl3 = true;
                    continue block0;
                }
                if (i != n2 - 1) continue;
                bl2 = true;
            }
        }
        if ((audioManager.g[16] & 0x40) == 0 && n != 101) {
            if (bl) {
                AudioManager.c(audioManager, n);
            }
            if (n != 100 && n != 106) {
                audioManager.g[16] = audioManager.g[16] & 0xFFFFFDFF;
                audioManager.g[16] = audioManager.g[16] & 0xFFFFBFFF;
            }
        } else if (bl) {
            AudioManager.c(audioManager, 101);
        } else {
            n = 101;
        }
        return n;
    }

    public static void a(AudioManager audioManager, AudioManager audioManager2) {
        AudioManager.E(audioManager);
        AudioManager.c(audioManager, 104);
        int n = audioManager2.a.a + (audioManager2.g[2] * 16 / 2 << 14);
        int n2 = audioManager2.a.b + (audioManager2.g[3] * 16 / 2 << 14);
        AudioManager.f(audioManager, n, n2, 0);
        int[] nArray = audioManager.a.a();
        AudioManager.a(audioManager, 0, -(nArray[3] - nArray[1]) / 2, 9, true, 0);
        GameCanvas.b(14);
    }

    private static void E(AudioManager audioManager) {
        boolean bl = AudioManager.d(audioManager);
        if (audioManager.g[10] != 3 && audioManager.g[10] != 4 && audioManager.g[12] < 100 && audioManager.g[12] != 14 && !bl) {
            int n = GameCanvas.h(audioManager.g[0], 33);
            for (int i = 0; i < n; ++i) {
                int n2;
                int n3 = GameCanvas.d(audioManager.g[0], i, 11);
                if (n3 > 0) {
                    for (n2 = 0; n2 < n3; ++n2) {
                        int n4 = GameCanvas.d(audioManager.g[0], i, n2, 0);
                        if (n4 <= 0) continue;
                        if (!d) {
                            n4 = 65;
                        }
                        AudioManager.a(audioManager, i, n2, n4, false, 0, 0, -1, true);
                    }
                    continue;
                }
                n2 = GameCanvas.d(audioManager.g[0], i, 0);
                if (n2 <= 0) continue;
                if (!d) {
                    n2 = 65;
                }
                AudioManager.a(audioManager, i, -1, n2, false, 0, 0, -1, true);
            }
            AudioManager.i(audioManager);
            AudioManager.G(audioManager);
            AudioManager.f(audioManager, GameCanvas.c.a.a, GameCanvas.c.a.b, 0);
            AudioManager.c(audioManager, 103);
            return;
        }
        if (bl) {
            audioManager.g[16] = audioManager.g[16] & 0xFFFFFFDF;
        }
    }

    private static void a(AudioManager audioManager, int n, int n2, int n3, int n4) {
        int n5 = GameCanvas.h(audioManager.g[0], 10);
        if (!AudioManager.d(audioManager) && !AudioManager.v(audioManager)) {
            AudioManager.i(audioManager);
            int n6 = GameCanvas.h(audioManager.g[0], 33);
            int n7 = 0;
            int n8 = 0;
            if ((n5 == 0 || n5 == 3 || n5 == 3) && n2 >= (n -= n >> 2)) {
                audioManager.g[16] = audioManager.g[16] | 8;
            }
            while (n2 > 0 && n8 < n6) {
                int n9 = GameCanvas.a() % n6;
                if ((n7 & 1 << n9) != 0) continue;
                int n10 = AudioManager.c(audioManager, n9, -1);
                if (n10 > 0) {
                    int n11 = GameCanvas.d(audioManager.g[0], n9, 11);
                    if (n11 > 0) {
                        int n12;
                        int n13;
                        while ((n13 = AudioManager.c(audioManager, n9, n12 = GameCanvas.a() % n11)) == 0) {
                        }
                        if (n13 > n2) {
                            n13 = n2;
                        }
                        AudioManager.a(audioManager, n9, n12, n13, false, 0, 0, -1, true);
                        n2 -= n13;
                        continue;
                    }
                    if (n10 > n2) {
                        n10 = n2;
                    }
                    AudioManager.a(audioManager, n9, -1, n10, false, 0, 0, -1, true);
                    n2 -= n10;
                    n7 |= 1 << n9;
                    ++n8;
                    continue;
                }
                n7 |= 1 << n9;
                ++n8;
            }
            AudioManager.c(audioManager, 103);
            AudioManager.f(audioManager, n3, n4, 0);
        }
    }

    private static void F(AudioManager audioManager) {
        block5: {
            int n;
            AudioManager audioManager2;
            block6: {
                block4: {
                    AudioManager.b(audioManager, true);
                    if ((audioManager.g[16] & 0x1000) == 0) break block4;
                    if (audioManager.g[12] == 101 || audioManager.g[12] == 100 || audioManager.g[12] == 107) break block5;
                    if (audioManager.g[12] == 106) {
                        AudioManager.M(audioManager);
                        audioManager.g[25] = audioManager.g[25] | audioManager.g[26];
                        audioManager.g[26] = 0;
                        audioManager2 = audioManager;
                        n = 107;
                    } else {
                        audioManager2 = audioManager;
                        n = 4;
                    }
                    break block6;
                }
                if (audioManager.g[12] == 101 || audioManager.g[12] == 100) {
                    audioManager2 = audioManager;
                    n = 101;
                } else {
                    audioManager2 = audioManager;
                    n = 11;
                }
            }
            AudioManager.c(audioManager2, n);
        }
    }

    public static void i(AudioManager audioManager) {
        if (audioManager.g[12] == 8) {
            AudioManager audioManager2 = AudioManager.a(audioManager);
            if (audioManager2 != null && audioManager2 != GameCanvas.c && AudioManager.m(audioManager2) == 100) {
                AudioManager.d(audioManager2, 0);
                return;
            }
            if (audioManager2 == GameCanvas.c) {
                a = null;
                AudioManager.a(0, GameCanvas.c);
            }
        }
    }

    private static void G(AudioManager audioManager) {
        AudioManager.c(audioManager, false);
    }

    private static void c(AudioManager audioManager, boolean bl) {
        audioManager.g[16] = audioManager.g[16] | 0x200;
        if (bl) {
            audioManager.g[16] = audioManager.g[16] | 0x4000;
        }
    }

    public static void j(AudioManager audioManager) {
        if (!AudioManager.d(audioManager)) {
            AudioManager.G(audioManager);
            AudioManager.c(audioManager, 15);
        }
    }

    private static void H(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        int n2 = GameCanvas.L[1];
        int n3 = 0;
        switch (n) {
            case 0: 
            case 3: 
            case 5: 
            case 7: {
                n3 = 8;
            }
        }
        int n4 = AudioManager.b(audioManager, n3);
        if (n4 >= 0) {
            AudioManager.a(audioManager, n4, -1, GameCanvas.l(n2), false, 0, 0, -1, false);
        }
    }

    /*
     * Unable to fully structure code
     */
    public static boolean a(AudioManager var0, int var1_1, int var2_2) {
        block8: {
            var3_3 = GameCanvas.h(var0.g[0], 10);
            var4_4 = GameCanvas.l(var1_1, 5);
            var5_5 = false;
            if (var0.g[12] >= 100 || AudioManager.d(var0)) break block8;
            switch (var3_3) {
                case 1: 
                case 2: 
                case 3: 
                case 7: {
                    v0 = false;
                    break;
                }
                default: {
                    var6_6 = -1;
                    switch (GameCanvas.a() % 3) {
                        case 0: {
                            v1 = var0;
                            v2 = 8;
                            ** GOTO lbl24
                        }
                        case 1: {
                            v1 = var0;
                            v3 = 4;
                            ** GOTO lbl23
                        }
                        case 2: {
                            v1 = var0;
                            v3 = 2;
lbl23:
                            // 2 sources

                            v2 = v3 & ~var0.g[25];
lbl24:
                            // 2 sources

                            var6_6 = AudioManager.b(v1, v2);
                        }
                    }
                    if (var6_6 == -1) break block8;
                    AudioManager.i(var0);
                    AudioManager.a(var0, var6_6, -1, var4_4, true, 0, var2_2, var1_1, true);
                    AudioManager.G(var0);
                    AudioManager.I(var0);
                    v0 = true;
                }
            }
            var5_5 = v0;
        }
        return var5_5;
    }

    public static boolean a(AudioManager audioManager, int n, boolean bl, boolean bl2) {
        int n2 = 0;
        boolean bl3 = false;
        boolean bl4 = false;
        if (audioManager.g[10] != 3 && audioManager.g[10] != 4 && audioManager.g[12] < 100) {
            audioManager.g[16] = audioManager.g[16] & 0xFFFFFF7F;
            int n3 = GameCanvas.l(n, 4);
            int[] nArray = GameCanvas.a(audioManager, GameCanvas.c);
            int n4 = nArray[0];
            int n5 = nArray[1];
            int n6 = GameCanvas.h(audioManager.g[0], 10);
            if (n6 == 1 && (audioManager.g[16] & 0x100) == 0) {
                audioManager.g[29] = audioManager.g[29] + 1;
            }
            if (!AudioManager.d(audioManager)) {
                AudioManager.i(audioManager);
            }
            boolean bl5 = false;
            boolean bl6 = false;
            for (int i = 0; i < n3; ++i) {
                int[] nArray2 = GameCanvas.a(n4, n5, GameCanvas.b(audioManager, GameCanvas.c));
                int n7 = AudioManager.a(audioManager, nArray2[0], nArray2[1], i == 0);
                int n8 = AudioManager.b(audioManager, n7);
                int n9 = AudioManager.b(audioManager, n8, n7);
                if (AudioManager.d(audioManager)) {
                    n8 = -1;
                    n9 = -1;
                }
                if (n8 >= 0 && (n7 & audioManager.g[18]) != 0) {
                    ++n2;
                    AudioManager.a(audioManager, n8, n9, GameCanvas.l(n), true, nArray2[0], nArray2[1], n, true);
                    switch (n6) {
                        case 0: 
                        case 3: 
                        case 5: 
                        case 7: {
                            if ((n7 & 1) == 0 || AudioManager.c(audioManager, n8, n9) != 0) break;
                            bl3 = true;
                        }
                    }
                    if (bl3 && !bl4) {
                        GameCanvas.b(14);
                        bl4 = true;
                    }
                    if (AudioManager.c(audioManager, n8, n9) > 0) continue;
                    int n10 = GameCanvas.d(audioManager.g[0], n8, 1);
                    if (n10 == -1) {
                        bl6 = true;
                    }
                    bl5 = true;
                    continue;
                }
                if (n8 >= 0 || i != n3 - 1 || n2 != 0) continue;
                GameCanvas.a(GameCanvas.a[4], 7, audioManager.a.a() + nArray2[0], audioManager.a.b() + nArray2[1]);
            }
            switch (n6) {
                case 0: 
                case 3: 
                case 5: 
                case 7: {
                    if (!bl6 || bl3) break;
                    GameCanvas.b(24);
                }
            }
            if (n2 != 0 && (n6 != 4 && n6 != 6 || bl5)) {
                AudioManager.I(audioManager);
                AudioManager.f(audioManager, GameCanvas.c.a.a, GameCanvas.c.a.b, 0);
                AudioManager.c(audioManager);
            }
            if (bl) {
                AudioManager.c(audioManager, bl3 && bl2);
            }
        }
        return n2 != 0;
    }

    private static void a(AudioManager audioManager, int n, int n2, int n3, boolean bl, int n4, int n5, int n6, boolean bl2) {
        block22: {
            boolean bl3;
            block18: {
                int n7;
                block19: {
                    int n8;
                    int n9;
                    int n10;
                    AudioManager audioManager2;
                    block21: {
                        block20: {
                            boolean bl4 = AudioManager.c(audioManager, n, -1) == -1;
                            bl3 = (audioManager.g[16] & 0x1000) != 0;
                            audioManager.g[17] = n;
                            if (!bl4) break block18;
                            if (!bl) break block19;
                            if (!bl3) break block20;
                            n7 = GameCanvas.h(audioManager.g[0], 33);
                            boolean bl5 = false;
                            for (int i = 0; i < n7; ++i) {
                                int n11 = GameCanvas.d(audioManager.g[0], i, 7);
                                if (AudioManager.c(audioManager, i, -1) == -1 || (n11 & audioManager.g[25]) == 0) continue;
                                AudioManager.a(audioManager, i, -1, n3, bl, n4, n5, n6, bl2);
                                bl5 = true;
                                break;
                            }
                            if (bl5) break block19;
                            audioManager2 = audioManager;
                            n10 = n4;
                            n9 = n5;
                            n8 = 22;
                            break block21;
                        }
                        audioManager2 = audioManager;
                        n10 = n4;
                        n9 = n5;
                        n8 = 4;
                    }
                    AudioManager.b(audioManager2, n10, n9, n8);
                }
                if (GameCanvas.bv < 5) {
                    ++GameCanvas.bv;
                    n7 = GameCanvas.d(audioManager.g[0], n, 8);
                    if (n7 >= 0) {
                        AudioManager.a(audioManager, n, n2, false, n4, n5);
                        return;
                    }
                }
                break block22;
            }
            int n12 = AudioManager.c(audioManager, n, n2);
            if (n12 > 0) {
                int n13;
                if (bl) {
                    n13 = 0;
                    if (n6 != -1 && (n13 = GameCanvas.l(n6, 24)) == -1) {
                        n13 = 0;
                    }
                    AudioManager.b(audioManager, n4, n5, n13);
                }
                AudioManager.d(audioManager, n, n2, n3);
                int n14 = AudioManager.c(audioManager, n, n2);
                if (n14 <= 0) {
                    n14 = 0;
                    AudioManager.e(audioManager, n, n2, 0);
                    n13 = GameCanvas.d(audioManager.g[0], n, 12);
                    int n15 = GameCanvas.d(audioManager.g[0], n, 7);
                    int n16 = GameCanvas.h(audioManager.g[0], 10);
                    if (n13 != -1 && (n16 != 0 && n16 != 3 && n16 != 7 || (n15 & 9) == 0)) {
                        int n17 = GameCanvas.d(audioManager.g[0], n, 1);
                        boolean bl6 = (audioManager.g[16] & 0x40) != 0;
                        if (!(bl6 || n17 == -1 && bl3 || !bl2)) {
                            int n18;
                            int n19;
                            int[] nArray;
                            int n20 = 0;
                            if (n17 != -1) {
                                n20 = GameCanvas.h(n17, 34);
                            }
                            if (n20 != -1 && (audioManager.g[16] & 0x2000) == 0) {
                                if ((audioManager.g[16] & 0x800) == 0) {
                                    audioManager.g[16] = audioManager.g[16] | 0x800;
                                    nArray = audioManager.g;
                                    n19 = 24;
                                    n18 = GameCanvas.a() % 5000;
                                }
                            } else {
                                audioManager.g[16] = audioManager.g[16] & 0xFFFFF7FF;
                                audioManager.g[16] = audioManager.g[16] & 0xFFFFEFFF;
                                audioManager.g[16] = audioManager.g[16] | 0x2000;
                                nArray = audioManager.g;
                                n19 = 24;
                                n18 = nArray[n19] = 0;
                            }
                        }
                    }
                    if (GameCanvas.N) {
                        switch (n16) {
                            case 0: 
                            case 3: 
                            case 5: 
                            case 7: {
                                if ((n15 & 1) == 0) break;
                                bl2 = false;
                            }
                        }
                    }
                    if (bl2) {
                        AudioManager.a(audioManager, n, n2, true, n4, n5);
                    }
                }
                GameCanvas.c(n12, n14, 100, 10, -16711936);
            }
        }
    }

    private static boolean q(AudioManager audioManager) {
        int n;
        int n2 = GameCanvas.b(audioManager.a.a() + audioManager.g[3]);
        AudioManager audioManager2 = AudioManager.a(audioManager, n2, n = GameCanvas.b(audioManager.a.b() + audioManager.g[4]), GameCanvas.b(audioManager.g[5]), GameCanvas.b(audioManager.g[6]));
        if (audioManager2 != null) {
            AudioManager.d(audioManager, audioManager2);
            return true;
        }
        return false;
    }

    private static void d(AudioManager audioManager, AudioManager audioManager2) {
        audioManager.g[20] = 0;
        if (audioManager2 != null) {
            int n = GameCanvas.b(audioManager2.a.a());
            int n2 = GameCanvas.b(audioManager2.a.b());
            int n3 = GameCanvas.d(n, n2);
            int n4 = GameCanvas.e(n, n2);
            audioManager.g[20] = GameCanvas.a(audioManager.g[20], 255, n3);
            audioManager.g[20] = GameCanvas.a(audioManager.g[20], 0xFFFF00, n4);
        }
    }

    private static boolean b(int n, AudioManager audioManager) {
        return audioManager == GameCanvas.c && a < 666 && !d || n != 2 && (audioManager.L == 0 && audioManager != GameCanvas.c || audioManager.L == 1 || audioManager.L == 2) && AudioManager.e(audioManager) != 0 && AudioManager.m(audioManager) < 555;
    }

    private static boolean b(AudioManager audioManager, AudioManager audioManager2) {
        return AudioManager.b(GameCanvas.h(audioManager.g[0], 10), audioManager2);
    }

    private static AudioManager a(AudioManager audioManager, int n, int n2, int n3, int n4) {
        AudioManager audioManager2 = null;
        int n5 = Integer.MAX_VALUE;
        int n6 = GameCanvas.h(audioManager.g[0], 10);
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n4; ++j) {
                int n7;
                AudioManager audioManager3 = GameCanvas.a(n + i, n2 + j);
                if (audioManager3 == null || !AudioManager.b(n6, audioManager3) || n5 <= (n7 = GameCanvas.a(audioManager, audioManager3))) continue;
                n5 = n7;
                audioManager2 = audioManager3;
            }
        }
        return audioManager2;
    }

    private static AudioManager c(AudioManager audioManager) {
        AudioManager audioManager2 = AudioManager.a(audioManager);
        audioManager.g[15] = audioManager.g[15] + (int)GameCanvas.d;
        if (audioManager.g[15] >= 500) {
            audioManager.g[15] = 0;
            AudioManager.q(audioManager);
            audioManager2 = AudioManager.a(audioManager);
            if (audioManager2 == null || audioManager2 != GameCanvas.c && AudioManager.m(audioManager2) >= 555) {
                AudioManager.c(audioManager, 0);
            }
        }
        return audioManager2;
    }

    public static void k(AudioManager audioManager) {
        int n;
        int n2;
        int n3;
        int n4 = 0;
        int n5 = 0;
        if (audioManager.g[13] == 0 && audioManager.g[14] == 0) {
            n3 = 1;
            n2 = 1;
            if (GameCanvas.a() % 2 == 1) {
                n3 = -1;
            }
            if (GameCanvas.a() % 2 == 1) {
                n2 = -1;
            }
            n4 = audioManager.a.a + (GameCanvas.a() % 100 * n3 << 14);
            n = audioManager.a.b + (GameCanvas.a() % 100 * n2 << 14);
        } else {
            n3 = audioManager.g[13] * 10000;
            n2 = audioManager.g[14] * 10000;
            n4 = audioManager.a.a + n3;
            n5 = audioManager.a.b + n2;
            int n6 = GameCanvas.b(audioManager.a.a, audioManager.a.b, n4, n5) >> 14;
            int n7 = 1;
            if (GameCanvas.a() % 2 == 1) {
                n7 = -1;
            }
            n4 = GameCanvas.b(audioManager.a.a(), 100, -(n6 += GameCanvas.a() % 45 * n7)) << 14;
            n = GameCanvas.c(audioManager.a.b(), 100, -n6) << 14;
        }
        n5 = n;
        AudioManager.f(audioManager, n4, n5, 0);
    }

    private static void I(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        if (audioManager.g[13] == 0 && audioManager.g[14] == 0) {
            AudioManager.f(audioManager, GameCanvas.c.a.a, GameCanvas.c.a.b, 0);
        }
        if (n == 0) {
            int n2 = GameCanvas.h(audioManager.g[0], 7);
            int n3 = GameCanvas.h(audioManager.g[0], 8);
            if (audioManager.a.d >= n2 && audioManager.a.d < n2 + 4 || audioManager.a.d >= n3 && audioManager.a.d < n3 + 4) {
                int n4 = AudioManager.b(audioManager, false);
                if (n4 >= 100) {
                    AudioManager.c(audioManager, 100);
                    AudioManager.c(audioManager, 101);
                }
                return;
            }
        }
        AudioManager.c(audioManager, 14);
    }

    private static void J(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        if ((audioManager.g[16] & 4) == 0) {
            switch (n) {
                case 0: 
                case 3: 
                case 5: 
                case 7: {
                    GameCanvas.b(11);
                }
            }
            audioManager.g[16] = audioManager.g[16] | 4;
        }
    }

    private static void K(AudioManager audioManager) {
        if (audioManager.g[13] == 0 && audioManager.g[14] == 0) {
            int n;
            int n2;
            int[] nArray;
            int n3;
            int[] nArray2 = GameCanvas.b(audioManager.a);
            int n4 = audioManager.a.a();
            if (n4 > (n3 = nArray2[0] + (nArray2[2] - nArray2[0] >> 1))) {
                nArray = audioManager.g;
                n2 = 13;
                n = 16384;
            } else {
                nArray = audioManager.g;
                n2 = 13;
                n = -16384;
            }
            nArray[n2] = n;
            audioManager.g[14] = 0;
        }
    }

    /*
     * Unable to fully structure code
     */
    public static void c(AudioManager var0, int var1_1) {
        var2_2 = GameCanvas.h(var0.g[0], 10);
        var3_3 = AudioManager.a(var0);
        switch (var1_1) {
            case -1: {
                GameCanvas.c(var0);
                GameCanvas.a(var0, 1, false);
                break;
            }
            case 0: {
                var0.g[16] = var0.g[16] & -17;
                v0 = 16;
                v1 = var0.g;
                v2 = var0.g[16] & -5;
                ** GOTO lbl140
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 4: {
                var0.g[16] = var0.g[16] & -17;
                var0.g[27] = AudioManager.g;
                if (var0.g[10] != 1 && var0.g[10] != 5) ** GOTO lbl29
                if (var0.g[10] == 5) {
                    AudioManager.K(var0);
                    var1_1 = 11;
                }
                v1 = var0.g;
                v0 = 10;
                v2 = 0;
                ** GOTO lbl140
lbl29:
                // 1 sources

                if (!AudioManager.t(var0)) break;
                if (var3_3 != null) {
                    AudioManager.f(var0, var3_3.a.a, var3_3.a.b, 0);
                }
                var1_1 = 24;
                AudioManager.J(var0);
                break;
            }
            case 5: {
                var0.g[27] = 0;
                v1 = var0.g;
                v0 = 28;
                v2 = 0;
                ** GOTO lbl140
            }
            case 14: {
                var4_4 = GameCanvas.h(var0.g[0], 15) == 1;
                if (var4_4 && var0.g[12] != 14) {
                    GameCanvas.c(var0);
                    var5_5 = GameCanvas.l(GameCanvas.L[1], 25);
                    AudioManager.d(var0, -var5_5);
                }
                AudioManager.a(var0.a.a(), var0.a.b(), 6);
                break;
            }
            case 17: {
                AudioManager.f(var0, GameCanvas.c.a.a, GameCanvas.c.a.b, 0);
                break;
            }
            case 15: {
                AudioManager.i(var0);
                var0.g[15] = 0;
                var0.g[16] = var0.g[16] | 16;
                v1 = var0.g;
                v0 = 17;
                v2 = 0;
                ** GOTO lbl140
            }
            case 16: {
                v1 = var0.g;
                v0 = 15;
                v2 = 0;
                ** GOTO lbl140
            }
            case 103: 
            case 104: {
                v1 = var0.g;
                v0 = 15;
                v2 = 0;
                ** GOTO lbl140
            }
            case 7: {
                switch (var2_2) {
                    case 0: {
                        var0.g[16] = var0.g[16] & -257;
                        break;
                    }
                    case 2: {
                        AudioManager.Q(var0);
                    }
                }
                break;
            }
            case 8: {
                break;
            }
            case 12: {
                v1 = var0.g;
                v0 = 27;
                v2 = AudioManager.g;
                ** GOTO lbl140
            }
            case 13: {
                break;
            }
            case 9: {
                switch (var2_2) {
                    case 2: {
                        AudioManager.P(var0);
                    }
                }
                if (var3_3 != null) {
                    AudioManager.f(var0, var3_3.a.a, var3_3.a.b, 0);
                }
                v1 = var0.g;
                v0 = 15;
                v2 = 0;
                ** GOTO lbl140
            }
            case 11: {
                break;
            }
            case 100: {
                if ((var0.g[16] & 4096) != 0) {
                    if ((var0.g[16] & 2048) == 0) {
                        var1_1 = 107;
                        break;
                    }
                    var1_1 = 4;
                    break;
                }
                GameCanvas.a(var0, 1, false);
                if ((var0.g[16] & 512) == 0) break;
                GameCanvas.d((var0.g[16] & 16384) != 0);
                var0.g[16] = var0.g[16] & -513;
                v0 = 16;
                v1 = var0.g;
                v2 = var0.g[16] & -16385;
                ** GOTO lbl140
            }
            case 101: {
                if ((var0.g[16] & 64) != 0) break;
                var0.g[16] = var0.g[16] | 64;
                AudioManager.g(var0);
                break;
            }
            case 106: {
                AudioManager.M(var0);
                break;
            }
            case 20: {
                if (var3_3 == null) break;
                var5_6 = GameCanvas.h(var0.g[0], 12);
                AudioManager.a(var0, var3_3.a.a, var3_3.a.b, var5_6);
                break;
            }
            case 22: {
                var0.g[28] = 0;
                v1 = var0.g;
                v0 = 29;
                v2 = 0;
                ** GOTO lbl140
            }
            case 25: {
                v0 = 16;
                v1 = var0.g;
                v2 = var0.g[16] | 256;
lbl140:
                // 11 sources

                v1[v0] = v2;
            }
        }
        var0.g[12] = var1_1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static int c(AudioManager audioManager, int n, int n2) {
        int n3;
        int n4 = 0;
        int n5 = GameCanvas.d(audioManager.g[0], n, 11);
        if (n5 == 0) {
            n3 = audioManager.g[30 + n];
            return n3;
        } else {
            if (n2 == -1) {
                int n6 = 0;
                while (n6 < n5) {
                    n4 += AudioManager.a(audioManager, n, n6);
                    ++n6;
                }
                return n4;
            }
            n3 = AudioManager.a(audioManager, n, n2);
        }
        return n3;
    }

    private static void e(AudioManager audioManager, int n, int n2, int n3) {
        int n4 = GameCanvas.d(audioManager.g[0], n, 11);
        if (n4 == 0) {
            audioManager.g[30 + n] = n3;
            return;
        }
        if (n2 == -1) {
            for (int i = 0; i < n4; ++i) {
                AudioManager.c(audioManager, n, i, n3 / n4);
            }
        } else {
            AudioManager.c(audioManager, n, n2, n3);
        }
    }

    public static AudioManager a(AudioManager audioManager) {
        AudioManager audioManager2 = null;
        int n = GameCanvas.a(audioManager.g[20], 255);
        int n2 = GameCanvas.a(audioManager.g[20], 0xFFFF00);
        audioManager2 = GameCanvas.b(n, n2);
        if (d && audioManager2 == GameCanvas.c) {
            return null;
        }
        if (audioManager2 != null && audioManager2.L != 3) {
            return audioManager2;
        }
        return null;
    }

    private static void L(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 33);
        for (int i = 0; i < n; ++i) {
            AudioManager audioManager2;
            int n2;
            int n3 = GameCanvas.d(audioManager.g[0], i, 7);
            int n4 = GameCanvas.d(audioManager.g[0], i, 12);
            if (n4 == -1) {
                n2 = GameCanvas.d(audioManager.g[0], i, 5);
                int n5 = GameCanvas.d(audioManager.g[0], i, 6);
                if (n2 != -1 && n5 != -1) continue;
                audioManager2 = audioManager;
            } else {
                n2 = GameCanvas.j(n4, 1) == 1 ? 1 : 0;
                if (n2 == 0) continue;
                audioManager2 = audioManager;
            }
            audioManager2.g[26] = audioManager2.g[26] & ~n3;
            audioManager.g[25] = audioManager.g[25] & ~n3;
        }
        audioManager.g[18] = audioManager.g[18] & ~(audioManager.g[25] | audioManager.g[26]);
        audioManager.g[25] = 0;
        audioManager.g[26] = 0;
        audioManager.g[16] = audioManager.g[16] & 0xFFFFE7FF;
    }

    public static void l(AudioManager audioManager) {
        boolean bl = GameCanvas.h(audioManager.g[0], 34) != -1;
        if (bl) {
            int n = GameCanvas.h(audioManager.g[0], 33);
            for (int i = 0; i < n; ++i) {
                int n2 = AudioManager.c(audioManager, i, -1);
                if (n2 != 0) continue;
                AudioManager.M(audioManager);
                audioManager.g[25] = audioManager.g[25] | audioManager.g[26];
                audioManager.g[26] = 0;
                AudioManager.k(audioManager);
                return;
            }
        }
    }

    private static void M(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 33);
        for (int i = 0; i < n; ++i) {
            int n2;
            boolean bl;
            int n3 = AudioManager.c(audioManager, i, -1);
            int n4 = GameCanvas.d(audioManager.g[0], i, 12);
            if (n4 == -1) continue;
            boolean bl2 = bl = GameCanvas.j(n4, 1) == 1;
            if (n3 != 0 && !bl || ((n2 = GameCanvas.d(audioManager.g[0], i, 7)) & audioManager.g[25]) != 0) continue;
            audioManager.g[18] = audioManager.g[18] | n2;
            audioManager.g[19] = audioManager.g[19] & ~n2;
            audioManager.g[26] = audioManager.g[26] | n2;
            int n5 = GameCanvas.j(n4, 0);
            AudioManager.e(audioManager, i, -1, n5);
        }
        audioManager.g[16] = audioManager.g[16] | 0x1000;
        audioManager.g[16] = audioManager.g[16] & 0xFFFFF7FF;
        audioManager.g[16] = audioManager.g[16] & 0xFFFFFEFF;
    }

    private static int g(AudioManager audioManager) {
        return AudioManager.c(audioManager, -1);
    }

    private static int c(AudioManager audioManager, int n) {
        GraphicsEngine graphicsEngine = audioManager.a.a;
        int n2 = audioManager.g[12];
        if (n != -1) {
            audioManager.g[12] = n;
        }
        int n3 = AudioManager.a(audioManager, false, true);
        int n4 = graphicsEngine.a(n3);
        int n5 = graphicsEngine.a(audioManager.a.d);
        int n6 = audioManager.a.e;
        audioManager.g[12] = n2;
        if (n4 < n5 && n6 >= n4) {
            n6 -= n4 * (n6 / n4);
        }
        return graphicsEngine.b(n3, n6);
    }

    private static boolean r(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        int n2 = GameCanvas.h(audioManager.g[0], 7);
        int n3 = audioManager.a.a.a(audioManager.a.d) >> 1;
        return (n == 0 || n == 5 || n == 3 || n == 7) && audioManager.a.d >= n2 && audioManager.a.d < n2 + 4 && audioManager.a.e > n3;
    }

    private static void N(AudioManager audioManager) {
        if ((audioManager.g[16] & 0x800) != 0 && audioManager.g[24] > 0) {
            audioManager.g[24] = audioManager.g[24] - (int)GameCanvas.d;
            if (audioManager.g[24] <= 0) {
                audioManager.g[24] = 0;
                int n = GameCanvas.h(audioManager.g[0], 34);
                if (n != -1) {
                    int n2;
                    AudioManager audioManager2;
                    if (audioManager.g[12] == 101 || audioManager.g[12] == 100 || AudioManager.r(audioManager)) {
                        audioManager2 = audioManager;
                        n2 = 105;
                    } else {
                        audioManager2 = audioManager;
                        n2 = 106;
                    }
                    AudioManager.c(audioManager2, n2);
                    AudioManager.c(audioManager);
                    return;
                }
                audioManager.g[16] = audioManager.g[16] & 0xFFFFF7FF;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean b(AudioManager audioManager, int n) {
        int n2 = GameCanvas.h(audioManager.g[0], 10);
        boolean bl = false;
        boolean bl2 = g - audioManager.g[27] >= 500;
        switch (n2) {
            case 0: 
            case 5: 
            case 7: {
                if ((audioManager.g[16] & 0x1000) == 0) return bl;
                AudioManager audioManager2 = AudioManager.a(audioManager);
                if (audioManager2 == null) return bl;
                if (GameCanvas.a(audioManager.a.a, audioManager.a.b, audioManager2.a.a, audioManager2.a.b, null, false, 197)) return bl;
                int n3 = AudioManager.b(audioManager);
                bl2 = bl2 && n <= 14400;
                switch (n3) {
                    case 2: 
                    case 3: {
                        bl = bl2 && GameCanvas.b(audioManager.a.b()) == GameCanvas.b(audioManager2.a.b());
                        return bl;
                    }
                    case 0: 
                    case 1: {
                        bl = bl2 && GameCanvas.b(audioManager.a.a()) == GameCanvas.b(audioManager2.a.a());
                    }
                }
                return bl;
            }
            case 1: {
                if ((audioManager.g[16] & 0x100) != 0) return bl;
                int n4 = GameCanvas.h(audioManager.g[0], 24);
                if (n4 == -1) return bl;
                boolean bl3 = bl2;
                return bl3;
            }
            case 4: {
                AudioManager audioManager3 = AudioManager.a(audioManager);
                if (audioManager3 == null) return bl;
                if (GameCanvas.a(audioManager.a.a, audioManager.a.b, audioManager3.a.a, audioManager3.a.b, null, false, 197)) return bl;
                return true;
            }
            case 6: {
                if (audioManager.g[29] < 15000) return bl;
                return true;
            }
        }
        return bl;
    }

    /*
     * Unable to fully structure code
     */
    public static void a(AudioManager var0, boolean var1_1) {
        block94: {
            block98: {
                block95: {
                    block97: {
                        block96: {
                            if (var0.g[12] == -1) break block94;
                            if (AudioManager.b == null && var0.g[10] == 2 && var0.g[12] < 100) {
                                AudioManager.b = var0;
                                AudioManager.C = AudioManager.h(var0);
                                AudioManager.D = -(AudioManager.j(var0) << 14);
                            }
                            if (AudioManager.b != null && AudioManager.D < 0 && AudioManager.b.g[12] < 100 && (AudioManager.D += (int)GameCanvas.d * (var2_2 = (AudioManager.j(AudioManager.b) << 14) / 1700)) >= 0) {
                                AudioManager.D = 0;
                            }
                            AudioManager.S(var0);
                            AudioManager.C(var0);
                            AudioManager.N(var0);
                            if (var1_1) {
                                var0.a.a();
                            }
                            if (var0.g[10] != 0 && var0.g[10] != 2 && var0.g[10] != 1 && var0.g[10] != 5) break block95;
                            if (var0.g[12] == 101 || var0.g[12] == -1) break block96;
                            var2_3 = AudioManager.a(var0);
                            var3_4 = GameCanvas.h(var0.g[0], 10);
                            var4_5 = GameCanvas.h(var0.g[0], 12);
                            var5_6 = GameCanvas.h(var0.g[0], 11);
                            var6_7 = GameCanvas.h(var0.g[0], 16);
                            var7_8 = GameCanvas.h(var0.g[0], 17);
                            var8_9 = GameCanvas.h(var0.g[0], 18);
                            var9_10 = var6_7;
                            if (var2_3 != null) {
                                var9_10 = GameCanvas.a(var0, var2_3);
                            }
                            var10_11 = GameCanvas.h(var0.g[0], 15) == 1;
                            AudioManager.g = AudioManager.b(var0.g[0], var0.a.a >> 14, var0.a.b >> 14);
                            switch (var3_4) {
                                case 6: {
                                    v0 = 29;
                                    v1 = var0.g;
                                    v2 = var0.g[29] + (int)GameCanvas.d;
                                    break;
                                }
                                case 0: {
                                    if ((var0.g[16] & 256) == 0) break;
                                    var4_5 += var4_5 * 120 / 100;
                                    break;
                                }
                                case 1: {
                                    var9_10 = AudioManager.a(var0, var2_3, var9_10);
                                    if (GameCanvas.h(var0.g[0], 29) == -1) ** GOTO lbl50
                                    if ((var0.g[16] & 256) == 0) ** GOTO lbl47
                                    var0.g[29] = var0.g[29] + (int)GameCanvas.d;
                                    if (var0.g[29] >= 5000) {
                                        var0.g[29] = 0;
                                    } else {
                                        var4_5 += var4_5 * 100 / 100;
                                        break;
lbl47:
                                        // 1 sources

                                        if (var0.g[29] < 3 || var0.g[12] != 4) break;
                                        AudioManager.c(var0, 25);
                                        break;
                                    }
lbl50:
                                    // 2 sources

                                    v0 = 16;
                                    v1 = var0.g;
                                    v2 = v1[v0] = var0.g[16] & -257;
                                }
                            }
                            if ((var0.g[16] & 128) != 0) {
                                var11_12 = GameCanvas.a(var0.g[16], -65536);
                                if ((var11_12 += (int)GameCanvas.d) >= 1000) {
                                    var0.g[16] = var0.g[16] & -129;
                                    var11_12 = 0;
                                }
                                v3 = var0.g;
                                v4 = 16;
                                v5 = GameCanvas.a(var0.g[16], -65536, var11_12);
                            } else {
                                v4 = 16;
                                v3 = var0.g;
                                v5 = var0.g[16] & 65535;
                            }
                            v3[v4] = v5;
                            switch (var0.g[12]) {
                                case 0: {
                                    if (AudioManager.q(var0)) {
                                        AudioManager.c(var0, 4);
                                        break;
                                    }
                                    if ((var0.g[10] == 0 || var0.g[10] == 2) && GameCanvas.a() % 100 < 25) {
                                        AudioManager.k(var0);
                                        AudioManager.c(var0, 1);
                                        var0.g[27] = 3000 + GameCanvas.a() % 10000;
                                        break;
                                    }
                                    break block97;
                                }
                                case 1: {
                                    if (AudioManager.q(var0)) {
                                        AudioManager.c(var0, 4);
                                        break;
                                    }
                                    var0.g[15] = var0.g[15] + (int)GameCanvas.d;
                                    if (var0.g[15] >= var0.g[27]) {
                                        var11_12 = var0.g[13];
                                        var12_13 = var0.g[14];
                                        AudioManager.k(var0);
                                        if (!AudioManager.d(var0, var5_6)) {
                                            var0.g[13] = var11_12;
                                            var0.g[14] = var12_13;
                                        }
                                        var0.g[15] = 0;
                                        var0.g[27] = 3000 + GameCanvas.a() % 10000;
                                        break;
                                    }
                                    if (!AudioManager.d(var0, var5_6)) {
                                        if (AudioManager.c(var0, var5_6)) {
                                            var8_9 -= 10;
                                        }
                                        if (GameCanvas.a() % 100 < var8_9) {
                                            AudioManager.c(var0, 2);
                                            break;
                                        }
                                        AudioManager.k(var0);
                                        break;
                                    }
                                    break block97;
                                }
                                case 2: {
                                    if (var0.a.a()) {
                                        AudioManager.c(var0, 3);
                                        break;
                                    }
                                    break block97;
                                }
                                case 3: {
                                    if (var0.a.a()) {
                                        var0.g[13] = 0;
                                        var0.g[14] = 0;
                                        AudioManager.c(var0, 0);
                                        break;
                                    }
                                    break block97;
                                }
                                case 4: {
                                    if (var2_3 == GameCanvas.c) {
                                        ++AudioManager.E;
                                    }
                                    if (var9_10 >= var6_7 || !AudioManager.b(var3_4, var2_3)) {
                                        AudioManager.c(var0, 0);
                                        break;
                                    }
                                    if (var9_10 <= var7_8) {
                                        if (var3_4 == 4 || var3_4 == 6) {
                                            AudioManager.f(var0, var2_3.a.a, var2_3.a.b, 0);
                                            if (var9_10 > 2500) {
                                                if (var3_4 == 6) {
                                                    if (AudioManager.b(var0, var9_10)) {
                                                        AudioManager.c(var0, 21);
                                                        break;
                                                    }
                                                    AudioManager.c(var0, 12);
                                                    break;
                                                }
                                                if (AudioManager.b(var0, var9_10)) {
                                                    AudioManager.c(var0, 12);
                                                    break;
                                                }
                                                AudioManager.c(var0, 20);
                                                break;
                                            }
                                            AudioManager.c(var0, 7);
                                            break;
                                        }
                                        if (!GameCanvas.a(var0.a.a, var0.a.b, var2_3.a.a, var2_3.a.b, null, false, 197)) {
                                            AudioManager.c(var0, 7);
                                            break;
                                        }
                                        AudioManager.c(var0, 6);
                                        break;
                                    }
                                    if (!AudioManager.b(var0, var9_10)) ** GOTO lbl149
                                    if (var3_4 == 6) {
                                        v6 = var0;
                                        v7 = 21;
                                    } else {
                                        v6 = var0;
                                        v7 = 12;
                                    }
                                    ** GOTO lbl157
lbl149:
                                    // 1 sources

                                    AudioManager.f(var0, var2_3.a.a, var2_3.a.b, 0);
                                    if (AudioManager.d(var0, var4_5)) ** GOTO lbl158
                                    if (AudioManager.a(var0, var2_3.a.a, var2_3.a.b, var4_5)) {
                                        v6 = var0;
                                        v7 = 5;
                                    } else {
                                        v6 = var0;
                                        v7 = 6;
                                    }
lbl157:
                                    // 4 sources

                                    AudioManager.c(v6, v7);
lbl158:
                                    // 2 sources

                                    AudioManager.c(var0);
                                    break;
                                }
                                case 5: {
                                    var0.g[28] = var0.g[28] + (int)GameCanvas.d;
                                    var2_3 = AudioManager.c(var0);
                                    if (var2_3 == null) ** GOTO lbl185
                                    var11_12 = var0.g[13];
                                    var12_14 = var0.g[14];
                                    AudioManager.f(var0, var2_3.a.a, var2_3.a.b, 0);
                                    if (AudioManager.d(var0, var4_5) || AudioManager.a(var0, var2_3, true)) {
                                        AudioManager.c(var0, 4);
                                        break;
                                    }
                                    var0.g[13] = var11_12;
                                    var0.g[14] = var12_14;
                                    if (!AudioManager.d(var0, var4_5)) {
                                        var0.g[27] = var0.g[27] + 1;
                                        if (var0.g[27] > 3) {
                                            AudioManager.c(var0, 6);
                                            break;
                                        }
                                        AudioManager.a(var0, var2_3.a.a, var2_3.a.b, var4_5);
                                        break;
                                    }
                                    if (var0.g[28] > 5000 || var9_10 > 25600) {
                                        AudioManager.c(var0, 6);
                                        break;
                                    }
                                    break block97;
lbl185:
                                    // 1 sources

                                    AudioManager.c(var0, 0);
                                    break;
                                }
                                case 6: {
                                    var2_3 = AudioManager.c(var0);
                                    if (var2_3 != null) {
                                        AudioManager.f(var0, var2_3.a.a, var2_3.a.b, 0);
                                        if (AudioManager.c(var0, var2_3)) {
                                            AudioManager.c(var0, 4);
                                            break;
                                        }
                                        if (AudioManager.b(var0, var9_10)) {
                                            if (var3_4 == 6) {
                                                AudioManager.c(var0, 21);
                                                break;
                                            }
                                            AudioManager.c(var0, 12);
                                            break;
                                        }
                                    }
                                    break block97;
                                }
                                case 14: {
                                    if (var0.a.a() || !var10_11) {
                                        AudioManager.b(var0, true);
                                        break;
                                    }
                                    break block97;
                                }
                                case 15: {
                                    var0.g[15] = var0.g[15] + (int)GameCanvas.d;
                                    if (AudioManager.a != 8 || GameCanvas.a(true) != var0) {
                                        if (var0.g[15] >= 200) {
                                            if (var0.g[15] >= 400) {
                                                AudioManager.c(var0, 17);
                                                break;
                                            }
                                            AudioManager.c(var0, 16);
                                            break;
                                        }
                                        AudioManager.c(var0, 4);
                                        break;
                                    }
                                    if (var0.g[15] >= 400 && var0.a.a()) {
                                        AudioManager.H(var0);
                                        var0.a.b(var0.a.d);
                                        break;
                                    }
                                    break block97;
                                }
                                case 17: {
                                    AudioManager.d(var0, -40);
                                    if (var0.a.a()) {
                                        AudioManager.F(var0);
                                        var0.g[16] = var0.g[16] | 16;
                                        break;
                                    }
                                    break block97;
                                }
                                case 16: {
                                    var0.g[15] = var0.g[15] + (int)GameCanvas.d;
                                    if (var0.g[15] >= 1000 && var0.a.a()) {
                                        var0.g[16] = var0.g[16] & -17;
                                        AudioManager.c(var0, 4);
                                        break;
                                    }
                                    break block97;
                                }
                                case 104: {
                                    var0.g[15] = var0.g[15] + (int)GameCanvas.d;
                                    if (var0.a.a()) {
                                        AudioManager.F(var0);
                                    }
                                    if (var0.g[15] < 1000) {
                                        AudioManager.d(var0, -(GameCanvas.cC + 100));
                                        break;
                                    }
                                    break block97;
                                }
                                case 103: {
                                    var0.g[15] = var0.g[15] + (int)GameCanvas.d;
                                    if (var0.a.a()) {
                                        AudioManager.F(var0);
                                    }
                                    if (var0.g[15] < 1000) {
                                        AudioManager.d(var0, -100);
                                        break;
                                    }
                                    break block97;
                                }
                                case 9: {
                                    if (var3_4 == 2) {
                                        AudioManager.R(var0);
                                        break;
                                    }
                                    AudioManager.d(var0, -var4_5);
                                    var0.g[15] = var0.g[15] + (int)GameCanvas.d;
                                    if (var0.a.a() && var0.g[15] >= 1000) {
                                        AudioManager.c(var0, 11);
                                        break;
                                    }
                                    break block97;
                                }
                                case 11: {
                                    if (var0.a.a()) {
                                        AudioManager.c(var0, 4);
                                        break;
                                    }
                                    break block97;
                                }
                                case 23: {
                                    var0.g[15] = var0.g[15] + (int)GameCanvas.d;
                                    if (var0.g[15] >= 1500) {
                                        AudioManager.c(var0, 11);
                                        break;
                                    }
                                    break block97;
                                }
                                case 100: {
                                    if (var0.a.a()) {
                                        AudioManager.c(var0, 101);
                                        break;
                                    }
                                    break block97;
                                }
                                case 105: {
                                    if (var0.a.a()) {
                                        AudioManager.c(var0, 106);
                                        break;
                                    }
                                    break block97;
                                }
                                case 106: {
                                    if (var0.a.a()) {
                                        var0.g[25] = var0.g[25] | var0.g[26];
                                        var0.g[26] = 0;
                                        AudioManager.c(var0, 4);
                                        break;
                                    }
                                    break block97;
                                }
                                case 107: {
                                    if (var0.a.a()) {
                                        AudioManager.L(var0);
                                        AudioManager.c(var0, 100);
                                        break;
                                    }
                                    break block97;
                                }
                                default: {
                                    AudioManager.h(var0, var9_10);
                                    break;
                                }
                            }
                            break block97;
                        }
                        if (var0.g[12] == 101 && AudioManager.b != null && AudioManager.b == var0) {
                            AudioManager.b = null;
                        }
                    }
                    AudioManager.c(var0);
                    break block98;
                }
                if (var0.g[10] == 6) {
                    AudioManager.p(var0);
                }
            }
            AudioManager.e(var0);
        }
    }

    private static void h(AudioManager audioManager, int n) {
        int n2 = GameCanvas.h(audioManager.g[0], 10);
        switch (n2) {
            case 0: 
            case 3: 
            case 5: 
            case 7: {
                AudioManager.i(audioManager, n);
                return;
            }
            case 1: {
                AudioManager.j(audioManager, n);
                return;
            }
            case 2: {
                AudioManager.R(audioManager);
                return;
            }
            case 4: 
            case 6: {
                AudioManager.k(audioManager, n);
            }
        }
    }

    public static int b(AudioManager audioManager) {
        int n;
        block2: {
            int n2;
            block4: {
                block6: {
                    block5: {
                        block3: {
                            n = -1;
                            if (audioManager.g[13] == 0 && audioManager.g[14] == 0) break block2;
                            if (audioManager.g[13] <= 8192) break block3;
                            n2 = 3;
                            break block4;
                        }
                        if (audioManager.g[13] >= -8192) break block5;
                        n2 = 2;
                        break block4;
                    }
                    if (audioManager.g[14] <= 8192) break block6;
                    n2 = 1;
                    break block4;
                }
                if (audioManager.g[14] >= -8192) break block2;
                n2 = 0;
            }
            n = n2;
        }
        return n;
    }

    private static boolean s(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        int n2 = GameCanvas.h(audioManager.g[0], 7);
        int n3 = GameCanvas.h(audioManager.g[0], 8);
        return (n == 0 || n == 5 || n == 3 || n == 7) && (audioManager.a.d >= n2 && audioManager.a.d < n2 + 4 || audioManager.a.d >= n3 && audioManager.a.d < n3 + 4);
    }

    public static boolean e(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        return audioManager.g[12] >= 100 || AudioManager.s(audioManager) || n == 2 && (audioManager.g[12] == 7 || audioManager.g[12] == 8);
    }

    public static void m(AudioManager audioManager) {
        if (audioManager.g[12] != 101 && (audioManager.g[10] == 0 || audioManager.g[10] == 2)) {
            int n = AudioManager.b(audioManager);
            if (n >= 0) {
                n += GameCanvas.h(audioManager.g[0], 2);
            }
            if (n >= 0 && audioManager.a.d != n) {
                audioManager.a.b(n);
            }
        }
    }

    public static int c(AudioManager audioManager) {
        return AudioManager.a(audioManager, true, false);
    }

    private static int a(AudioManager audioManager, boolean bl, boolean bl2) {
        int n = 0;
        if (audioManager != null && audioManager.a != null && audioManager.a.a != null) {
            int n2;
            boolean bl3;
            boolean bl4 = false;
            int n3 = GameCanvas.h(audioManager.g[0], 10);
            n = AudioManager.b(audioManager);
            int n4 = 1;
            boolean bl5 = bl3 = n3 == 0 && (audioManager.g[16] & 0x100) != 0 || n3 == 1 && (audioManager.g[16] & 0x100) != 0;
            if (bl2 && (n2 = GameCanvas.h(audioManager.g[0], 34)) == -1) {
                bl2 = false;
            }
            if (n >= 0) {
                n2 = GameCanvas.h(audioManager.g[0], 15) == 1 ? 1 : 0;
                block0 : switch (audioManager.g[12]) {
                    case 0: 
                    case 6: 
                    case 28: {
                        int n5;
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 0);
                            break;
                        }
                        n5 = n + GameCanvas.h(audioManager.g[0], 2);
                        break;
                    }
                    case 1: {
                        int n5;
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 1);
                            break;
                        }
                        n5 = n + GameCanvas.h(audioManager.g[0], 3);
                        break;
                    }
                    case 5: {
                        if (audioManager.g[28] < 100) {
                            bl = false;
                        }
                    }
                    case 4: 
                    case 20: {
                        int n5;
                        if (bl3) {
                            n5 = n + GameCanvas.h(audioManager.g[0], 30);
                            break;
                        }
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 2);
                            break;
                        }
                        n5 = n + GameCanvas.h(audioManager.g[0], 4);
                        break;
                    }
                    case 7: {
                        int n5;
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 3);
                            break;
                        }
                        n5 = n + GameCanvas.h(audioManager.g[0], 5);
                        break;
                    }
                    case 8: {
                        int n5;
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 4);
                            break;
                        }
                        n5 = n + GameCanvas.h(audioManager.g[0], 6);
                        break;
                    }
                    case 12: {
                        int n5;
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 7);
                            break;
                        }
                        n5 = n + GameCanvas.h(audioManager.g[0], 23);
                        break;
                    }
                    case 13: {
                        int n5;
                        switch (n3) {
                            case 1: {
                                if (bl2) {
                                    n5 = n + (GameCanvas.i(audioManager.g[0], 8) + audioManager.g[28] * 4);
                                    break block0;
                                }
                                n5 = n + (GameCanvas.h(audioManager.g[0], 24) + audioManager.g[28] * 4);
                                break block0;
                            }
                        }
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 8);
                            break;
                        }
                        n5 = n + GameCanvas.h(audioManager.g[0], 24);
                        break;
                    }
                    case 2: 
                    case 9: 
                    case 17: 
                    case 103: 
                    case 104: {
                        int n5;
                        if ((audioManager.g[16] & 0x1000) != 0) {
                            if (bl2) {
                                n5 = n + GameCanvas.i(audioManager.g[0], 9);
                                break;
                            }
                            n5 = n + GameCanvas.d(audioManager.g[0], audioManager.g[17], 2);
                            break;
                        }
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 5);
                            break;
                        }
                        n5 = n + GameCanvas.h(audioManager.g[0], 7);
                        break;
                    }
                    case 23: {
                        bl4 = true;
                    }
                    case 3: 
                    case 11: 
                    case 105: {
                        int n5;
                        if ((audioManager.g[16] & 0x1000) != 0) {
                            if (bl2) {
                                n5 = n + GameCanvas.i(audioManager.g[0], 9);
                                break;
                            }
                            n5 = n + GameCanvas.d(audioManager.g[0], audioManager.g[17], 2);
                            break;
                        }
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 6);
                            break;
                        }
                        n5 = n + GameCanvas.h(audioManager.g[0], 8);
                        break;
                    }
                    case 15: 
                    case 16: {
                        int n5;
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 9);
                            break;
                        }
                        n5 = n + GameCanvas.d(audioManager.g[0], audioManager.g[17], 2);
                        break;
                    }
                    case 14: 
                    case 18: {
                        int n5;
                        if (n2 != 0 || audioManager.g[12] == 18) {
                            if (bl2) {
                                n5 = n + GameCanvas.i(audioManager.g[0], 9);
                                break;
                            }
                            n5 = n + GameCanvas.d(audioManager.g[0], audioManager.g[17], 2);
                            break;
                        }
                        n5 = -1;
                        break;
                    }
                    case 100: {
                        int n5;
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 10);
                            break;
                        }
                        if ((audioManager.g[16] & 0x18) != 0) {
                            n5 = n + GameCanvas.h(audioManager.g[0], 27);
                            break;
                        }
                        n5 = n + GameCanvas.d(audioManager.g[0], audioManager.g[17], 3);
                        break;
                    }
                    case 101: {
                        int n5;
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 11);
                            break;
                        }
                        if ((audioManager.g[16] & 0x18) != 0) {
                            n5 = n + GameCanvas.h(audioManager.g[0], 28);
                            break;
                        }
                        n5 = n + GameCanvas.d(audioManager.g[0], audioManager.g[17], 4);
                        break;
                    }
                    case 19: {
                        n += 28;
                        break;
                    }
                    case 107: {
                        n4 = -1;
                    }
                    case 106: {
                        int n5;
                        if (bl2) {
                            n5 = n + GameCanvas.i(audioManager.g[0], 12);
                            break;
                        }
                        if (audioManager.g[12] == 106 && audioManager.g[25] != 0) {
                            n5 = n + GameCanvas.h(audioManager.g[0], 2);
                            break;
                        }
                        n5 = n + GameCanvas.i(audioManager.g[0], 13);
                        break;
                    }
                    case 22: {
                        n += 43;
                        break;
                    }
                    case 21: {
                        n += 51;
                        break;
                    }
                    case 24: 
                    case 25: {
                        int n5 = n + GameCanvas.h(audioManager.g[0], 29);
                        break;
                    }
                    case 26: {
                        int n5 = 77;
                        break;
                    }
                    case 27: 
                    case 108: {
                        int n5 = 87;
                        break;
                    }
                    default: {
                        int n5 = n = -1;
                    }
                }
            }
            if (bl4 || bl && n >= 0 && audioManager.a.d != n) {
                audioManager.a.a(n, n4, -1);
            }
        }
        return n;
    }

    private static void f(AudioManager audioManager, int n, int n2, int n3) {
        int n4;
        int n5;
        int n6;
        int n7;
        int[] nArray = null;
        if ((n3 & 1) != 0) {
            n7 = n;
            n6 = n2;
            n5 = audioManager.a.a;
            n4 = audioManager.a.b;
        } else {
            n7 = audioManager.a.a;
            n6 = audioManager.a.b;
            n5 = n;
            n4 = n2;
        }
        nArray = GameCanvas.a(n7, n6, n5, n4, false);
        audioManager.g[13] = nArray[0];
        audioManager.g[14] = nArray[1];
    }

    public static boolean f(AudioManager audioManager) {
        return audioManager != null && audioManager.g[12] < 100 && audioManager.g[12] != -1 && audioManager.g[10] != 5;
    }

    private static boolean b(int n, int n2) {
        byte by;
        block8: {
            int n3;
            byte[] byArray;
            block3: {
                int n4;
                byte[][] byArray2;
                block7: {
                    block6: {
                        block5: {
                            block4: {
                                block2: {
                                    by = 0;
                                    if (!GameCanvas.b(GameCanvas.b(n), GameCanvas.b(n2))) break block2;
                                    byArray = GameCanvas.e[GameCanvas.b(n)];
                                    n3 = n2;
                                    break block3;
                                }
                                if (!GameCanvas.b(GameCanvas.b(n - 5), GameCanvas.b(n2 + 4))) break block4;
                                byArray = GameCanvas.e[GameCanvas.b(n - 5)];
                                n3 = n2 + 4;
                                break block3;
                            }
                            if (!GameCanvas.b(GameCanvas.b(n + 4), GameCanvas.b(n2 + 4))) break block5;
                            byArray = GameCanvas.e[GameCanvas.b(n + 4)];
                            n3 = n2 + 4;
                            break block3;
                        }
                        if (!GameCanvas.b(GameCanvas.b(n - 5), GameCanvas.b(n2 - 5))) break block6;
                        byArray2 = GameCanvas.e;
                        n4 = n - 5;
                        break block7;
                    }
                    if (!GameCanvas.b(GameCanvas.b(n + 4), GameCanvas.b(n2 - 5))) break block8;
                    byArray2 = GameCanvas.e;
                    n4 = n + 4;
                }
                byArray = byArray2[GameCanvas.b(n4)];
                n3 = n2 - 5;
            }
            by = byArray[GameCanvas.b(n3)];
        }
        return (by & 8) != 0;
    }

    private static boolean c(AudioManager audioManager, int n) {
        int n2 = audioManager.g[13] * (int)GameCanvas.d * n / 1000;
        int n3 = audioManager.g[14] * (int)GameCanvas.d * n / 1000;
        return AudioManager.b(audioManager.a.a + n2 >> 14, audioManager.a.b + n3 >> 14);
    }

    private static boolean c(int n, int n2) {
        return GameCanvas.a(GameCanvas.b(n), GameCanvas.b(n2), 64) || GameCanvas.a(GameCanvas.b(n - 5), GameCanvas.b(n2 + 4), 64) || GameCanvas.a(GameCanvas.b(n + 4), GameCanvas.b(n2 + 4), 64) || GameCanvas.a(GameCanvas.b(n - 5), GameCanvas.b(n2 - 5), 64) || GameCanvas.a(GameCanvas.b(n + 4), GameCanvas.b(n2 - 5), 64);
    }

    private static boolean b(int n, int n2, int n3) {
        int n4 = GameCanvas.h(n, 19);
        int n5 = GameCanvas.h(n, 20);
        int n6 = n2 - (n4 * 16 >> 1) + 8;
        int n7 = n3 - (n5 * 16 >> 1) + 8;
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n5; ++j) {
                if (!AudioManager.c(n6 + i * 16, n7 + j * 16)) continue;
                return true;
            }
        }
        return false;
    }

    private static boolean c(int n, int n2, int n3) {
        return GameCanvas.a(GameCanvas.b(n), GameCanvas.b(n2), n3) || GameCanvas.a(GameCanvas.b(n - 5), GameCanvas.b(n2 + 4), n3) || GameCanvas.a(GameCanvas.b(n + 4), GameCanvas.b(n2 + 4), n3) || GameCanvas.a(GameCanvas.b(n - 5), GameCanvas.b(n2 - 5), n3) || GameCanvas.a(GameCanvas.b(n + 4), GameCanvas.b(n2 - 5), n3);
    }

    private static boolean d(int n, int n2) {
        return GameCanvas.e(n / 16, n2 / 16) || GameCanvas.e((n - 5) / 16, (n2 + 4) / 16) || GameCanvas.e((n + 4) / 16, (n2 + 4) / 16) || GameCanvas.e((n - 5) / 16, (n2 - 5) / 16) || GameCanvas.e((n + 4) / 16, (n2 - 5) / 16);
    }

    public static boolean a(int n, int n2, int n3) {
        return AudioManager.a(n, n2, n3, 77);
    }

    public static boolean a(int n, int n2, int n3, int n4) {
        int n5;
        int n6;
        int n7 = GameCanvas.h(n, 19);
        int n8 = GameCanvas.h(n, 20);
        int n9 = n2 - (n7 * 16 >> 1) + 8;
        int n10 = n3 - (n8 * 16 >> 1) + 8;
        for (n6 = 0; n6 < n7; ++n6) {
            for (n5 = 0; n5 < n8; ++n5) {
                if (!AudioManager.c(n9 + n6 * 16, n10 + n5 * 16, n4)) continue;
                return true;
            }
        }
        int n11 = GameCanvas.h(n, 10);
        if (n11 == 5 || n11 == 7) {
            for (n6 = 0; n6 < n7; ++n6) {
                for (n5 = 0; n5 < n8; ++n5) {
                    if (!AudioManager.d(n9 + n6 * 16, n10 + n5 * 16)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean c(AudioManager audioManager, AudioManager audioManager2) {
        int n = audioManager.a.a();
        int n2 = audioManager.a.b();
        int n3 = audioManager.a.a;
        int n4 = audioManager.a.b;
        GameCanvas.a(audioManager, 1, false);
        int n5 = GameCanvas.h(audioManager.g[0], 10);
        if (n5 == 2) {
            audioManager.a.b(n, n2);
            if (!AudioManager.a(audioManager.g[0], n, n2) && AudioManager.a(audioManager, audioManager2, false)) {
                GameCanvas.a(audioManager, 0, false);
                return true;
            }
        } else {
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    if ((i != 0 || j == 0) && (i == 0 || j != 0)) continue;
                    int n6 = n + i * 16;
                    int n7 = n2 + j * 16;
                    audioManager.a.b(n6, n7);
                    if (AudioManager.a(audioManager.g[0], n6, n7) || !AudioManager.a(audioManager, audioManager2, false)) continue;
                    GameCanvas.a(audioManager, 0, false);
                    return true;
                }
            }
        }
        audioManager.a.a = n3;
        audioManager.a.b = n4;
        GameCanvas.a(audioManager, 0, false);
        return false;
    }

    /*
     * Unable to fully structure code
     */
    private static boolean a(AudioManager var0, int var1_1, int var2_2, int var3_3) {
        block26: {
            block25: {
                block28: {
                    block27: {
                        block22: {
                            block24: {
                                block23: {
                                    block16: {
                                        block19: {
                                            block21: {
                                                block20: {
                                                    block13: {
                                                        block18: {
                                                            block17: {
                                                                block15: {
                                                                    block14: {
                                                                        var4_4 = false;
                                                                        GameCanvas.a(var0, 1, false);
                                                                        var5_5 = 0;
                                                                        var6_6 = 0;
                                                                        if ((var0.g[16] & 128) != 0) {
                                                                            var5_5 = GameCanvas.b(var0.a.a());
                                                                            var6_6 = GameCanvas.b(var0.a.b());
                                                                        }
                                                                        var7_7 = var0.a.a / 16 * 16;
                                                                        var8_8 = var0.a.b / 16 * 16;
                                                                        AudioManager.f(var0, var1_1, var2_2, 0);
                                                                        var9_9 = var0.g[13];
                                                                        var10_10 = var0.g[14];
                                                                        var11_11 = var0.g[13] * (int)GameCanvas.d * var3_3 / 1000;
                                                                        var12_12 = var0.g[14] * (int)GameCanvas.d * var3_3 / 1000;
                                                                        var13_13 = 81920;
                                                                        if (GameCanvas.d(var9_9) <= GameCanvas.d(var10_10)) break block13;
                                                                        if (var10_10 == 0 || AudioManager.a(var0.g[0], var7_7 + var11_11 >> 14, var8_8 + var12_12 + (var10_10 > 0 ? var13_13 : -var13_13) >> 14) && AudioManager.a(var0.g[0], var7_7 >> 14, var8_8 + (var10_10 > 0 ? var13_13 : -var13_13) >> 14)) break block14;
                                                                        var9_9 = 0;
                                                                        v0 = var10_10 > 0 ? 1 : -1;
                                                                        v1 = 14;
                                                                        ** GOTO lbl74
                                                                    }
                                                                    if (var9_9 == 0 || AudioManager.a(var0.g[0], var7_7 + var11_11 + (var9_9 > 0 ? var13_13 : -var13_13) >> 14, var8_8 + var12_12 >> 14) && AudioManager.a(var0.g[0], var7_7 + (var9_9 > 0 ? var13_13 : -var13_13) >> 14, var8_8 >> 14)) break block15;
                                                                    var9_9 = (var9_9 > 0 ? 1 : -1) << 14;
                                                                    v2 = 0;
                                                                    break block16;
                                                                }
                                                                if (var10_10 <= 0) break block17;
                                                                var9_9 = 0;
                                                                v2 = 16384;
                                                                break block16;
                                                            }
                                                            if (var10_10 >= 0) break block18;
                                                            var9_9 = 0;
                                                            v2 = -16384;
                                                            break block16;
                                                        }
                                                        var9_9 = 0;
                                                        v0 = GameCanvas.a() % 100 < 50 ? 1 : -1;
                                                        v1 = 14;
                                                        ** GOTO lbl74
                                                    }
                                                    if (GameCanvas.d(var9_9) >= GameCanvas.d(var10_10)) break block19;
                                                    if (var9_9 == 0 || AudioManager.a(var0.g[0], var7_7 + var11_11 + (var9_9 > 0 ? var13_13 : -var13_13) >> 14, var8_8 + var12_12 >> 14) && AudioManager.a(var0.g[0], var7_7 + (var9_9 > 0 ? var13_13 : -var13_13) >> 14, var8_8 >> 14)) break block20;
                                                    var9_9 = (var9_9 > 0 ? 1 : -1) << 14;
                                                    v2 = 0;
                                                    break block16;
                                                }
                                                if (var10_10 == 0 || AudioManager.a(var0.g[0], var7_7 + var11_11 >> 14, var8_8 + var12_12 + (var10_10 > 0 ? var13_13 : -var13_13) >> 14) && AudioManager.a(var0.g[0], var7_7 >> 14, var8_8 + (var10_10 > 0 ? var13_13 : -var13_13) >> 14)) break block21;
                                                var9_9 = 0;
                                                v0 = var10_10 > 0 ? 1 : -1;
                                                v1 = 14;
                                                ** GOTO lbl74
                                            }
                                            if (var9_9 > 0) {
                                                var9_9 = 16384;
                                                v2 = 0;
                                            } else if (var9_9 < 0) {
                                                var9_9 = -16384;
                                                v2 = 0;
                                            } else {
                                                var9_9 = (GameCanvas.a() % 100 < 50 ? 1 : -1) << 14;
                                                v2 = 0;
                                            }
                                            break block16;
                                        }
                                        if (GameCanvas.d(var1_1 - var7_7) < GameCanvas.d(var2_2 - var8_8)) {
                                            var9_9 = (var9_9 >= 0 ? 1 : -1) << 14;
                                            v2 = 0;
                                        } else {
                                            var9_9 = 0;
                                            v0 = var10_10 >= 0 ? 1 : -1;
                                            v1 = 14;
lbl74:
                                            // 4 sources

                                            v2 = v0 << v1;
                                        }
                                    }
                                    var10_10 = v2;
                                    var11_11 = var9_9 * (int)GameCanvas.d * var3_3 / 1000;
                                    var12_12 = var10_10 * (int)GameCanvas.d * var3_3 / 1000;
                                    if (var11_11 == 0 || AudioManager.a(var0.g[0], var7_7 + var11_11 >> 14, var8_8 >> 14)) break block22;
                                    var0.g[13] = var9_9;
                                    var0.g[14] = 0;
                                    if ((var0.g[16] & 128) == 0) break block23;
                                    var14_14 = GameCanvas.b(var7_7 + var11_11 >> 14);
                                    var15_15 = GameCanvas.b(var8_8 + var12_12 >> 14);
                                    if (var14_14 != var5_5 || var15_15 != var6_6) break block24;
                                }
                                var0.a.a = var7_7 + var11_11;
                                var0.a.b = var8_8;
                            }
                            v3 = true;
                            break block25;
                        }
                        if (var12_12 == 0 || AudioManager.a(var0.g[0], var7_7 >> 14, var8_8 + var12_12 >> 14)) break block26;
                        var0.g[13] = 0;
                        var0.g[14] = var10_10;
                        if ((var0.g[16] & 128) == 0) break block27;
                        var14_14 = GameCanvas.b(var7_7 + var11_11 >> 14);
                        var15_15 = GameCanvas.b(var8_8 + var12_12 >> 14);
                        if (var14_14 != var5_5 || var15_15 != var6_6) break block28;
                    }
                    var0.a.a = var7_7;
                    var0.a.b = var8_8 + var12_12;
                }
                v3 = true;
            }
            var4_4 = v3;
        }
        GameCanvas.a(var0, 0, false);
        return var4_4;
    }

    private static boolean d(AudioManager audioManager, int n) {
        boolean bl;
        block12: {
            int n2;
            int n3;
            block9: {
                block11: {
                    block10: {
                        bl = false;
                        GameCanvas.a(audioManager, 1, false);
                        int n4 = 0;
                        int n5 = 0;
                        if ((audioManager.g[16] & 0x80) != 0) {
                            n4 = GameCanvas.b(audioManager.a.a());
                            n5 = GameCanvas.b(audioManager.a.b());
                        }
                        n3 = audioManager.a.a;
                        n2 = audioManager.a.b;
                        int n6 = audioManager.g[13] * (int)GameCanvas.d * n / 1000;
                        int n7 = audioManager.g[14] * (int)GameCanvas.d * n / 1000;
                        if (AudioManager.a(audioManager.g[0], (n3 += n6) >> 14, (n2 += n7) >> 14) && !g) break block9;
                        if ((audioManager.g[16] & 0x80) == 0) break block10;
                        int n8 = GameCanvas.b(n3 >> 14);
                        int n9 = GameCanvas.b(n2 >> 14);
                        if (n8 != n4 || n9 != n5) break block11;
                    }
                    audioManager.a.a = n3;
                    audioManager.a.b = n2;
                }
                bl = true;
                break block12;
            }
            if (AudioManager.a(audioManager.g[0], audioManager.a.a(), audioManager.a.b())) {
                n3 = audioManager.a.a() / 16 * 16 + 8;
                if (!AudioManager.a(audioManager.g[0], n3, n2 = audioManager.a.b() / 16 * 16 + 8)) {
                    audioManager.a.b(n3, n2);
                    bl = true;
                } else {
                    int n10 = n3 - 16;
                    int n11 = n3 + 16;
                    int n12 = n2 - 16;
                    int n13 = n2 + 16;
                    block0: for (int i = n10; i < n11; i += 16) {
                        for (int j = n12; j < n13; j += 16) {
                            if (AudioManager.a(audioManager.g[0], i, j)) continue;
                            audioManager.a.b(i, j);
                            bl = true;
                            continue block0;
                        }
                    }
                }
            }
        }
        GameCanvas.a(audioManager, 0, false);
        return bl;
    }

    public static int a(AudioManager audioManager, int n) {
        int n2;
        block5: {
            int n3;
            block6: {
                block4: {
                    n2 = 8;
                    int n4 = GameCanvas.h(audioManager.g[0], 10);
                    if (n == 1) {
                        n2 = 136;
                    }
                    if (n4 != 2) break block4;
                    n2 = 128;
                    if (n != 0 || audioManager.g[12] != 7 && audioManager.g[12] != 8) break block5;
                    n3 = 0;
                    break block6;
                }
                if (!AudioManager.s(audioManager) || n != 0) break block5;
                n3 = 128;
            }
            n2 = n3;
        }
        return n2;
    }

    public static boolean g(AudioManager audioManager) {
        return audioManager.g[10] != 5 && audioManager.g[10] != 3 && audioManager.g[10] != 4 && audioManager.g[12] < 100 && audioManager.g[12] > -1;
    }

    /*
     * Unable to fully structure code
     */
    public static void n(AudioManager var0) {
        block12: {
            block8: {
                block10: {
                    block7: {
                        block11: {
                            block9: {
                                block5: {
                                    block6: {
                                        var1_1 = GameCanvas.h(var0.g[0], 10);
                                        if (var0.g[12] != 101) break block5;
                                        if (var1_1 != 1) break block6;
                                        v0 = var0;
                                        v1 = var0.a.b() - 8;
                                        break block7;
                                    }
                                    v0 = var0;
                                    v2 = (var0.a.b() << 8) + var0.a.a() >> 1;
                                    break block8;
                                }
                                if ((var1_1 == 5 || var1_1 == 7) && var0.g[12] != -1 && (var0.g[16] & 32) != 0) {
                                    AudioManager.f = true;
                                }
                                if (var0.g[10] != 4) break block9;
                                v0 = var0;
                                v3 = (var0.a.b() + 320 << 8) + var0.a.a();
                                v4 = 240;
                                break block10;
                            }
                            if (var1_1 != 0 && var1_1 != 3 && var1_1 != 5 && var1_1 != 7 || var0.g[12] != 8) break block11;
                            var2_2 = AudioManager.a(var0);
                            if (var2_2 == null) break block12;
                            switch (AudioManager.b(var0)) {
                                case 1: 
                                case 2: {
                                    v5 = var0;
                                    v6 = var2_2.a.b() - 1;
                                    ** GOTO lbl31
                                }
                                case 0: 
                                case 3: {
                                    v5 = var0;
                                    v6 = var2_2.a.b() + 1;
lbl31:
                                    // 2 sources

                                    v5.M = (v6 << 8) + var2_2.a.a();
                                }
                            }
                            return;
                        }
                        v0 = var0;
                        v1 = var0.a.b();
                    }
                    v3 = v1 << 8;
                    v4 = var0.a.a();
                }
                v2 = v3 + v4;
            }
            v0.M = v2;
        }
    }

    private static void a(int n, int n2, int n3) {
        int n4 = GameCanvas.b(n) - n3;
        int n5 = GameCanvas.b(n2) - n3;
        n3 <<= 1;
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n3; ++j) {
                AudioManager audioManager = GameCanvas.a(n4 + i, n5 + j);
                if (audioManager == null || audioManager.L != 3 || audioManager.g[12] >= 4 || audioManager.g[12] == -1 || audioManager.g[10] == 3 || audioManager.g[10] == 6 || audioManager.g[10] == 4 || audioManager.g[10] == 5) continue;
                AudioManager.d(audioManager, GameCanvas.c);
                AudioManager.c(audioManager, 4);
            }
        }
    }

    private static boolean a(AudioManager audioManager, AudioManager audioManager2, boolean bl) {
        boolean bl2 = true;
        if (audioManager2 != null && AudioManager.b(audioManager, audioManager2)) {
            int n = GameCanvas.h(audioManager.g[0], 10);
            if (bl) {
                GameCanvas.a(audioManager, 1, false);
            }
            GameCanvas.a(audioManager2, 1, true);
            int n2 = 0;
            int n3 = 0;
            if (n == 4 || n == 6) {
                n2 = 2;
                n3 = 128;
            }
            bl2 = GameCanvas.a(audioManager.a.a, audioManager.a.b, audioManager2.a.a, audioManager2.a.b, null, false, 197, n2, n3);
            int n4 = 16 * GameCanvas.h(audioManager.g[0], 19) / 2 - 5 << 14;
            int n5 = 16 * GameCanvas.h(audioManager.g[0], 20) / 2 - 5 << 14;
            if (!bl2) {
                bl2 = GameCanvas.a(audioManager.a.a - n4, audioManager.a.b - n5, audioManager2.a.a - n4, audioManager2.a.b - n5, null, false, 197, n2, n3);
            }
            if (!bl2) {
                bl2 = GameCanvas.a(audioManager.a.a + n4, audioManager.a.b - n5, audioManager2.a.a + n4, audioManager2.a.b - n5, null, false, 197, n2, n3);
            }
            if (!bl2) {
                bl2 = GameCanvas.a(audioManager.a.a + n4, audioManager.a.b + n5, audioManager2.a.a + n4, audioManager2.a.b + n5, null, false, 197, n2, n3);
            }
            if (!bl2) {
                bl2 = GameCanvas.a(audioManager.a.a - n4, audioManager.a.b + n5, audioManager2.a.a - n4, audioManager2.a.b + n5, null, false, 197, n2, n3);
            }
            if (bl) {
                GameCanvas.a(audioManager, 0, false);
            }
            GameCanvas.a(audioManager2, 0, true);
        }
        return !bl2;
    }

    /*
     * Unable to fully structure code
     */
    private static boolean t(AudioManager var0) {
        block6: {
            var1_1 = false;
            var2_2 = GameCanvas.h(var0.g[0], 10);
            var3_3 = GameCanvas.h(var0.g[0], 29);
            if (var3_3 == -1 || var2_2 != 0 || GameCanvas.h(var0.g[0], 34) != -1 || (var0.g[16] & 4356) != 0) break block6;
            var4_4 = 20;
            switch (GameCanvas.bF) {
                case 0: {
                    v0 = 20;
                    ** GOTO lbl18
                }
                case 1: {
                    v0 = 35;
                    ** GOTO lbl18
                }
                case 2: {
                    v0 = 50;
                    ** GOTO lbl18
                }
                case 3: {
                    v0 = 65;
lbl18:
                    // 4 sources

                    var4_4 = v0;
                }
            }
            var1_1 = GameCanvas.a() % 100 < var4_4;
        }
        return var1_1;
    }

    private static void i(AudioManager audioManager, int n) {
        int n2 = GameCanvas.h(audioManager.g[0], 12);
        int n3 = GameCanvas.h(audioManager.g[0], 17);
        AudioManager audioManager2 = AudioManager.a(audioManager);
        switch (audioManager.g[12]) {
            case 7: {
                AudioManager.d(audioManager, n2);
                if (!audioManager.a.a()) break;
                if (audioManager2 != null && n <= n3 && !GameCanvas.a(audioManager.a.a, audioManager.a.b, audioManager2.a.a, audioManager2.a.b, null, false, 197) && (audioManager2 == GameCanvas.c && a != 100 && a != 14 && a != 17 && (a != 18 || GameCanvas.m(audioManager2.g[29], 1) != 1) || audioManager2 != GameCanvas.c && AudioManager.m(audioManager2) < 555)) {
                    AudioManager.f(audioManager, audioManager2.a.a, audioManager2.a.b, 0);
                    audioManager.g[15] = 0;
                    AudioManager.c(audioManager, 8);
                    if (audioManager2 == GameCanvas.c) {
                        AudioManager.b(audioManager2, GameCanvas.h(audioManager.g[0], 14));
                        GameCanvas.cu = GameCanvas.h(AudioManager.a(GameCanvas.c, audioManager, true));
                        a = audioManager;
                        GameCanvas.b();
                        GameCanvas.j();
                        AudioManager.a(100, GameCanvas.c);
                        return;
                    }
                    AudioManager.g(audioManager2, audioManager.a.a, audioManager.a.b, 0);
                    AudioManager.d(audioManager2, 100);
                    if (AudioManager.e(audioManager2) != 3) break;
                    AudioManager.c(audioManager2);
                    return;
                }
                if (audioManager2 != null) {
                    AudioManager.c(audioManager, 4);
                    return;
                }
                AudioManager.c(audioManager, 0);
                return;
            }
            case 8: {
                if (audioManager2 != null && !GameCanvas.a(audioManager.a.a, audioManager.a.b, audioManager2.a.a, audioManager2.a.b, null, false, 197)) {
                    audioManager.g[15] = audioManager.g[15] + (int)GameCanvas.d;
                    if (audioManager.g[15] < 1000) break;
                    GameCanvas.b(21);
                    audioManager.g[15] = 0;
                    int n4 = GameCanvas.h(audioManager.g[0], 14);
                    if (audioManager2 == null) break;
                    if (audioManager2 == GameCanvas.c) {
                        if (a == 100) {
                            AudioManager.b(GameCanvas.c, n4);
                            return;
                        }
                        AudioManager.c(audioManager, 4);
                        return;
                    }
                    if (AudioManager.e(audioManager2) == 0) break;
                    AudioManager.o(audioManager2, n4);
                    if (AudioManager.m(audioManager2) < 555) {
                        AudioManager.c(audioManager, 9);
                        AudioManager.d(audioManager2, 101);
                        return;
                    }
                    AudioManager.c(audioManager, 0);
                    return;
                }
                AudioManager.i(audioManager);
                AudioManager.c(audioManager, 4);
                return;
            }
            case 12: {
                if (!audioManager.a.a()) break;
                AudioManager.c(audioManager, 13);
                if (audioManager2 == null || (!AudioManager.b(audioManager, n) || audioManager2 != GameCanvas.c || a == 100 || a == 14 || a == 17) && (audioManager2 == GameCanvas.c || AudioManager.m(audioManager2) >= 555)) break;
                int n5 = GameCanvas.h(audioManager.g[0], 14) << 1;
                if (audioManager2 == GameCanvas.c) {
                    AudioManager.a(audioManager.g[13], audioManager.g[14]);
                    AudioManager.b(audioManager2, n5);
                    return;
                }
                AudioManager.o(audioManager2, n5);
                return;
            }
            case 13: {
                if (!audioManager.a.a()) break;
                AudioManager.c(audioManager, 4);
                return;
            }
            case 24: {
                if (!audioManager.a.a()) break;
                audioManager.g[16] = audioManager.g[16] | 0x100;
                AudioManager.c(audioManager, 4);
                return;
            }
            case 26: 
            case 27: 
            case 28: 
            case 108: {
                AudioManager.p(audioManager);
            }
        }
    }

    public static void o(AudioManager audioManager) {
        block2: {
            block6: {
                int n;
                AudioManager audioManager2;
                block4: {
                    block5: {
                        block3: {
                            int n2 = GameCanvas.h(audioManager.g[0], 10);
                            if (n2 != 0) break block2;
                            AudioManager.i(audioManager);
                            if (audioManager.g[12] != 0) break block3;
                            audioManager.g[13] = 0;
                            audioManager.g[14] = -16384;
                            audioManager2 = audioManager;
                            n = 26;
                            break block4;
                        }
                        if (audioManager.g[12] != 14) break block5;
                        audioManager2 = audioManager;
                        n = 100;
                        break block4;
                    }
                    if (audioManager.g[12] >= 100) break block6;
                    audioManager2 = audioManager;
                    n = 28;
                }
                AudioManager.c(audioManager2, n);
            }
            AudioManager.c(audioManager);
            GameCanvas.a(audioManager, 0, false);
            return;
        }
        audioManager.g[10] = 0;
    }

    public static void p(AudioManager audioManager) {
        block17: {
            int n;
            AudioManager audioManager2;
            block19: {
                int n2;
                block18: {
                    n2 = GameCanvas.h(audioManager.g[0], 10);
                    if (n2 != 0) break block17;
                    if (audioManager.g[10] != 0) break block18;
                    if (audioManager.g[12] != 108) break block17;
                    audioManager2 = audioManager;
                    n = 100;
                    break block19;
                }
                if (audioManager.g[10] != 6) break block17;
                switch (audioManager.g[12]) {
                    case 26: {
                        i = true;
                        if (audioManager.a.a()) {
                            AudioManager.c(audioManager, 27);
                            AudioManager.c(audioManager);
                            h = true;
                            return;
                        }
                        break block17;
                    }
                    case 27: {
                        return;
                    }
                    case 28: {
                        if (h || !i && audioManager.a.a()) {
                            AudioManager.c(audioManager, 27);
                            AudioManager.c(audioManager);
                            return;
                        }
                        break block17;
                    }
                    case 108: {
                        int n3 = audioManager.a.a.a(audioManager.a.d);
                        if (audioManager.a.e >= n3 / 4) {
                            AudioManager.c(audioManager, 100);
                            audioManager.g[10] = 0;
                            AudioManager.c(audioManager);
                            return;
                        }
                        break block17;
                    }
                    case 14: {
                        AudioManager.c(audioManager);
                        if (audioManager.a.a()) {
                            int n4 = AudioManager.b(audioManager, false);
                            n2 = GameCanvas.h(audioManager.g[0], 10);
                            if (n2 == 0 && n4 != 100 && n4 != 101) {
                                AudioManager.c(audioManager, 27);
                                AudioManager.c(audioManager);
                                return;
                            }
                            if (n2 == 0) {
                                AudioManager.c(audioManager, 108);
                                AudioManager.c(audioManager);
                                return;
                            }
                            AudioManager.c(audioManager, n4);
                            audioManager.g[10] = 0;
                            AudioManager.c(audioManager);
                            return;
                        }
                        break block17;
                    }
                    case -1: 
                    case 100: 
                    case 101: {
                        audioManager.g[10] = 0;
                        return;
                    }
                    default: {
                        audioManager2 = audioManager;
                        n = 26;
                    }
                }
            }
            AudioManager.c(audioManager2, n);
        }
    }

    private static boolean u(AudioManager audioManager) {
        return audioManager.g[12] == 8 && AudioManager.a(audioManager) != null;
    }

    /*
     * Unable to fully structure code
     */
    public static int[] b(AudioManager var0, boolean var1_1, boolean var2_2) {
        block7: {
            var3_3 = GameCanvas.s;
            var4_4 = AudioManager.a(var0);
            if (var4_4 == null) break block7;
            var6_5 = var4_4.a.a - (GameCanvas.T >> 14 << 14);
            var7_6 = var4_4.a.b - ((GameCanvas.U >> 14) - 0 << 14);
            var8_7 = 262144;
            var9_8 = 0;
            if (AudioManager.s != 0 && var2_2) {
                var9_8 = GameCanvas.a() % 3 - 1 << 14;
            }
            switch (AudioManager.b(var0)) {
                case 0: {
                    var3_3[0] = var6_5;
                    v0 = var3_3;
                    v1 = 1;
                    v2 = var7_6 + (var8_7 >> 1) + var9_8;
                    ** GOTO lbl36
                }
                case 1: {
                    var3_3[0] = var6_5;
                    v0 = var3_3;
                    v1 = 1;
                    v2 = var7_6 - var8_7 + var9_8;
                    ** GOTO lbl36
                }
                case 2: {
                    v3 = var3_3;
                    v4 = 0;
                    v5 = var6_5 + var8_7;
                    ** GOTO lbl32
                }
                case 3: {
                    v3 = var3_3;
                    v4 = 0;
                    v5 = var6_5 - var8_7;
lbl32:
                    // 2 sources

                    v3[v4] = v5 + var9_8;
                    v0 = var3_3;
                    v1 = 1;
                    v2 = var7_6;
lbl36:
                    // 3 sources

                    v0[v1] = v2;
                }
            }
        }
        return var3_3;
    }

    private static void d(AudioManager audioManager, boolean bl) {
        if (AudioManager.u(audioManager)) {
            int[] nArray = AudioManager.b(audioManager, bl, true);
            audioManager.a.a = nArray[0];
            audioManager.a.b = nArray[1];
        }
    }

    private static int a(AudioManager audioManager, AudioManager audioManager2, int n) {
        int n2;
        if (audioManager2 != null && audioManager != null && (n2 = GameCanvas.h(audioManager.g[0], 10)) == 1) {
            int n3 = GameCanvas.h(audioManager.g[0], 33);
            if (n3 == 3) {
                n = GameCanvas.a(audioManager.a.a(), audioManager.a.b() + 10, audioManager2.a.a(), audioManager2.a.b());
            }
            for (int i = 0; i < n3; ++i) {
                int n4;
                int n5 = GameCanvas.d(audioManager.g[0], i, 9);
                if (n5 == 0 || (n4 = GameCanvas.a(audioManager.a.a() + n5, audioManager.a.b(), audioManager2.a.a(), audioManager2.a.b())) >= n) continue;
                n = n4;
            }
        }
        return n;
    }

    /*
     * Unable to fully structure code
     */
    private static void j(AudioManager var0, int var1_1) {
        var2_2 = GameCanvas.h(var0.g[0], 12);
        var4_3 = GameCanvas.h(var0.g[0], 17);
        var5_4 = AudioManager.a(var0);
        block0 : switch (var0.g[12]) {
            case 12: {
                if (AudioManager.g - var0.g[27] <= 1500 || var1_1 > 14400) ** GOTO lbl11
                var0.g[28] = 0;
                v0 = var0;
                v1 = 13;
                ** GOTO lbl48
lbl11:
                // 1 sources

                if (AudioManager.g - var0.g[27] >= 0 && var1_1 <= 14400) break;
                AudioManager.c(var0, 13);
                var0.g[28] = 1;
                return;
            }
            case 13: {
                switch (var0.g[28]) {
                    case 0: {
                        if (!var0.a.a() || var5_4 == null) break;
                        GameCanvas.a(var0.a.a, GameCanvas.h(var0.g[0], 25), var0.a.a, var0.a.b, var5_4.a.a, var5_4.a.b, 50, 200);
                        var0.g[28] = var0.g[28] + 1;
                        break block0;
                    }
                    case 1: {
                        if (!var0.a.a()) break;
                        AudioManager.c(var0, 4);
                    }
                }
                return;
            }
            case 7: {
                AudioManager.d(var0, var2_2);
                if (!var0.a.a()) break;
                if (var1_1 > var4_3 || AudioManager.a == 100) ** GOTO lbl38
                AudioManager.f(var0, var5_4.a.a, var5_4.a.b, 0);
                var0.g[15] = 0;
                if (var5_4 == GameCanvas.c) {
                    AudioManager.b(GameCanvas.c, GameCanvas.h(var0.g[0], 14));
                }
                v0 = var0;
                v1 = 8;
                ** GOTO lbl48
lbl38:
                // 1 sources

                v0 = var0;
                ** GOTO lbl47
            }
            case 8: {
                if (!var0.a.a()) break;
                ** GOTO lbl46
            }
            case 25: {
                if (!var0.a.a()) break;
                var0.g[29] = 0;
lbl46:
                // 2 sources

                v0 = var0;
lbl47:
                // 2 sources

                v1 = 4;
lbl48:
                // 3 sources

                AudioManager.c(v0, v1);
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    private static void O(AudioManager var0) {
        switch (var0.g[12]) {
            case 7: {
                v0 = var0.a;
                v1 = v0;
                v2 = v0.b;
                v3 = GameCanvas.k[var0.g[28]].g;
                v4 = 10;
                ** GOTO lbl22
            }
            case 8: {
                var1_1 = GameCanvas.c.a.a.a(0, var0.g[27]);
                var0.a.a += var1_1[0] + (var1_1[2] >> 1) << 14;
                v5 = var0.a;
                v1 = v5;
                v6 = v5.b + (var1_1[1] + (var1_1[3] >> 1) << 14);
                ** GOTO lbl23
            }
            case 9: {
                v7 = var0.a;
                v1 = v7;
                v2 = v7.b;
                v3 = var0.g;
                v4 = 28;
lbl22:
                // 2 sources

                v6 = v2 - v3[v4];
lbl23:
                // 2 sources

                v1.b = v6;
            }
        }
    }

    private static void P(AudioManager audioManager) {
        int n = 0;
        do {
            audioManager.a.a = GameCanvas.c.a.a + (GameCanvas.a() % 131072 - 65536);
            audioManager.a.b = GameCanvas.c.a.b + (GameCanvas.a() % 131072 - 65536);
        } while (AudioManager.a(audioManager.g[0], audioManager.a.a(), audioManager.a.b()) && ++n < 10);
        int n2 = GameCanvas.a(GameCanvas.c.a.a, 0);
        if (audioManager.g[27] >= 0 && audioManager.g[27] < n2) {
            byte[] byArray = GameCanvas.c.a.a.a(0, audioManager.g[27]);
            audioManager.g[28] = GameCanvas.d(byArray[1] + (byArray[3] >> 1) << 14);
        }
    }

    private static void Q(AudioManager audioManager) {
        AudioManager audioManager2;
        int n = GameCanvas.a(GameCanvas.c.a.a, 0);
        if (t < n) {
            audioManager.g[27] = t++;
            audioManager.g[28] = GameCanvas.a(audioManager.a.a, audioManager.a.b, GameCanvas.c.a.a, GameCanvas.c.a.b, 50, 200);
            if (audioManager.g[28] >= 0) {
                audioManager.g[15] = 0;
                return;
            }
            audioManager2 = audioManager;
        } else {
            audioManager2 = audioManager;
        }
        AudioManager.c(audioManager2, 4);
    }

    private static void R(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 12);
        switch (audioManager.g[12]) {
            case 9: {
                audioManager.g[15] = audioManager.g[15] + (int)GameCanvas.d;
                if (audioManager.g[28] > 0) {
                    audioManager.g[28] = audioManager.g[28] - 0x320000 * (int)GameCanvas.d / 1000;
                    AudioManager.d(audioManager, -(n << 1));
                    return;
                }
                AudioManager.c(audioManager, 10);
                return;
            }
            case 10: {
                audioManager.g[15] = audioManager.g[15] + (int)GameCanvas.d;
                if (audioManager.g[15] < 3000) break;
                AudioManager.c(audioManager, 11);
                return;
            }
            case 7: {
                GameCanvas.a(audioManager, 1, false);
                audioManager.g[15] = audioManager.g[15] + (int)GameCanvas.d;
                audioManager.a.a = GameCanvas.k[audioManager.g[28]].a.a;
                audioManager.a.b = GameCanvas.k[audioManager.g[28]].a.b;
                if (GameCanvas.a(GameCanvas.c.a, audioManager.a)) {
                    if (a == 3 || a == 2 || AudioManager.e()) {
                        AudioManager.G(audioManager);
                        AudioManager.a(audioManager, 0, -1, true, 0, 0);
                        AudioManager.c(audioManager, 100);
                        if (--t >= 0) break;
                        t = 0;
                        return;
                    }
                    AudioManager.b(GameCanvas.c, GameCanvas.h(audioManager.g[0], 14));
                    AudioManager.c(audioManager, 8);
                    AudioManager.a(0, GameCanvas.c);
                    return;
                }
                if (!GameCanvas.c(audioManager.g[28])) break;
                if (--t < 0) {
                    t = 0;
                }
                AudioManager.c(audioManager, 4);
                return;
            }
            case 8: {
                b = true;
                GameCanvas.b();
                audioManager.a.a = GameCanvas.c.a.a;
                audioManager.a.b = GameCanvas.c.a.b + 2;
            }
        }
    }

    private static void S(AudioManager audioManager) {
        int[] nArray;
        int n = GameCanvas.h(audioManager.g[0], 10);
        if (n == 2 && audioManager.g[12] != 8 && audioManager.g[12] != 9 && audioManager.g[12] != 100 && audioManager.g[12] != 101 && (nArray = GameCanvas.b(audioManager.a)) != null && GameCanvas.a(nArray[0], nArray[1], nArray[2], nArray[3], GameCanvas.c.a.a(), GameCanvas.c.a.b()) && (AudioManager.d() || AudioManager.e())) {
            AudioManager.G(audioManager);
            AudioManager.a(audioManager, 0, -1, true, 0, 0);
            AudioManager.c(audioManager, 100);
        }
    }

    public static void b(AudioManager audioManager, AudioManager audioManager2) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        if ((n == 4 || n == 6) && audioManager.g[12] != 7 && audioManager.g[12] != 8 && audioManager.g[12] != 13 && audioManager.g[12] != 19 && audioManager.g[12] != 18) {
            AudioManager.f(audioManager, audioManager2.a.a, audioManager2.a.b, 0);
            AudioManager.c(audioManager, 18);
            AudioManager.c(audioManager);
        }
    }

    private static boolean v(AudioManager audioManager) {
        int n = GameCanvas.h(audioManager.g[0], 10);
        return n == 6 && audioManager.g[12] == 18;
    }

    /*
     * Unable to fully structure code
     */
    private static void k(AudioManager var0, int var1_1) {
        var2_2 = GameCanvas.h(var0.g[0], 10);
        var4_3 = AudioManager.a(var0);
        switch (var0.g[12]) {
            case 18: {
                if (!var0.a.a()) break;
                v0 = var0;
                v1 = 1;
                ** GOTO lbl102
            }
            case 12: {
                if (!var0.a.a()) break;
                if (var4_3 != null) {
                    AudioManager.f(var0, var4_3.a.a, var4_3.a.b, 0);
                    var0.g[15] = 0;
                    AudioManager.c(var0, 13);
                    var0.g[16] = var0.g[16] & -257;
                    return;
                }
                v0 = var0;
                v1 = 0;
                ** GOTO lbl102
            }
            case 13: {
                if ((var0.g[16] & 256) == 0) {
                    AudioManager.T(var0);
                }
                if (!var0.a.a()) break;
                if (var2_2 != 6) ** GOTO lbl33
                if (GameCanvas.a() % 100 >= 40) ** GOTO lbl30
                if (AudioManager.a(var0, var4_3, true)) ** GOTO lbl100
                v0 = var0;
                v1 = 20;
                ** GOTO lbl102
lbl30:
                // 1 sources

                v0 = var0;
                v1 = 19;
                ** GOTO lbl102
lbl33:
                // 1 sources

                v0 = var0;
                v1 = 19;
                ** GOTO lbl102
            }
            case 19: {
                if (!var0.a.a() || var4_3 == null) break;
                AudioManager.f(var0, var4_3.a.a, var4_3.a.b, 0);
                if (AudioManager.a(var0, var4_3, true)) ** GOTO lbl100
                v0 = var0;
                v1 = 20;
                ** GOTO lbl102
            }
            case 20: {
                if (var4_3 == null) break;
                var5_4 = GameCanvas.h(var0.g[0], 12);
                if (!AudioManager.d(var0, var5_4)) ** GOTO lbl100
                if (!AudioManager.a(var0, var4_3, true)) break;
                AudioManager.f(var0, var4_3.a.a, var4_3.a.b, 0);
                v0 = var0;
                v1 = 12;
                ** GOTO lbl102
            }
            case 7: {
                var5_5 = GameCanvas.h(var0.g[0], 12);
                AudioManager.d(var0, var5_5);
                if (var1_1 > 2500) ** GOTO lbl78
                AudioManager.f(var0, var4_3.a.a, var4_3.a.b, 0);
                var0.g[15] = 0;
                if (var4_3 == GameCanvas.c) {
                    if (AudioManager.a != 16) {
                        AudioManager.b(GameCanvas.c, GameCanvas.h(var0.g[0], 14));
                        if (AudioManager.a == 11 || AudioManager.a == 10) {
                            GameCanvas.e(false);
                        }
                        AudioManager.a(var0.g[13], var0.g[14]);
                        AudioManager.b(var4_3, var0, true);
                        v0 = var0;
                        v1 = 8;
                    } else {
                        v0 = var0;
                        v1 = 19;
                    }
                } else {
                    AudioManager.e(var4_3, GameCanvas.h(var0.g[0], 14));
                    AudioManager.g(var4_3, var0.a.a, var0.a.b, 1);
                    AudioManager.d(var4_3, 102);
                    v0 = var0;
                    v1 = 8;
                }
                ** GOTO lbl102
lbl78:
                // 1 sources

                v0 = var0;
                ** GOTO lbl101
            }
            case 8: {
                if (!var0.a.a()) break;
                ** GOTO lbl100
            }
            case 21: {
                if (!var0.a.a()) break;
                v0 = var0;
                v1 = 22;
                ** GOTO lbl102
            }
            case 22: {
                if (var4_3 != null && var0.g[28] < 6 && GameCanvas.bv < 5) {
                    var0.g[28] = var0.g[28] + 1;
                    var5_6 = GameCanvas.a() % 60 - 30;
                    var6_7 = GameCanvas.a() % 60 - 30;
                    var5_6 = var0.a.a > var4_3.a.a ? (var5_6 -= 60) : (var5_6 += 60);
                    var6_7 = var0.a.b > var4_3.a.b ? (var6_7 -= 60) : (var6_7 += 60);
                    var7_8 = AudioManager.a(var0, true, false);
                    GameCanvas.b(var0.a.a, 47, var0.a.a, var0.a.b, var4_3.a.a + (var5_6 << 14), var4_3.a.b + (var6_7 << 14), var7_8[0], var7_8[1], 100, 200, var0.g[0] + 1, var0.g[2], var0.g[1]);
                }
                if (!var0.a.a()) break;
                var0.g[28] = 0;
                var0.g[29] = 0;
lbl100:
                // 5 sources

                v0 = var0;
lbl101:
                // 2 sources

                v1 = 4;
lbl102:
                // 12 sources

                AudioManager.c(v0, v1);
            }
        }
    }

    private static void T(AudioManager audioManager) {
        GameCanvas.a(audioManager, 1, true);
        int[] nArray = GameCanvas.s;
        int n = (int)GameCanvas.d;
        int n2 = audioManager.a.a;
        int n3 = audioManager.a.b;
        int n4 = n2;
        int n5 = n3;
        int n6 = audioManager.g[13] * n * 150 / 1000;
        int n7 = audioManager.g[14] * n * 150 / 1000;
        audioManager.a.a += n6;
        audioManager.a.b += n7;
        AudioManager audioManager2 = null;
        boolean bl = false;
        boolean bl2 = false;
        do {
            AudioManager audioManager3;
            int n8;
            bl = GameCanvas.a(n2, n3, n2 + n6, n3 + n7, nArray, false, 13, 2, 136);
            if (audioManager2 != null) {
                if (audioManager2.L < 9) {
                    GameCanvas.a(audioManager2, 0, true);
                } else {
                    GameCanvas.a(audioManager2, false);
                }
                audioManager2 = null;
            }
            if (!bl) continue;
            int n9 = GameCanvas.b(nArray[0] >> 14);
            if (GameCanvas.b(n9, n8 = GameCanvas.b(nArray[1] >> 14), 140)) {
                n2 = nArray[0];
                n3 = nArray[1];
                audioManager2 = GameCanvas.a(n9, n8);
                if (audioManager2 != null) {
                    if (audioManager2.L < 9) {
                        switch (audioManager2.L) {
                            case 3: {
                                int n10 = GameCanvas.h(audioManager2.g[0], 10);
                                if (n10 != 0 && n10 != 3 && n10 != 7 && n10 != 2) break;
                                AudioManager.l(audioManager2, n10);
                                break;
                            }
                            case 1: {
                                break;
                            }
                            case 0: {
                                if (audioManager2 != GameCanvas.c || a == 16) break;
                                AudioManager.b(audioManager2, GameCanvas.h(audioManager.g[0], 14));
                                if (a == 11 || a == 10) {
                                    GameCanvas.e(false);
                                }
                                AudioManager.a(audioManager.g[13], audioManager.g[14]);
                                AudioManager.b(audioManager2, audioManager, false);
                            }
                        }
                        GameCanvas.a(audioManager2, 1, true);
                        continue;
                    }
                    switch (audioManager2.L) {
                        case 9: {
                            if (AudioManager.a(audioManager2, 0)) break;
                        }
                        default: {
                            GameCanvas.d(audioManager2);
                            break;
                        }
                    }
                    continue;
                }
                audioManager3 = audioManager;
            } else {
                audioManager3 = audioManager;
            }
            audioManager3.a.a = nArray[0] - 16 * audioManager.g[13];
            audioManager.a.b = nArray[1] - 16 * audioManager.g[14];
            audioManager.g[16] = audioManager.g[16] | 0x100;
            bl = false;
            bl2 = true;
        } while (bl);
        if (bl2 && AudioManager.a(audioManager.g[0], audioManager.a.a >> 14, audioManager.a.b >> 14)) {
            audioManager.g[16] = audioManager.g[16] | 0x100;
            audioManager.a.a = n4;
            audioManager.a.b = n5;
        }
        GameCanvas.a(audioManager, 0, true);
    }

    private static void l(AudioManager audioManager, int n) {
        AudioManager.i(audioManager);
        int n2 = 0;
        if (n == 2) {
            // empty if block
        }
        n2 = AudioManager.b(audioManager, 1);
        AudioManager.a(audioManager, n2, -1, 100, true, 0, 0, -1, true);
        AudioManager.I(audioManager);
    }

    private static boolean w(AudioManager audioManager) {
        boolean bl = false;
        int n = GameCanvas.h(audioManager.g[0], 33);
        for (int i = 0; i < n && !bl; ++i) {
            int n2 = GameCanvas.d(audioManager.g[0], i, 11);
            if (n2 <= 0) continue;
            bl = true;
        }
        return bl;
    }

    private static int h(AudioManager audioManager) {
        int n = 0;
        int n2 = GameCanvas.h(audioManager.g[0], 33);
        for (int i = 0; i < n2; ++i) {
            int n3 = GameCanvas.d(audioManager.g[0], i, 11);
            if (n3 <= 0) continue;
            for (int j = 0; j < n3; ++j) {
                n += GameCanvas.d(audioManager.g[0], i, j, 0);
            }
        }
        return n;
    }

    private static int i(AudioManager audioManager) {
        int n = 0;
        int n2 = GameCanvas.h(audioManager.g[0], 33);
        for (int i = 0; i < n2; ++i) {
            int n3 = GameCanvas.d(audioManager.g[0], i, 11);
            if (n3 <= 0) continue;
            for (int j = 0; j < n3; ++j) {
                n += AudioManager.c(audioManager, i, j);
            }
        }
        return n;
    }

    private static int j(AudioManager audioManager) {
        int n = 0;
        int n2 = GameCanvas.h(audioManager.g[0], 33);
        for (int i = 0; i < n2; ++i) {
            int n3;
            int n4 = GameCanvas.d(audioManager.g[0], i, 1);
            if (n4 != -1) continue;
            int n5 = GameCanvas.d(audioManager.g[0], i, 11);
            if (n5 == 0) {
                if ((audioManager.g[16] & 0x1000) == 0) {
                    n += GameCanvas.d(audioManager.g[0], i, 0);
                    continue;
                }
                n3 = GameCanvas.d(audioManager.g[0], i, 12);
                n += GameCanvas.j(n3, 0);
                continue;
            }
            for (n3 = 0; n3 < n5; ++n3) {
                if ((audioManager.g[16] & 0x1000) == 0) {
                    n += GameCanvas.d(audioManager.g[0], i, n3, 0);
                    continue;
                }
                System.out.println("#WARNING: CObject_Enemy.ENEMY_GetTotalHealth: Mutated enemies with subparts not supported yet.");
            }
        }
        return n;
    }

    private static int k(AudioManager audioManager) {
        int n = 0;
        if (audioManager.g[12] != 100 && audioManager.g[12] != 101) {
            int n2 = GameCanvas.h(audioManager.g[0], 33);
            for (int i = 0; i < n2; ++i) {
                int n3 = GameCanvas.d(audioManager.g[0], i, 1);
                if (n3 != -1) continue;
                int n4 = AudioManager.c(audioManager, i, -1);
                n += n4;
            }
        }
        return n;
    }

    public static void b(Graphics graphics, AudioManager audioManager) {
        boolean bl = AudioManager.w(b);
        byte[] byArray = GameCanvas.a[12].a(8, 2);
        GameCanvas.a[12].a(graphics, 8, 0, 0, 0);
        GameCanvas.a(graphics, GameCanvas.a[12], 0, 0, 0, 1, 0, AudioManager.j(audioManager), AudioManager.k(audioManager) + (D >> 14), byArray[0] & 0xFF, byArray[1] & 0xFF, byArray[2] & 0xFF, byArray[3] & 0xFF, 0, 0, 64);
        if (bl) {
            byArray = GameCanvas.a[12].a(8, 3);
            GameCanvas.a(graphics, GameCanvas.a[12], 14, 0, 14, 1, 0, C, AudioManager.i(audioManager) + (D >> 14), byArray[0] & 0xFF, byArray[1] & 0xFF, byArray[2] & 0xFF, byArray[3] & 0xFF, 0, 0, 64);
        }
        byArray = GameCanvas.a[12].a(8, 0);
        GameCanvas.a(graphics, GameCanvas.b, null, 9, GameCanvas.h(audioManager.g[0], 31), 0, byArray[0] & 0xFF, byArray[1] & 0xFF, byArray[2] & 0xFF, byArray[3] & 0xFF, 0, 6);
        if (bl) {
            byArray = GameCanvas.a[12].a(8, 1);
            GameCanvas.a(graphics, GameCanvas.b, null, 9, GameCanvas.h(audioManager.g[0], 31) + 1, 0, byArray[0] & 0xFF, byArray[1] & 0xFF, byArray[2] & 0xFF, byArray[3] & 0xFF, 0, 6);
        }
    }

    public static void d(int[] nArray) {
        nArray[22] = -1;
        nArray[25] = 0;
        nArray[26] = 0;
        nArray[27] = 0;
        nArray[28] = 0;
        nArray[29] = 0;
        nArray[30] = 0;
        nArray[31] = 0;
        nArray[32] = 0;
        nArray[33] = 0;
        nArray[34] = 0;
        nArray[35] = 0;
        nArray[36] = 0;
        nArray[39] = 0;
    }

    public static int[] a(int[] nArray, int n, int n2) {
        int[] nArray2 = new int[nArray.length + 4 + 15];
        int[] nArray3 = nArray2;
        nArray2[26] = n;
        nArray3[27] = n2;
        nArray3[28] = -2;
        nArray3[29] = -2;
        System.arraycopy(nArray, 0, nArray3, 0, nArray.length);
        nArray3[30] = 0;
        nArray3[31] = 0;
        nArray3[32] = 0;
        nArray3[33] = 0;
        nArray3[34] = 0;
        nArray3[35] = 0;
        nArray3[36] = 0;
        nArray3[37] = 0;
        nArray3[38] = 0;
        nArray3[39] = 0;
        nArray3[40] = 0;
        nArray3[41] = 0;
        nArray3[43] = GameCanvas.a(nArray3[43], 65280, -1);
        nArray3[44] = 0;
        return nArray3;
    }

    private static int l(AudioManager audioManager) {
        int n = 0;
        n = audioManager.L == 1 ? audioManager.g[16] : 5;
        return n;
    }

    public static int d(AudioManager audioManager) {
        int n = 24;
        if (audioManager.L != 1) {
            n = 29;
        }
        return n;
    }

    public static int e(AudioManager audioManager) {
        int n = 0;
        if (audioManager.L == 1) {
            // empty if block
        }
        n = audioManager.g[17];
        return n;
    }

    private static void m(AudioManager audioManager, int n) {
        if (audioManager.L == 1) {
            // empty if block
        }
        audioManager.g[17] = n;
    }

    private static boolean x(AudioManager audioManager) {
        boolean bl = false;
        int n = (int)GameCanvas.d;
        int n2 = AudioManager.d(audioManager);
        int n3 = n2 + 2;
        audioManager.g[n3] = audioManager.g[n3] - n;
        if (audioManager.g[n2 + 2] <= 0) {
            bl = true;
            audioManager.g[n2 + 2] = 1000 + GameCanvas.a() % 500;
            int n4 = AudioManager.e(audioManager);
            if (audioManager.L == 0 && (n4 == 2 || n4 == 4 || n4 == 5)) {
                int n5 = n2 + 2;
                audioManager.g[n5] = audioManager.g[n5] >> 1;
            }
        }
        return bl;
    }

    public static AudioManager b(AudioManager audioManager) {
        int n = AudioManager.d(audioManager);
        if (audioManager.g[n + 15] != 0) {
            int n2 = GameCanvas.a(audioManager.g[n + 15], 255);
            int n3 = GameCanvas.a(audioManager.g[n + 15], 0xFFFF00);
            AudioManager audioManager2 = GameCanvas.b(n2, n3);
            return audioManager2;
        }
        return GameCanvas.c;
    }

    public static void c(AudioManager audioManager, AudioManager audioManager2) {
        int n = AudioManager.d(audioManager);
        audioManager.g[n + 15] = 0;
        if (audioManager2 != null) {
            int n2 = GameCanvas.b(audioManager2.a.a());
            int n3 = GameCanvas.b(audioManager2.a.b());
            int n4 = GameCanvas.d(n2, n3);
            int n5 = GameCanvas.e(n2, n3);
            audioManager.g[n + 15] = GameCanvas.a(audioManager.g[n + 15], 255, n4);
            audioManager.g[n + 15] = GameCanvas.a(audioManager.g[n + 15], 0xFFFF00, n5);
            if (audioManager.g[n + 15] == 0) {
                System.out.println("WARNING: NPC_SetFollowTargetObject: Cannot get target from collision map");
            }
            AudioManager.d(audioManager, 0);
            AudioManager.m(audioManager, 2);
        }
    }

    public static void a(AudioManager audioManager, AudioManager audioManager2, boolean bl) {
        AudioManager.g(audioManager, audioManager2.a.a, audioManager2.a.b, 0);
        AudioManager.d(audioManager, 0);
        if (audioManager.L == 1) {
            int n = 0 + AudioManager.a(audioManager, bl);
            audioManager.a.b(n);
            return;
        }
        AudioManager.ad(audioManager);
    }

    public static void d(AudioManager audioManager, int n) {
        int n2 = AudioManager.d(audioManager);
        if (audioManager.g[n2 + 1] != n) {
            int n3 = n2 + 14;
            audioManager.g[n3] = audioManager.g[n3] & 0xFFFFFFFE;
        }
        audioManager.g[n2 + 1] = n;
        switch (audioManager.g[n2 + 1]) {
            case 7: {
                AudioManager.Z(audioManager);
                return;
            }
            case 8: {
                AudioManager.Y(audioManager);
            }
        }
    }

    private static void n(AudioManager audioManager, int n) {
        int n2 = AudioManager.d(audioManager);
        int n3 = n2 + 14;
        audioManager.g[n3] = audioManager.g[n3] | n;
    }

    private static int m(AudioManager audioManager) {
        int n = AudioManager.d(audioManager);
        return audioManager.g[n + 1];
    }

    /*
     * Unable to fully structure code
     */
    private static void U(AudioManager var0) {
        block19: {
            var1_1 = AudioManager.d(var0);
            if (var0.g[var1_1 + 1] < 100 && AudioManager.x(var0)) {
                AudioManager.W(var0);
                AudioManager.X(var0);
                var2_2 = 0;
                var2_2 = AudioManager.y(var0) != false ? AudioManager.o(var0) : AudioManager.n(var0);
                AudioManager.d(var0, var2_2);
            }
            if (var0.g[var1_1 + 1] < 4) break block19;
            var2_3 = null;
            var3_4 = GameCanvas.a(var0, AudioManager.b(var0));
            switch (var0.g[var1_1 + 1]) {
                case 4: {
                    if (var3_4 <= 2560) break;
                    v0 = var0;
                    v1 = 5;
                    ** GOTO lbl93
                }
                case 5: {
                    if (var3_4 <= 2048) {
                        v0 = var0;
                        v1 = 4;
                    } else {
                        if (var3_4 < 6656) break;
                        v0 = var0;
                        v1 = 6;
                    }
                    ** GOTO lbl93
                }
                case 6: {
                    if (var3_4 >= 6144) break;
                    v0 = var0;
                    v1 = 5;
                    ** GOTO lbl93
                }
                case 101: {
                    if (!var0.a.a()) break;
                    v0 = var0;
                    v1 = 0;
                    ** GOTO lbl93
                }
                case 8: {
                    if (AudioManager.z(var0)) break;
                    v0 = var0;
                    v1 = 7;
                    ** GOTO lbl93
                }
                case 7: {
                    if (!AudioManager.z(var0)) break;
                    v0 = var0;
                    v1 = 8;
                    ** GOTO lbl93
                }
                case 102: {
                    if (!var0.a.a()) break;
                    if (!AudioManager.j(var0)) ** GOTO lbl52
                    v0 = var0;
                    v1 = 104;
                    ** GOTO lbl93
lbl52:
                    // 1 sources

                    v0 = var0;
                    ** GOTO lbl92
                }
                case 104: {
                    if (!var0.a.a()) break;
                    v0 = var0;
                    v1 = 0;
                    ** GOTO lbl93
                }
                case 555: {
                    if (!var0.a.a()) break;
                    AudioManager.d(var0, 556);
                    var5_5 = GameCanvas.a(var0.a.a(), var0.a.b() + 1, true, AudioManager.a(var0, false), false);
                    var0.g[var1_1 + 14] = GameCanvas.a(var0.g[var1_1 + 14], 0xFF0000, var5_5);
                    return;
                }
                case 103: {
                    if (!var0.a.a()) break;
                    v2 = var1_1 + 13;
                    var0.g[v2] = var0.g[v2] + 1;
                    if (GameCanvas.l(var0.g[28], 2) == 1 && var0.g[var1_1 + 13] < GameCanvas.l(var0.g[28], 13)) {
                        v3 = var0.g;
                        v4 = var1_1 + 2;
                        v5 = 0;
                    } else {
                        var0.g[var1_1 + 13] = 0;
                        v3 = var0.g;
                        v4 = var1_1 + 2;
                        v5 = 5000;
                    }
                    v3[v4] = v5;
                    v0 = var0;
                    v1 = 9;
                    ** GOTO lbl93
                }
                case 556: {
                    var4_6 = GameCanvas.a(var0.g[var1_1 + 14], 0xFF0000);
                    if (!GameCanvas.g[var4_6].a.a()) break;
                    AudioManager.j = true;
                    GameCanvas.a(var0.a.a(), var0.a.b() + 1, false, AudioManager.a(var0, false), false);
                    return;
                }
                case 666: {
                    if (!var0.a.a()) break;
                    v0 = var0;
lbl92:
                    // 2 sources

                    v1 = 667;
lbl93:
                    // 11 sources

                    AudioManager.d(v0, v1);
                }
            }
        }
    }

    private static int n(AudioManager audioManager) {
        int n = AudioManager.d(audioManager);
        int n2 = AudioManager.l(audioManager);
        int n3 = 0;
        if ((audioManager.g[n + 14] & 2) == 0 || AudioManager.e(audioManager) == 1) {
            switch (n2) {
                case 5: {
                    int n4 = 80;
                    break;
                }
                case 4: {
                    int n4 = 60;
                    break;
                }
                case 3: {
                    int n4 = 40;
                    break;
                }
                case 2: {
                    int n4 = n3 = 20;
                }
            }
            if (GameCanvas.a() % 100 < n3) {
                AudioManager.b(audioManager, GameCanvas.a() % 8, true);
                return 1;
            }
            return 0;
        }
        return 4;
    }

    private static void V(AudioManager audioManager) {
        if (audioManager != null && audioManager.L == 0) {
            int n = audioManager.g[28];
            if (AudioManager.n(audioManager)) {
                int n2 = GameCanvas.g(audioManager.g[4]);
                audioManager.g[28] = -2;
                for (int i = 0; i < GameCanvas.c[n2].length; ++i) {
                    int n3;
                    if (GameCanvas.c[n2][i] == -1 || (n3 = GameCanvas.n(GameCanvas.c[n2][i])) == -1 || n3 == n || AudioManager.a(GameCanvas.l(n3, 18))) continue;
                    audioManager.g[28] = n3;
                    return;
                }
            }
        }
    }

    private static int o(AudioManager audioManager) {
        int n = AudioManager.d(audioManager);
        int n2 = AudioManager.e(audioManager);
        if ((audioManager.g[n + 14] & 2) == 0 || n2 == 1) {
            int[] nArray = AudioManager.c(audioManager);
            int n3 = nArray[0];
            boolean bl = false;
            AudioManager.b(audioManager, n3, true);
            return 3;
        }
        if (audioManager.L == 0 && (n2 == 4 || n2 == 5)) {
            AudioManager.V(audioManager);
            int n4 = audioManager.g[28];
            if (n4 >= 0 && (a != null || c != null)) {
                int n5 = GameCanvas.a(audioManager, AudioManager.b(audioManager));
                AudioManager audioManager2 = c;
                int n6 = J;
                if (a != null) {
                    audioManager2 = a;
                    n6 = GameCanvas.a(audioManager, a);
                }
                AudioManager.g(audioManager, audioManager2.a.a, audioManager2.a.b, 0);
                int n7 = GameCanvas.l(n4, 11);
                if (n2 == 4 && n5 > 2560) {
                    return 6;
                }
                if (n6 > n7) {
                    if (n2 == 4) {
                        if (a == null) {
                            return 9;
                        }
                        return 6;
                    }
                    AudioManager.g(audioManager, audioManager2.a.a, audioManager2.a.b, 0);
                    return 3;
                }
                GameCanvas.a(audioManager2, audioManager, n4);
                return 103;
            }
        }
        return 8;
    }

    private static boolean y(AudioManager audioManager) {
        int n = AudioManager.d(audioManager);
        for (int i = 0; i < 8; ++i) {
            if (audioManager.g[n + 3 + i] <= 0) continue;
            return true;
        }
        return false;
    }

    private static void W(AudioManager audioManager) {
        int n = AudioManager.d(audioManager);
        for (int i = 0; i < 8; ++i) {
            int n2;
            int n3;
            int[] nArray;
            if (audioManager.g[n + 3 + i] <= 1) {
                nArray = audioManager.g;
                n3 = n + 3 + i;
                n2 = 0;
            } else {
                nArray = audioManager.g;
                n3 = n + 3 + i;
                n2 = audioManager.g[n + 3 + i] >> 1;
            }
            nArray[n3] = n2;
        }
    }

    private static void X(AudioManager audioManager) {
        J = -1;
        c = null;
        int n = AudioManager.d(audioManager);
        AudioManager audioManager2 = AudioManager.b(audioManager);
        if (audioManager.L == 1) {
            int n2 = n + 14;
            audioManager.g[n2] = audioManager.g[n2] & 0xFFFFFFFD;
        }
        int n3 = (audioManager.a.a >> 14) / 16;
        int n4 = (audioManager.a.b >> 14) / 16;
        for (int i = n3 - 20; i <= n3 + 20; ++i) {
            for (int j = n4 - 20; j <= n4 + 20; ++j) {
                int n5;
                AudioManager audioManager3;
                if (i < 0 || j < 0 || (audioManager3 = GameCanvas.a(i, j)) == null) continue;
                int n6 = audioManager.a.a;
                int n7 = audioManager.a.b;
                int n8 = audioManager3.a.a;
                int n9 = audioManager3.a.b;
                int n10 = 0;
                switch (audioManager3.L) {
                    case 3: {
                        if (audioManager3.g[10] == 3 || audioManager3.g[10] == 6 || audioManager3.g[10] == 4 || audioManager3.g[10] == 5 || audioManager3.g[12] >= 100) break;
                        n10 = 10;
                        if (audioManager.L != 0) break;
                        n5 = GameCanvas.a(audioManager, audioManager3);
                        if (J != -1 && J <= n5 || GameCanvas.a(n6, n7, n8, n9, null, false, 1)) break;
                        J = n5;
                        c = audioManager3;
                        break;
                    }
                    case 0: 
                    case 1: {
                        if (audioManager3 != audioManager2) break;
                        int n11 = n + 14;
                        audioManager.g[n11] = audioManager.g[n11] | 2;
                    }
                }
                if (n10 == 0) continue;
                n5 = GameCanvas.b(n6, n7, n8, n9);
                for (int k = 0; k < 8; ++k) {
                    if (!GameCanvas.c(k, n5)) continue;
                    int n12 = n + 3 + k;
                    audioManager.g[n12] = audioManager.g[n12] + n10;
                    if (n10 <= 0) continue;
                    AudioManager.c(audioManager, k, n10);
                }
            }
        }
        AudioManager.aa(audioManager);
    }

    private static boolean z(AudioManager audioManager) {
        int[] nArray = AudioManager.b(audioManager);
        return GameCanvas.b(nArray[0], nArray[1], 13) ? GameCanvas.a(nArray[0] - 2, nArray[1] - 2, nArray[0] + 2, nArray[1] + 2, GameCanvas.b(audioManager.a.a()), GameCanvas.b(audioManager.a.b())) : nArray[0] == GameCanvas.b(audioManager.a.a()) && nArray[1] == GameCanvas.b(audioManager.a.b());
    }

    private static void Y(AudioManager audioManager) {
        AudioManager audioManager2 = AudioManager.b(audioManager);
        int n = 0;
        int n2 = 0x5A0000;
        int n3 = (audioManager2.a.a >> 14) / 16;
        int n4 = (audioManager2.a.b >> 14) / 16;
        boolean bl = false;
        for (int i = n3 - 20; i <= n3 + 20; ++i) {
            for (int j = n4 - 20; j <= n4 + 20; ++j) {
                AudioManager audioManager3 = GameCanvas.a(i, j);
                if (audioManager3 == null || audioManager3.L != 3) continue;
                bl = true;
                int n5 = GameCanvas.b(audioManager2.a.a, audioManager2.a.b, audioManager3.a.a, audioManager3.a.b);
                if (n5 > n) {
                    n = n5;
                }
                if (n5 >= n2) continue;
                n2 = n5;
            }
        }
        if (bl) {
            H = -(n + n2 >> 1 >> 14);
            if (n - n2 >> 14 < 225) {
                H += 180;
                return;
            }
        } else {
            I = 0;
            H = 0;
            AudioManager.e[0] = GameCanvas.b(audioManager2.a.a());
            AudioManager.e[1] = GameCanvas.b(audioManager2.a.b());
        }
    }

    private static void Z(AudioManager audioManager) {
        int[] nArray = AudioManager.b(audioManager);
        int n = GameCanvas.c(nArray[0]) + 8 << 14;
        int n2 = GameCanvas.c(nArray[1]) + 8 << 14;
        AudioManager.g(audioManager, n, n2, 0);
    }

    private static int[] b(AudioManager audioManager) {
        int[] nArray = e;
        AudioManager audioManager2 = AudioManager.b(audioManager);
        if (H != I) {
            I = H;
            int n = H;
            int n2 = 786432;
            int n3 = GameCanvas.b(audioManager2.a.a, n2, n);
            int n4 = GameCanvas.c(audioManager2.a.b, n2, n);
            nArray[0] = GameCanvas.b(n3 >> 14);
            nArray[1] = GameCanvas.b(n4 >> 14);
        }
        return nArray;
    }

    private static void aa(AudioManager audioManager) {
        GameCanvas.a(audioManager, 1, true);
        int n = AudioManager.d(audioManager);
        int n2 = audioManager.a.a;
        int n3 = audioManager.a.b;
        int n4 = n + 11;
        int n5 = n + 12;
        int n6 = audioManager.g[n4];
        int n7 = audioManager.g[n5];
        int n8 = 16;
        if (audioManager.L == 0) {
            n8 = 32;
        }
        for (int i = 0; i < 8; ++i) {
            AudioManager.b(audioManager, i, false);
            n4 = n + 11;
            n5 = n + 12;
            int n9 = n2 + 320 * audioManager.g[n4];
            int n10 = n3 + 320 * audioManager.g[n5];
            int[] nArray = GameCanvas.s;
            if ((audioManager.g[n + 14] & 4) != 0) {
                GameCanvas.a(n2, n3, n9, n10, nArray, false, 13, n8, 0);
            } else {
                GameCanvas.a(n2, n3, n9, n10, nArray, false, 13);
            }
            int n11 = GameCanvas.a(n2 >> 14, n3 >> 14, nArray[0] >> 14, nArray[1] >> 14);
            int n12 = 102400;
            int n13 = 10 * n11 / n12;
            int n14 = n + 3 + i;
            audioManager.g[n14] = audioManager.g[n14] - n13;
        }
        GameCanvas.a(audioManager, 0, true);
        audioManager.g[n4] = n6;
        audioManager.g[n5] = n7;
    }

    private static void c(AudioManager audioManager, int n, int n2) {
        int n3 = AudioManager.d(audioManager);
        int n4 = n3 + 3 + n - 1;
        if (n == 0) {
            n4 = n3 + 10;
        }
        int n5 = n4;
        audioManager.g[n5] = audioManager.g[n5] + (n2 >> 1);
        n4 = n3 + 3 + n + 1;
        if (n == 7) {
            n4 = n3 + 3;
        }
        int n6 = n4;
        audioManager.g[n6] = audioManager.g[n6] + (n2 >> 1);
    }

    private static int[] c(AudioManager audioManager) {
        int n = 0;
        int n2 = 1000000;
        int[] nArray = GameCanvas.s;
        int n3 = AudioManager.d(audioManager);
        for (int i = 0; i < 8; ++i) {
            int n4 = audioManager.g[n3 + 3 + i];
            if (n4 >= n2) continue;
            nArray[0] = n = i;
            nArray[1] = n2 = n4;
        }
        return nArray;
    }

    private static boolean A(AudioManager audioManager) {
        return audioManager.L == 1 && AudioManager.b(audioManager) == GameCanvas.c && (audioManager.g[17] == 2 || audioManager.g[17] == 4);
    }

    private static boolean B(AudioManager audioManager) {
        int n = AudioManager.d(audioManager);
        for (int i = 0; i < 8; ++i) {
            int n2 = audioManager.g[n + 3 + i];
            if (n2 <= 0) continue;
            return true;
        }
        return false;
    }

    private static void b(AudioManager audioManager, int n, boolean bl) {
        int n2;
        int n3;
        block9: {
            int n4;
            block8: {
                block7: {
                    int n5;
                    n3 = audioManager.a.a;
                    n2 = audioManager.a.b;
                    if (n == 7 || n == 0 || n == 1) {
                        n5 = n3 + 819200;
                    } else if (n == 5 || n == 4 || n == 3) {
                        n5 = n3 = n3 - 819200;
                    }
                    if (n != 3 && n != 2 && n != 1) break block7;
                    n4 = n2 - 819200;
                    break block8;
                }
                if (n != 5 && n != 6 && n != 7) break block9;
                n4 = n2 + 819200;
            }
            n2 = n4;
        }
        int[] nArray = GameCanvas.a(audioManager.a.a, audioManager.a.b, n3, n2, false);
        int n6 = AudioManager.d(audioManager);
        audioManager.g[n6 + 11] = nArray[0];
        audioManager.g[n6 + 12] = nArray[1];
        if (bl) {
            audioManager.g[n6 + 14] = GameCanvas.a(audioManager.g[n6 + 14], -268435456, n);
        }
    }

    private static void ab(AudioManager audioManager) {
        int n;
        int n2 = AudioManager.d(audioManager);
        int n3 = 16;
        if (audioManager.L == 0) {
            n3 = 32;
        }
        if ((audioManager.g[n2 + 14] & 4) == 0 && ((n = GameCanvas.f(GameCanvas.b(audioManager.a.a()), GameCanvas.b(audioManager.a.b()))) & n3) != 0) {
            int n4 = n2 + 14;
            audioManager.g[n4] = audioManager.g[n4] | 4;
        }
    }

    public static boolean h(AudioManager audioManager) {
        int n = AudioManager.d(audioManager);
        return (audioManager.g[n + 14] & 0x10) != 0;
    }

    public static void a(AudioManager audioManager, boolean bl, int n, int n2, boolean bl2) {
        int n3 = AudioManager.e(audioManager);
        int n4 = AudioManager.d(audioManager);
        int n5 = n4 + 14;
        audioManager.g[n5] = audioManager.g[n5] & 0xFFFFFFEF;
        boolean bl3 = AudioManager.m(audioManager) < 555;
        boolean bl4 = false;
        if (f != null && bl3 && n == f[12] && n2 == f[13] && AudioManager.b(audioManager) == GameCanvas.c) {
            f[10] = f[10] + 1;
            bl4 = true;
        }
        if ((bl2 || bl4) && audioManager.L == 1 && (n3 == 2 || n3 == 4) && bl3) {
            ++F;
            if (!bl2 && bl4 || !bl || (audioManager.g[n4 + 14] & 2) == 0) {
                ++G;
                int n6 = n4 + 14;
                audioManager.g[n6] = audioManager.g[n6] | 0x10;
            }
        }
    }

    public static void q(AudioManager audioManager) {
        int n = AudioManager.d(audioManager);
        int n2 = n + 14;
        audioManager.g[n2] = audioManager.g[n2] & 0xFFFFFFDF;
        audioManager.a.a();
        int n3 = AudioManager.e(audioManager);
        if (n3 != 3 && n3 != 0) {
            if (AudioManager.m(audioManager) < 667) {
                AudioManager.ab(audioManager);
                AudioManager.U(audioManager);
                AudioManager.ac(audioManager);
            } else {
                GameCanvas.a(audioManager, 1, true);
            }
            AudioManager.ad(audioManager);
        } else {
            GameCanvas.a(audioManager, 0, true);
        }
        if (j) {
            j = false;
            AudioManager.h(audioManager);
        }
    }

    private static void g(AudioManager audioManager, int n, int n2, int n3) {
        int n4;
        int n5;
        int n6;
        int n7;
        int n8 = AudioManager.d(audioManager);
        int[] nArray = null;
        if ((n3 & 1) != 0) {
            n7 = n;
            n6 = n2;
            n5 = audioManager.a.a;
            n4 = audioManager.a.b;
        } else {
            n7 = audioManager.a.a;
            n6 = audioManager.a.b;
            n5 = n;
            n4 = n2;
        }
        nArray = GameCanvas.a(n7, n6, n5, n4, false);
        audioManager.g[n8 + 11] = nArray[0];
        audioManager.g[n8 + 12] = nArray[1];
    }

    private static void ac(AudioManager audioManager) {
        int n = AudioManager.d(audioManager);
        int n2 = audioManager.g[n + 1];
        int n3 = audioManager.g[n + 11];
        int n4 = audioManager.g[n + 12];
        boolean bl = n2 == 5 || n2 == 6;
        boolean bl2 = n2 == 7;
        int n5 = 60;
        AudioManager audioManager2 = AudioManager.b(audioManager);
        switch (n2) {
            case 3: 
            case 6: 
            case 7: {
                n5 = 80;
            }
            case 1: 
            case 5: {
                if ((audioManager.g[n + 14] & 1) == 0) {
                    if (bl) {
                        AudioManager.g(audioManager, audioManager2.a.a, audioManager2.a.b, 0);
                    }
                    if (AudioManager.e(audioManager, n5)) break;
                    if (AudioManager.a(audioManager, n5, true)) {
                        int n6 = n + 14;
                        audioManager.g[n6] = audioManager.g[n6] | 1;
                        return;
                    }
                    AudioManager.d(audioManager, 0);
                    return;
                }
                if (bl) {
                    AudioManager.g(audioManager, audioManager2.a.a, audioManager2.a.b, 0);
                } else if (bl2) {
                    AudioManager.Z(audioManager);
                } else {
                    int n7 = GameCanvas.a(audioManager.g[n + 14], -268435456);
                    AudioManager.b(audioManager, n7, true);
                }
                boolean bl3 = AudioManager.e(audioManager, n5);
                if (!bl3 || AudioManager.b(audioManager, bl, n5)) {
                    audioManager.g[n + 11] = n3;
                    audioManager.g[n + 12] = n4;
                    if (AudioManager.a(audioManager, n5, false)) break;
                    int n8 = n + 14;
                    audioManager.g[n8] = audioManager.g[n8] & 0xFFFFFFFE;
                    AudioManager.d(audioManager, 0);
                    return;
                }
                int n9 = n + 14;
                audioManager.g[n9] = audioManager.g[n9] & 0xFFFFFFFE;
                return;
            }
            case 102: {
                AudioManager.e(audioManager, 100);
            }
        }
    }

    private static boolean e(AudioManager audioManager, int n) {
        boolean bl = false;
        int n2 = AudioManager.d(audioManager);
        boolean bl2 = (audioManager.g[n2 + 14] & 4) != 0;
        boolean bl3 = (audioManager.g[n2 + 14] & 8) != 0;
        GameCanvas.a(audioManager, 1, true);
        int n3 = audioManager.a.a;
        int n4 = audioManager.a.b;
        int n5 = audioManager.g[n2 + 11] * (int)GameCanvas.d * n / 1000;
        int n6 = audioManager.g[n2 + 12] * (int)GameCanvas.d * n / 1000;
        if (!AudioManager.a((n3 += n5) >> 14, (n4 += n6) >> 14, bl2, bl3, audioManager.L == 0)) {
            audioManager.a.a = n3;
            audioManager.a.b = n4;
            bl = true;
        }
        GameCanvas.a(audioManager, 0, true);
        return bl;
    }

    private static boolean b(AudioManager audioManager, boolean bl, int n) {
        int n2;
        int n3 = AudioManager.d(audioManager);
        AudioManager audioManager2 = AudioManager.b(audioManager);
        int n4 = 0;
        int n5 = 0;
        if (bl) {
            n4 = audioManager2.a.a;
            n2 = audioManager2.a.b;
        } else {
            n4 = audioManager.a.a + audioManager.g[n3 + 11] * (int)GameCanvas.d * n / 1000;
            n2 = audioManager.a.b + audioManager.g[n3 + 12] * (int)GameCanvas.d * n / 1000;
        }
        n5 = n2;
        GameCanvas.a(audioManager2, 1, true);
        GameCanvas.a(audioManager, 1, false);
        boolean bl2 = GameCanvas.a(audioManager.a.a, audioManager.a.b, n4, n5, null, false, 13);
        GameCanvas.a(audioManager2, 0, true);
        GameCanvas.a(audioManager, 0, false);
        return bl2;
    }

    /*
     * Unable to fully structure code
     */
    private static boolean a(AudioManager var0, int var1_1, boolean var2_2) {
        block26: {
            block25: {
                block24: {
                    block17: {
                        block20: {
                            block18: {
                                block23: {
                                    block22: {
                                        block21: {
                                            block19: {
                                                GameCanvas.a(var0, 1, true);
                                                var3_3 = false;
                                                var4_4 = AudioManager.d(var0);
                                                var5_5 = (var0.g[var4_4 + 14] & 4) != 0;
                                                var6_6 = (var0.g[var4_4 + 14] & 8) != 0;
                                                var7_7 = var0.a.a / 16 * 16;
                                                var8_8 = var0.a.b / 16 * 16;
                                                var9_9 = 0;
                                                var10_10 = 0;
                                                var11_11 = var0.g[var4_4 + 11];
                                                var12_12 = var0.g[var4_4 + 12];
                                                if (!var2_2) break block17;
                                                var13_13 = 81920;
                                                var9_9 = var11_11 * (int)GameCanvas.d * var1_1 / 1000;
                                                var10_10 = var12_12 * (int)GameCanvas.d * var1_1 / 1000;
                                                if (GameCanvas.d(var11_11) <= GameCanvas.d(var12_12)) break block18;
                                                if (AudioManager.a(var7_7 + var9_9 >> 14, var8_8 + var10_10 + var13_13 >> 14, var5_5, var6_6, var0.L == 0)) break block19;
                                                var11_11 = 0;
                                                v0 = 16384;
                                                break block20;
                                            }
                                            if (AudioManager.a(var7_7 + var9_9 >> 14, var8_8 + var10_10 - var13_13 >> 14, var5_5, var6_6, var0.L == 0)) break block21;
                                            var11_11 = 0;
                                            v0 = -16384;
                                            break block20;
                                        }
                                        if (var12_12 <= 0) break block22;
                                        var11_11 = 0;
                                        v0 = 16384;
                                        break block20;
                                    }
                                    if (var12_12 >= 0) break block23;
                                    var11_11 = 0;
                                    v0 = -16384;
                                    break block20;
                                }
                                var11_11 = 0;
                                v1 = GameCanvas.a() % 100 < 50 ? 1 : -1;
                                v2 = 14;
                                ** GOTO lbl69
                            }
                            if (GameCanvas.d(var11_11) < GameCanvas.d(var12_12)) {
                                if (!AudioManager.a(var7_7 + var9_9 + var13_13 >> 14, var8_8 + var10_10 >> 14, var5_5, var6_6, var0.L == 0)) {
                                    var11_11 = 16384;
                                    v0 = 0;
                                } else if (!AudioManager.a(var7_7 + var9_9 - var13_13 >> 14, var8_8 + var10_10 >> 14, var5_5, var6_6, var0.L == 0)) {
                                    var11_11 = -16384;
                                    v0 = 0;
                                } else if (var11_11 > 0) {
                                    var11_11 = 16384;
                                    v0 = 0;
                                } else if (var11_11 < 0) {
                                    var11_11 = -16384;
                                    v0 = 0;
                                } else {
                                    var11_11 = (GameCanvas.a() % 100 < 50 ? 1 : -1) << 14;
                                    v0 = 0;
                                }
                            } else if (GameCanvas.a() % 100 < 50) {
                                var11_11 = (GameCanvas.a() % 100 < 50 ? 1 : -1) << 14;
                                v0 = 0;
                            } else {
                                var11_11 = 0;
                                v1 = GameCanvas.a() % 100 < 50 ? 1 : -1;
                                v2 = 14;
lbl69:
                                // 2 sources

                                v0 = v1 << v2;
                            }
                        }
                        var12_12 = v0;
                    }
                    var9_9 = var11_11 * (int)GameCanvas.d * var1_1 / 1000;
                    var10_10 = var12_12 * (int)GameCanvas.d * var1_1 / 1000;
                    if (var9_9 == 0 || AudioManager.a(var7_7 + var9_9 >> 14, var8_8 >> 14, var5_5, var6_6, var0.L == 0)) break block24;
                    if (var2_2) {
                        var0.g[var4_4 + 11] = var11_11;
                        var0.g[var4_4 + 12] = 0;
                    }
                    var0.a.a = var7_7 + var9_9;
                    v3 = var0.a;
                    v4 = var8_8;
                    break block25;
                }
                if (var10_10 == 0 || AudioManager.a(var7_7 >> 14, var8_8 + var10_10 >> 14, var5_5, var6_6, var0.L == 0)) break block26;
                if (var2_2) {
                    var0.g[var4_4 + 11] = 0;
                    var0.g[var4_4 + 12] = var12_12;
                }
                var0.a.a = var7_7;
                v3 = var0.a;
                v4 = var8_8 + var10_10;
            }
            v3.b = v4;
            var3_3 = true;
        }
        GameCanvas.a(var0, 0, true);
        return var3_3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int a(AudioManager audioManager, boolean bl) {
        int n = 0;
        int n2 = AudioManager.d(audioManager);
        int n3 = n2 + 11;
        int n4 = n2 + 12;
        if (!bl) {
            if (audioManager.g[n3] == 0) {
                if (audioManager.g[n4] == 0) return n;
            }
            if (audioManager.g[n3] > 8192) {
                return 3;
            }
            if (audioManager.g[n3] < -8192) {
                return 2;
            }
            if (audioManager.g[n4] > 8192) {
                return 1;
            }
        } else {
            int n5;
            int n6 = GameCanvas.f(20) << 6;
            int n7 = GameCanvas.e(20) << 6;
            int n8 = -n7;
            int n9 = -n6;
            int n10 = n6;
            int n11 = n7;
            if (audioManager.g[n3] < n8) {
                return 4;
            }
            if (audioManager.g[n3] < n9) {
                n5 = 1;
            } else if (audioManager.g[n3] < n10) {
                n5 = 2;
            } else {
                if (audioManager.g[n3] >= n11) return 0;
                n5 = 3;
            }
            int n12 = n5;
            if (n5 == 0) {
                return 4;
            }
            if (n12 == 1) {
                if (audioManager.g[n4] >= 0) return 5;
                return 3;
            }
            if (n12 == 2) {
                if (audioManager.g[n4] >= 0) return 6;
                return 2;
            }
            if (n12 != 3) return 0;
            if (audioManager.g[n4] >= 0) return 7;
            return 1;
        }
        if (audioManager.g[n4] >= -8192) return n;
        return 0;
    }

    private static boolean a(int n, int n2, boolean bl, boolean bl2, boolean bl3) {
        int n3 = 13;
        if (bl2) {
            n3 = 77;
        }
        boolean bl4 = true;
        if (bl) {
            int n4 = 16;
            if (bl3) {
                n4 = 32;
            }
            boolean bl5 = bl4 = (GameCanvas.f(GameCanvas.b(n), GameCanvas.b(n2)) & n4) != 0;
        }
        return !bl4 || GameCanvas.a(GameCanvas.b(n), GameCanvas.b(n2), n3) || GameCanvas.a(GameCanvas.b(n - 5), GameCanvas.b(n2 + 4), n3) || GameCanvas.a(GameCanvas.b(n + 4), GameCanvas.b(n2 + 4), n3) || GameCanvas.a(GameCanvas.b(n - 5), GameCanvas.b(n2 - 5), n3) || GameCanvas.a(GameCanvas.b(n + 4), GameCanvas.b(n2 - 5), n3);
    }

    public static boolean i(AudioManager audioManager) {
        return audioManager.g[25] >= 555;
    }

    public static void r(AudioManager audioManager) {
        int n = AudioManager.e(audioManager);
        if (n != 3 && n != 0) {
            int n2 = AudioManager.a(audioManager, audioManager.L != 1);
            if (audioManager.L == 1) {
                n2 += 0;
            } else {
                n2 += 64;
                n2 += AudioManager.a();
            }
            if (n2 >= 0 && audioManager.a.d != n2) {
                audioManager.a.b(n2);
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    public static void s(AudioManager var0) {
        if (var0.L == 0) {
            return;
        }
        var1_1 = var0.a.d % 4;
        if (var0.a.d >= 22 && var0.a.d <= 25) {
            var1_1 = GameCanvas.d(var0.a.d - 22) % 4;
        }
        var2_2 = AudioManager.d(var0);
        switch (var1_1) {
            case 0: {
                var0.g[var2_2 + 11] = 0;
                v0 = var0.g;
                v1 = var2_2 + 12;
                v2 = -16384;
                ** GOTO lbl33
            }
            case 1: {
                var0.g[var2_2 + 11] = 0;
                v0 = var0.g;
                v1 = var2_2 + 12;
                v2 = 16384;
                ** GOTO lbl33
            }
            case 2: {
                v3 = var0.g;
                v4 = var2_2 + 11;
                v5 = -16384;
                ** GOTO lbl29
            }
            case 3: {
                v3 = var0.g;
                v4 = var2_2 + 11;
                v5 = 16384;
lbl29:
                // 2 sources

                v3[v4] = v5;
                v0 = var0.g;
                v1 = var2_2 + 12;
                v2 = 0;
lbl33:
                // 3 sources

                v0[v1] = v2;
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    private static void ad(AudioManager var0) {
        block34: {
            block33: {
                var1_1 = AudioManager.a(var0, false);
                var2_2 = AudioManager.d(var0);
                var4_3 = var0.g[var2_2 + 1];
                var5_4 = false;
                if (var0.L != 1) break block33;
                switch (var4_3) {
                    case 0: {
                        if (AudioManager.B(var0)) {
                            v0 = var2_2 + 13;
                            var0.g[v0] = var0.g[v0] + (int)GameCanvas.d;
                        }
                        if (var0.g[var2_2 + 13] < 5000) break;
                        var5_4 = true;
                        break;
                    }
                    default: {
                        var0.g[var2_2 + 13] = 0;
                    }
                }
                switch (var4_3) {
                    case 3: 
                    case 6: 
                    case 7: {
                        var1_1 += 4;
                        break;
                    }
                    case 1: 
                    case 5: {
                        var1_1 += 8;
                        break;
                    }
                    case 0: 
                    case 4: 
                    case 8: {
                        if (var5_4) {
                            var1_1 = 16;
                            break;
                        }
                        var1_1 += 0;
                        break;
                    }
                    case 100: {
                        var1_1 = 19;
                        break;
                    }
                    case 101: {
                        var1_1 = 20;
                        break;
                    }
                    case 102: 
                    case 555: 
                    case 666: {
                        var1_1 += 12;
                        break;
                    }
                    case 556: 
                    case 667: {
                        var1_1 += 22;
                        break;
                    }
                    case 104: {
                        var1_1 += 29;
                    }
                }
                break block34;
            }
            var6_5 = 0;
            var2_2 = 29;
            var7_6 = GameCanvas.a(var0.g[43], 65280);
            var4_3 = var0.g[30];
            switch (var4_3) {
                case 3: 
                case 6: 
                case 7: {
                    var6_5 = 1;
                    var1_1 += 76;
                    break;
                }
                case 1: 
                case 5: {
                    if (var7_6 == 255) {
                        var6_5 = 1;
                        var1_1 += 72;
                        break;
                    }
                    v1 = GameCanvas.a(var0, var7_6, 3) + AudioManager.a(var0, true);
                    break;
                }
                case 0: 
                case 4: 
                case 8: {
                    if (var7_6 == 255) {
                        var6_5 = 1;
                        v1 = 64 + AudioManager.a(var0, true);
                        break;
                    }
                    v1 = GameCanvas.a(var0, var7_6, 2) + AudioManager.a(var0, true);
                    break;
                }
                case 100: {
                    var1_1 += 8;
                    break;
                }
                case 101: {
                    var1_1 += 12;
                    break;
                }
                case 102: 
                case 555: 
                case 666: {
                    v1 = 7;
                    break;
                }
                case 104: {
                    break;
                }
                case 103: {
                    v2 = var0;
                    v3 = 8;
                    ** GOTO lbl86
                }
                case 9: {
                    v2 = var0;
                    v3 = 6;
lbl86:
                    // 2 sources

                    v1 = var1_1 = AudioManager.a(v2, v3, AudioManager.a(var0, true), 0, var0.g[28], var0.g[29]);
                }
            }
            if (var6_5 != 0) {
                var1_1 += AudioManager.a();
                var1_1 += GameCanvas.k(var0.g[28]);
            }
        }
        if (var1_1 >= 0 && var0.a.d != var1_1) {
            if ((var0.g[var2_2 + 14] & 1) != 0) {
                var6_5 = GameCanvas.a(var0.g[var2_2 + 14], 0xF000000);
                if (var6_5 >= 3) {
                    var0.a.b(var1_1);
                    var0.g[var2_2 + 14] = GameCanvas.a(var0.g[var2_2 + 14], 0xF000000, 0);
                    return;
                }
                var0.g[var2_2 + 14] = GameCanvas.a(var0.g[var2_2 + 14], 0xF000000, ++var6_5);
                return;
            }
            var0.a.b(var1_1);
            var0.g[var2_2 + 14] = GameCanvas.a(var0.g[var2_2 + 14], 0xF000000, 0);
            if (var5_4) {
                var0.a.e = GameCanvas.a() % var0.a.a.a(var0.a.d);
            }
        }
    }

    public static boolean j(AudioManager audioManager) {
        int n = 10;
        if (audioManager.L == 0) {
            n = 7;
        }
        return audioManager.g[n] != 0;
    }

    public static void e(AudioManager audioManager, int n) {
        if (AudioManager.m(audioManager) < 555) {
            if (audioManager.L == 0) {
                return;
            }
            if (audioManager.g[10] > 0) {
                audioManager.g[10] = audioManager.g[10] - n;
                if (audioManager.g[10] <= 0) {
                    audioManager.g[10] = 0;
                }
                if (AudioManager.e(audioManager) == 3) {
                    AudioManager.m(audioManager, 1);
                }
            }
        }
    }

    private static void o(AudioManager audioManager, int n) {
        if (AudioManager.m(audioManager) < 555) {
            AudioManager.e(audioManager, n);
            int n2 = 10;
            if (audioManager.L == 0) {
                n2 = 7;
            }
            if (audioManager.g[n2] == 0) {
                AudioManager.d(audioManager, 555);
            }
        }
    }

    private static void e(Graphics graphics, AudioManager audioManager) {
        int n;
        audioManager.a.a.e = audioManager.g[2];
        int n2 = -1;
        if (audioManager.L == 0 || audioManager.g[11] != 0) {
            n2 = 0;
        }
        if (n2 >= 0) {
            AudioManager.a(graphics, n2, audioManager.a.a >> 14, audioManager.a.b >> 14);
        }
        if (audioManager.g[(n = AudioManager.d(audioManager)) + 1] == 556) {
            audioManager.a.a(graphics);
        }
        int n3 = GameCanvas.a(audioManager);
        audioManager.a.a.a(n3);
        audioManager.a.a(graphics);
        if (GameCanvas.h[0][0] != 0 && audioManager.L == 1 && (audioManager.g[17] == 2 || audioManager.g[17] == 4)) {
            int[] nArray = GameCanvas.a(audioManager.a);
            int n4 = nArray[3] - nArray[1];
            int n5 = 11;
            if ((audioManager.g[n + 14] & 2) == 0 && GameCanvas.I) {
                n5 = 12;
            }
            GraphicsEngine graphicsEngine = GameCanvas.a[12];
            graphicsEngine.a(graphics, n5, audioManager.a.a(), audioManager.a.b() - n4, 0);
        }
        if (GameCanvas.g > 0) {
            graphics.setColor(0xFF0000);
            GameCanvas.a(graphics, audioManager.a.a(), audioManager.a.b());
        }
    }

    public static int[] d(int[] nArray) {
        int n;
        int n2;
        int[] nArray2;
        int[] nArray3 = null;
        if (nArray != null) {
            nArray3 = new int[nArray.length + 3];
            System.arraycopy(nArray, 0, nArray3, 0, nArray.length);
            nArray3[0] = GameCanvas.a(GameCanvas.w, nArray3[0]);
        } else {
            nArray3 = new int[4];
        }
        for (int i = 0; i < 3; ++i) {
            nArray3[4 + i] = 0;
        }
        if (nArray == null) {
            nArray2 = nArray3;
            n2 = 4;
            n = -1;
        } else {
            nArray3[4] = 0;
            nArray2 = nArray3;
            n2 = 5;
            n = GameCanvas.k(nArray3[0], 8);
        }
        nArray2[n2] = n;
        return nArray3;
    }

    private static void ae(AudioManager audioManager) {
        audioManager.a.a();
        if (audioManager.g[4] != -1) {
            switch (audioManager.g[4]) {
                case 0: {
                    break;
                }
                case 2: {
                    if (!audioManager.a.a()) break;
                    AudioManager.b(audioManager, true);
                    break;
                }
                case 1: {
                    if (!audioManager.a.a()) break;
                    int n = GameCanvas.k(audioManager.g[0], 6);
                    int n2 = GameCanvas.k(audioManager.g[0], 7);
                    int n3 = audioManager.a.a() + (n * 16 >> 1);
                    int n4 = audioManager.a.b() + (n2 * 16 >> 1);
                    int n5 = GameCanvas.k(audioManager.g[0], 9) << 14;
                    int n6 = GameCanvas.k(audioManager.g[0], 10);
                    GameCanvas.a(n3, n4, 0, n5, n6, true);
                    AudioManager.b(audioManager, true);
                    break;
                }
                case 3: {
                    break;
                }
                default: {
                    GameCanvas.n(audioManager);
                }
            }
        }
        AudioManager.af(audioManager);
    }

    public static void b(AudioManager audioManager, boolean bl) {
        audioManager.g[5] = 0;
        audioManager.g[4] = 3;
        GameCanvas.d(audioManager);
        GameCanvas.c(audioManager);
        if (bl) {
            int n;
            boolean bl2 = false;
            int n2 = 16 * GameCanvas.k(audioManager.g[0], 6);
            int n3 = 16 * GameCanvas.k(audioManager.g[0], 7);
            int n4 = audioManager.a.a() + (n2 >> 1);
            if (!GameCanvas.b(n4 / 16, (n = audioManager.a.b() + (n3 >> 1)) / 16, 5)) {
                bl2 = true;
            }
            if (!bl2 && GameCanvas.a(GameCanvas.s, audioManager.a.a() / 16, audioManager.a.b() / 16, (audioManager.a.a() + n2 - 1) / 16, (audioManager.a.b() + n3 - 1) / 16, 5)) {
                bl2 = true;
                n4 = GameCanvas.s[0] * 16 + 8;
                n = GameCanvas.s[1] * 16 + 8;
            }
            if (!bl2) {
                n4 = audioManager.a.a();
                n = audioManager.a.b();
            }
            GameCanvas.b(audioManager.g[3], n4 << 14, n << 14);
        }
        GameCanvas.k(audioManager);
    }

    public static boolean a(AudioManager audioManager, int n) {
        return AudioManager.a(GameCanvas.l(n), audioManager);
    }

    private static void af(AudioManager audioManager) {
        int n;
        int n2 = 0;
        switch (audioManager.g[4]) {
            case 0: {
                n = GameCanvas.k(audioManager.g[0], 3);
                break;
            }
            case 1: 
            case 2: {
                n = GameCanvas.k(audioManager.g[0], 5);
                break;
            }
            case 3: {
                n = GameCanvas.k(audioManager.g[0], 4);
                break;
            }
            default: {
                n = n2 = -1;
            }
        }
        if (n >= 0 && audioManager.a.d != n2) {
            audioManager.a.b(n2);
        }
    }

    /*
     * Unable to fully structure code
     */
    private static void a(int var0, int var1_1, AudioManager var2_2, int var3_3, int var4_4) {
        switch (var2_2.L) {
            case 9: {
                if (var2_2.g[4] != 0) break;
                AudioManager.a(var1_1, var2_2);
                return;
            }
            case 3: {
                if (var2_2.g[12] == -1) break;
                AudioManager.G(var2_2);
                AudioManager.a(var2_2, var0, var1_1, var3_3 << 14, var4_4 << 14);
                return;
            }
            case 0: {
                if (var2_2 == GameCanvas.c) {
                    AudioManager.a(var2_2, var1_1, true);
                    return;
                }
                v0 = var2_2;
                ** GOTO lbl20
            }
            case 1: {
                v0 = var2_2;
lbl20:
                // 2 sources

                AudioManager.e(v0, var1_1);
                AudioManager.g(var2_2, var3_3 << 14, var4_4 << 14, 1);
                AudioManager.d(var2_2, 102);
            }
        }
    }

    public static void a(int n, int n2, int n3, int n4, AudioManager audioManager) {
        int n5;
        int n6 = 0;
        int n7 = 0;
        if (audioManager.L == 9) {
            n6 = GameCanvas.k(audioManager.g[0], 6) << 4 >> 1;
            n7 = GameCanvas.k(audioManager.g[0], 7) << 4 >> 1;
        }
        if ((n5 = GameCanvas.a(n, n2, audioManager.a.a() + n6, audioManager.a.b() + n7)) >= 0 && n5 <= n4) {
            int n8 = n3 - n3 / n4 * n5 >> 14;
            AudioManager.a(n3 >> 14, n8, audioManager, n, n2);
        }
    }

    public static void a(int n, int n2, int n3, int n4, AudioManager[] audioManagerArray) {
        for (int i = 0; i < audioManagerArray.length; ++i) {
            AudioManager.a(n, n2, n3, n4, audioManagerArray[i]);
        }
    }

    public static boolean k(AudioManager audioManager) {
        int n = GameCanvas.k(audioManager.g[0], 8);
        return AudioManager.a(n, audioManager);
    }

    public static boolean a(int n, AudioManager audioManager) {
        if (audioManager.g[4] < 100) {
            if (audioManager.g[5] > 0) {
                int n2 = GameCanvas.k(audioManager.g[0], 12);
                if (n >= n2) {
                    audioManager.g[5] = audioManager.g[5] - n;
                }
                if (audioManager.g[5] <= 0) {
                    int n3;
                    int n4;
                    int[] nArray;
                    audioManager.g[5] = 0;
                    int n5 = GameCanvas.k(audioManager.g[0], 9);
                    if (n5 > 0) {
                        nArray = audioManager.g;
                        n4 = 4;
                        n3 = 1;
                    } else {
                        nArray = audioManager.g;
                        n4 = 4;
                        n3 = 2;
                    }
                    nArray[n4] = n3;
                    AudioManager.af(audioManager);
                }
                return true;
            }
        } else {
            GameCanvas.m(audioManager);
        }
        return false;
    }

    private static void f(Graphics graphics, AudioManager audioManager) {
        int n;
        if (audioManager.g[4] == 3 && (n = GameCanvas.k(audioManager.g[0], 13)) >= 0) {
            GameCanvas.e[audioManager.g[0]].a = audioManager.a.a;
            GameCanvas.e[audioManager.g[0]].b = audioManager.a.b;
            GameCanvas.e[audioManager.g[0]].a();
            GameCanvas.e(16);
            GameCanvas.e[audioManager.g[0]].a();
            GameCanvas.e[audioManager.g[0]].a(graphics);
            GameCanvas.f(16);
        }
        audioManager.a.a(graphics);
    }

    public static void t(AudioManager audioManager) {
        if (audioManager.g[4] == 3) {
            k = true;
        }
    }

    public static boolean l(AudioManager audioManager) {
        int n = GameCanvas.k(audioManager.g[0], 9);
        return audioManager.g[4] == 3 && n > 0;
    }

    public static int[] a(AudioManager audioManager) {
        return GameCanvas.a(GameCanvas.a[18], 18, audioManager.a.a(), audioManager.a.b(), 0);
    }

    public static void a(Graphics graphics, AudioManager audioManager, boolean bl) {
        if (audioManager.g[4] == 3) {
            int n = audioManager.a.a - GameCanvas.T >> 14;
            int n2 = (audioManager.a.b - GameCanvas.U >> 14) - 0;
            int n3 = GameCanvas.k(audioManager.g[0], 9);
            if (n3 > 0) {
                GameCanvas.a[18].a(graphics, 18, n, n2, 0);
            }
            if (n3 > 0) {
                int[] nArray = GameCanvas.a(GameCanvas.a[18], 18, audioManager.a.a(), audioManager.a.b(), 0);
                GameCanvas.a(graphics, nArray, nArray[3], true);
            }
        }
    }

    public static void u(AudioManager audioManager) {
        int n;
        AudioManager audioManager2;
        int n2 = GameCanvas.k(audioManager.g[0], 6) << 4;
        int n3 = GameCanvas.k(audioManager.g[0], 7) << 4;
        if (audioManager.g[4] == 3) {
            audioManager2 = audioManager;
            n = audioManager.a.b();
        } else {
            audioManager2 = audioManager;
            n = audioManager.a.b() + n3 - 1;
        }
        audioManager2.M = (n << 8) + audioManager.a.a() + (n2 >> 1);
    }

    public static int[] e(int[] nArray) {
        int[] nArray2 = new int[nArray.length + 2];
        System.arraycopy(nArray, 0, nArray2, 0, nArray.length);
        nArray2[6] = -1;
        return nArray2;
    }

    public static void v(AudioManager audioManager) {
        int n = audioManager.a.a() + audioManager.g[0];
        int n2 = audioManager.a.b() + audioManager.g[1];
        audioManager.M = (n2 << 8) + n;
    }

    public static void f(AudioManager audioManager, int n) {
        audioManager.g[4] = n;
        int n2 = audioManager.a.a() / 16;
        int n3 = (audioManager.a.a() + audioManager.g[0]) / 16;
        int n4 = audioManager.a.b() / 16;
        int n5 = (audioManager.a.b() + audioManager.g[1]) / 16;
        for (int i = 0; i < GameCanvas.cg; ++i) {
            int n6;
            int n7;
            int n8;
            AudioManager audioManager2 = GameCanvas.e[i];
            if (audioManager2.L != 3 || (n8 = GameCanvas.h(audioManager2.g[0], 10)) != 5 && n8 != 7 || !GameCanvas.b(n2, n4, n3, n5, n7 = audioManager2.a.a() / 16, n6 = audioManager2.a.b() / 16, n7, n6)) continue;
            if (n != 0) {
                AudioManager.D(audioManager2);
            }
            if (GameCanvas.b == 0) continue;
            AudioManager.C(audioManager2);
        }
    }

    private static void p(AudioManager audioManager, int n) {
        audioManager.g[7] = n;
        for (int i = 0; i < audioManager.g[3]; ++i) {
            AudioManager audioManager2 = GameCanvas.j[GameCanvas.by][GameCanvas.bz][audioManager.g[6] + i];
            int n2 = audioManager2.g[12];
            if (!(audioManager2.g[12] >= 100 || audioManager2.g[12] == -1 && (audioManager2.g[16] & 0x40) != 0 || AudioManager.s(audioManager2))) {
                int n3;
                switch (n) {
                    case 2: {
                        n3 = 4;
                        break;
                    }
                    case 1: {
                        n3 = 0;
                        break;
                    }
                    case 3: {
                        n3 = -1;
                        break;
                    }
                    default: {
                        n3 = -1;
                    }
                }
                n2 = n3;
            }
            AudioManager.c(audioManager2, n2);
        }
    }

    private static void ag(AudioManager audioManager) {
        GameCanvas.b(audioManager.a.a(), audioManager.a.b(), audioManager.g[0], audioManager.g[1], audioManager.g[6], audioManager.g[3]);
    }

    private static boolean d(AudioManager audioManager, AudioManager audioManager2) {
        int n = audioManager2.a.a() / 16;
        int n2 = audioManager2.a.b() / 16;
        int n3 = audioManager.a.a();
        int n4 = audioManager.a.b();
        int n5 = audioManager.g[0];
        int n6 = audioManager.g[1];
        int n7 = n3 / 16;
        int n8 = (n3 + n5 - 1) / 16;
        int n9 = n4 / 16;
        int n10 = (n4 + n6 - 1) / 16;
        return GameCanvas.b(n, n2, n, n2, n7, n9, n8, n10);
    }

    private static AudioManager d(AudioManager audioManager) {
        int n;
        AudioManager audioManager2 = null;
        for (n = 0; n < GameCanvas.c.length && audioManager2 == null; ++n) {
            if (!AudioManager.b(5, GameCanvas.c[n]) || !AudioManager.d(audioManager, GameCanvas.c[n])) continue;
            audioManager2 = GameCanvas.c[n];
        }
        for (n = 0; n < GameCanvas.f[GameCanvas.by][GameCanvas.bz].length && audioManager2 == null; ++n) {
            if (!AudioManager.b(5, GameCanvas.f[GameCanvas.by][GameCanvas.bz][n]) || !AudioManager.d(audioManager, GameCanvas.f[GameCanvas.by][GameCanvas.bz][n])) continue;
            audioManager2 = GameCanvas.f[GameCanvas.by][GameCanvas.bz][n];
        }
        return audioManager2;
    }

    private static void e(AudioManager audioManager, AudioManager audioManager2) {
        for (int i = 0; i < audioManager.g[3]; ++i) {
            AudioManager audioManager3 = GameCanvas.j[GameCanvas.by][GameCanvas.bz][audioManager.g[6] + i];
            if (audioManager3.g[12] >= 100) continue;
            if (audioManager2 == null) {
                AudioManager.i(audioManager3);
            }
            AudioManager.d(audioManager3, audioManager2);
        }
    }

    private static void ah(AudioManager audioManager) {
        block8: {
            int n;
            AudioManager audioManager2;
            block7: {
                block5: {
                    AudioManager audioManager3;
                    block6: {
                        if (audioManager.g[5] == 0) break block5;
                        if (audioManager.g[7] == 0) {
                            AudioManager.ag(audioManager);
                            AudioManager.p(audioManager, 1);
                        }
                        if (audioManager.g[7] == 3) {
                            AudioManager.p(audioManager, 1);
                        }
                        if ((audioManager3 = AudioManager.d(audioManager)) == null || audioManager.g[7] != 1) break block6;
                        AudioManager.e(audioManager, audioManager3);
                        audioManager2 = audioManager;
                        n = 2;
                        break block7;
                    }
                    if (audioManager3 != null || audioManager.g[7] != 2) break block8;
                    AudioManager.e(audioManager, null);
                    audioManager2 = audioManager;
                    n = 1;
                    break block7;
                }
                if (audioManager.g[7] == 3 || audioManager.g[7] == 0) break block8;
                audioManager2 = audioManager;
                n = 3;
            }
            AudioManager.p(audioManager2, n);
        }
    }

    public static AudioManager[] a(AudioManager audioManager) {
        return GameCanvas.a(audioManager.a.a(), audioManager.a.b(), audioManager.g[3], 174, 3, 2, 16);
    }

    public AudioManager(int n, int[] nArray) {
        this.a = new GameData(null, 0, 0, null);
        this.L = n;
        this.g = nArray;
    }

    public AudioManager(GameData gameData, int n, int n2, int[] nArray) {
        this.a = gameData;
        this.K = n;
        this.L = n2;
        this.g = nArray;
        this.M = (this.a.b() << 8) + this.a.a();
    }

    public AudioManager(int n, int n2, int n3, int n4, int[] nArray) {
        this.K = n;
        this.L = n2;
        this.g = nArray;
        this.a = new GameData(null, n3, n4, null);
        if (n2 < 9) {
            switch (n2) {
                case 0: {
                    this.a.b(66);
                    return;
                }
                case 3: {
                    this.a.b(GameCanvas.h(this.g[0], 2));
                    return;
                }
            }
            this.a.b(this.g[5]);
            return;
        }
        if (n2 < 20) {
            switch (n2) {
                case 16: {
                    this.a.b(this.g[2]);
                    return;
                }
                case 15: {
                    this.a.a(GameCanvas.a[GameCanvas.x[this.g[3]]]);
                    if (this.g[0] >= 0) {
                        GameCanvas.a(this.a, this.g[0]);
                        return;
                    }
                    this.a.b(this.g[1]);
                    return;
                }
                case 12: {
                    return;
                }
                case 9: {
                    this.a.b(GameCanvas.k(this.g[0], 3));
                    return;
                }
                case 18: {
                    this.a.a(GameCanvas.a[16]);
                    return;
                }
                case 26: {
                    this.a.b(this.g[2]);
                    return;
                }
            }
            if (this.g[4] >= 0) {
                GameCanvas.a(this.a, this.g[4]);
                return;
            }
            this.a.b(this.g[5]);
            return;
        }
        if (n2 == 21) {
            AudioManager.b(this.g);
        }
    }

    public final void g() {
        if (this.a != null) {
            if (this.a.a != null) {
                this.a.a.b();
                this.a.a = null;
            }
            this.a = null;
        }
        this.g = null;
    }

    public final void a(GraphicsEngine graphicsEngine) {
        if (this.a != null && graphicsEngine != null) {
            this.a.a(graphicsEngine);
        }
    }

    public final void h() {
        switch (this.L) {
            case 0: {
                AudioManager.a(this.a);
                return;
            }
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: {
                AudioManager.a(this.L, this.g, this.a.a(), this.a.b());
                return;
            }
            case 14: {
                GameCanvas.a(this);
                return;
            }
            case 18: {
                AudioManager.ah(this);
                return;
            }
            case 9: {
                AudioManager.ae(this);
                return;
            }
            case 1: {
                AudioManager.q(this);
                return;
            }
            case 3: {
                AudioManager.a(this, true);
                return;
            }
        }
        this.a.a();
    }

    public static void a(Graphics graphics, int n, int n2, int n3) {
        GameCanvas.a[13].a(graphics, n, n2, n3, 0);
        int[] nArray = GameCanvas.a(GameCanvas.a[13], n);
        int[] nArray2 = nArray;
        nArray[0] = nArray[0] + (n2 + (GameCanvas.T >> 14));
        nArray2[1] = nArray2[1] + (n3 + (GameCanvas.U >> 14) - 0);
        nArray2[2] = nArray2[2] + (n2 + (GameCanvas.T >> 14));
        nArray2[3] = nArray2[3] + (n3 + (GameCanvas.U >> 14) - 0);
        GameCanvas.a(graphics, nArray2, n3, true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean a(Graphics graphics, boolean bl) {
        boolean bl2 = false;
        switch (this.L) {
            case 0: 
            case 1: {
                if (this == GameCanvas.c) {
                    AudioManager.c(graphics, this);
                    return true;
                } else {
                    AudioManager.e(graphics, this);
                }
                return true;
            }
            case 3: {
                AudioManager.e(graphics, this, bl);
                return true;
            }
            case 14: {
                GameCanvas.a(graphics, this);
                return true;
            }
            case 9: {
                AudioManager.f(graphics, this);
                return true;
            }
            case -101: {
                GameCanvas.d(graphics, this);
                return true;
            }
            case 18: {
                return true;
            }
            case 26: {
                GameCanvas.c(graphics, this);
                return true;
            }
            case 22: {
                AudioManager.b(graphics, this.g, this.a.a(), this.a.b());
                return bl2;
            }
            case -100: {
                if (this.g[0] != 4) return bl2;
                GameCanvas.b(graphics, this);
                return true;
            }
        }
        return bl2;
    }

    /*
     * Unable to fully structure code
     */
    public final void a(Graphics var1_1, boolean var2_2) {
        block12: {
            block13: {
                block15: {
                    block14: {
                        if (this.a == null || this.a.a == null && this.L != 16 && this.L != 22) break block12;
                        var4_3 = this.a.a;
                        var5_4 = this.a.b;
                        this.a.a -= GameCanvas.T >> 14 << 14;
                        this.a.b -= (GameCanvas.U >> 14) - 0 << 14;
                        if (this.a(var1_1, var2_2)) break block13;
                        if (this.L == -100) {
                            this.a.a.e = this.g[9];
                            this.a.a.a(this.g[10]);
                            this.a.b += this.g[6];
                        }
                        if (this.L != 15) break block14;
                        switch (this.g[2]) {
                            case 1: {
                                v0 = 8;
                                ** GOTO lbl18
                            }
                            case 2: {
                                v0 = 16;
lbl18:
                                // 2 sources

                                GameCanvas.e(v0);
                            }
                        }
                        this.a.a(var1_1);
                        GameCanvas.f(24);
                        break block15;
                    }
                    this.a.a(var1_1);
                }
                switch (this.L) {
                    case -100: {
                        GameCanvas.a(var1_1, this.g[8]);
                        break;
                    }
                    case 16: {
                        if (this.g[24] != 1 || (this.g[28] & 8) != 0 || (var6_5 = this.g[21]) < 0) break;
                        var7_7 = GameCanvas.b[var6_5][2];
                        if (var7_7 != GameCanvas.a.d) {
                            GameCanvas.a.b(var7_7);
                        }
                        GameCanvas.a.a = this.a.a;
                        GameCanvas.a.b = this.a.b;
                        GameCanvas.a.a(var1_1);
                    }
                }
            }
            this.a.a += GameCanvas.T >> 14 << 14;
            this.a.b += (GameCanvas.U >> 14) - 0 << 14;
            if (!(this.L == 10 && this.g[7] != 0 || this.L == 3 && this.g[10] == 4)) {
                var6_6 = AudioManager.d(this);
                var7_7 = 0;
                var8_8 = 0;
                if (this.L == -101) {
                    var7_7 = GameCanvas.a(this.g[15], 255);
                    var8_8 = GameCanvas.l(var7_7, 21);
                }
                var9_9 = this.L == 0 || this.L == 3 || this.L == 1 || this.L == -100 && this.g[0] == 3 || this.L == 9 || this.L == 14 && this.g[16] != -1 || this.L == -101 && var8_8 != 0 && (this.g[0] == 5 || this.g[0] == 3 && this.g[1] == 3) || this.L == -200;
                GameCanvas.a(var1_1, var6_6, AudioManager.p(this), var9_9);
            }
            this.a.a = var4_3;
            this.a.b = var5_4;
        }
    }

    /*
     * Unable to fully structure code
     */
    private static int p(AudioManager var0) {
        var1_1 = var0.a.b;
        var2_2 = null;
        switch (var0.L) {
            case 14: {
                v0 = var1_1;
                v1 = var0.g[12] << 14;
                ** GOTO lbl66
            }
            case 3: {
                var3_3 = GameCanvas.h(var0.g[0], 10);
                block6 : switch (var3_3) {
                    case 2: {
                        switch (var0.g[12]) {
                            case 7: {
                                v2 = var1_1;
                                v3 = GameCanvas.k[var0.g[28]].g;
                                v4 = 10;
                                ** GOTO lbl26
                            }
                            case 8: {
                                var7_4 = GameCanvas.c.a.a.a(0, var0.g[27]);
                                v5 = var1_1 - (var7_4[1] + (var7_4[3] >> 1) << 14);
                                ** GOTO lbl27
                            }
                            case 9: {
                                v2 = var1_1;
                                v3 = var0.g;
                                v4 = 28;
lbl26:
                                // 2 sources

                                v5 = v2 + v3[v4];
lbl27:
                                // 2 sources

                                var1_1 = v5;
                            }
                        }
                        break;
                    }
                    case 1: {
                        var2_2 = GameCanvas.b(var0.a);
                        var4_6 = GameCanvas.b(var2_2[3] - (var1_1 >> 14)) + 1;
                        var5_7 = GameCanvas.b(var0.a.a());
                        var6_8 = GameCanvas.b(var0.a.b());
                        for (var7_5 = 0; var7_5 < var4_6; ++var7_5) {
                            if (GameCanvas.b(var5_7 - 1, var6_8, 2) || GameCanvas.b(var5_7, var6_8, 2) || GameCanvas.b(var5_7 + 1, var6_8, 2)) {
                                var1_1 = var2_2[3] << 14;
                                break block6;
                            }
                            ++var6_8;
                        }
                        break;
                    }
                }
                break;
            }
            case -101: {
                if (var0.g[0] != 5) break;
                var1_1 = var0.g[5];
                switch (var0.g[15]) {
                    case 0: 
                    case 4: {
                        v6 = var0;
                        ** GOTO lbl58
                    }
                    case 6: {
                        v7 = var1_1 - 131072;
                        ** GOTO lbl59
                    }
                    case 1: 
                    case 2: 
                    case 3: {
                        if (GameCanvas.a(var0.a.a, var0.a.b, var0.g[4] + var0.g[9], var0.g[5] + var0.g[10], GameCanvas.s, false, 1)) {
                            v7 = GameCanvas.s[1] + 262144;
                        } else {
                            v6 = var0;
lbl58:
                            // 2 sources

                            v7 = v6.a.b;
                        }
lbl59:
                        // 3 sources

                        var1_1 = v7;
                    }
                }
                break;
            }
            case -200: {
                var2_2 = GameCanvas.b(var0.a);
                if (!GameCanvas.a(var0.a.a, var0.a.b, var0.a.a, var2_2[3] << 14, GameCanvas.s, false, 1)) break;
                v0 = GameCanvas.s[1];
                v1 = 262144;
lbl66:
                // 2 sources

                var1_1 = v0 + v1;
            }
        }
        return var1_1 >> 14;
    }

    /*
     * Unable to fully structure code
     */
    private static int[] d(AudioManager var0) {
        block17: {
            block14: {
                block16: {
                    block15: {
                        var1_1 = GameCanvas.b(var0.a);
                        if (var0.L != 3 || GameCanvas.h(var0.g[0], 34) == -1) break block14;
                        var2_2 = var1_1[0];
                        var3_3 = var1_1[1];
                        var4_4 = var1_1[2];
                        var5_5 = var1_1[3];
                        if ((var0.g[16] & 4096) == 0) break block15;
                        var1_1 = GameCanvas.a(var0.a.a, AudioManager.g(var0), var0.a.a(), var0.a.b(), 0);
                        if (var1_1[0] > var2_2) {
                            var1_1[0] = var2_2;
                        }
                        if (var1_1[1] > var3_3) {
                            var1_1[1] = var3_3;
                        }
                        if (var1_1[2] < var4_4) {
                            var1_1[2] = var4_4;
                        }
                        if (var1_1[3] >= var5_5) break block14;
                        break block16;
                    }
                    var1_1 = GameCanvas.a(var0.a.a, GameCanvas.i(var0.g[0], 14));
                    var6_6 = var1_1[3] - var1_1[1];
                    var1_1[0] = var2_2;
                    var1_1[1] = var3_3 - var6_6;
                    var1_1[2] = var4_4;
                }
                var1_1[3] = var5_5;
            }
            if (var0.L == 3 && var1_1 != null) {
                var2_2 = AudioManager.b[0];
                var3_3 = AudioManager.b[1];
                var4_4 = AudioManager.b[2];
                var5_5 = AudioManager.b[3];
                if (!AudioManager.f()) {
                    var2_2 += GameCanvas.T >> 14;
                    var3_3 += GameCanvas.U >> 14;
                    var4_4 += GameCanvas.T >> 14;
                    var5_5 += GameCanvas.U >> 14;
                }
                if (var2_2 < var1_1[0]) {
                    var1_1[0] = var2_2;
                }
                if (var4_4 > var1_1[2]) {
                    var1_1[2] = var4_4;
                }
                if (var3_3 < var1_1[1]) {
                    var1_1[1] = var3_3;
                }
                if (var5_5 > var1_1[3]) {
                    var1_1[3] = var5_5;
                }
            }
            if (var0.L != -101) break block17;
            switch (var0.g[0]) {
                case 5: {
                    var1_1[0] = (Math.min(var0.g[4], var0.g[2]) >> 14) - 16;
                    var1_1[1] = (Math.min(var0.g[5], var0.g[3]) >> 14) - 16;
                    var1_1[2] = (Math.max(var0.g[4], var0.g[2]) >> 14) + 16;
                    v0 = var1_1;
                    v1 = 3;
                    v2 = Math.max(var0.g[5], var0.g[3]) >> 14;
                    v3 = 16;
                    ** GOTO lbl72
                }
                case 4: {
                    var1_1[1] = var1_1[1] - (var0.g[10] >> 14);
                    v1 = 3;
                    v0 = var1_1;
                    v4 = var1_1[3] - (var0.g[10] >> 14);
                    ** GOTO lbl73
                }
                case 3: {
                    if (var0.g[1] != 3 || (var3_3 = GameCanvas.l(var2_2 = GameCanvas.a(var0.g[15], 255), 21)) == 0) break;
                    var4_4 = var0.a.a() - (var0.g[9] >> 14);
                    var5_5 = var0.a.b() - (var0.g[10] >> 14);
                    var1_1[0] = var4_4 - 32;
                    var1_1[1] = var5_5 - 32;
                    var1_1[2] = var4_4 + 32;
                    v0 = var1_1;
                    v1 = 3;
                    v2 = var5_5;
                    v3 = 32;
lbl72:
                    // 2 sources

                    v4 = v2 + v3;
lbl73:
                    // 2 sources

                    v0[v1] = v4;
                }
            }
        }
        return var1_1;
    }

    static {
        e = -1;
        f = -1;
        h = 2304;
        n = 1;
        o = 1;
        a = null;
        b = false;
        c = false;
        d = false;
        z = -1;
        a = null;
        a = null;
        a = null;
        b = null;
        c = GameCanvas.r;
        d = null;
        a = null;
        b = null;
        f = false;
        g = false;
        h = false;
        i = false;
        e = new int[2];
        c = null;
        J = -1;
        f = null;
        j = false;
        k = false;
    }
}

