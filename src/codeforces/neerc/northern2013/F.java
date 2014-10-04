package codeforces.neerc.northern2013;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class F {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public F() throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("flight.in")));
        out = new PrintWriter(new FileOutputStream("flight.out"));
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new F().solve();
    }

    private void solve() throws IOException {
        final int n = nextInt();
        final int s = nextInt();
        final int k = nextInt();
        final int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = nextInt();
        }
        class SegmentTree {
            int q = 1024;
            int[] a = new int[q * 2];

            void inc(int i) {
                for (i += q; i > 0; i /= 2) {
                    a[i]++;
                }
            }

            int sum(int l, int r) {
                int result = 0;
                for (l += q, r += q; l < r; l /= 2, r /= 2) {
                    if (l % 2 == 1) {
                        result += a[l++];
                    }
                    if (r % 2 == 1) {
                        result += a[--r];
                    }
                }
                return result;
            }

            void clear() {
                Arrays.fill(a, 0);
            }
        }
        class Utils {
            List<List<Integer>> lists = new ArrayList<>();
            int[][] f = new int[s + 1][s + 1];

            {
                for (int i = 0; i <= s; i++) {
                    lists.add(new ArrayList<Integer>());
                }
                for (int i = 0; i < n; i++) {
                    lists.get(r[i]).add(i);
                }
                SegmentTree segmentTree = new SegmentTree();
                for (int row1 = 1; row1 <= s; row1++) {
                    segmentTree.clear();
                    int sum = 0;
                    for (int row2 = row1; row2 <= s; row2++) {
                        final List<Integer> list = lists.get(row2);
                        for (int index = list.size() - 1; index >= 0; index--) {
                            int ii = list.get(index);
                            sum += segmentTree.sum(0, ii);
                            segmentTree.inc(ii);
                        }
                        f[row1][row2] = sum;
                    }
                }
            }

            int INF = 1000000;

            int rec(int phases, int rows) {
                if (phases == k) {
                    return rows == s ? 0 : INF;
                }
                if (rows == s) {
                    return INF;
                }
                int result = INF;
                for (int i = rows + 1; i <= s; i++) {
                    result = Math.min(result, rec(phases + 1, i) + f[rows + 1][i]);
                }
                return result;
            }
        }

        class UtilsWithCache extends Utils {
            int[][] cache = new int[k + 1][s + 1];
            boolean[][] visited = new boolean[k + 1][s + 1];

            @Override
            int rec(int phases, int rows) {
                if (!visited[phases][rows]) {
                    visited[phases][rows] = true;
                    cache[phases][rows] = super.rec(phases, rows);
                }
                return cache[phases][rows];
            }
        }
        out.println(new UtilsWithCache().rec(0, 0));
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

