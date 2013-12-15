package timus.urfu2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class I {

    private static final int MOD = 1000 * 1000 * 1000 + 7;
    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public I() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new I().solve();
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
        String str = next();
        char[] ch = str.toCharArray();
        final int n = str.length();
        int[] s = new int[n + 1];
        int[] p = new int[n + 1];
        int[] xs = new int[n + 1];
        int x = 1;
        for (int i = 1; i <= n; ++i) {
            xs[i - 1] = x;
            p[i] = sum(p[i - 1], mul(ch[i - 1] - 'a' + 1, x));
            s[i] = sum(s[i - 1], mul(ch[n - i] - 'a' + 1, x));
            x = mul(x, 31);

        }
        class SegTree {
            int q = 1;

            {
                while (q <= n) {
                    q *= 2;
                }
            }

            int[] a = new int[2 * q];
            int[] add = new int[2 * q];

            void addInterval(int l, int r, int value, int L, int R, int i) {
                if (R - L == 1) {
                    a[i] = sum(a[i], value);
                    return;
                }
                if (l == L && r == R) {
                    add[i] = sum(add[i], value);
                    return;
                }
                if (add[i] != 0) {
                    add[2 * i] = sum(add[2 * i], add[i]);
                    add[2 * i + 1] = sum(add[2 * i + 1], add[i]);
                    add[i] = 0;
                }
                int M = L + R >> 1;
                if (r <= M) {
                    addInterval(l, r, value, L, M, 2 * i);
                } else if (l >= M) {
                    addInterval(l, r, value, M, R, 2 * i + 1);
                } else {
                    addInterval(l, M, value, L, M, 2 * i);
                    addInterval(M, r, value, M, R, 2 * i + 1);
                }
            }

            void addInterval(int l, int r, int value) {
                addInterval(l, r, value, 0, q, 1);
            }

            int get(int index, int L, int R, int i) {
                if (R - L == 1) {
                    return sum(a[i], add[i]);
                }
                if (add[i] != 0) {
                    add[2 * i] = sum(add[2 * i], add[i]);
                    add[2 * i + 1] = sum(add[2 * i + 1], add[i]);
                    add[i] = 0;
                }
                int M = L + R >> 1;
                if (index >= M) {
                    return get(index, M, R, 2 * i + 1);
                } else {
                    return get(index, L, M, 2 * i);
                }
            }

            int get(int index) {
                return get(index, 0, q, 1);
            }
        }

        SegTree pt = new SegTree(), st = new SegTree();
        for (int i = 0; i <= n; i++) {
            pt.addInterval(i, i + 1, p[i]);
            st.addInterval(i, i + 1, s[i]);
        }

        int q = nextInt();
        for (int i = 0; i < q; ++i) {
            String req = next();
            if (req.equals("palindrome?")) {
                int a = nextInt();
                int b = nextInt();
                int cnt = b - a + 1;
                cnt /= 2;
                b = n - b + 1;
                int h1 = sum(pt.get(a + cnt - 1), MOD - pt.get(a - 1));
                h1 = mul(h1, xs[b]);

                int h2 = sum(st.get(b + cnt - 1), MOD - st.get(b - 1));
                h2 = mul(h2, xs[a]);
                if (h1 == h2) {
                    out.println("Yes");
                } else {
                    out.println("No");
                }
                // find ans
            } else {
                int index = nextInt() - 1;
                char change = next().charAt(0);
                char was = ch[index];
                ch[index] = change;
                pt.addInterval(index + 1, n + 1, mul(sum(change, MOD - was), xs[index]));
                st.addInterval(n - index, n + 1, mul(sum(change, MOD - was), xs[n - index - 1]));
                // update tree
            }
        }
        out.flush();
        out.close();
    }

    private int sum(int a, int b) {
        a += b;
        return a >= MOD ? a - MOD : a;
    }

    private int mul(int a, int b) {
        return (int) (1L * a * b % MOD);
    }
}
