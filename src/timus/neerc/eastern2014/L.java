package timus.neerc.eastern2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class L {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public L() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new L().solve();
    }

    private void solve() throws IOException {
        final Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 1);
        map.put("Ariel", 1);
        map.put("Aurora", 1);
        map.put("Phil", 1);
        map.put("Peter", 1);
        map.put("Olaf", 1);
        map.put("Phoebus", 1);
        map.put("Ralph", 1);
        map.put("Robin", 1);

        map.put("Bambi", 2);
        map.put("Belle", 2);
        map.put("Bolt", 2);
        map.put("Mulan", 2);
        map.put("Mowgli", 2);
        map.put("Mickey", 2);
        map.put("Silver", 2);
        map.put("Simba", 2);
        map.put("Stitch", 2);

        map.put("Dumbo", 3);
        map.put("Genie", 3);
        map.put("Jiminy", 3);
        map.put("Kuzko", 3);
        map.put("Kida", 3);
        map.put("Kenai", 3);
        map.put("Tarzan", 3);
        map.put("Tiana", 3);
        map.put("Winnie", 3);


        final int n = nextInt();
        int curr = 1;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            String v = next();
            final Integer x = map.get(v);
            ans += Math.abs(x - curr);
            curr = x;
        }
        out.println(ans);
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
