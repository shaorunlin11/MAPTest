package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.AbstractList;
import java.util.List;
public class UtilsrangeTest {
    @Test
    public void testRangeWithBeginLessThanEnd() {
        List<Integer> result = Utils.range(1, 5);
        assertEquals(4, result.size());
        assertEquals(1, (int) result.get(0));
        assertEquals(2, (int) result.get(1));
        assertEquals(3, (int) result.get(2));
        assertEquals(4, (int) result.get(3));
    }

    @Test
    public void testRangeWithBeginEqualToEnd() {
        List<Integer> result = Utils.range(3, 3);
        assertEquals(0, result.size());
    }


    @Test
    public void testRangeWithNegativeValues() {
        List<Integer> result = Utils.range(-3, 2);
        assertEquals(5, result.size());
        assertEquals(-3, (int) result.get(0));
        assertEquals(-2, (int) result.get(1));
        assertEquals(-1, (int) result.get(2));
        assertEquals(0, (int) result.get(3));
        assertEquals(1, (int) result.get(4));
    }
}
