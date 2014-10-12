package codeforces.neerc.south2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class C {
    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public C() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new C().solve();
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    private void solve() throws IOException {
        int n = nextInt();
        Map<Long, ArrayList<Integer>> ans = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            double ellipseSq = (Math.PI * dist(nextDouble(), nextDouble(), nextDouble(), nextDouble()) * dist(nextDouble(), nextDouble(), nextDouble(), nextDouble())) / 4.0d;
            int r = nextInt();
            double paralSq = 0.0d;
            for (int j = 0; j < r; ++j) {
                paralSq += square(nextDouble(), nextDouble(), nextDouble(), nextDouble(), nextDouble(), nextDouble(), nextDouble(), nextDouble());
            }
            long key = (long) ((ellipseSq / paralSq) * 10000);
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<Integer>());
            }
            ans.get(key).add(i);

        }
        int[] ret = new int[n];
        int pos = 1;
        for (Map.Entry<Long, ArrayList<Integer>> e: ans.entrySet()) {
            ArrayList<Integer> arr = e.getValue();
            for (int i = 0; i < arr.size(); ++i) {
                ret[arr.get(i)] = pos;
            }
            ++pos;
        }
        for (int i = 0; i < ret.length; ++i) {
            out.print(ret[i] + " ");
        }
        out.close();
    }

    private double square(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)) + Math.abs((x3 - x2) * (y4 - y2) - (y3 - y2) * (x4 - x2));
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
