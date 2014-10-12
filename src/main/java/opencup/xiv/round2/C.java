package opencup.xiv.round2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.StringTokenizer;

public class C {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public C() throws IOException {
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new C().solve();
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
        final FileInputStream reader = new FileInputStream("c.in");
        FileChannel in = reader.getChannel();
        final int all = (int) in.size() / 4;
        final ByteBuffer buffer = in.map(FileChannel.MapMode.READ_ONLY, 0, in.size());

        class Utils {
            final int getInt(int k) {
                int ret = 0;
                for (int i = 3; i >= 0; i--) {
                    ret <<= 8;
                    ret += (int) (buffer.get(k * 4 + i)) & (1 << 8) - 1;
                }
                return ret;
            }

            int binarySearch(int x, int i1, int i2) {
                //how many numbers in g, <= x
                int lo = i1 - 1, hi = i2;
                while (hi - lo > 1) {
                    int med = lo + hi >> 1;
                    if (getInt(med) > x) {
                        hi = med;
                    } else lo = med;
                }
                return hi - i1;
            }
        }
        Utils u = new Utils();
        int n = u.getInt(0);
        int m = u.getInt(1);
        int end = n * m + 2;
        for (int j = end; j < all; j += 2) {
            int x = u.getInt(j);
            int y = u.getInt(j + 1);
            x = Math.max(-1, x);
            y = Math.max(-1, y);
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += u.binarySearch(y, i * m + 2, (i + 1) * (m) + 2) - u.binarySearch(x - 1, i * m + 2, (i + 1) * (m) + 2);
            }
            out.println(sum);
        }
        out.flush();
        out.close();
    }
}
