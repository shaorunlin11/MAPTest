package humanevaltest.original.task127;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
public class SolutionintersectionTest {
    @Test
    public void testIntersectionNoOverlap() {
        Solution solution = new Solution();
        List<Integer> interval1 = new ArrayList<>();
        interval1.add(1);
        interval1.add(2);
        List<Integer> interval2 = new ArrayList<>();
        interval2.add(3);
        interval2.add(4);
        assertEquals("NO", solution.intersection(interval1, interval2));
    }

    @Test
    public void testIntersectionSinglePoint() {
        Solution solution = new Solution();
        List<Integer> interval1 = new ArrayList<>();
        interval1.add(1);
        interval1.add(3);
        List<Integer> interval2 = new ArrayList<>();
        interval2.add(3);
        interval2.add(5);
        assertEquals("NO", solution.intersection(interval1, interval2));
    }

    @Test
    public void testIntersectionTwoPoints() {
        Solution solution = new Solution();
        List<Integer> interval1 = new ArrayList<>();
        interval1.add(1);
        interval1.add(4);
        List<Integer> interval2 = new ArrayList<>();
        interval2.add(2);
        interval2.add(5);
        assertEquals("YES", solution.intersection(interval1, interval2));
    }

@Test
    public void testIntersectionPrimeLength() {
        Solution solution = new Solution();
        List<Integer> interval1 = new ArrayList<>();
        interval1.add(1);
        interval1.add(10);
        List<Integer> interval2 = new ArrayList<>();
        interval2.add(2);
        interval2.add(9);
        assertEquals("YES", solution.intersection(interval1, interval2));
    }

@Test
    public void testIntersectionTargetLines14() {
        Solution solution = new Solution();
        List<Integer> interval1 = new ArrayList<>();
        interval1.add(2);
        interval1.add(3);
        List<Integer> interval2 = new ArrayList<>();
        interval2.add(2);
        interval2.add(3);
        assertEquals("NO", solution.intersection(interval1, interval2));
    }
}
