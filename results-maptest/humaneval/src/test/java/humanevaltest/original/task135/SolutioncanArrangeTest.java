package humanevaltest.original.task135;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class SolutioncanArrangeTest {

    @Test
    public void testCanArrangeEmptyList() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        assertEquals(-1, solution.canArrange(arr));
    }

    @Test
    public void testCanArrangeSingleElement() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(5);
        assertEquals(-1, solution.canArrange(arr));
    }

    @Test
    public void testCanArrangeStrictlyIncreasing() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        assertEquals(-1, solution.canArrange(arr));
    }

    @Test
    public void testCanArrangeWithOneDecrease() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(3);
        arr.add(2);
        assertEquals(2, solution.canArrange(arr));
    }

    @Test
    public void testCanArrangeWithMultipleDecreases() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(3);
        arr.add(2);
        arr.add(5);
        arr.add(4);
        assertEquals(4, solution.canArrange(arr));
    }

    @Test
    public void testCanArrangeWithDecreaseAtEnd() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(10);
        arr.add(20);
        arr.add(30);
        arr.add(25);
        assertEquals(3, solution.canArrange(arr));
    }

    @Test
    public void testCanArrangeWithDecreaseAtStart() {
        Solution solution = new Solution();
        List<Integer> arr = new ArrayList<>();
        arr.add(5);
        arr.add(4);
        arr.add(3);
        arr.add(2);
        assertEquals(3, solution.canArrange(arr));
    }
}
