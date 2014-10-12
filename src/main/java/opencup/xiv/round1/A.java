package opencup.xiv.round1;

import java.io.*;

public class A {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public A() throws IOException {

        reader = new BufferedReader(new FileReader("bracket-expression.in"));
        writer = new PrintWriter(new FileWriter("bracket-expression.out"));

//        reader = new BufferedReader(new InputStreamReader(System.in));
//        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new A().solve();
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
        char[] c = reader.readLine().toCharArray();
        writer.println(getAns(c, 0, c.length));
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
