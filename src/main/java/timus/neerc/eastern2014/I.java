package timus.neerc.eastern2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class I {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public I() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new I().solve();
    }

    private void solve() throws IOException {
        String fLine = next();
        String sLine = next();
        int sum = 0;
        int fIndex = 0;
        int sIndex = 0;

        while (fIndex < fLine.length() && sIndex < sLine.length()) {
            char fCur = fLine.charAt(fIndex);
            char sCur = sLine.charAt(sIndex);
            if (fCur == 'L' && sCur == 'L') {
                fIndex++;
                sIndex++;

            } else if (fCur == 'L') {
                sIndex++;
            } else if (sCur == 'L') {
                fIndex++;
            } else {
                sIndex++;
                fIndex++;
            }
            sum++;
        }
        if (fIndex < fLine.length()) {
            sum += fLine.length()  - fIndex;
        }
        if (sIndex < sLine.length()) {
            sum += sLine.length()  - sIndex;
        }
        out.print(sum);
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
