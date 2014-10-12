package timus.neerc.eastern2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    private void solve() throws IOException {
        final int zMoney = nextInt();
        final int liveAlone = nextInt();
        final int livingAloneIn2 = nextInt();
        final int n = nextInt();
        Friend[] friends = new Friend[n];
        for (int i = 0; i < n; ++i) {
            friends[i] = new Friend(nextInt(), nextInt());
        }
        final int m = nextInt();
        Apart[] aparts = new Apart[m];
        for (int i = 0; i < m; ++i) {
            aparts[i] = new Apart(nextInt(), nextInt(), nextInt());
        }
        List<Solution> sols = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            if (aparts[i].cost <= zMoney) {
                if (aparts[i].roomsNumber == 1) {
                    sols.add(new Solution(aparts[i].cost, aparts[i].rating + liveAlone, -1, i));
                }  else {
                    sols.add(new Solution(aparts[i].cost, aparts[i].rating + livingAloneIn2, -1, i));
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (aparts[i].cost <= zMoney * 2 && aparts[i].cost <= friends[j].money * 2 && aparts[i].roomsNumber == 2) {
                    sols.add(new Solution(aparts[i].cost, aparts[i].rating + friends[j].rating, j, i));
                }
            }
        }

        int maxRating = Integer.MIN_VALUE;
        int pos = -1;
        for (int i = 0; i < sols.size(); ++i) {
            if (sols.get(i).rating > maxRating) {
                maxRating = sols.get(i).rating;
                pos = i;
            }
        }

        if (pos == -1) {
            out.println("Forget about apartments. Live in the dormitory.");
        } else {
            final Solution solution = sols.get(pos);
            if (solution.friendNumber == -1) {
                out.printf(Locale.US, "You should rent the apartment #%d alone.", solution.apartNumber + 1);
            } else {
                out.printf(Locale.US, "You should rent the apartment #%d with the friend #%d.", solution.apartNumber + 1, solution.friendNumber + 1);
            }
        }

        out.close();
    }

    class Solution {
        int cost;
        int rating;
        int friendNumber;
        int apartNumber;

        Solution(int cost, int rating, int friendNumber, int apartNumber) {
            this.cost = cost;
            this.rating = rating;
            this.friendNumber = friendNumber;
            this.apartNumber = apartNumber;
        }
    }


    class Friend {
        int money;
        int rating;

        Friend(int money, int rating) {
            this.money = money;
            this.rating = rating;
        }
    }

    class Apart {
        int roomsNumber;
        int cost;
        int rating;

        Apart(int roomsNumber, int cost, int rating) {
            this.roomsNumber = roomsNumber;
            this.cost = cost;
            this.rating = rating;
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
