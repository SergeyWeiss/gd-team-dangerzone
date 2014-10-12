package opencup.xiv.round4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
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
        final F j = new F();
        j.solve();
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

    private void solve() throws IOException {
        final int n = nextInt();
        final int k = nextInt();
        final int[][] g = new int[n][];
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n - 1; ++i) {
            a[i] = nextInt() - 1;
            b[i] = nextInt() - 1;
            c[a[i]]++;
            c[b[i]]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[c[i]];
        }
        for (int i = 0; i < n - 1; i++) {
            g[a[i]][--c[a[i]]] = b[i];
            g[b[i]][--c[b[i]]] = a[i];
        }
        for (int i = 0; i < n; i++) {
            c[i] = g[i].length;
        }
        boolean[] used = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        if (k == 1) {
            out.println("1\n1");
            out.flush();
            return;
        }
        int levels = k / 2 - 1;
        for (int i = 0; i < n; i++) {
            if (c[i] <= 1) {
                q.add(i);
                used[i] = true;
            }
        }
        for (int l = 0; l < levels; l++) {
            int szq = q.size();
            if (szq == 0) break;
            for (int j = 0; j < szq; j++) {
                int u = q.poll();
                for (int i = 0; i < g[u].length; i++) {
                    int v = g[u][i];
                    if (!used[v]) {
                        c[v]--;
                        if (c[v] <= 1) {
                            q.add(v);
                            used[v] = true;
                        }
                    }
                }
            }
        }
        if (k % 2 == 1) {
            for (int i = 0; i < n; i++) {
                if (!used[i]) {
                    used[i] = true;
                    break;
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (used[i]) cnt++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnt);
        sb.append("\n");
        for (int i = 0; i < n; i++) {
            if (used[i]) {
                sb.append(i + 1);
                sb.append(" ");
            }
        }
        out.println(sb);
        out.flush();
    }

}
