package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleisLtrDominantTest {
    @Test
    public void testIsLtrDominantReturnsZero() {
        Rectangle rectangle = new Rectangle();
        int result = rectangle.isLtrDominant();
        assertEquals(0, result);
    }
}
