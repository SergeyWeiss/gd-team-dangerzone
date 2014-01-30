package codeforces.neerc.south2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class E {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public E() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
//        for (int i = 0; i < 1000; ++i) {
//            new  E().test();
//        }
        new E().solve();
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

    private long[] naive(int[] iqs, int[] q) {
        int[] soldiers = new int[iqs.length];
        System.arraycopy(iqs, 0, soldiers, 0, iqs.length);
        long[] ans = new long[q.length + 1];
        ans[0] = naiveCountInversions(soldiers);
        for (int i = 0; i < q.length; ++i) {
            final int num = q[i] - 1;
            final int iq = soldiers[num];
            for (int j = num; j < soldiers.length; ++j) {
                for (int k = j + 1; k < soldiers.length; ++k) {
                    if (soldiers[k] <= iq && soldiers[j] <= iq) {
                        if (soldiers[j] > soldiers[k]) {
                            int t = soldiers[k];
                            soldiers[k] = soldiers[j];
                            soldiers[j] = t;
                        }
                    }
                }
            }
            ans[i + 1] = naiveCountInversions(soldiers);
        }
        return ans;
    }

    private int naiveCountInversions(int[] iq) {
        int ans = 0;
        for (int i = 0; i < iq.length; ++i) {
            for (int j = i + 1; j < iq.length; ++j) {
                if (iq[i] > iq[j]) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private long[] solve(int[] iq, int[] q) {
        final int n = iq.length;
        final int m = q.length;
        class MaxSegTree {
            int q = 1;
            int[] a;

            {
                while (q <= n) {
                    q *= 2;
                }
                a = new int[q * 2];
            }

            void add(int i, int x) {
                for (i += q; i > 0; i >>= 1) {
                    a[i] = Math.max(a[i], x);
                }
            }

            int max(int left, int right) {
                int ans = 0;
                for (left += q, right += q; left < right; left >>= 1, right >>= 1) {
                    if (left % 2 == 1) {
                        ans = Math.max(ans, a[left++]);
                    }
                    if (right % 2 == 1) {
                        ans = Math.max(ans, a[--right]);
                    }
                }
                return ans;
            }


        }
        class MinSegTree {

            int q = 1;
            int[] a;


            {
                while (q <= n) {
                    q *= 2;
                }
                a = new int[q * 2];
                Arrays.fill(a, Integer.MAX_VALUE);
            }

            void add(int i, int x) {
                for (i += q; i > 0; i >>= 1) {
                    a[i] = Math.min(a[i], x);
                }
            }

            int min(int left, int right) {
                int ans = Integer.MAX_VALUE;
                for (left += q, right += q; left < right; left >>= 1, right >>= 1) {
                    if (left % 2 == 1) {
                        ans = Math.min(ans, a[left++]);
                    }
                    if (right % 2 == 1) {
                        ans = Math.min(ans, a[--right]);
                    }
                }
                return ans;
            }


        }
        class SumSegTree {
            int q = 1;
            long[] a;

            {
                while (q <= n) {
                    q *= 2;
                }
                a = new long[q * 2];

            }

            void add(int i, int x) {
                for (i += q; i > 0; i >>= 1) {
                    a[i] += x;
                }
            }

            long sum(int left, int right) {
                long ans = 0;
                for (left += q, right += q; left < right; left >>= 1, right >>= 1) {
                    if (left % 2 == 1) {
                        ans += a[left++];
                    }
                    if (right % 2 == 1) {
                        ans += a[--right];
                    }
                }
                return ans;
            }

        }
        Soldier[] soldiers = new Soldier[n];
        for (int i = 0; i < n; ++i) {
            soldiers[i] = new Soldier(i, iq[i]);
        }
        Arrays.sort(soldiers);
        int[] r = new int[n];
        SumSegTree sst = new SumSegTree();
        for (int i = 0; i < n; i++) {
            Soldier s = soldiers[i];
            r[s.pos] = (int) sst.sum(s.pos, n);
            sst.add(s.pos, 1);
        }
        int[] qbase = new int[n];
        Arrays.fill(qbase, -1);
        MaxSegTree mst = new MaxSegTree();
        for (int i = 0; i < m; i++) {

            int s = q[i] - 1;
            if (qbase[s] != -1) {
                continue;
            }
            if (mst.max(0, s) >= iq[s]) {

            } else {
                qbase[s] = i;
                mst.add(s, iq[s]);
            }
        }
        for (int i = 0; i < n; i++) {
            Soldier s = soldiers[i];
            if (qbase[s.pos] != -1)
                s.setQbase(qbase[s.pos]);
        }
        Arrays.sort(soldiers);
        MinSegTree minst = new MinSegTree();
        int[] qc = new int[m]; //how many soldiers rearrange
        for (int i = n - 1; i >= 0; i--) {
            int s = soldiers[i].pos;
            if (qbase[s] != -1) {
                minst.add(s, qbase[s]);
            } else {
                qbase[s] = minst.min(0, s); // can be INF

            }
            if (qbase[s] < Integer.MAX_VALUE) {
                qc[qbase[s]]++;
            }
        }
        int[][] rearrangements = new int[m][];
        for (int i = 0; i < m; i++) {
            rearrangements[i] = new int[qc[i]];
        }
        for (int i = 0; i < n; i++) {
            if (qbase[i] < Integer.MAX_VALUE) {
                int qb = qbase[i];
                rearrangements[qb][--qc[qb]] = i;
            }
        }

        Arrays.fill(sst.a, 0);
        for (int i = 0; i < n; i++) {
            sst.add(i, r[i]);
        }
        long[] ans = new long[m + 1];
        ans[0] = (sst.sum(0, n));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < rearrangements[i].length; j++) {
                int s = rearrangements[i][j];
                sst.add(s, -r[s]);
            }
            ans[i + 1] = (sst.sum(0, n));
        }
        return ans;
    }

    private void solve() throws IOException {
        final int n = nextInt();
        final int m = nextInt();
        int[] iq = new int[n];
        for (int i = 0; i < n; i++) {
            iq[i] = nextInt();
        }
        int[] q = new int[m];
        for (int i = 0; i < m; i++) {
            q[i] = nextInt();
        }
        long[] ans = solve(iq, q);
        for (int i = 0; i < ans.length; i++) {
            out.println(ans[i]);
        }
        out.flush();
    }

    private void test() {
        int n = 5;
        int[] iq = new int[n];
        int[] q = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; ++i) {
            iq[i] = r.nextInt(n + 5) + 1;
            q[i] = r.nextInt(n) + 1;
        }

        long[] naive = naive(iq, q);
        long[] solve = solve(iq, q);
        for (int i = 0; i < naive.length; ++i) {
            if (naive[i] != solve[i]) {
                out.println(n + " " + n);
                for (int j = 0; j < n; ++j) {
                    out.print(iq[j] + " ");
                }
                out.println();
                for (int j = 0; j < n; ++j) {
                    out.print(q[j] + " ");
                }
                out.println();
                for (int j = 0; j < n + 1; j++) {
                    out.print(naive[j] + " ");
                }
                out.println();
                for (int j = 0; j < n + 1; j++) {
                    out.print(solve[j] + " ");
                }

                out.close();
                System.exit(0);
            }
        }
    }

    class Soldier implements Comparable<Soldier> {
        int pos;
        int iq;
        int qbase = Integer.MAX_VALUE;

        public void setQbase(int qbase) {
            this.qbase = qbase;
        }

        Soldier(int pos, int iq) {
            this.pos = pos;
            this.iq = iq;
        }

        @Override
        public int compareTo(Soldier o) {
            if (this.iq == o.iq) {
                return Integer.compare(o.qbase, this.qbase);
            }
            return Integer.compare(this.iq, o.iq);
        }
    }
}