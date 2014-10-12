package opencup.xiv.round5;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public G() throws IOException {
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
        br = new BufferedReader(new FileReader("patterns.in"));
        out = new PrintWriter(new FileWriter("patterns.out"));
    }

    public static void main(String[] args) throws IOException {
        final G j = new G();
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

    String commonPrefix(String a, String b) {
        int i = 0;
        while(i < a.length() && i < b.length() && a.charAt(i) != '*' && a.charAt(i) == b.charAt(i)) {
            i++;
        }
        return a.substring(0, i);
    }
    String commonSuffix(String a, String b) {
        int i = 0;
        while(i < a.length() && i < b.length() && a.charAt(a.length() - 1 - i) != '*' && a.charAt(a.length() - 1 - i) == b.charAt(b.length() - 1 - i)) {
            i++;
        }
        return a.substring(a.length() - i);
    }

    int[] prefixFunction(char[] s) {
        int[] pi = new int[s.length + 1];
        for(int i = 2; i <= s.length; i++) {
            int k = pi[i - 1];
            while(k > 0 && s[k] != s[i - 1]) k = pi[k];
            if(s[k] == s[i - 1]) k++;
            pi[i] = k;
        }
        return pi;
    }

    boolean matches(String pattern, String s) {
        int i = 0;
        for(String p: pattern.split("\\*")) {
            if("".equals(p)) continue;
            int[] pi = prefixFunction(p.toCharArray());
            int k = 0;
            while(k < p.length() && i < s.length()) {
                while(k > 0 && p.charAt(k) != s.charAt(i)) k = pi[k];
                if(p.charAt(k) == s.charAt(i)) k++;
                i++;
            }
            if(i == s.length() && k != p.length()) return false;
        }
        return true;
    }

    String purePrefix(String s) {
        int i = 0;
        while(s.charAt(i) != '*') i++;
        return s.substring(0, i);
    }

    String pureSuffix(String s) {
        int i = 0;
        while(s.charAt(s.length() - 1 - i) != '*') {
            i++;
        }
        return s.substring(s.length() - i);
    }

    String concat(String... s) {
        StringBuilder sb = new StringBuilder();
        for(String x: s) {
            sb.append(x);
        }
        return sb.toString();
    }

    private void solve() throws IOException {
        String a = next();
        String b = next();
        boolean onlyStar = true;
        for(int i = 0; onlyStar && i < a.length(); i++) {
            if(a.charAt(i) != '*') onlyStar = false;
        }
        for(int i = 0; onlyStar && i < b.length(); i++) {
            if(b.charAt(i) != '*') onlyStar = false;
        }
        if(onlyStar) {
            out.println("a");
            out.close();
            return;
        }
        String prefix = commonPrefix(a, b);
        a = a.substring(prefix.length());
        b = b.substring(prefix.length());
        String suffix = commonSuffix(a, b);
        a = a.substring(0, a.length() - suffix.length());
        b = b.substring(0, b.length() - suffix.length());
        if(a.length() == 0 && b.length() == 0) {
            out.println(concat(prefix, suffix));
        }
        else if(a.length() == 0 || b.length() == 0) {
            a = a.replaceAll("\\*", "");
            b = b.replaceAll("\\*", "");
            if(a.length() == 0 && b.length() == 0) {
                out.println(concat(prefix, suffix));
            }
            else {
                out.println("Impossible");
            }
        }
        else if(a.charAt(0) != '*' && b.charAt(0) != '*' || a.charAt(a.length() - 1) != '*' && b.charAt(b.length() - 1) != '*') {
            out.println("Impossible");
        }
        else if(a.contains("*") && b.contains("*")) {
            String pa = purePrefix(a);
            a = a.substring(pa.length());
            String pb = purePrefix(b);
            b = b.substring(pb.length());
            String sa = pureSuffix(a);
            a = a.substring(0, a.length() - sa.length());
            String sb = pureSuffix(b);
            b = b.substring(0, b.length() - sb.length());
            a = a.replaceAll("\\*", "");
            b = b.replaceAll("\\*", "");
            out.println(concat(prefix, pa, pb, a, b, sa, sb, suffix));
        }
        else if(a.charAt(0) == '*') {
            if(matches(a, b)) {
                out.println(concat(prefix, b, suffix));
            }
            else {
                out.println("Impossible");
            }
        }
        else if(b.charAt(0) == '*') {
            if(matches(b, a)) {
                out.println(concat(prefix, a, suffix));
            }
            else {
                out.println("Impossible");
            }
        }
        out.close();
    }



}
