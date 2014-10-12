package opencup.xiv.round1;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class J {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public J() throws IOException {

        reader = new BufferedReader(new FileReader("snakes2.in"));
        writer = new PrintWriter(new FileWriter("snakes2.out"));
    }

    public static void main(String[] args) throws IOException {
        new J().solve();
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
        class Point {
            int x, y;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Point point = (Point) o;

                if (x != point.x) return false;
                if (y != point.y) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = x;
                result = 31 * result + y;
                return result;
            }
        }
        int k = readInt();
        Map<Point, Integer> points = new HashMap<>();
        Point[][] snakes = new Point[k][];
        for (int i = 0; i < k; i++) {
            int l = readInt();
            snakes[i] = new Point[l];
            for (int j = 0; j < l; j++) {
                snakes[i][j] = new Point(readInt(), readInt());
                if (!points.containsKey(snakes[i][j])) {
                    points.put(snakes[i][j], 1);
                } else {
                    points.put(snakes[i][j], 2);
                }
            }
        }
        Set<Point> visited = new HashSet<>();
        for (int i = 0; i < k; i++) {
            for (Point p : snakes[i]) {
                if (points.get(p) == 2) {
                    if (visited.contains(p)) {
                        writer.print('+');
                    } else {
                        writer.print('-');
                        visited.add(p);
                    }
                }
            }
            writer.println();
        }
        writer.flush();
    }
}
