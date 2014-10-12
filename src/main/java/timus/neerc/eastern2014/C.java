package timus.neerc.eastern2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class C {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public C() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException, ParseException {
        new C().solve();
    }

    class Event {
        int amount;
        Date date;

        Event(int amount, Date date) {
            this.amount = amount;
            this.date = date;
        }
    }

    private void solve() throws IOException, ParseException {
        int n = nextInt();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM hh:mm");
        Event[] events = new Event[n];
        for(int i = 0; i < n; i++) {
            int amount = nextInt();
            Date date = dateFormat.parse(next() + " " + next());
            events[i] = new Event(amount, date);
        }
        List<Long> answer = calculateDebt(events);
        for (Long debt : answer) {
            out.println(debt);
        }
        out.close();
    }

    List<Long> calculateDebt(Event[] events) {
        int n = events.length;
        Date[] dates = new Date[n];
        for(int i = 0; i < n; i++) {
            dates[i] = events[i].date;
        }
        Arrays.sort(dates);
        Map<Date, Integer> dateMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            dateMap.put(dates[i], i + 1);
        }
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
        for (Event event : events) {
            segmentTree.update(dateMap.get(event.date), segmentTree.q, event.amount);
            answer.add(segmentTree.a[1]);
        }
        return answer;
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
