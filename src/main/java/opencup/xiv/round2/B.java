package opencup.xiv.round2;

import java.io.*;
import java.util.*;

public class B {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter writer = new PrintWriter(System.out);

    B() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader("b.in"));
    }

    long[] readLongs() throws IOException {
        String[] s = reader.readLine().split(" ");
        long[] longs = new long[s.length];
        for(int i = 0; i < longs.length; i++) {
            longs[i] = Long.parseLong(s[i]);
        }
        return longs;
    }

    int[] readInts() throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for(int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }

    int[] tt;
    int tx = 0;
    int readInt() throws IOException {
        if(tt == null || tx >= tt.length) {
            tt = readInts();
            tx = 0;
        }
        return tt[tx++];
    }

    void solve() throws IOException {
        final int n = readInt();
        int k = readInt();
        int a = readInt();
        int b = readInt();
        int[][] edges = new int[k][];
        for(int i = 0; i < k; i++) edges[i] = readInts();
        final int[] c = new int[n + 1];
        for(int[] e: edges) {
            for(int x: e) {
                c[x]++;
            }
        }
        final int[][] g = new int[n + 1][];
        for(int i = 1; i <= n; i++) {
            g[i] = new int[c[i]];
        }
        for(int[] e: edges) {
            g[e[0]][--c[e[0]]] = e[1];
            g[e[1]][--c[e[1]]] = e[0];
        }
        for(int i = 1; i <= n; i++) {
            Arrays.sort(g[i]);
        }
        final int INF = 1000 * 1000;
        int[] d = new int[n + 1];
        Arrays.fill(d, INF);
        d[1] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while(!q.isEmpty()) {
            int u = q.poll();
            for(int v: g[u]) {
                if(d[v] > d[u] + 1) {
                    d[v] = d[u] + 1;
                    q.add(v);
                }
            }
        }
        int ans = INF;
        if(1L * d[n] * a < ans) {
            ans = d[n] * a;
        }
        Set<Integer> canUse = new TreeSet<>();
        for(int i = 2; i <= n; i++) {
            canUse.add(i);
        }
        Arrays.fill(d, INF);
        d[1] = 0;
        q.add(1);
        int[] toRemove = new int[n + 1];
        int qtr = 0;
        while(!q.isEmpty()) {
            int u = q.poll();
            int ix = 0;
            for(int v: canUse) {
                while(ix < g[u].length && g[u][ix] < v) {
                    ix++;
                }
                if(ix < g[u].length && g[u][ix] == v) {
                    ix++;
                }
                else {
                    if(d[v] > d[u] + 1) {
                        toRemove[qtr++] = v;
                        d[v] = d[u] + 1;
                        q.add(v);
                    }
                }
            }
            while(qtr > 0) {
                canUse.remove(toRemove[--qtr]);
            }
        }
        if(1L * d[n] * b < ans) {
            ans = d[n] * b;
        }
        writer.println(ans);
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new B().solve();
    }
}