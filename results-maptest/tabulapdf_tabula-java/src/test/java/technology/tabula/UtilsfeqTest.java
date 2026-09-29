package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
public class UtilsfeqTest {
    @Test
    public void testFeqWithEqualValues() {
        assertTrue(Utils.feq(1.0, 1.0));
    }


    @Test
    public void testFeqWithExactlyEpsilonDifference() {
        assertFalse(Utils.feq(1.0, 1.01));
        assertFalse(Utils.feq(1.0, 0.99));
    }

    @Test
    public void testFeqWithLargerDifference() {
        assertFalse(Utils.feq(1.0, 1.02));
        assertFalse(Utils.feq(1.0, 0.98));
    }

    @Test
    public void testFeqWithZeroDifference() {
        assertTrue(Utils.feq(1.0, 1.0));
    }

}
