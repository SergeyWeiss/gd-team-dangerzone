package timus.urfu2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public G() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new G().solve();
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

    private void solve() throws IOException {
        class Event implements Comparable<Event> {

            // type = 1 - open
            // type = 2 - query
            // type = 3 - close
            int type;
            int coord;

            Event(int type, int coord) {
                this.type = type;
                this.coord = coord;
            }

            @Override
            public int compareTo(Event o) {
                if (o.coord != this.coord) {
                    return coord - o.coord;
                } else {
                    return type - o.type;
                }
            }
        }
        int n = nextInt();
        ArrayList<Event> events = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int open = nextInt();
            int close = nextInt();
            events.add(new Event(1, open));
            events.add(new Event(3, close));
        }
        int m = nextInt();
        for (int i = 0; i < m; ++i) {
            int coord = nextInt();
            events.add(new Event(2, coord));
        }
        Collections.sort(events);
        LinkedList<Integer> s = new LinkedList<>();
        int x = 0;
        for (int i = 0; i < events.size(); ++i) {
            Event event = events.get(i);
            switch (event.type) {
                case 1: {
                    s.addFirst(++x);
                    break;
                }
                case 2: {
                    if (s.isEmpty()) {
                        out.println(-1);
                    } else {
                        out.println(s.peekFirst());
                    }
                    break;
                }
                case 3: {
                    s.pollFirst();
                    break;
                }
            }
        }
        out.flush();
    }
}
