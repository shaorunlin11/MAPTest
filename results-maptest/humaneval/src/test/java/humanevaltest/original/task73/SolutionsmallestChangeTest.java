package humanevaltest.original.task73;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class SolutionsmallestChangeTest {
    @Test
    public void testEmptyList() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        assertEquals(0, solution.smallestChange(arr));
    }

    @Test
    public void testSingleElementList() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(5);
        assertEquals(0, solution.smallestChange(arr));
    }

    @Test
    public void testSymmetricList() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(2);
        arr.add(1);
        assertEquals(0, solution.smallestChange(arr));
    }

    @Test
    public void testAsymmetricList() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        assertEquals(2, solution.smallestChange(arr));
    }

    @Test
    public void testEvenLengthListWithMismatches() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(2);
        arr.add(1);
        assertEquals(0, solution.smallestChange(arr));
    }

    @Test
    public void testOddLengthListWithMismatches() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        assertEquals(2, solution.smallestChange(arr));
    }
}
