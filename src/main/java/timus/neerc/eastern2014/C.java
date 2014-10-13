package timus.neerc.eastern2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class C {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public C() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new C().solve();
    }

    class Event<D extends Comparable<D>> {
        int amount;
        D date;

        Event(int amount, D date) {
            this.amount = amount;
            this.date = date;
        }
    }

    private String readDate() throws IOException {
        String[] date = next().split("\\.");
        return date[1] + date[0] + next();
    }

    private void solve() throws IOException {
        int n = nextInt();
        List<Event<String>> events = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int amount = nextInt();
            String date = readDate();
            events.add(new Event<>(amount, date));
        }
        List<Long> answer = calculateDebt(events);
        for (Long debt : answer) {
            out.println(debt);
        }
        out.close();
    }

    <D extends Comparable<D>> List<Long> naiveSolution(List<Event<D>> events) {
        int n = events.size();
        List<Event<D>> localCopy = new ArrayList<>(events);
        List<Long> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Collections.sort(localCopy.subList(0, i + 1), new Comparator<Event<D>>() {
                @Override
                public int compare(Event<D> o1, Event<D> o2) {
                    return o1.date.compareTo(o2.date);
                }
            });
            long debt = 0;
            long sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += localCopy.get(j).amount;
                debt = Math.min(debt, sum);
            }
            answer.add(debt);
        }
        return answer;
    }

    <D extends Comparable<D>> List<Long> calculateDebt(List<Event<D>> events) {
        Map<D, Integer> dateMap = getDateMapping(events);
        class SegmentTree {
            int q = 128 << 10;
            long[] a = new long[2 * q];
            long[] added = new long[2 * q];

            void update(int left, int right, int x, int l, int r, int i) {
                if(left == l && right == r) {
                    added[i] += x;
                    return;
                }
                added[i * 2] += added[i];
                added[i * 2 + 1] += added[i];
                added[i] = 0;
                int m = l + r >> 1;
                if(left < m) {
                    update(left, Math.min(m, right), x, l, m, 2 * i);
                }
                if(right > m) {
                    update(Math.max(left, m), right, x, m, r, 2 * i + 1);
                }
                a[i] = Math.min(a[2 * i] + added[2 * i], a[2 * i + 1] + added[2 * i + 1]);
            }

            void update(int l, int r, int x) {
                update(l, r, x, 0, q, 1);
            }
        }
        List<Long> answer = new ArrayList<>();
        SegmentTree segmentTree = new SegmentTree();
        for (Event<D> event : events) {
            segmentTree.update(dateMap.get(event.date), segmentTree.q, event.amount);
            answer.add(segmentTree.a[1]);
        }
        return answer;
    }

    private <D extends Comparable<D>> Map<D, Integer> getDateMapping(List<Event<D>> events) {
        int n = events.size();
        List<D> dates = new ArrayList<>();
        for (Event<D> event : events) {
            dates.add(event.date);
        }
        Collections.sort(dates);
        Map<D, Integer> dateMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            dateMap.put(dates.get(i), i + 1);
        }
        return dateMap;
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
