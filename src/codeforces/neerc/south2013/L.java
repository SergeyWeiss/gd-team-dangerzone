package codeforces.neerc.south2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class L {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public L() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws Exception {
        new L().solve();
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

    private void solve() throws Exception {
        int n = nextInt();
        int d = nextInt();
        int a = nextInt();
        int b = nextInt();
        int[] prices = new int[n];
        int[] maxBuy = new int[n];
        int[] maxSell = new int[n];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = nextInt();
            if (i > 0) {
                if (prices[i] > prices[i-1]) {
                    maxBuy[i] = maxBuy[i-1] + a;
                } else if (prices[i] < prices[i-1]) {
                    maxSell[i] = maxSell[i-1] + b;
                }
            }
        }

        int shares = 0;
        for (int i = 1; i < prices.length; i++) {
            if (maxBuy[i] > 0) {
                int canBuy = d / prices[i];
                canBuy = Math.min(canBuy, maxBuy[i]);
                shares += canBuy;
                d -= canBuy * prices[i];
            }
            if (maxSell[i] > 0) {
                int canSell = Math.min(shares, maxSell[i]);
                d += canSell * prices[i];
                shares -= canSell;
            }
        }
        out.println(d + " " + shares);
        out.close();
    }
}
