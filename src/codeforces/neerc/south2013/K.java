package codeforces.neerc.south2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class K {
    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public K() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new K().solve();
    }

    boolean can(int[] a, int l, int r) {
        for(int i = 1; i <= r - l; i++) {
            if(a[l + i - 1] < i) {
                return false;
            }
        }
        return true;
    }
    boolean canReverse(int[] a, int l, int r) {
        for(int i = 1; i <= r - l; i++) {
            if(a[r - i] < i) {
                return false;
            }
        }
        return true;
    }

    private void solve() throws IOException {
        int n = nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        List<Integer> ans1 = new ArrayList<>(), ans2 = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int lo = 1;
            int hi = n - i + 1;
            while(hi - lo > 1) {
                int med = lo + hi >> 1;
                if(can(a, i, i + med) || canReverse(a, i, i + med)) {
                    lo = med;
                }
                else {
                    hi = med;
                }
            }
            if(can(a, i, i + lo)) {
                ans1.add(i + 1);
                ans2.add(i + lo);
            }
            else {
                ans1.add(i + lo);
                ans2.add(i + 1);
            }
            i += lo - 1;

        }
        out.println(ans1.size());
        for(int i = 0; i < ans1.size(); i++) {
            out.println(ans1.get(i) + " " + ans2.get(i));
        }
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

}
