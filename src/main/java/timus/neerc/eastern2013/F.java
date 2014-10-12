package timus.neerc.eastern2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class F {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public F() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new F().solve();
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
        int n = nextInt();
        int k = nextInt();
        int p = nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n - k; ++i) {
            arr.add(1);
        }
        int current = 2;
        int count = 0;
        for (int i = 0; i < k; ++i) {
            int total = arr.size();
            int lighter = total - count;
            if (lighter * 100 >= p * total) {
                arr.add(current);
                ++count;
            } else {
                arr.add(++current);
                count = 1;
            }
        }
        long ans = 0;
        for (int i = 0; i < arr.size(); ++i) {
            ans += arr.get(i);
        }
        out.println(ans);
        for (int i = 0; i < arr.size(); ++i) {
            out.print(arr.get(i) + " ");
        }
        out.flush();
    }

}
