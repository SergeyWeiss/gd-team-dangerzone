package codeforces.neerc.northern2013;

import java.io.*;
import java.util.*;

public class D {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public D() throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream("dwarf.in")));
        out = new PrintWriter(new FileOutputStream("dwarf.out"));
//        br = new BufferedReader(new InputStreamReader(System.in));
//        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new D().solve();
    }

    private void solve() throws IOException {
        final int n = nextInt();
        final int m = nextInt();
        final Item[] items = new Item[n + 1];
        final TreeSet<Item> itemSet = new TreeSet<>();
        for (int i = 1; i <= n; ++i) {
            items[i] = new Item(nextInt(), i);
            itemSet.add(items[i]);
        }
        final Craft[] crafts = new Craft[m];
        List<List<Craft>> l = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            l.add(new ArrayList<Craft>());
        }
        for (int i = 0; i < m; ++i) {
            final int result = nextInt();
            final int ingr1 = nextInt();
            final int ingr2 = nextInt();
            crafts[i] = new Craft(result, ingr1, ingr2);
            l.get(ingr1).add(crafts[i]);
            l.get(ingr2).add(crafts[i]);
        }
        while (!itemSet.isEmpty()) {
            final Item first = itemSet.first();
            itemSet.remove(first);
            final List<Craft> craftList = l.get(first.number);
            for (int i = 0; i < craftList.size(); ++i) {
                final Craft craft = craftList.get(i);
                final Item item = items[craft.result];
                if (itemSet.contains(item)) {
                    if (items[craft.ingr1].cost + items[craft.ingr2].cost < item.cost) {
                        itemSet.remove(item);
                        item.cost = items[craft.ingr1].cost + items[craft.ingr2].cost;
                        itemSet.add(item);
                    }
                }
            }
        }
        out.println(items[1].cost);
        out.close();
    }

    class Craft {
        int result;
        int ingr1;
        int ingr2;

        Craft(int result, int ingr1, int ingr2) {
            this.result = result;
            this.ingr1 = ingr1;
            this.ingr2 = ingr2;
        }

        @Override
        public String toString() {
            return "Craft{" +
                    "result=" + result +
                    ", ingr1=" + ingr1 +
                    ", ingr2=" + ingr2 +
                    '}';
        }
    }

    class Item implements Comparable<Item> {
        int cost;
        int number;

        Item(int cost, int number) {
            this.cost = cost;
            this.number = number;
        }

        @Override
        public int compareTo(Item o) {
            if (this.cost == o.cost) {
                return Integer.compare(this.number, o.number);
            }
            return Integer.compare(this.cost, o.cost);
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", number=" + number +
                    '}';
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

