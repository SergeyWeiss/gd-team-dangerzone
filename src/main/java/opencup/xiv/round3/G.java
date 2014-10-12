package opencup.xiv.round3;

import java.io.*;

public class G {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter writer = new PrintWriter(System.out);

    G() throws IOException {
//        reader = new BufferedReader(new FileReader("input.txt"));
//        writer = new PrintWriter(new FileWriter("output.txt"));
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
        final char[][] w = new char[n][];
        for(int i = 0; i < n; i++) {
            w[i] = reader.readLine().toCharArray();
        }

        final int MOD = 1000 * 1000 * 1000 + 7;
        final int BASE = 256;
        final int[] p = new int[5 * 10 * 1000 + 5];
        p[0] = 1;
        for(int i = 1; i < p.length; i++) {
            p[i] = (int)(1L * p[i - 1] * BASE % MOD);
        }
        final int[][] h = new int[n][];
        for(int i = 0; i < n; i++) {
            h[i] = new int[w[i].length + 1];
            for(int j = 1; j <= w[i].length; j++) {
                h[i][j] = (int)((h[i][j - 1] + 1L * w[i][j - 1] * p[j]) % MOD);
            }
        }

        class Utils {
            boolean equals(int i, int i1, int j, int j1, int len) {
                int h1 = h[i][i1 + len] - h[i][i1];
                if(h1 < 0) {
                    h1 += MOD;
                }
                int h2 = h[j][j1 + len] - h[j][j1];
                if(h2 < 0) {
                    h2 += MOD;
                }
                if ((1L * p[j1] * h1 - 1L * p[i1] * h2) % MOD != 0)  return false;
                for(int k = 0; k < len; k++) {
                    if(w[i][i1 + k] != w[j][j1 + k]) {
                        return false;
                    }
                }
                return true;
            }

            boolean[][] visited = new boolean[n][50 * 1000 + 5];

            boolean dfs(int i, int l) {
                if(visited[i][l]) {
                    return false;
                }
                visited[i][l] = true;
                if(l == 0) {
                    return true;
                }
                for(int j = 0; j < n; j++) {
                    if(j == i && l == w[i].length) {
                        continue;
                    }
                    int len = Math.min(l, w[j].length);
                    if(equals(i, w[i].length - l, j, 0, len)) {
                        if(len == l && len == w[j].length) {
                            return true;
                        }
                        if(len < l && dfs(i, l - len)) return true;
                        if(len == l && dfs(j, w[j].length - len)) return true;
                    }
                }
                return false;
            }
        }
        Utils utils = new Utils();
        for(int i = 0; i < n; i++) {
            if(utils.dfs(i, w[i].length)) {
                writer.println("YES");
                writer.close();
                return;
            }
        }
        writer.println("NO");
        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        new G().solve();
    }

}