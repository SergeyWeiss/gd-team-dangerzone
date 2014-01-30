package opencup.xiv.round5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class E {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public E() throws IOException {
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        br = new BufferedReader(new FileReader("kpaths.in"));
        out = new PrintWriter(new FileWriter("kpaths.out"));
    }

    public static void main(String[] args) throws IOException {
        final E j = new E();
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

    private void solve() throws IOException {
        long K = nextInt();
        int last = 0;
        while(K > (1 << last)) last++;
        int n = 2 + last;
        out.println(n);
        if(K == (1 << last)) {
            out.print(n - 1);
            for(int i = 2; i <= n; i++) {
                out.print(" " + i);
            }
            out.println();
            K--;
        }
        else {
            out.print(n - 2);
            for(int i = 3; i <= n; i++) {
                out.print(" " + i);
            }
            out.println();
        }
        out.println("0");
        for(int i = 0; i < n - 2; i++) {
            if((K & (1 << i)) != 0) {
                out.print(n - 2 - i);
                out.print(" 2");
            }
            else {
                out.print(n - 3 - i);
            }
            for(int j = i + 4; j <= n; j++) {
                out.print(" " + j);
            }
            out.println();
        }
        out.close();
    }

}
