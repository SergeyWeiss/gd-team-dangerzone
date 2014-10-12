package opencup.xiv.round3;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class B {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter writer = new PrintWriter(System.out);

    B() throws IOException {
        reader = new BufferedReader(new FileReader("input.txt"));
        writer = new PrintWriter(new FileWriter("output.txt"));
    }

    long[] readLongs() throws IOException {
        String[] s = reader.readLine().split(" ");
        long[] longs = new long[s.length];
        for(int i = 0; i < longs.length; i++) {
            longs[i] = Long.parseLong(s[i]);
        }
        return longs;
    }

    int[] readInts() throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for(int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }

    int[] tt;
    int tx = 0;
    int readInt() throws IOException {
        if(tt == null || tx >= tt.length) {
            tt = readInts();
            tx = 0;
        }
        return tt[tx++];
    }

    void solve() throws IOException {
        final int n = readInt();
        final int g = readInt();
        final int[][] a = new int[n + 1][];
        for(int i = 1; i <= n; i++) {
            a[i] = new int[readInt()];
            for(int j = 0; j < a[i].length; j++) {
                a[i][j] = readInt();
            }
        }
        class Utils {
            int[] color = new int[n + 1];
            void dfs(int u, int c) {
                color[u] = c;
                for(int i = 0; i < a[u].length; i++) {
                    int v = a[u][i];
                    if(color[v] == 0) {
                        dfs(v, 3 - c);
                    }
                }
            }
        }
        String[] colors = {"-", "CW", "CCW"};
        Utils utils = new Utils();
        utils.dfs(g, 1);
        for(int i = 1; i <= n; i++) {
            if(a[i].length <= 1) {
                writer.println(colors[utils.color[i]]);
            }
        }
        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        new B().solve();
    }

    private static final class MutableInt {
        int value;

        private MutableInt(int value) {
            this.value = value;
        }
    }
}