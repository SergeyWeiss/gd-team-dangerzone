package timus.neerc.eastern2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class B {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public B() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new B().solve();
    }

    @SuppressWarnings("unchecked")
    private void solve() throws IOException {
        final int n = nextInt();
        final int[] drinks = new int[n + 1];
        final int[][] g = new int[n + 1][];
        for(int i = 1; i <= n; i++) {
            drinks[i] = nextInt();
            g[i] = new int[nextInt()];
            for(int j = 0; j < g[i].length; j++) {
                g[i][j] = nextInt();
            }
        }
        class DFSAction {
            void act(int u) {}
            void act(int u, int v) {}
        }
        class Utils {
            boolean[] used = new boolean[n + 1];

            void dfs(int u, DFSAction action) {
                used[u] = true;
                for(int i = 0; i < g[u].length; i++) {
                    int v = g[u][i];
                    if(drinks[v] == 0) {
                        if(!used[v]) {
                            dfs(v, action);
                        }
//                        action.act(u, v);
                    }
                }
                action.act(u);
            }
        }
        final List<Integer> stack = new ArrayList<>();
        Utils utils = new Utils();
        for(int u = 1; u <= n; u++) {
            if(drinks[u] == 0 && !utils.used[u]) {
                utils.dfs(u, new DFSAction() {
                    @Override
                    void act(int u) {
                        stack.add(u);
                    }
                });
            }
        }
        Arrays.fill(utils.used, false);
        final List[] components = new List[n + 1];
        List<List<Integer>> componentsStack = new ArrayList<>();
        for(int i = stack.size() - 1; i >= 0; i--) {
            final int u = stack.get(stack.size() - 1 - i);
            if(!utils.used[u]) {
                components[u] = new ArrayList<Integer>();
                componentsStack.add(components[u]);
                utils.dfs(u, new DFSAction() {
                    @SuppressWarnings("unchecked")
                    @Override
                    void act(int v) {
                        components[u].add(v);
                        components[v] = components[u];
                    }
                });
            }
        }
        int[] answer = new int[n + 1];
        Arrays.fill(answer, -2);
        answer[1] = 0;
        for(int u = 1; u <= n; u++) {
            if(drinks[u] == 0) {
                continue;
            }
            for(int i = 0; i < g[u].length; i++) {
                int v = g[u][i];
                if(drinks[v] == 0) {
                    v = (int) components[v].get(0);
                    if(components[v] == components[1]) {
                        v = 1;
                    }
                }
                if(answer[v] == -2) {
                    answer[v] = drinks[u];
                    continue;
                }
                if(answer[v] == drinks[u]) {
                    continue;
                }
                answer[v] = -1;
            }
        }
        for(int i = componentsStack.size() - 1; i >= 0; i--) {
            final List<Integer> component = componentsStack.get(i);
            int leader = component.get(0);
            if(component == components[1]) {
                leader = 1;
            }
            if(answer[leader] == -2) {
                answer[leader] = 0;
            }
            for(int j = 0; j < component.size(); j++) {
                int u = component.get(j);
                answer[u] = answer[leader];
                for(int k = 0; k < g[u].length; k++) {
                    int v = g[u][k];
                    if(drinks[v] == 0) {
                        v = (int) components[v].get(0);
                    }
                    if(answer[v] == -2) {
                        answer[v] = answer[u];
                        continue;
                    }
                    if(answer[v] == answer[u]) {
                        continue;
                    }
                    answer[v] = -1;
                }
            }
        }
        if(answer[1] != 0) {
            answer[1] = -1;
        }
        for(int u = 1; u <= n; u++) {
            out.printf("%s %s\n", foo(answer[u]), drinks[u] == 0 ? foo(answer[u]) : drinks[u]);
        }
        out.close();
    }

    String foo(int x) {
        if(x == 0) return "sober";
        if(x == -1) return "unknown";
        return Integer.toString(x);
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
