package opencup.xiv.round1;

import java.io.*;

public class F {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public F() throws IOException {
        reader = new BufferedReader(new FileReader("intset.in"));
        writer = new PrintWriter(new FileWriter("intset.out"));
    }

    public static void main(String[] args) throws IOException {
        new F().solve();
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
        int l = readInt();
        int m = readInt();
        double[] logFacts = new double[1000 * 1000 + 1000];
        logFacts[0] = Math.log(1.0d);
        for (int i = 1; i < logFacts.length; ++i) {
            logFacts[i] = logFacts[i - 1] + Math.log(i);
        }
        double sum = 0.0d;
        double[] logProb = new double[Math.min(l, m) + 1];
        double[] prob = new double[Math.min(l, m) + 1];
        for (int s = 0; s <= Math.min(l, m); ++s) {
            if (l + m - 2 * s > n - s) {
                continue;
            }
            logProb[s] = logFacts[l] + logFacts[n - l] + logFacts[m] + logFacts[n - m] - logFacts[l - s] - logFacts[m - s] - logFacts[n] - logFacts[n - m - l + s] - logFacts[s];
            prob[s] = Math.exp(logProb[s]);
            sum += prob[s];
        }
        double prefixSum = 0.0d;
        for (int ans = logProb.length - 1; ans >= 0; --ans) {
            prefixSum += prob[ans];
            if (prefixSum >= 0.5 * sum) {
                writer.println(ans);
                writer.flush();
                return;
            }
        }
    }
}
