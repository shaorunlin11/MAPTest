package humanevaltest.original.task151;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SolutiondoubleTheDifferenceTest {
    @Test
    public void testDoubleTheDifference() {
        Solution solution = new Solution();

        // Test case 1: Empty list
        List<Object> emptyList = new ArrayList<>();
        assertEquals(0, solution.doubleTheDifference(emptyList));

        // Test case 2: List with no positive odd integers
        List<Object> noPositiveOdds = new ArrayList<>();
        noPositiveOdds.add(2);
        noPositiveOdds.add(4.5);
        noPositiveOdds.add("hello");
        noPositiveOdds.add(0);
        noPositiveOdds.add(-3);
        assertEquals(0, solution.doubleTheDifference(noPositiveOdds));

        // Test case 3: List with positive odd integers
        List<Object> positiveOdds = new ArrayList<>();
        positiveOdds.add(1);
        positiveOdds.add(3);
        positiveOdds.add(5);
        assertEquals(1 + 9 + 25, solution.doubleTheDifference(positiveOdds));

        // Test case 4: Mixed types with some positive odd integers
        List<Object> mixedTypes = new ArrayList<>();
        mixedTypes.add(2);
        mixedTypes.add(3);
        mixedTypes.add("world");
        mixedTypes.add(5.5);
        mixedTypes.add(7);
        assertEquals(9 + 49, solution.doubleTheDifference(mixedTypes));

        // Test case 5: List with non-integer values and one positive odd integer
        List<Object> singlePositiveOdd = new ArrayList<>();
        singlePositiveOdd.add("test");
        singlePositiveOdd.add(5);
        singlePositiveOdd.add(null);
        assertEquals(25, solution.doubleTheDifference(singlePositiveOdd));

        // Test case 6: List with negative odd integers
        List<Object> negativeOdds = new ArrayList<>();
        negativeOdds.add(-1);
        negativeOdds.add(-3);
        assertEquals(0, solution.doubleTheDifference(negativeOdds));

        // Test case 7: List with even positive integers
        List<Object> evenNumbers = new ArrayList<>();
        evenNumbers.add(2);
        evenNumbers.add(4);
        evenNumbers.add(6);
        assertEquals(0, solution.doubleTheDifference(evenNumbers));
    }
}
