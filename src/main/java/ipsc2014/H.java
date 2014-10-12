package ipsc2014;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

public class H {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public H() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new FileWriter("src/ipsc2014/h.out"));
    }

    public static void main(String[] args) throws IOException {
        final H j = new H();
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

    int max(int x, int y) {
        return (((y - x) >> (32 - 1)) & (x ^ y)) ^ y;
    }

    int min(int x, int y) {
        return (((y - x) >> (32 - 1)) & (x ^ y)) ^ x;
    }

    private void solve() throws IOException {
        Random r = new Random();
        int count = 0;
        while (count < 50_000) {
            long p1 = (long) r.nextInt();
            long p2 = p1;
            long p3 = (p1 << 32) + p2;
            if (p3 % 62233 == 0) {
                out.print(p3);
//            out.print(" ");
                out.println();
                ++count;
            }
        }
        out.flush();
    }


}
