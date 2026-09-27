package humanevaltest.original.task163;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class SolutiongenerateIntegersTest {
    @Test
    public void testGenerateIntegersWithBothValuesBelow2() {
        Solution solution = new Solution();
        List<Integer> result = solution.generateIntegers(1, 1);
        assertEquals("Expected list to be empty", 0, result.size());
    }

    @Test
    public void testGenerateIntegersWithBothValuesAbove8() {
        Solution solution = new Solution();
        List<Integer> result = solution.generateIntegers(9, 10);
        assertEquals("Expected list to be empty", 0, result.size());
    }



    @Test
    public void testGenerateIntegersWithLowerBoundExactly2() {
        Solution solution = new Solution();
        List<Integer> result = solution.generateIntegers(2, 5);
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(4);
        assertEquals("Expected list of even numbers starting at 2", expected, result);
    }


    @Test
    public void testGenerateIntegersWithSameLowerAndUpperBounds() {
        Solution solution = new Solution();
        List<Integer> result = solution.generateIntegers(4, 4);
        List<Integer> expected = new ArrayList<>();
        expected.add(4);
        assertEquals("Expected list with single even number", expected, result);
    }


    @Test
    public void testGenerateIntegersWithOneValueExactly2AndOneAbove8() {
        Solution solution = new Solution();
        List<Integer> result = solution.generateIntegers(2, 9);
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(4);
        expected.add(6);
        expected.add(8);
        assertEquals("Expected list with even numbers up to 8", expected, result);
    }

    @Test
    public void testGenerateIntegersWithOneValueBelow2AndOneExactly8() {
        Solution solution = new Solution();
        List<Integer> result = solution.generateIntegers(1, 8);
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(4);
        expected.add(6);
        expected.add(8);
        assertEquals("Expected list of even numbers from 2 to 8", expected, result);
    }
}
