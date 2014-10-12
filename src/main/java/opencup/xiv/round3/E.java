package opencup.xiv.round3;

import java.io.*;
import java.util.*;

public class E {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter writer = new PrintWriter(System.out);

    E() throws IOException {
        reader = new BufferedReader(new FileReader("input.txt"));
        writer = new PrintWriter(new FileWriter("output.txt"));
    }

    long[] readLongs() throws IOException {
        String[] s = reader.readLine().split(" ");
        long[] longs = new long[s.length];
        for(int i = 0; i < longs.length; i++) {
            longs[i] = Long.parseLong(s[i]);
        }
        return longs;
    }

    int[] readInts() throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for(int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }

    int[] tt;
    int tx = 0;
    int readInt() throws IOException {
        if(tt == null || tx >= tt.length) {
            tt = readInts();
            tx = 0;
        }
        return tt[tx++];
    }

    void solve() throws IOException {

        int[] line = readInts();
        final int n = line[0];
        final int l = line[1];
        HashMap<Integer, MutableInt> map = new HashMap<>(n * 2);
        for (int i = 0; i < n; i++) {
            int input = readInt();
            MutableInt mutableInt = map.get(input);
            if (mutableInt != null) {
                mutableInt.value++;
            } else {
                map.put(input, new MutableInt(1));
            }

        }

        for (Map.Entry<Integer, MutableInt> integerMutableIntEntry : map.entrySet()) {
            if (integerMutableIntEntry.getValue().value % l != 0) {
                writer.println("ARGH!!1");
                writer.flush();
                writer.close();
                reader.close();

                return;

            }
        }
        writer.println("OK");
        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        new E().solve();
    }

    private static final class MutableInt {
        int value;

        private MutableInt(int value) {
            this.value = value;
        }
    }
}