package humanevaltest.original.task36;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionfizzBuzzTest {
    @Test
    public void testFizzBuzzWithN1() {
        Solution solution = new Solution();
        int result = solution.fizzBuzz(1);
        assertEquals(0, result);
    }

    @Test
    public void testFizzBuzzWithN14() {
        Solution solution = new Solution();
        int result = solution.fizzBuzz(14);
        assertEquals(0, result);
    }

    @Test
    public void testFizzBuzzWithN22() {
        Solution solution = new Solution();
        int result = solution.fizzBuzz(22);
        assertEquals(0, result);
    }

    @Test
    public void testFizzBuzzWithN26() {
        Solution solution = new Solution();
        int result = solution.fizzBuzz(26);
        assertEquals(0, result);
    }

    @Test
    public void testFizzBuzzWithN77() {
        Solution solution = new Solution();
        int result = solution.fizzBuzz(77);
        assertEquals(0, result);
    }
}
