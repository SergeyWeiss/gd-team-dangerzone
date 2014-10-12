package timus.urfu2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public A() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new A().solve();
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
        long n = a;
        if (n == 4) {
            out.println("0 2");
        } else if (n == 5) {
            out.println("0 0");
        } else if (n == 6) {
            out.println("6 9");

        } else if (n % 2 == 1) {
            out.println(n * (n - 3) / 2 + " 0");
        } else {
            out.println((n / 2 * (n - 3)) + " " + (n / 2 * (n - 3)));

        }
        out.close();
    }
}
