package humanevaltest.original.task111;
import org.junit.Test;
import org.junit.Assert;
import java.util.*;
public class SolutionhistogramTest {
    @Test
    public void testHistogramWithMultipleWords() {
        Solution solution = new Solution();
        String input = "apple banana apple orange banana";
        Map<String, Integer> result = solution.histogram(input);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("apple", 2);
        expected.put("banana", 2);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testHistogramWithSingleWord() {
        Solution solution = new Solution();
        String input = "apple";
        Map<String, Integer> result = solution.histogram(input);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("apple", 1);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testHistogramWithEmptyInput() {
        Solution solution = new Solution();
        String input = "";
        Map<String, Integer> result = solution.histogram(input);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testHistogramWithWhitespaceOnly() {
        Solution solution = new Solution();
        String input = "   ";
        Map<String, Integer> result = solution.histogram(input);
        Assert.assertTrue(result.isEmpty());
    }


    @Test
    public void testHistogramWithAllDifferentFrequencies() {
        Solution solution = new Solution();
        String input = "apple banana orange";
        Map<String, Integer> result = solution.histogram(input);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("apple", 1);
        expected.put("banana", 1);
        expected.put("orange", 1);
        Assert.assertEquals(expected, result);
    }
}
