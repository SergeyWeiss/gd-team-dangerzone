package opencup.xiv.round4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class I {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public I() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        final I j = new I();
        j.solve();
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

    private void solve() throws IOException {
        int n = nextInt();
        int k = nextInt();
        int[][] positions = new int[1000 * 1000 + 1][];
        int[] sz = new int[1000 * 1000 + 1];
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
            sz[a[i]]++;
        }
        for (int i = 0; i < 1000 * 1000 + 1; i++) {
            if (sz[i] > 0) {
                positions[i] = new int[sz[i]];
                sz[i] = 0;
            }
        }
        int[] s = new int[n + 1];
        int[] prev = new int[n];
        Arrays.fill(prev, -1);
        for (int i = n - 1; i >= 0; i--) {
            int[] pos = positions[a[i]];
            pos[sz[a[i]]++] = i;
            if (sz[a[i]] >= k) {
                s[i] = Math.max(s[i + 1], s[pos[sz[a[i]] - k] + 1] + 1);
                prev[i] = pos[sz[a[i]] - k];
            } else {
                s[i] = s[i + 1];
            }
        }
        out.println(s[0] * k);
        int[] ans = new int[s[0]];
        Arrays.fill(ans, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (s[i] == 0) break;
            int si = s[0] - s[i];
            int r = 0;
            for (int j = i; s[i] == s[j]; j++) {
                if (prev[j] != -1 && a[j] < ans[si] && s[prev[j] + 1] >= s[i] - 1) {
                    ans[si] = a[j];
                    r = prev[j];
                    r = Math.max(r, j);
                }
            }
            i = r;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < k; j++) {
                sb.append(ans[i]);
                sb.append(" ");
            }
        }
        out.println(sb);
        out.flush();
    }

}
