package humanevaltest.original.task65;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutioncircularShiftTest {
    @Test
    public void testCircularShiftWithShiftGreaterThanLength() {
        Solution solution = new Solution();
        String result = solution.circularShift(1234, 5);
        assertEquals("4321", result);
    }

    @Test
    public void testCircularShiftWithShiftEqualtoLength() {
        Solution solution = new Solution();
        String result = solution.circularShift(1234, 4);
        assertEquals("1234", result);
    }

    @Test
    public void testCircularShiftWithShiftZero() {
        Solution solution = new Solution();
        String result = solution.circularShift(1234, 0);
        assertEquals("1234", result);
    }

    @Test
    public void testCircularShiftWithShiftLessThanLength() {
        Solution solution = new Solution();
        String result = solution.circularShift(1234, 2);
        assertEquals("3412", result);
    }

    @Test
    public void testCircularShiftWithSingleDigit() {
        Solution solution = new Solution();
        String result = solution.circularShift(5, 1);
        assertEquals("5", result);
    }

    @Test
    public void testCircularShiftWithMultipleDigitsAndShift() {
        Solution solution = new Solution();
        String result = solution.circularShift(987654, 3);
        assertEquals("654987", result);
    }
}
