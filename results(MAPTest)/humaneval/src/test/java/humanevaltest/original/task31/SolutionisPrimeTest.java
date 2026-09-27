package humanevaltest.original.task31;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionisPrimeTest {

    @Test
    public void testIsPrimeForNonPrimeNumbers() {
        Solution solution = new Solution();
        assertFalse("isPrime(0) should return false", solution.isPrime(0));
        assertFalse("isPrime(1) should return false", solution.isPrime(1));
        assertFalse("isPrime(4) should return false", solution.isPrime(4));
        assertFalse("isPrime(6) should return false", solution.isPrime(6));
        assertFalse("isPrime(9) should return false", solution.isPrime(9));
        assertFalse("isPrime(15) should return false", solution.isPrime(15));
    }

    @Test
    public void testIsPrimeForPrimeNumbers() {
        Solution solution = new Solution();
        assertTrue("isPrime(2) should return true", solution.isPrime(2));
        assertTrue("isPrime(3) should return true", solution.isPrime(3));
        assertTrue("isPrime(5) should return true", solution.isPrime(5));
        assertTrue("isPrime(7) should return true", solution.isPrime(7));
        assertTrue("isPrime(11) should return true", solution.isPrime(11));
        assertTrue("isPrime(13) should return true", solution.isPrime(13));
    }

    @Test
    public void testIsPrimeForEdgeCases() {
        Solution solution = new Solution();
        assertFalse("isPrime(0) should return false", solution.isPrime(0));
        assertFalse("isPrime(1) should return false", solution.isPrime(1));
        assertTrue("isPrime(2) should return true", solution.isPrime(2));
    }
}
