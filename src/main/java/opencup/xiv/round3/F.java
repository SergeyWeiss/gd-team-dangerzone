package opencup.xiv.round3;

import java.util.LinkedList;
import java.io.*;

public class F {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter writer = new PrintWriter(System.out);

    F() throws IOException {
        reader = new BufferedReader(new FileReader("input.txt"));
        writer = new PrintWriter(new FileWriter("output.txt"));
    }

    long[] readLongs() throws IOException {
        String[] s = reader.readLine().split(" ");
        long[] longs = new long[s.length];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = Long.parseLong(s[i]);
        }
        return longs;
    }

    int[] readInts() throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }

    int[] tt;
    int tx = 0;

    int readInt() throws IOException {
        if (tt == null || tx >= tt.length) {
            tt = readInts();
            tx = 0;
        }
        return tt[tx++];
    }


    char[] brackets = "([{<>}])".toCharArray();
    boolean[] closing = {false, false, false, false, true, true, true, true};
    int[] type = {0, 1, 2, 3, 3, 2, 1, 0};

    boolean isClosing(char c) {
        for(int i = 0; i < brackets.length; i++) {
            if(brackets[i] == c) {
                return closing[i];
            }
        }
        return false;
    }
    int getType(char c) {
        for(int i = 0; i < brackets.length; i++) {
            if(brackets[i] == c) {
                return type[i];
            }
        }
        return 0;
    }

    class Series {
        int[] cnt = new int[4];
        int total = 0;
        void inc(char c) {
            cnt[getType(c)]++;
            total++;
        }
        void dec(int type, int howMany) {
            total -= howMany;
            cnt[type] -= howMany;
        }
        boolean closing;
    }

    Series readSeries(char[] s, int i) {
        int j = i + 1;
        while(j < s.length && isClosing(s[j]) == isClosing(s[i])) j++;
        Series series = new Series();
        series.closing = isClosing(s[i]);
        for(int k = i; k < j; k++) {
            series.inc(s[k]);
        }
        return series;
    }

    boolean check(char[] s) {
        LinkedList<Series> stack = new LinkedList<>();
        for(int i = 0; i < s.length; i++) {
            Series series = readSeries(s, i);
            i += series.total - 1;
            if(series.closing) {
                while(series.total > 0 && !stack.isEmpty()) {
                    Series open = stack.pollLast();
                    for(int t = 0; t < 4; t++) {
                        int howMany = Math.min(series.cnt[t], open.cnt[t]);
                        open.dec(t, howMany);
                        series.dec(t, howMany);
                    }
                    if(open.total > 0 && series.total > 0) {
                        return false;
                    }
                    if(open.total > 0) {
                        stack.offerLast(open);
                    }
                }
                if(series.total > 0 && stack.isEmpty()) {
                    return false;
                }
            }
            else {
                stack.offerLast(series);
            }
        }
        return stack.isEmpty();
    }



    void solve() throws IOException {
        int n = readInt();
        for (int i = 0; i < n; i++) {
            writer.println(check(reader.readLine().toCharArray()) ? "T" : "NIL");
        }

        writer.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        new F().solve();
    }

}
