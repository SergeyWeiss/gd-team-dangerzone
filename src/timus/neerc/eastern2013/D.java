package timus.neerc.eastern2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class D {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public D() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new D().solve();
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
        String message = br.readLine();
        String[] s = message.split(", ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length; ++i) {
            if (i != 0) {
                res.append(", ");
            }
            res.append(process(s[i]));
        }
        out.println(res.substring(0, 1).toUpperCase() + res.substring(1).toString().toLowerCase());
        out.flush();
    }

    private String process(String s) {
        char[] ch = s.toCharArray();
        StringBuilder object = new StringBuilder();
        StringBuilder subject = new StringBuilder();
        StringBuilder verb = new StringBuilder();
        StringBuilder conj = new StringBuilder();
        int i = 0;
        while (i < ch.length) {
            switch (ch[i]) {
                case '{': {
                    i++;
                    while (ch[i] != '}') {
                        object.append(ch[i]);
                        ++i;
                    }
                    i++;
                    break;
                }
                case '(': {
                    i++;
                    while (ch[i] != ')') {
                        subject.append(ch[i]);
                        ++i;
                    }
                    i++;
                    break;
                }
                case '[': {
                    i++;
                    while (ch[i] != ']') {
                        verb.append(ch[i]);
                        ++i;
                    }
                    i++;
                    break;
                }
                default: {
                    conj.append(ch[i]);
                    ++i;
                }
            }
        }
        if (conj.toString().trim().isEmpty()) {
            return object.toString() + " " + subject.toString().toLowerCase() + " " + verb.toString().toLowerCase();
        } else {
            return conj.toString().trim() + " " + object.toString().toLowerCase() + " " + subject.toString().toLowerCase() + " " + verb.toString().toLowerCase();
        }

    }
}
