package timus.neerc.eastern2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class A {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public A() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new A().solve();
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
        int n = nextInt();
        Point[] left = new Point[n];
        for (int i = 0; i < n; ++i) {
            left[i] = new Point(nextInt(), nextInt());
        }
        int m = nextInt();
        Point[] right = new Point[m];
        for (int i = 0; i < m; ++i) {
            right[i] = new Point(nextInt(), nextInt());
        }
        int q = nextInt();
        Point[] cameras = new Point[q + n + m];
        for (int i = 0; i < q; ++i) {
            int x = nextInt();
            int y = nextInt();
            cameras[i] = (new Point(x, y));
        }
        for(int i = 0; i < n; i++) {
            cameras[q + i] = left[i];
        }
        for(int i = 0; i < m; i++) {
            cameras[q + n + i] = right[i];
        }
        int li = 0;
        int ri = 0;
        class Utils {
            Point intersect(Point[] line, int low, int high, int y) {
                double x2 = line[high].x;
                double x1 = line[low].x;
                double y1 = line[low].y;
                double y2 = line[high].y;
                if (Math.abs(x1 - x2) < 1e-6) {
                    return new Point(x1, y);
                }
                return new Point(x2 - (x2 - x1) / (y2 - y1) * (y2 - y), y);
            }
        }
        Utils u = new Utils();
        Arrays.sort(cameras);
        double ans = Double.MAX_VALUE;
        for(int i = 0; i < cameras.length; i++) {
            int j = i;
            while(j < cameras.length && cameras[i].y == cameras[j].y) j++;
            if(cameras[i].y < left[0].y) {
                i = j - 1;
                continue;
            }
            if(cameras[i].y > left[n - 1].y) {
                break;
            }
            int y = cameras[i].y;
            if(left[li + 1].y < y) li++;
            if(right[ri + 1].y < y) ri++;
            Point lp = u.intersect(left, li, li + 1, y);
            Point rp = u.intersect(right, ri, ri + 1, y);
            double width = 0;
            for(int k = i + 1; k < j; k++) {
                Point current = cameras[k];
                Point prev = cameras[k - 1];
                if(current.x < lp.x) {
                    continue;
                }
                if(prev.x > rp.x) {
                    break;
                }
                double w = current.x - prev.x;
                if(lp.x > prev.x) {
                    w -= lp.x - prev.x;
                }
                if(rp.x < current.x) {
                    w -= current.x - rp.x;
                }
                width = Math.max(width, w);
            }
            double gate = rp.x - lp.x;
            if(lp.x < cameras[i].x) {
                width = Math.max(width, Math.min(gate, cameras[i].x - lp.x));
            }
            if(rp.x > cameras[j - 1].x) {
                width = Math.max(width, Math.min(gate, rp.x - cameras[j - 1].x));
            }
            ans = Math.min(ans, width);
            i = j - 1;
        }
        out.printf(Locale.US, "%.10f", ans);
        out.flush();
    }

    class Point implements Comparable<Point> {
        double x;
        int y;

        Point(double x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.y == o.y) {
                return Double.compare(this.x, o.x);
            } else {
                return this.y - o.y;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (Double.compare(point.x, x) != 0) return false;
            if (y != point.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(x);
            result = (int) (temp ^ (temp >>> 32));
            result = 31 * result + y;
            return result;
        }
    }

}
