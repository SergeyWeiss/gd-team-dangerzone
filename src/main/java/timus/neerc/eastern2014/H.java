package timus.neerc.eastern2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class H {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public H() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new H().solve();
    }

    final boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    final boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    final char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c - 'A' + 'a');
        }
        return c;
    }

    private void solve() throws IOException {
        final int n = nextInt();
        char[] in = next().toCharArray();
        int capitalIndex = 1;
        int lowerIndex = 1;
        int[] index = new int[n * 2];
        for (int i = 0; i < n * 2; ++i) {
            if (isLowerCase(in[i])) {
                index[i] = lowerIndex++;
            } else {
                index[i] = capitalIndex++;
            }
        }
        int[] a = new int[n + 1];
        final MyLinkedList list = new MyLinkedList(in);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < list.length - 1; j = list.next[j]) {
                int next = list.next[j];
                char ch1 = list.ch[j];
                char ch2 = list.ch[next];
                if (toLowerCase(ch1) == toLowerCase(ch2)) {
                    if (isLowerCase(ch1) && isUpperCase(ch2)) {
                        a[index[next - 1]] = index[j - 1];
                        list.deleteAtPos(j);
                        list.deleteAtPos(next);
                        j = list.prev[j];
                    } else if (isLowerCase(ch2) && isUpperCase(ch1)) {
                        a[index[j - 1]] = index[next - 1];
                        list.deleteAtPos(j);
                        list.deleteAtPos(next);
                        j = list.prev[j];
                    }
                }
            }
        }
        if (list.isEmpty()) {
            for (int i = 1; i < a.length; ++i) {
                out.print(a[i] + " ");
            }
        } else {
            out.println("Impossible");
        }
        out.close();
    }

    class MyLinkedList {
        final int length;
        char[] ch;
        int[] next;
        int[] prev;
        int cnt;

        MyLinkedList(char[] ch) {
            this.cnt = ch.length;
            this.length = ch.length + 2;
            this.ch = new char[length];
            System.arraycopy(ch, 0, this.ch, 1, length - 2);
            this.next = new int[length];
            this.prev = new int[length];
            for (int i = 0; i < length; ++i) {
                next[i] = i + 1;
                prev[i] = i - 1;
            }
        }

        void deleteAtPos(int pos) {
            next[prev[pos]] = next[pos];
            prev[next[pos]] = prev[pos];
            cnt--;
        }

        boolean isEmpty() {
            return cnt == 0;
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
