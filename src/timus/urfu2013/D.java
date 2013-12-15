package timus.urfu2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class D {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public D() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new D().solve();
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
        if (n == 1) {
            out.println(1);
        } else {
            double a = Math.PI / n;
            out.printf(Locale.US, "%.10f", 1.0d / Math.sin(a) + 1);
        }
        out.flush();
    }
}
