package opencup.xiv.round13;

import java.io.*;
import java.util.StringTokenizer;

public class B {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public B() throws IOException {
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        br = new BufferedReader(new FileReader("coloring.in"));
        out = new PrintWriter(new FileWriter("coloring.out"));
    }

    public static void main(String[] args) throws IOException {
        final B j = new B();
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
        int n = nextInt();
        if (n == 0) {
            return false;
        }
        final int[][] graph = new int[n + 1][];
        int[] parent = new int[n + 1];
        final int[] color = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            parent[i] = nextInt();
        }
        int m = nextInt();
        for (int i = 0; i < m; ++i) {
            color[nextInt()] = nextInt();
        }
        for (int i = 1; i < n + 1; ++i) {
            if (i == 1) {
                graph[i] = new int[3];
            } else if (color[i] == 0) {
                graph[i] = new int[2];
            } else {
                graph[i] = new int[0];
            }
        }
        int[] count = new int[n + 1];
        for (int i = 2; i < n + 1; ++i) {
            graph[parent[i]][count[parent[i]]++] = i;
        }
        final int[] colorMask = new int[n + 1];

        class Utils {
            public void dfs(int v) {
                if (color[v] != 0) {
                    colorMask[v] = 1 << color[v];
                } else {
                    for (int j = 0; j < graph[v].length; ++j) {
                        dfs(graph[v][j]);
                    }
                    for (int i = 1; i < 4; ++i) {
                        int t = 1 << i;
                        boolean isOk = true;
                        for (int j = 0; j < graph[v].length; ++j) {
                            int u = graph[v][j];
                            if ((colorMask[u] ^ t) == 0 || colorMask[u] == 0) {
                                isOk = false;
                            }
                        }
                        if (isOk) {
                            colorMask[v] += t;
                        }
                    }
                }
            }

            public void colorDfs(int v, int parentColor) {
                colorMask[v] &= ~(1 << parentColor);
                for (int i = 1; i < 4; ++i) {
                    if ((colorMask[v] & (1 << i)) != 0) {
                        color[v] = i;
                        for (int j = 0; j < graph[v].length; ++j) {
                            colorDfs(graph[v][j], i);
                        }
                        return;
                    }
                }
            }
        }
        Utils u = new Utils();
        u.dfs(1);
        if (colorMask[1] == 0) {
            out.println("NO");
        } else {
            u.colorDfs(1, 0);
            out.println("YES");
            for (int i = 1; i < n + 1; ++i) {
                out.print(color[i] + " ");
            }
            out.println();
        }
        return true;
    }

}
