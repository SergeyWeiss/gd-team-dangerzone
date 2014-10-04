package codeforces.neerc.northern2013;

import java.io.*;
import java.util.*;

public class B {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public B() throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("bad.in")));
        out = new PrintWriter(new FileOutputStream("bad.out"));
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new B().solve();
    }

    private void solve() throws IOException {
        final int n = nextInt();
        final int m = nextInt();
        final List<String> candidates = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            candidates.add(next());
        }
        final int[] votes = new int[n];
        for (int i = 0; i < m; ++i) {
            char[] ballot = next().toCharArray();
            int numberOfVotes = 0;
            for (int j = 0; j < n; ++j) {
                if (ballot[j] == 'X') {
                    numberOfVotes++;
                }
            }
            if (numberOfVotes == 1) {
                boolean flag = true;
                for (int j = 0; j < n && flag; ++j) {
                    if (ballot[j] == 'X') {
                        flag = false;
                        votes[j]++;
                    }
                }
            }
        }
        final Candidate[] c = new Candidate[n];
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            c[i] = new Candidate((100.0d * votes[i]) / (1.0d * m), candidates.get(i), i);
            sum += votes[i];
        }
        Arrays.sort(c);
        double invalid = (100.0d * (m - sum)) / (1.0d * m);
        for (int i = n - 1; i >= 0; --i) {
            out.printf(Locale.US, c[i].name + " %.2f%%\n", c[i].percentage);
        }
        out.printf(Locale.US, "Invalid %.2f%%\n", invalid);
        out.close();
    }

    class Candidate implements Comparable<Candidate> {
        double percentage;
        String name;
        int pos;

        Candidate(double percentage, String name, int pos) {
            this.percentage = percentage;
            this.name = name;
            this.pos = pos;
        }

        @Override
        public int compareTo(Candidate o) {
            if (this.percentage == o.percentage) {
                return -Integer.compare(this.pos, o.pos);
            }
            return Double.compare(this.percentage, o.percentage);
        }

        @Override
        public String toString() {
            return "Candidate{" +
                    "percentage=" + percentage +
                    ", name='" + name + '\'' +
                    '}';
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
