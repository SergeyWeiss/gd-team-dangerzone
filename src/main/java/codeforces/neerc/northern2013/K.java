package codeforces.neerc.northern2013;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class K {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public K() throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("kids.in")));
        out = new PrintWriter(new FileOutputStream("kids.out"));
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new K().solve();
    }

    class Edge {
        int u, v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    class Item implements Comparable<Item> {
        int u;
        int friends;

        Item(int u, int friends) {
            this.u = u;
            this.friends = friends;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(friends, o.friends);
        }
    }

    Edge[] generate(int n, int k) {
        int e = n * k / 2;
        Edge[] result = new Edge[e];
        e = 0;
        int[] f = new int[n];
        for(int i = 0; i < n; i++) {
            PriorityQueue<Item> q = new PriorityQueue<>();
            for(int j = i + 1; j < n; j++) {
                q.add(new Item(j, f[j]));
            }
            for(int j = f[i]; j < k; j++) {
                result[e++] = new Edge(i, q.peek().u);
                f[q.poll().u]++;
                f[i]++;
            }
        }
        for(int i = 0; i < n; i++) {
            if(f[i] != k) {
                throw new RuntimeException();
            }
        }
        return result;
    }

    private void solve() throws IOException {
        int a = nextInt();
        int b = nextInt();
        int c = nextInt();
        int d = nextInt();
        int g = gcd(b, c);
        int bg = b / g;
        int cg = c / g;
        for (int boys = bg; ; boys += bg) {
            int girls = boys / bg * cg;
            if(boys < d + 1 || girls < a + 1) {
                continue;
            }
            if(boys * d % 2 == 1 || girls * a % 2 == 1) {
                continue;
            }
            if(boys < b || girls < c) {
                continue;
            }
            out.println(girls + " " + boys);
            for (Edge edge : generate(girls, a)) {
                out.printf("%d %d\n", edge.u + 1, edge.v + 1);
            }
            for (Edge edge : generate(boys, d)) {
                out.printf("%d %d\n", edge.u + girls + 1, edge.v + girls + 1);
            }
            int current_boy = girls + 1;
            for(int i = 0; i < girls; i++) {
                for(int j = 0; j < b; j++) {
                    out.printf("%d %d\n", i + 1, current_boy);
                    current_boy++;
                    if(current_boy > boys + girls) {
                        current_boy = girls + 1;
                    }
                }
            }
            break;
        }
        out.close();
    }

    int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
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

