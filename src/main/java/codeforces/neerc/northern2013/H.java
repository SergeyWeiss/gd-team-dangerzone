package codeforces.neerc.northern2013;

import java.io.*;
import java.util.*;

public class H {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public H() throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("heavy.in")));
        out = new PrintWriter(new FileOutputStream("heavy.out"));
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new H().solve();
    }

    private void solve() throws IOException {
        final int n = nextInt();
        int k = nextInt();
        final Map<String, Integer> prefixMapping = new HashMap<>();
        final Map<String, Integer> suffixMapping = new HashMap<>();
        final int[][] g = new int[n][n];
        final int[] prevString = new int[n + 1];
        final List<List<Integer>> prefixGraph = new ArrayList<>();
        final List<List<Integer>> suffixGraph = new ArrayList<>();
        class Utils {
            int getFromMap(Map<String, Integer> map, String key) {
                Integer value = map.get(key);
                if(value == null) {
                    value = map.size();
                    map.put(key, value);
                }
                return value;
            }

            Collection<Integer> getFamily(int father) {
                Collection<Integer> family = new ArrayList<>();
                for(int i = father; i > 0; i = prevString[i]) {
                    family.add(i);
                }
                return family;
            }

            boolean[] used = new boolean[n];
            int[] prev = new int[n];
            int[] next = new int[n];
            int maxMatch() {
                int result = 0;
                Arrays.fill(prev, -1);
                Arrays.fill(next, -1);
                for(int i = 0; i < prefixMapping.size(); i++) {
                    Arrays.fill(used, false);
                    if(findPath(i)) {
                        result++;
                    }
                }
                return result;
            }

            private boolean findPath(int u) {
                used[u] = true;
                for (int v : prefixGraph.get(u)) {
                    if(prev[v] == -1 || !used[prev[v]] && findPath(prev[v])) {
                        prev[v] = u;
                        next[u] = v;
                        return true;
                    }
                }
                return false;
            }
        }
        Utils utils = new Utils();
        for(int i = 0; i < n; i++) {
            prefixGraph.add(new ArrayList<Integer>());
            suffixGraph.add(new ArrayList<Integer>());
        }
        for(int i = 1; i <= n; i++) {
            String s = next();
            String prefix = s.substring(0, k);
            String suffix = s.substring(s.length() - k);
            final int pkey = utils.getFromMap(prefixMapping, prefix);
            final int skey = utils.getFromMap(suffixMapping, suffix);
            if(g[pkey][skey] != 0) {
                prevString[i] = g[pkey][skey];
            }
            g[pkey][skey] = i;
            prefixGraph.get(pkey).add(skey);
            suffixGraph.get(skey).add(pkey);
        }
        out.println(utils.maxMatch());
        final boolean[] used = utils.used;
        final int[] next = utils.next;
        Arrays.fill(used, false);
        for(int u = 0; u < prefixMapping.size(); u++) {
            if(next[u] == -1 && !used[u]) {
                utils.findPath(u);
            }
        }
        boolean[] stringIsUsed = new boolean[n + 1];
        List<Integer> cluster = new ArrayList<>();
        for(int u = 0; u < prefixMapping.size(); u++) {
            if(next[u] == -1) {
                continue;
            }
            cluster.clear();
            if(used[u]) {
                int v = next[u];
                for(int i : suffixGraph.get(v)) {
                    if(g[i][v] > 0 && !stringIsUsed[g[i][v]]) {
                        cluster.addAll(utils.getFamily(g[i][v]));
                        stringIsUsed[g[i][v]] = true;
                    }
                }
            } else {
                for(int v : prefixGraph.get(u)) {
                    if(g[u][v] > 0 && !stringIsUsed[g[u][v]]) {
                        cluster.addAll(utils.getFamily(g[u][v]));
                        stringIsUsed[g[u][v]] = true;
                    }
                }
            }
            out.print(cluster.size());
            for (Integer strId : cluster) {
                out.print(" " + strId);
            }
            out.println();
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

}

