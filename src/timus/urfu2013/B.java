package timus.urfu2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public B() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new B().solve();
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

    private void solve() throws IOException {
        int n = nextInt();
        int k = nextInt();

        int[] array = new int[k];
        for (int i = 0; i < k; i++) {
            array[i] = nextInt() - 1;
        }

        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int i2 = 0; i2 < n; i2++) {
                cost[i][i2] = nextInt();
            }
        }

        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);

        for (int x : array) {
            d[x] = 0;
        }

        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            int u = -1;
            int dist = Integer.MAX_VALUE;

            for (int i2 = 0; i2 < n; i2++) {
                if (!used[i2] && d[i2] < dist) {
                    u = i2;
                    dist = d[u];
                }
            }
            if (u == -1) {
                break;
            }

            used[u] = true;
            for (int v = 0; v < n; v++) {
                if (!used[v])
                    d[v] = Math.min(d[v], cost[u][v]);
            }
        }

        int sum = 0;
        for (int x : d) {
            sum += x;
        }

        out.println(sum);
        out.flush();
    }
}
