package humanevaltest.original.task137;

import org.junit.Test;
import java.util.Optional;

import static org.junit.Assert.*;

public class SolutioncompareOneTest {

    @Test
    public void testCompareOneEqualIntegers() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne(5, 5);
        assertFalse(result.isPresent());
    }

    @Test
    public void testCompareOneIntegerVsDouble() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne(5, 5.0);
        assertFalse(result.isPresent());
    }

    @Test
    public void testCompareOneStringWithDecimal() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne("123.45", "123.45");
        assertFalse(result.isPresent());
    }

    @Test
    public void testCompareOneStringWithComma() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne("123,45", "123.45");
        assertFalse(result.isPresent());
    }

    @Test
    public void testCompareOneIntegerVsString() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne(10, "10.0");
        assertFalse(result.isPresent());
    }

    @Test
    public void testCompareOneDoubleVsString() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne(10.5, "10.5");
        assertFalse(result.isPresent());
    }

    @Test
    public void testCompareOneAIsLarger() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne(10, 5);
        assertEquals(Optional.of(10), result);
    }

    @Test
    public void testCompareOneBIsLarger() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne(5, 10);
        assertEquals(Optional.of(10), result);
    }

    @Test
    public void testCompareOneStringVsInteger() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne("15", 10);
        assertEquals(Optional.of("15"), result);
    }

    @Test
    public void testCompareOneStringWithCommaVsDouble() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne("15,5", 15.5);
        assertFalse(result.isPresent());
    }

    @Test
    public void testCompareOneStringCanBeConvertedToDouble() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne("123.45", "67.89");
        assertEquals(Optional.of("123.45"), result);
    }

    @Test
    public void testCompareOneStringCanBeConvertedToDoubleWithComma() {
        Solution solution = new Solution();
        Optional<Object> result = solution.compareOne("123,45", "67.89");
        assertEquals(Optional.of("123,45"), result);
    }
}
