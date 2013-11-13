package opencup.xiv.round3;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class C {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter writer = new PrintWriter(System.out);

    C() throws IOException {
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
        int w = readInt();
        class Node {
            String name;
            List<Node> children = null;

            Node(String name, List<Node> children) {
                this.name = name;
                this.children = children;
            }
        }
        LinkedList<Node> path = new LinkedList<>();
        Node root = null;
        while(true) {
            String s = reader.readLine();
            if(s == null || "!".equals(s)) {
                break;
            }
            String name = s.contains(".") ? s.substring(s.indexOf('.') + 1) : s.substring(s.indexOf('-') + 1);
            boolean isDirectory = s.contains("-");
            Node node = new Node(name, isDirectory ? new ArrayList<Node>() : null);
            if(path.isEmpty()) {
                root = node;

            }
            int level = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '|') {
                    level++;
                }
            }
            while(level != path.size()) {
                path.pollLast();
            }
            path.peekLast().children.add(node);
            path.offerLast(node);
        }
        writer.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        new C().solve();
    }

}
