//package opencup.xiv.round1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public G() throws IOException {
        br = new BufferedReader(new FileReader("medals.in"));
        out = new PrintWriter(new FileWriter("medals.out"));
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new G().solve();
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
        int[][] disciplines = new int[n + 1][];
        int[][] medalWon = new int[n + 1][];
        final int source = 1000 + n + 1;
        final int sink = source + 1;
        final int total = sink + 1;
        final int[] c = new int[total];
        for(int i = 1; i <= n; i++) {
            int k = nextInt();
            disciplines[i] = new int[k];
            medalWon[i] = new int[k];
            for(int j = 0; j < k; j++) {
                disciplines[i][j] = nextInt();
                c[n + disciplines[i][j]]++;
                medalWon[i][j] = nextInt();
            }
            c[i] = k + 1; //disciplines + source
        }
        for(int i = 1; i <= 1000; i++) {
            c[n + i]++; //edge to sink
        }
        c[source] = n;
        c[sink] = 1000;
        final int[][] medal = new int[total][total];
        final int[][] multiplier = new int[total][total];
        final int[][] g = new int[total][];
        for(int i = 1; i <= sink; i++) {
            g[i] = new int[c[i]];
        }
        final int[] prev = new int[total];
        final int[][] pi = new int[total][11];
        final int[][] dist = new int[total][11];
        final boolean[] used = new boolean[total];
        final int[][] cap = new int[total][total];

        class Utils {
            final int INF = 1 << 20;
            void addEdge(int from, int to, int medalValue) {
                g[from][--c[from]] = to;
                g[to][--c[to]] = from;
                medal[from][to] = medal[to][from] = medalValue;
                multiplier[from][to] = 1;
                multiplier[to][from] = -1;
                cap[from][to] = 1;
            }
            boolean increaseFlow() {
                prev[sink] = -1;
                for(int i = 0; i < total; i++) {
                    dist[i][10] = INF;
                }
                for(int i = 1; i <= 10; i++) {
                    dist[source][i] = 0;
                }
                Arrays.fill(used, false);
                for(int k = 0; k < total; k++) {
                    int u = 0;
                    for(int i = 1; i < total; i++) {
                        if(used[i]) {
                            continue;
                        }
                        for(int j = 10; j > 0; j--) {
                            if(dist[i][j] == dist[u][j]) {
                                continue;
                            }
                            if(dist[i][j] < dist[u][j]) {
                                u = i;
                            }
                            break;
                        }
                    }
                    if(u == 0) {
                        break;
                    }
                    used[u] = true;
                    for(int i = 0; i < g[u].length; i++) {
                        int v = g[u][i];
                        if(cap[u][v] == 0) {
                            continue;
                        }
                        boolean better = false;
                        for(int j = 10; j > 0; j--) {
                            int relaxed = dist[u][j] + (medal[u][v] == j ? multiplier[u][v] : 0) + pi[u][j] - pi[v][j];
                            if(better) {
                                dist[v][j] = relaxed;
                                continue;
                            }
                            if(dist[v][j] == relaxed) {
                                continue;
                            }
                            if(dist[v][j] < relaxed) {
                                break;
                            }
                            dist[v][j] = relaxed;
                            better = true;
                        }
                        if(better) {
                            prev[v] = u;
                        }
                    }
                }
                for(int i = 1; i < total; i++) {
                    for(int j = 1; j <= 10; j++) {
                        pi[i][j] += dist[i][j];
                        if(pi[i][j] > INF) {
                            pi[i][j] = INF;
                        }
                    }
                }
                return prev[sink] != -1;
            }
            int[] best = new int[11];
            int[] assignment = new int[n + 1];
            void minCostMaxFlow() {
                int[] dm = new int[11];
                while(true) {
                    Arrays.fill(dm, 0);
                    if(!increaseFlow()) {
                        break;
                    }
                    for(int v = sink; v != source; v = prev[v]) {
                        int u = prev[v];
                        dm[medal[u][v]] += multiplier[u][v];
                    }
                    boolean ok = true;
                    for(int i = 1; i <= 10; i++) {
                        if(dm[i] > 0) {
                            break;
                        }
                        if(dm[i] < 0) {
                            ok = false;
                            break;
                        }
                    }
                    if(!ok) {
                        break;
                    }
                    for(int i = 1; i <= 10; i++) {
                        best[i] += dm[i];
                    }
                    for(int v = sink; v != source; v = prev[v]) {
                        int u = prev[v];
                        cap[u][v]--;
                        cap[v][u]++;
                    }
                }
                for(int u = 1; u <= n; u++) {
                    for(int i = 0; i < g[u].length; i++) {
                        int v = g[u][i];
                        if(v != source && cap[u][v] == 0) {
                            assignment[u] = v - n;
                        }
                    }
                }
            }
        }
        Utils utils = new Utils();
        for(int i = 1; i <= n; i++) {
            utils.addEdge(source, i, 0);
        }
        for(int i = 1; i <= 1000; i++) {
            utils.addEdge(n + i, sink, 0);
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < disciplines[i].length; j++) {
                utils.addEdge(i, n + disciplines[i][j], medalWon[i][j]);
            }
        }
        utils.minCostMaxFlow();
        for(int i = 1; i <= 10; i++) {
            out.print(utils.best[i] + " ");
        }
        out.println();
        for(int i = 1; i <= n; i++) {
            out.print(utils.assignment[i] + " ");
        }
        out.println();
        out.flush();
        out.close();
    }
}
