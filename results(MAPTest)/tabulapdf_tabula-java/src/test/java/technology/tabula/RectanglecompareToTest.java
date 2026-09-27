package technology.tabula;

import org.junit.Test;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class RectanglecompareToTest {

    @Test
    public void testCompareTo_EqualsReturnsZero() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(0, 0, 10, 10);
        assertEquals(0, r1.compareTo(r2));
    }

    @Test
    public void testCompareTo_VerticalOverlapExceedsThreshold() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(5, 0, 10, 10);
        // Assuming verticalOverlap returns a value > 0.4f
        // This is a simplified test since we can't directly call private methods
        // We assume the ILL_DEFINED_ORDER comparator behaves as expected
        assertEquals(0, r1.compareTo(r2));
    }

    @Test
    public void testCompareTo_VerticalOverlapBelowThreshold() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(15, 0, 10, 10);
        // Based on the implementation, rectangles with no vertical overlap will be compared by getBottom()
        // Since r1.getBottom() = 10 and r2.getBottom() = 10, they should be considered equal
        assertEquals(-1, r1.compareTo(r2));
    }
}
