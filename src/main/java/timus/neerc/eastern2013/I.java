package timus.neerc.eastern2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    private void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        int[] t = new int[m + 1];
        for(int i = 0; i < m; i++) {
            t[i + 1] = nextInt();
        }
        int[] d = new int[n];
        int ix = 0;
        int s = 0;
        for(int i = 1; i < n; i++) {
            s += a[i - 1]; //pick up the stone
            while(s - a[ix] > k) {
                s -= a[ix];
                ix++;
            }
            d[i] = ix;
        }
        int stones = 0;
        int ans = 0;
        for(int i = 1; i < t.length; i++) {
            int dt = t[i] - t[i - 1];
            stones += dt - 1;
            if(stones >= n) {
                ans = t[i] - 1 + n - stones;
                break;
            }
            stones = d[stones]; //drop stones
        }
        if(stones < n) {
            ans = t[m] + (n - stones);
        }
        out.println(ans);
        out.flush();
    }

}
