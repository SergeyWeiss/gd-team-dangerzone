package opencup.xiv.round1;

import java.io.*;
import java.util.Arrays;

public class E {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public E() throws IOException {

        reader = new BufferedReader(new FileReader("fourprimes.in"));
        writer = new PrintWriter(new FileWriter("fourprimes.out"));
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
        long[] d = new long[100000 + 1];

        boolean[] isPrime = new boolean[100000 + 1];
        int pcnt = 0;
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= 100 * 1000; i++) {
            if (isPrime[i]) {
                pcnt++;
                for (long j = 1L * i * i; j <= 100 * 1000; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }
        int[] primes = new int[pcnt];
        for (int i = 100000; i > 1; i--) {
            if (isPrime[i]) {
                primes[--pcnt] = i;
            }
        }
        pcnt = primes.length;
        for (int k = 0; k < pcnt; ++k) {
            for (int i = k; i < pcnt; ++i) {
                if (primes[k] + primes[i] >= n) {
                    break;
                }
                if (primes[k] != primes[i]) {
                    d[primes[k] + primes[i]]++;
                }
                d[primes[k] + primes[i]]++;
            }
        }


        long sum = 0;
        for (int i = 0; i <= n; ++i) {
            sum += d[i] * d[n - i];
        }


        writer.println(sum);
        writer.flush();
    }
}
