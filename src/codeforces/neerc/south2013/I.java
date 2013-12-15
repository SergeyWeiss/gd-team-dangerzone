package codeforces.neerc.south2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class I {
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
        final int n = nextInt();
        final int a = nextInt();
        final int b = nextInt();
        final List<List<Edge>> graph = new ArrayList<>();
        final int sz = n + 4;
        for (int i = 0; i < sz; ++i) {
            graph.add(new ArrayList<Edge>());
        }
        final int A = n + 1;
        final int B = n + 2;
        final int sink = n + 3;
        final int source = 0;

        final Comp[] comps = new Comp[n];
        for (int i = 0; i < n; ++i) {
            comps[i] = new Comp(nextInt(), nextInt(), i + 1);
        }
        Arrays.sort(comps);
        final int[][] capacity = new int[sz][sz];
        for (int i = 0; i < comps.length; ++i) {
            int type = comps[i].type;
            if (type == 1) {
                graph.get(comps[i].number).add(new Edge(A));
                graph.get(A).add(new Edge(comps[i].number));
                capacity[comps[i].number][A] = 1;
            } else if (type == 2) {
                graph.get(comps[i].number).add(new Edge(B));
                graph.get(B).add(new Edge(comps[i].number));
                capacity[comps[i].number][B] = 1;
            } else {
                graph.get(comps[i].number).add(new Edge(A));
                graph.get(A).add(new Edge(comps[i].number));
                graph.get(B).add(new Edge(comps[i].number));
                capacity[comps[i].number][A] = 1;
                capacity[comps[i].number][B] = 1;
                graph.get(comps[i].number).add(new Edge(B));
            }
        }
        for (int i = 0; i < n; ++i) {
            graph.get(source).add(new Edge(comps[i].number));
            graph.get(comps[i].number).add(new Edge(source));
            capacity[source][comps[i].number] = 1;
        }
        graph.get(A).add(new Edge(sink));
        graph.get(B).add(new Edge(sink));
        graph.get(sink).add(new Edge(A));
        graph.get(sink).add(new Edge(B));
        capacity[A][sink] = a;
        capacity[B][sink] = b;

        class MaxFlow {

            final boolean[] used = new boolean[sz];

            int maxFlow(int s, int f) {
                int maxFlow = 0;
                while (true) {
                    Arrays.fill(used, false);
                    if (!dfs(s, f)) {
                        return maxFlow;
                    }
                    maxFlow++;
                }
            }

            private boolean dfs(int s, int f) {
                if (s == f) {
                    return true;
                }
                used[s] = true;
                for (int i = 0; i < graph.get(s).size(); ++i) {
                    int to = graph.get(s).get(i).to;
                    if (!used[to] && capacity[s][to] > 0 && dfs(to, f)) {
                        capacity[s][to]--;
                        capacity[to][s]++;
                        return true;
                    }
                }
                return false;
            }
        }
        MaxFlow m = new MaxFlow();
        int flow = m.maxFlow(source, sink);
        out.print(flow + " ");
        int sum = 0;
        for (int i = 0; i < comps.length; ++i) {
            if (capacity[source][comps[i].number] == 0) {
                sum += comps[i].watt;
            }
        }
        out.println(sum);
        int useA = 0;
        int useB = 0;
        for (int i = 0; i < comps.length; ++i) {
            if (capacity[source][i + 1] == 0) {
                if (capacity[A][i + 1] == 1) {
                    out.println((i + 1) + " " + ++useA);
                } else {
                    out.println((i + 1) + " " + (a + ++useB));
                }
            }
        }
        out.close();
    }

    class Comp implements Comparable<Comp> {
        int type;
        int watt;
        int number;

        Comp(int type, int watt, int number) {
            this.type = type;
            this.watt = watt;
            this.number = number;
        }

        @Override
        public int compareTo(Comp o) {
            return Integer.compare(this.watt, o.watt);
        }
    }

    class Edge {
        int to;

        Edge(int to) {
            this.to = to;
        }
    }
}



