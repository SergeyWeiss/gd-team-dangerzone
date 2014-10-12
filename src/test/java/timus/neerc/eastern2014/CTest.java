package timus.neerc.eastern2014;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class CTest extends RandomizedTest {
    @Test
    public void testCalculateDebt() throws IOException {
        C solution = new C();
        for (int k = 0; k < 1000; k++) {
            C.Event[] events = new C.Event[randomIntBetween(1, 1000)];
            for (int i = 0; i < events.length; i++) {
                events[i] = solution.new Event(randomIntBetween(-50000, 50000), new Date(randomLong()));
            }
            Assert.assertTrue(solution.calculateDebt(events).equals(naiveSolution(events)));
        }
    }

    private List<Long> naiveSolution(C.Event[] events) {
        int n = events.length;
        C.Event[] localCopy = new C.Event[n];
        System.arraycopy(events, 0, localCopy, 0, n);
        List<Long> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Arrays.sort(localCopy, 0, i + 1, new Comparator<C.Event>() {
                @Override
                public int compare(C.Event o1, C.Event o2) {
                    return o1.date.compareTo(o2.date);
                }
            });
            long debt = 0;
            long sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += localCopy[j].amount;
                debt = Math.min(debt, sum);
            }
            answer.add(debt);
        }
        return answer;
    }
}
