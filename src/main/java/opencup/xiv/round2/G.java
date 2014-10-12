package opencup.xiv.round2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public G() throws IOException {
        br = new BufferedReader(new FileReader("g.in"));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new G().solve();
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
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
            b[i] = nextInt();
            c[i] = nextInt();
        }
        int[][] dp = new int[n + 1][4];
        for (int i = 0; i < n + 1; ++i) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][0] = 0;
        for (int k = 0; k < n; ++k) {
            for (int j = 0; j < 4; ++j) {
                if (dp[k][j] == Integer.MIN_VALUE) {
                    continue;
                }
                int prevBit = j & 1;
                int prevPrevBit = j >> 1;
                dp[k + 1][prevBit << 1] = Math.max(dp[k + 1][prevBit << 1], dp[k][j]);
                int cost = a[k];
                if (prevBit == 1) {
                    cost -= a[k];
                    cost += b[k];
                }
                if (prevBit == 1 && prevPrevBit == 0) {
                    cost -= a[k - 1];
                    cost += b[k - 1];
                }
                if (prevBit == 1 && prevPrevBit == 1) {
                    cost -= b[k - 1];
                    cost += c[k - 1];
                }
                dp[k + 1][(prevBit << 1) + 1] = Math.max(cost + dp[k][j], dp[k + 1][(prevBit << 1) + 1]);
            }
        }
        int ans = 0;
        for (int i = 0; i < 4; ++i) {
            ans = Math.max(ans, dp[n][i]);
        }
        out.println(ans);
        out.flush();
        out.close();
    }
}
