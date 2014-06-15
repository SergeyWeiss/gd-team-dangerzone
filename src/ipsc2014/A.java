package ipsc2014;

import java.io.*;
import java.util.StringTokenizer;

public class A {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public A() throws IOException {
        br = new BufferedReader(new FileReader("src/ipsc2014/a2.in"));
        out = new PrintWriter(new FileWriter("src/ipsc2014/a2.out"));
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
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

    int max(int x, int y) {
        return (((y - x) >> (32 - 1)) & (x ^ y)) ^ y;
    }

    int min(int x, int y) {
        return (((y - x) >> (32 - 1)) & (x ^ y)) ^ x;
    }

    private void solve() throws IOException {
        int t = nextInt();

        for (int i = 0; i < t; i++) {
            String origin = next();
            String fail = next();

            String commands = null;
            int len = Integer.MAX_VALUE;

            String temp = null;
            int tempLen = Integer.MAX_VALUE;

            // success
            if (origin.equals(fail)) {
                commands = "*";
                out.println(commands);
                continue;
            }

            // all fail
            commands = "*" + origin + "*";
            len = commands.length();

            // remove last and append
            int lastPos = -1;
            for (int i2 = 0; i2 < origin.length(); i2++) {
                if (i2 >= fail.length()) {
                    lastPos = i2;
                    break;
                }

                if (origin.charAt(i2) != fail.charAt(i2)) {
                    lastPos = i2;
                    break;
                }

            }
            if (lastPos == -1){
                lastPos = origin.length();
            }

            String removeSt = new String();
            int rmLen = fail.length() - lastPos;
            for (int k = 0; k < rmLen; k++) {
                removeSt += "<";
            }

            temp = removeSt + origin.substring(lastPos, origin.length()) + "*";
            tempLen = temp.length();

            if (tempLen <= len) {
                commands = temp;
            }

            out.println(commands);
        }
        out.println();
        out.flush();
    }

}
