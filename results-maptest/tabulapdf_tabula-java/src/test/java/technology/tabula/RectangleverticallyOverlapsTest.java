package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
public class RectangleverticallyOverlapsTest {
    @Test
    public void testVerticallyOverlaps_Overlapping() {
        Rectangle r1 = new Rectangle(0, 0, 100, 100);
        Rectangle r2 = new Rectangle(50, 50, 100, 100);
        assertTrue(r1.verticallyOverlaps(r2));
    }


    @Test
    public void testVerticallyOverlaps_NullOther() {
        Rectangle r1 = new Rectangle(0, 0, 100, 100);
        try {
            r1.verticallyOverlaps(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }


    @Test
    public void testVerticallyOverlaps_TopEdgeOverlap() {
        Rectangle r1 = new Rectangle(0, 0, 100, 100);
        Rectangle r2 = new Rectangle(0, 99, 100, 100);
        assertTrue(r1.verticallyOverlaps(r2));
    }
}
