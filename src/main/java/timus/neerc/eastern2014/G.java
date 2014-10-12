package timus.neerc.eastern2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public G() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new G().solve();
    }

    private void solve() throws IOException {
        final int n = nextInt();
        final int a = nextInt();
        final int b = nextInt();
        final int sz = 302;
        final int MOD = (int) (1e9 + 7);
        int[][][] dp = new int[2][2][sz];
        int[] type = new int[2];
        type[0] = a;
        type[1] = b;
        for (int j = 0; j < type.length; ++j) {
            dp[1][j][1] = 1;
        }

        for (int i = 1; i < n; ++i) {
            final int x = i % 2;
            for (int j = 0; j < type.length; ++j) {
                Arrays.fill(dp[x + 1 & 1][j], 0);
            }
            for (int j = 0; j < type.length; ++j) {
                for (int k = 1; k <= type[j]; ++k) {
                    dp[x + 1 & 1][j][k + 1] = (dp[x + 1 & 1][j][k + 1] + dp[x][j][k]) % MOD;
                    dp[x + 1 & 1][1 - j][1] = (dp[x + 1 & 1][1 - j][1] + dp[x][j][k]) % MOD;

                }
            }
        }
        int ans = 0;
        for (int j = 0; j < type.length; ++j) {
            for (int k = 1; k <= type[j]; ++k) {
                ans = (ans  + dp[n & 1][j][k]) % MOD;
            }
        }
        out.println(ans);
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
}
