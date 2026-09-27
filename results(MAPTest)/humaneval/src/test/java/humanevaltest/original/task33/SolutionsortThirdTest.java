package humanevaltest.original.task33;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
public class SolutionsortThirdTest {
    @Test
    public void test() {
        Solution solution = new Solution();
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        input.add(6);
        input.add(7);
        List<Integer> result = solution.sortThird(input);
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));
        assertEquals(Integer.valueOf(4), result.get(3));
        assertEquals(Integer.valueOf(5), result.get(4));
        assertEquals(Integer.valueOf(6), result.get(5));
        assertEquals(Integer.valueOf(7), result.get(6));
    }
}
