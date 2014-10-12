package opencup.xiv.round4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class E {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public E() throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new E().solve();
    }

    int[] readInts() throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }

    int readInt() throws IOException {
        if (tt == null || tx == tt.length) {
            tt = readInts();
            tx = 0;
        }
        return tt[tx++];
    }

    private void solve() throws IOException {
        int n = readInt();
        int k = readInt();
        if (k > n / 2) {
            writer.println("NIE");
            writer.flush();
            return;
        }
        for (int i = n - k + 1; i <= n; ++i) {
            for (int j = i; j >= 1; j -= k) {
                writer.print(j + " ");
            }
        }
        writer.flush();
    }

    private long getAns(char[] c, int l, int r) {
        if (l == r) return 1;
        int balance = 0;
        for (int i = l; i < r; ++i) {
            if (c[i] == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance == 0) {
                return (1 + getAns(c, l + 1, i)) * getAns(c, i + 1, r);
            }
        }
        return 0;
    }

}
