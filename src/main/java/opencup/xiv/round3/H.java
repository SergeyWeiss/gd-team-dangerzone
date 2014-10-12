package opencup.xiv.round3;

import java.io.*;
import java.util.LinkedList;

public class H {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter writer = new PrintWriter(System.out);

    H() throws IOException {
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


    void solve() throws IOException {
        int n = readInt();
        LinkedList<Boolean>[] stacks = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            stacks[i] = new LinkedList<>();
            String[] s = reader.readLine().split(" ");
            int k = Integer.parseInt(s[0]);
            for (int j = k; j >=1; --j) {
                boolean bw = "bw".equals(s[j]);
                if (stacks[i].isEmpty() || stacks[i].peekLast() != bw) {
                    stacks[i].offer(bw);
                }
            }
        }

        int count = 0;

        for (int i = 0; i < stacks.length; i++) {
            LinkedList<Boolean> stack = stacks[i];
            if (stack.peekLast()) {
                count++;
                stack.pollLast();
            }
        }
        if (stacks.length == 1 && stacks[0].size() > 3) {
            writer.println("-1");
            writer.close();
            return;
        }
        boolean hasSizeBiggerThanOne = false;
        for(int i = 0; i < stacks.length; i++) {
            if(stacks[i].size() > 1) {
                hasSizeBiggerThanOne = true;
            }
            count += stacks[i].size();
        }
        if(hasSizeBiggerThanOne) {
            count--;
        }

        writer.println(count);
        writer.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        new H().solve();
    }

}
