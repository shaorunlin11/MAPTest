package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectanglegetRightTest {
    @Test
    public void testGetRight() {
        Rectangle rect = new Rectangle(0, 0, 100, 50);
        assertEquals(100.0f, rect.getRight(), 0.0f);
    }

    @Test
    public void testGetRightWithDifferentCoordinates() {
        Rectangle rect = new Rectangle(10, 20, 150, 75);
        assertEquals(170.0f, rect.getRight(), 0.0f);
    }

    @Test
    public void testGetRightWithNegativeCoordinates() {
        Rectangle rect = new Rectangle(-5, -10, 200, 100);
        assertEquals(190.0f, rect.getRight(), 0.0f);
    }
}
