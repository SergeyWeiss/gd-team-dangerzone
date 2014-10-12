//package opencup.xiv.round13;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class G {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public G() throws IOException {
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        br = new BufferedReader(new FileReader("randommst.in"));
        out = new PrintWriter(new FileWriter("randommst.out"));
    }

    public static void main(String[] args) throws IOException {
        final G j = new G();
        j.solveMany();
    }

    public void solveMany() throws IOException {
        while (solve()) {
            out.flush();
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

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    private boolean solve() throws IOException {
        final int n = nextInt();
        final int[][] dots = new int[n][2];
        for (int i = 0; i < n; ++i) {
            dots[i][0] = nextInt();
            dots[i][1] = nextInt();
        }

        final int[][] graph = new int[n][];
        if (n <= 50000) {
            generateForSmallN(dots, graph);
        } else {
            generateForBigN(dots, graph);
        }
        final int[] neighbour = new int[n];
        Arrays.fill(neighbour, -1);
        class Utils {
            public BigDecimal dijkstra() {
                BigDecimal ans = BigDecimal.ZERO;
                Queue<Pair> queue = new PriorityQueue<>();
                boolean[] used = new boolean[n];
                int[] dist = new int[n];
                Arrays.fill(dist, Integer.MAX_VALUE);
                dist[0] = 0;
                queue.add(new Pair(0, 0.0d));
                while (!queue.isEmpty()) {
                    final Pair p = queue.poll();
                    final int u = p.index;
                    if (used[u]) continue;
                    ans = ans.add(sqrt(dist[u]));
                    used[u] = true;
                    for (int i = 0; i < graph[u].length; ++i) {
                        int v = graph[u][i];
                        final int d = dist(dots[u], dots[v]);
                        if (d < dist[v]) {
                            dist[v] = d;
                            neighbour[v] = u;
                            queue.add(new Pair(v, d));
                        }
                    }
                }
                return ans;
            }

            BigDecimal sqrt(int x) {
                BigDecimal lo = BigDecimal.ZERO;
                BigDecimal hi = BigDecimal.valueOf(x);
                for(int i = 0; i < 100; i++) {
                    BigDecimal med = lo.add(hi).divide(BigDecimal.valueOf(2));
                    if(med.multiply(med).compareTo(BigDecimal.valueOf(x)) >= 0) hi = med;
                    else lo = med;
                }
                return hi;
            }
        }
        Utils u = new Utils();
        out.println(u.dijkstra().toPlainString());
//        out.printf(Locale.US, "%.20f\n", u.dijkstra());
        for (int i = 0; i < n; ++i) {
            if (neighbour[i] != -1) {
                out.println((i + 1) + " " + (neighbour[i] + 1));
            }
        }
        return false;
    }



    private int dist(int[] dot, int[] dot1) {
        return ((dot[0] - dot1[0]) * (dot[0] - dot1[0]) + (dot[1] - dot1[1]) * (dot[1] - dot1[1]));
    }


    class Pair implements Comparable<Pair> {

        int index;
        double dist;

        Pair(int index, double dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair o) {
            return Double.compare(dist, o.dist);
        }
    }

    private void generateForBigN(int[][] dots, int[][] graph) {
        final int SQUARE_SIZE = 300;
        final int NUMBER_OF_SEGMENTS = 30000 / SQUARE_SIZE + 2;
        int[][][] buckets = new int[NUMBER_OF_SEGMENTS][NUMBER_OF_SEGMENTS][];
        int[][] count = new int[NUMBER_OF_SEGMENTS][NUMBER_OF_SEGMENTS];
        for(int i = 0; i < dots.length; i++) {
            count[dots[i][0] / SQUARE_SIZE][dots[i][1] / SQUARE_SIZE]++;
        }
        for(int i = 0; i < NUMBER_OF_SEGMENTS; i++) {
            for(int j = 0; j < NUMBER_OF_SEGMENTS; j++) {
                buckets[i][j] = new int[count[i][j]];
            }
        }
        for(int i = 0; i < dots.length; i++) {
            int ii = dots[i][0] / SQUARE_SIZE;
            int jj = dots[i][1] / SQUARE_SIZE;
            buckets[ii][jj][--count[ii][jj]] = i;
        }
        List<List<Integer>> g = new ArrayList<>(dots.length);
        for(int i = 0; i < dots.length; i++) {
            g.add(new ArrayList<Integer>());
        }
        int[] di = {0, -1, -1, -1, 0, 1, 1, 1, 0};
        int[] dj = {0, -1, 0, 1, 1, 1, 0, -1, -1};
        for(int i = 0; i < dots.length; i++) {
            int ii = dots[i][0] / SQUARE_SIZE;
            int jj = dots[i][1] / SQUARE_SIZE;
            for(int k = 0; k < di.length; k++) {
                int r = ii + di[k];
                int c = jj + dj[k];
                if(r < 0 || c < 0 || r >= NUMBER_OF_SEGMENTS || c >= NUMBER_OF_SEGMENTS) ;
                else {
                    for(int index = 0; index < buckets[r][c].length; index++) {
                        int v = buckets[r][c][index];
                        if(v != i) {
                            g.get(i).add(v);
                        }
                    }
                }
            }
        }
        for(int i = 0; i < dots.length; i++) {
            graph[i] = new int[g.get(i).size()];
            for(int j = 0; j < graph[i].length; j++) {
                graph[i][j] = g.get(i).get(j);
            }
        }
    }

    private void generateForSmallN(int[][] dots, int[][] graph) {
        int n = dots.length;
        for (int i = 0; i < n; ++i) {
            graph[i] = new int[n - 1];
            for (int j = 0; j < n; ++j) {
                if (j < i) {
                    graph[i][j] = j;
                }
                if (j > i) {
                    graph[i][j - 1] = j;
                }
            }
        }
    }

}
