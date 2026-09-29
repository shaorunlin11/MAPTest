package humanevaltest.original.task149;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;
public class SolutionlistSortTest {
    private Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }

    @After
    public void tearDown() {
        solution = null;
    }

    @Test
    public void testListSortEmptyInput() {
        List<String> input = new ArrayList<>();
        List<String> result = solution.listSort(input);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testListSortWithEvenAndOddLengthStrings() {
        List<String> input = new ArrayList<>(Arrays.asList("a", "bb", "ccc", "dddd", "eeeee"));
        List<String> result = solution.listSort(input);
        List<String> expected = new ArrayList<>(Arrays.asList("bb", "dddd"));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testListSortWithAllEvenLengthStrings() {
        List<String> input = new ArrayList<>(Arrays.asList("ab", "cd", "efg", "ghij"));
        List<String> result = solution.listSort(input);
        List<String> expected = new ArrayList<>(Arrays.asList("ab", "cd", "ghij"));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testListSortWithAllOddLengthStrings() {
        List<String> input = new ArrayList<>(Arrays.asList("a", "bbb", "ccccc"));
        List<String> result = solution.listSort(input);
        Assert.assertTrue(result.isEmpty());
    }
}
