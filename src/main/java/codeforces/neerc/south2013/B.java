package codeforces.neerc.south2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class B {

    private static final double EPS = 1e-6;

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public B() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new B().solve();
    }

    private void solve() throws IOException {
        int m = nextInt();
        int n = nextInt();
        int r = nextInt();

        double[] x = new double[n];
        double[] c = new double[m];

        for (int i = 0; i < m; i++) {
              c[i] = nextDouble();
        }
        for (int i = 0; i < n; i++) {
            x[i] = nextDouble();
        }
        int currentCameraPosition = 0;
        double totalDist = 0D;
        for (int i = 0; i < n; ++i) {
            double d = Math.abs(c[currentCameraPosition] - x[i]);
            while (gt(d * d + 1 , r * r)) {
                int newCameraPosition;
                if (c[currentCameraPosition] < x[i]) {
                    newCameraPosition = findLeftMostPosition(x[i], c, currentCameraPosition, c.length-1, r);
                } else {
                    newCameraPosition = findRightMostPosition(x[i], c, 0, currentCameraPosition, r);
                }
                totalDist += Math.abs(c[currentCameraPosition] - c[newCameraPosition]);
                currentCameraPosition = newCameraPosition;
                d = Math.abs(c[currentCameraPosition] - x[i]);
            }
        }

        out.printf(Locale.US, "%.3f", totalDist);
        out.close();
    }


    private boolean eq(double a, double b) {
        return Math.abs(a - b) < EPS;
    }

    private boolean ge(double a, double b) {
        return a > b || eq(a, b);
    }

    private boolean le(double a, double b) {
        return ge(b, a);
    }

    private boolean gt(double a, double b) {
        return a > b && !eq(a, b);
    }

    private int findRightMostPosition(double v, double[] c, int left, int right, int r) {
        while (right - left > 1) {
            int mid = (right + left) / 2;
            if (c[mid] < v) {
                left = mid;
            } else {
                double d = Math.abs(c[mid] - v);
                if (le(d * d + 1 , r * r)) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        return left;
    }

    private int findLeftMostPosition(double v, double[] c, int left, int right, int r) {
        while (right - left > 1) {
            int mid = (right + left) / 2;
            if (c[mid] > v) {
                right = mid;
            } else {
                double d = c[mid] - v;
                if (le(d * d + 1 , r * r)) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
        }
        return right;
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
