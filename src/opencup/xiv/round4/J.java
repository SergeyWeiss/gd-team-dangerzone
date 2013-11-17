package opencup.xiv.round4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author pvasilyev
 * @since 17 Nov 2013
 */
public class J {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public J() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        final J j = new J();
        j.fastSolve();
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

    private void fastSolve() throws IOException {
        int n = readInt();
        long ans = 0;
        for(int i = 1; i <= n; i *= 2) {
            ans += 1L * (i - 1) * i / 2;
            n -= i;
        }
        ans += 1L * (n - 1) * n / 2;
        writer.println(ans);
        writer.flush();
    }

}
