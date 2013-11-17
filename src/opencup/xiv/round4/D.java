//package opencup.xiv.round4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author pvasilyev
 * @since 17 Nov 2013
 */
public class D {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public D() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        final D j = new D();
        j.solve();
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
    

    int max(int x, int y) { return (((y-x)>>(32-1))&(x^y))^y; }
    int min(int x, int y) { return (((y-x)>>(32-1))&(x^y))^x; }

    private void solve() throws IOException {
        final int OPEN = 10, CLOSE = 2;
        class Event implements Comparable<Event> {
            int type, time, n;

            Event(final int type, final int time, final int n) {
                this.type = type;
                this.time = time;
                this.n = n;
            }


            @Override
            public int compareTo(final Event o) {
                if (time == o.time) return type - o.type;
                return time - o.time;
            }
        }
        int n = nextInt();
        Event[] e = new Event[2 * n];
        for (int i = 0; i < n; i++) {
            int l = nextInt();
            int r = nextInt();
            e[2 * i] = new Event(OPEN, l, i);
            e[2 * i + 1] = new Event(CLOSE, r, i);
        }
        Arrays.sort(e);
        int[] span = new int[n];
        int[] a1 = new int[1000 * 1000 + 5];
        int[] a2 = new int[1000 * 1000 + 5];
        int[] t = new int[1000 * 1000 + 5];
        int sz = 0;
        int[] oO = new int[1000 * 1000 + 5];
        int sO = 0;
        int actualSize = 0;
        boolean[] removed = new boolean[1000 * 1000 + 5];
        for (int i = 0; i < e.length; i++) {
            int j = i;
            while (j < e.length && e[i].time == e[j].time) {
                Event event = e[j];
                if (event.type == CLOSE) {
//                    set.remove(event.n);
                    removed[event.n] = true;
                    actualSize--;
                } else {
//                    set.add(event.n);
                    oO[sO++] = event.n;
                    actualSize++;
                }
                j++;
            }
            if (actualSize == 1) {
                while(removed[oO[sO - 1]]) sO--;
                int x = oO[sO - 1];
                span[x] += e[j].time - e[i].time;

            }
            if (actualSize == 2) {
                while(removed[oO[sO - 1]]) sO--;
                int a0 = oO[--sO];
                while(removed[oO[sO - 1]]) sO--;
                oO[0] = oO[sO - 1];
                oO[1] = a0;
                sO = 2;

                int a11 = min(oO[0], oO[1]);
                int a22 = oO[0] + oO[1] - a11;
                if (sz == 0 || a1[sz - 1] != a11 || a2[sz - 1] != a22) {
                    a1[sz] = a11;
                    a2[sz] = a22;
                    sz++;
                }
                t[sz - 1] += e[j].time - e[i].time;
            }
            i = j - 1;
        }
        int ans = 0;
        for (int i = 0; i < sz; i++) {
            ans = max(t[i] + span[a1[i]] + span[a2[i]], ans);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            if(pq.size() < 2) {
                pq.add(span[i]);
            }
            else {
                if(pq.peek() < span[i]) {
                    pq.poll();
                    pq.add(span[i]);
                }
            }
        }
        ans = max(ans, pq.poll() + pq.poll());
        out.println(ans);
        out.flush();
    }

}
