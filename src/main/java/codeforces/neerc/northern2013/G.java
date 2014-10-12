package codeforces.neerc.northern2013;

import java.io.*;
import java.util.*;

public class G {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public G() throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("garage.in")));
        out = new PrintWriter(new FileOutputStream("garage.out"));
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new G().solve();
    }

    private void solve() throws IOException {
        final int W = nextInt();
        final int H = nextInt();
        final int w = nextInt();
        final int h = nextInt();

        int ww = calcL(W, w);
        int hh = calcL(H, h);

        long result = 1L * ww * hh;

        out.println(result);
        out.close();
    }

    private int calcL(int L, int l) {
        int i = L - l;
        int j = 2 * l;
        if (i % j == 0) {
            return i / j + 1;
        } else {
            return i / j + 1;
        }
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

