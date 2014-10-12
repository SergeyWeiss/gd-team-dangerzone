package timus.urfu2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

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
        Set<String> salads = new TreeSet<>();
        Set<String> ingredients = new TreeSet<>();
        final Map<String, Map<String, Double>> g = new HashMap<>();

        while (true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }
            String[] ss = s.split(" ");
            Map<String, Double> content = new HashMap<>();
            for (int i = 2; i < ss.length; i += 2) {
                content.put(ss[i], 0.01 * Double.parseDouble(ss[i + 1]));
                ingredients.add(ss[i]);
            }
            salads.add(ss[0]);
            g.put(ss[0], content);
        }
        for (String s : salads) {
            ingredients.remove(s);
        }
        Map<String, Integer> index = new HashMap<>();
        int ix = 0;
        for (String salad : salads) {
            index.put(salad, ix++);
        }
        int n = g.size();
        Map<String, Map<String, Double>> ans = new TreeMap<>();
        for (String salad : salads) {
            ans.put(salad, new TreeMap<String, Double>());
        }
        for (String ingr : ingredients) {
            double[][] a = new double[n][n + 1];
            for (Map.Entry<String, Map<String, Double>> entry : g.entrySet()) {
                int i = index.get(entry.getKey());
                a[i][i] = 1;
                Double q = entry.getValue().get(ingr);
                a[i][n] = q == null ? 0 : q;
                for (Map.Entry<String, Double> stringDoubleEntry : entry.getValue().entrySet()) {
                    if (salads.contains(stringDoubleEntry.getKey())) {
                        int j = index.get(stringDoubleEntry.getKey());
                        a[i][j] -= stringDoubleEntry.getValue();
                    }
                }
            }
            for (int row = 0; row < n; row++) {
                double f = a[row][row];
                for (int i = 0; i <= n; i++) {
                    a[row][i] /= f;
                }
                for (int row2 = 0; row2 < n; row2++) {
                    if (row == row2) {
                        continue;
                    }
                    double f2 = a[row2][row];
                    for (int column = 0; column <= n; column++) {
                        a[row2][column] -= a[row][column] * f2;
                    }
                }
            }
            ix = 0;
            for (String salad : salads) {
                ans.get(salad).put(ingr, a[ix++][n]);
            }
        }

        for (Map.Entry<String, Map<String, Double>> entry : ans.entrySet()) {
            out.printf("%s :", entry.getKey());
            for (Map.Entry<String, Double> stringDoubleEntry : entry.getValue().entrySet()) {
                out.printf(Locale.US, " %s %.3f", stringDoubleEntry.getKey(), stringDoubleEntry.getValue() * 100);
            }
            out.println();
        }
        out.flush();
    }
}
