package timus.neerc.eastern2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class E {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public E() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new E().solve();
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
        final int nn = nextInt();
        final int kk = nextInt();
        class Utils {
            double[][] C = new double[nn + 1][nn + 1];
            double[] f = new double[nn + 1];

            {
                for (int N = 0; N <= nn; N++) {
                    C[N][0] = 1;
                    for (int K = 1; K <= N; K++) {
                        C[N][K] = C[N - 1][K - 1] + C[N - 1][K];
                    }
                }
                for (int i = 1; i <= nn; i++) {
                    f[i] = f[i - 1] + Math.log(i);
                }
            }

            boolean[][] visited = new boolean[nn + 1][kk + 1];
            double[][] dp = new double[nn + 1][kk + 1];

            double logC(int n, int k) {
                return f[n] - f[k] - f[n - k];
            }

            double expected(final int n, final int k) {
                if (visited[n][k]) {
                    return dp[n][k];
                }
                visited[n][k] = true;
                if (k == 0) {
                    return dp[n][k] = n;
                }
                int fair = n - k;
                if (k >= fair) {
                    return dp[n][k] = 0;
                }
                int currN = n - k;
                int good = currN - k;
                double cur = 0;
                for (int i = 1; i < currN; i++) {
                    double foo = 0;
                    for (int j = 0; j <= Math.min(i, k); j++) {
                        int s = i - j;
                        if (s > good) {
                            continue;
                        }
                        double p = Math.exp(logC(k, j) + logC(good, s) - logC(currN, i));
                        foo += p * expected(currN - i, k - j);
                    }
                    cur = Math.max(cur, foo);
                }
                return dp[n][k] = cur;
            }
        }
        out.printf(Locale.US, "%.10f\n", new Utils().expected(nn, kk));
        out.flush();
    }
}
