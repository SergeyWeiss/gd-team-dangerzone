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
            List<C.Event<Date>> events = new ArrayList<>();
            for (int i = randomIntBetween(1, 100); i > 0; i--) {
                events.add(solution.new Event<>(randomIntBetween(-50000, 50000), new Date(randomLong())));
            }
            Assert.assertTrue(solution.calculateDebt(events).equals(solution.naiveSolution(events)));
        }
    }

}
