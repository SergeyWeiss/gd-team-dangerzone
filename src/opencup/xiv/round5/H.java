package opencup.xiv.round5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class H {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public H() throws IOException {
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        br = new BufferedReader(new FileReader("wtf8.in"));
        out = new PrintWriter(new FileWriter("wtf8.out"));
    }

    public static void main(String[] args) throws IOException {
        final H j = new H();
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

    int foo(char[] s) {
        int ret = 0;
        for(char c: s) {
            ret <<= 4;
            if(c >= '0' && c <= '9') {
                ret += c - '0';
            }
            if(c >= 'A' && c <= 'Z') {
                ret += c - 'A' + 10;
            }
        }
        return ret;
    }

    private void solve() throws IOException {
        String s = null;
        List<Integer> bytes = new ArrayList<>();
        while((s = br.readLine()) != null) {
            for(String _byte: s.split(" ")) {
                bytes.add(foo(_byte.toCharArray()));
            }
        }
        long ans = 0;
        for(int i = 0; i < bytes.size(); i++) {
            int x = bytes.get(i);
            int b = 7;
            while((x & (1 << b)) > 0) b--;
            long add = x & (1 << b) - 1;
            for(int j = 1; j < 7 - b; j++) {
                add <<= 6;
                add += bytes.get(i + j) & (1 << 6) - 1;
            }
            ans += add;
            if(b != 7) {
                i += 7 - b - 1;
            }
        }
        out.println(ans);
        out.close();
    }

}
