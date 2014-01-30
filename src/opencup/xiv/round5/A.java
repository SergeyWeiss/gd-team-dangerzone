package opencup.xiv.round5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public A() throws IOException {
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        br = new BufferedReader(new FileReader("bag-repacking.in"));
        out = new PrintWriter(new FileWriter("bag-repacking.out"));
    }

    public static void main(String[] args) throws IOException {
        final A j = new A();
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
        final int[] p1 = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            p1[i] = nextInt();
        }
        final int[] p2 = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            p2[i] = nextInt();
        }
        final List<int[]> unpackOps = new ArrayList<>();
        final List<int[]> packOps = new ArrayList<>();
        class Utils {
            void unpack(int i) {
                if(p1[i] == 0) {
                    return;
                }
                unpack(p1[i]);
                unpackOps.add(new int[]{i, p1[i]});
                p1[i] = 0;
            }

            void pack(int i) {
                for(int j = 1; j <= n; j++) {
                    if(p2[j] == i) {
                        pack(j);
                    }
                }
                if(p1[i] != p2[i]) {
                    packOps.add(new int[] {i, p2[i]});
                    p1[i] = p2[i];
                }
            }
        }
        Utils utils = new Utils();
        for(int i = 1; i <= n; i++) {
            if(p1[i] != p2[i]) utils.unpack(i);
            for(int j = 1; j <= n; j++) {
                if(p1[j] != p2[j] && p2[j] == i) {
                    utils.unpack(i);
                }
            }
        }
        for(int i = 1; i <= n; i++) {
            if(p1[i] != p2[i]) utils.pack(i);
        }
        out.println(packOps.size() + unpackOps.size());
        for(int[] a: unpackOps) {
            out.printf("out %d %d\n", a[0], a[1]);
        }
        for(int[] a: packOps) {
            out.printf("in %d %d\n", a[0], a[1]);
        }
        out.close();
    }

}
