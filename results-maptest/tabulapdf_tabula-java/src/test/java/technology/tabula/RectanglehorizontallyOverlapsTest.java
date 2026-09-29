package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
public class RectanglehorizontallyOverlapsTest {
    @Test
    public void testHorizontallyOverlaps_Overlapping() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 5);
        Rectangle rect2 = new Rectangle(0, 5, 10, 5);
        assertTrue(rect1.horizontallyOverlaps(rect2));
    }

    @Test
    public void testHorizontallyOverlaps_NotOverlapping() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 5);
        Rectangle rect2 = new Rectangle(0, 15, 10, 5);
        assertFalse(rect1.horizontallyOverlaps(rect2));
    }

    @Test
    public void testHorizontallyOverlaps_ZeroOverlap() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 5);
        Rectangle rect2 = new Rectangle(0, 10, 10, 5);
        assertFalse(rect1.horizontallyOverlaps(rect2));
    }
}
