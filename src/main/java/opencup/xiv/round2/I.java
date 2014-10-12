package opencup.xiv.round2;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class I {

    private BufferedReader br, reader;
    private PrintWriter out;
    private StringTokenizer st;

    public I() throws IOException {
//        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("i.in"));
        out = new PrintWriter(System.out);
        reader = br;
    }

    public static void main(String[] args) throws IOException {
        new I().solve();
    }

    public String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(next());
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

    private void solve() throws IOException {
        final int n = readInt();
        int k = readInt();
        int[] x = readInts();
        final int[][] g = new int[n + 1][];
        for(int i = 1; i <= n; i++) {
            int[] b = readInts();
            g[b[0]] = b;
        }
        class Utils {
            int[] bits = new int[1 << 16];
            {
                for(int i = 1; i < 1 << 16; i++) {
                    bits[i] = bits[i >> 1] + (i & 1);
                }
            }
            int[][] reach = new int[n + 1][n / 32 + 1];
            boolean[] visited = new boolean[n + 1];
            int[] influence = new int[n + 1];

            int getInfluence(int u) {
                if(!visited[u]) {
                    visited[u] = true;
                    for(int i = 1; i < g[u].length; i++) {
                        int v = g[u][i];
                        reach[u][v >> 5] |= 1 << (v & 31);
                        getInfluence(v);
                        for(int j = 0; j < reach[v].length; j++) {
                            reach[u][j] |= reach[v][j];
                        }
                    }
                    for(int j = 0; j < reach[u].length; j++) {
                        influence[u] += bits[reach[u][j] & (1 << 16) - 1] + bits[reach[u][j] >>> 16];
                    }
                }
                return influence[u];
            }
        }
        Arrays.sort(x);
        int maxInfluence = -1;
        int ans = 0;
        Utils utils = new Utils();
        for(int y: x) {
            if(utils.getInfluence(y) > maxInfluence) {
                ans = y;
                maxInfluence = utils.getInfluence(y);
            }
        }
        out.println(ans);
        out.flush();
        out.close();
    }
}
