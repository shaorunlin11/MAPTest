package humanevaltest.original.task25;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

public class SolutionfactorizeTest {
    @Test
    public void testFactorize() {
        Solution solution = new Solution();

        // Test case: n = 1
        List<Integer> result1 = solution.factorize(1);
        assertTrue(result1.isEmpty());

        // Test case: n = 2
        List<Integer> result2 = solution.factorize(2);
        assertEquals(1, result2.size());
        assertEquals(Integer.valueOf(2), result2.get(0));

        // Test case: n = 4
        List<Integer> result4 = solution.factorize(4);
        assertEquals(2, result4.size());
        assertEquals(Integer.valueOf(2), result4.get(0));
        assertEquals(Integer.valueOf(2), result4.get(1));

        // Test case: n = 6
        List<Integer> result6 = solution.factorize(6);
        assertEquals(2, result6.size());
        assertEquals(Integer.valueOf(2), result6.get(0));
        assertEquals(Integer.valueOf(3), result6.get(1));

        // Test case: n = 12
        List<Integer> result12 = solution.factorize(12);
        assertEquals(3, result12.size());
        assertEquals(Integer.valueOf(2), result12.get(0));
        assertEquals(Integer.valueOf(2), result12.get(1));
        assertEquals(Integer.valueOf(3), result12.get(2));

        // Test case: n = 17 (prime)
        List<Integer> result17 = solution.factorize(17);
        assertEquals(1, result17.size());
        assertEquals(Integer.valueOf(17), result17.get(0));

        // Test case: n = 0
        List<Integer> result0 = solution.factorize(0);
        assertTrue(result0.isEmpty());

        // Test case: n = -5
        List<Integer> resultNegative = solution.factorize(-5);
        assertTrue(resultNegative.isEmpty());
    }
}
