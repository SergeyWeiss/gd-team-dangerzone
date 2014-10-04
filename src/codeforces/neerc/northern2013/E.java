package codeforces.neerc.northern2013;

import java.io.*;
import java.util.*;

public class E {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public E() throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("energy.in")));
        out = new PrintWriter(new FileOutputStream("energy.out"));
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new E().solve();
    }

    private void solve() throws IOException {
        final int n = nextInt();
        char[] a = next().toCharArray();
        int l = 0;
        int[] q = new int[3];
        long ans = 0;
        for (char c : a) {
            int x = c - '0';
            if (x == 1) {
                if (q[1] + 2 * q[2] < l) {
                    q[1]++;
                } else if (l < n) {
                    q[1]++;
                    l++;
                } else if (q[2] > 0) {
                    q[2]--;
                    q[1]++;
                }
            } else {
                if (l + 2 <= n) {
                    q[2]++;
                    l += 2;
                }
            }
            ans += q[1] + q[2];
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
