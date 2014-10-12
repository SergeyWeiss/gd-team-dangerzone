package opencup.xiv.round13;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public D() throws IOException {
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        br = new BufferedReader(new FileReader("lots.in"));
        out = new PrintWriter(new FileWriter("lots.out"));
    }

    public static void main(String[] args) throws IOException {
        final D j = new D();
        j.solveMany();
    }

    public void solveMany() throws IOException {
        while (solve()) {
            out.flush();
        }
        out.close();
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

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    private boolean solve() throws IOException {
        final int n = nextInt();
        int m = nextInt();
        if (n == 0 && m == 0) {
            return false;
        }
        char[][] s = new char[n][];
        for (int i = 0; i < n; ++i) {
            s[i] = next().toCharArray();
        }

        char[][] needed = new char[n][];
        for (int i = 0; i < n; ++i) {
            needed[i] = next().toCharArray();
        }
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; ++j) {
                if (needed[i][j] == 'x') {
                    count[i]++;
                }
            }
        }
        for (int i = 1; i < n; ++i) {
            if (count[i] > count[i - 1]) {
                out.println("NO");
                return true;
            }
        }
        final boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                graph[i][j] = match(s[i], needed[j]);
            }
        }
        final int[] prev = new int[n];
        class Utils {
            boolean[] used = new boolean[n];

            public boolean maxBipartiteMatch() {
                Arrays.fill(prev, -1);
                for (int i = 0; i < n; ++i) {
                    Arrays.fill(used, false);
                    if (!buildChain(i)) {
                        return false;
                    }
                }
                return true;
            }

            private boolean buildChain(int v) {
                used[v] = true;
                for (int u = 0; u < n; ++u) {
                    if (graph[v][u] && (prev[u] == -1 || !used[prev[u]] && buildChain(prev[u]))) {
                        prev[u] = v;
                        return true;
                    }
                }
                return false;
            }
        }

        Utils u = new Utils();
        if (!u.maxBipartiteMatch()) {
            out.println("NO");
            return true;
        }
        for (int i = 0; i < n; ++i) {
            s[prev[i]] = needed[i];
        }
        out.println("YES");
        for (int i = 0; i < n; ++i) {
            out.println(s[i]);
        }
        return true;
    }


    public boolean match(char[] pattern, char[] text) {
        for (int i = 0; i < pattern.length; ++i) {
            if (pattern[i] == '?') continue;
            if (pattern[i] != text[i]) {
                return false;
            }
        }
        return true;
    }

}
