package timus.neerc.eastern2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class J {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public J() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new J().solve();
    }

    private void solve() throws IOException {
        int n = nextInt();
        class Monster {
            int id;
            int scariness;

            Monster(int id, int scariness) {
                this.id = id;
                this.scariness = scariness;
            }
        }
        Monster[][] monsters = new Monster[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                monsters[i][j] = new Monster(j + 1, nextInt());
            }
        }
        int[] totalScariness = new int[2];
        for (int i = 0; i < 2; i++) {
            for (Monster monster : monsters[i]) {
                totalScariness[i] += monster.scariness;
            }
        }
        int winner = totalScariness[0] >= totalScariness[1] ? 0 : 1;
        Arrays.sort(monsters[winner], new Comparator<Monster>() {
            @Override
            public int compare(Monster o1, Monster o2) {
                return Integer.compare(o1.scariness, o2.scariness);
            }
        });
        Arrays.sort(monsters[1 - winner], new Comparator<Monster>() {
            @Override
            public int compare(Monster o1, Monster o2) {
                return Integer.compare(o2.scariness, o1.scariness);
            }
        });
        for(int i = 0; i < n; i++) {
            out.println(monsters[0][i].id + " " + monsters[1][i].id);
        }
        out.close();
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
