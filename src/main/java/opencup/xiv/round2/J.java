package opencup.xiv.round2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class J {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public J() throws IOException {
        br = new BufferedReader(new FileReader("j.in"));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new J().solve();
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
        int a = nextInt();
        int b = nextInt();
        int m = (b - a + 1);
        int ans = (m - 1) * m / 2;
        out.println(ans);
        out.flush();
        out.close();
    }
}
