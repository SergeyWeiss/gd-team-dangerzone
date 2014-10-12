package codeforces.neerc.south2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class F {
    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public F() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new F().solve();
    }

    private void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        int[] time = new int[n];
        double[] probability = new double[n];

        for (int i = 0; i < n; i++) {
            time[i] = nextInt();
            probability[i] = nextDouble();
        }

        double expectedTotalTime = 0D;
        int[] timeFinished = calculateTimeFinished(m,  time);
        int expectedTime = 0;
        double p = 1D;
        for (int i = 0; i < n; i++) {
            expectedTime = Math.max(expectedTime, timeFinished[i]);
            double currentProbability = p * (1 - probability[i]);
            expectedTotalTime += expectedTime * currentProbability;
            p *= probability[i];
        }
        expectedTotalTime += p * expectedTime;

        out.printf(Locale.US, "%.6f", expectedTotalTime);
        out.close();
    }

    private int[] calculateTimeFinished(int m,  int[] time) {
        int[] timeFinished = new int[time.length];
        PriorityQueue<Pair> queue = new PriorityQueue<>(m, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.spentTime, o2.spentTime);
            }
        });
        for (int j = 0; j < m; j++) {
            queue.offer(new Pair(j, 0));
        }
        for (int j = 0; j < time.length; j++) {
            Pair poll = queue.poll();
            poll.spentTime += time[j];
            timeFinished[j] = poll.spentTime;
            queue.offer(poll);
        }
        return timeFinished;
    }

    private static final class Pair {
        int machineNumber;
        int spentTime;

        private Pair(int machineNumber, int spentTime) {
            this.machineNumber = machineNumber;
            this.spentTime = spentTime;
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
