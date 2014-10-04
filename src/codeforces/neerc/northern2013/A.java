package codeforces.neerc.northern2013;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class A {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public A() throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("arrange.in")));
        out = new PrintWriter(new FileOutputStream("arrange.out"));
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new A().solve();
    }

    private void solve() throws IOException {
        int n = nextInt();
        TreeSet<O> list = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            list.add(new O(next()));
        }

        char[] chars = new char[list.size()];
        int i = 0;
        for (O o : list) {
            chars[i] = o.c;
            i++;
        }
        i = 0;
        int result = 0;
        for (char aChar : chars) {
            if (aChar == 'A' + i) {
                result++;
            } else {
                break;
            }
            i++;
        }

        out.println(result);
        out.close();
    }

    static final class O implements Comparable<O> {

        private O(String string) {
            this.c = string.charAt(0);
        }

        Character c;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            O other = (O) o;

            if (c != null ? !c.equals(other.c) : other.c != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return c != null ? c.hashCode() : 0;
        }

        @Override
        public int compareTo(O o) {
            return Integer.compare(this.c, o.c);
        }
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
