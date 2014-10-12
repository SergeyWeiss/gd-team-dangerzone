package codeforces.neerc.south2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class H {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public H() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new H().solve();
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
        int n = nextInt();
        char[] s = next().toCharArray();
        int[][] prev = new int[s.length + 1][n];
        for(int i = 1; i <= s.length; i++) {
            Arrays.fill(prev[i], -1);
        }
        for(int i = 0; i < s.length; i++) {
            for(int j = 0; j < n; j++) {
                if(prev[i][j] == -1) {
                    continue;
                }
                if(s[i] == '=') {
                    prev[i + 1][j] = j;
                }
                if(s[i] == '>') {
                    for(int k = 0; k < j; k++) {
                        prev[i + 1][k] = j;
                    }
                }
                if(s[i] == '<') {
                    for(int k = j + 1; k < n; k++) {
                        prev[i + 1][k] = j;
                    }
                }
            }
        }
        int ix = 0;
        while(ix < n && prev[s.length][ix] == -1) {
            ix++;
        }
        if(ix == n) {
            out.println(-1);
        }
        else {
            StringBuilder ans = new StringBuilder();
            for(int i = s.length; i >= 0; i--) {
                ans.append((char)('a' + ix));
                ix = prev[i][ix];
            }
            out.println(ans.reverse());
        }
        out.flush();
    }

}