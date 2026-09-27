package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.awt.geom.Rectangle2D;

import static org.junit.Assert.assertEquals;

public class RectanglesetRightTest {
    private Rectangle rectangle;

    @Before
    public void setUp() {
        rectangle = new Rectangle(0, 0, 10, 20);
    }

    @After
    public void tearDown() {
        rectangle = null;
    }

    @Test
    public void testSetRightUpdatesWidthCorrectly() {
        float newRight = 30;
        rectangle.setRight(newRight);

        assertEquals("X coordinate should remain unchanged", 0, rectangle.getX(), 0.0f);
        assertEquals("Y coordinate should remain unchanged", 0, rectangle.getY(), 0.0f);
        assertEquals("Height should remain unchanged", 20, rectangle.getHeight(), 0.0f);
        assertEquals("Width should be updated to newRight - x", 30, rectangle.getWidth(), 0.0f);
    }

    @Test
    public void testSetRightWithNegativeWidth() {
        float newRight = -5;
        rectangle.setRight(newRight);

        assertEquals("X coordinate should remain unchanged", 0, rectangle.getX(), 0.0f);
        assertEquals("Y coordinate should remain unchanged", 0, rectangle.getY(), 0.0f);
        assertEquals("Height should remain unchanged", 20, rectangle.getHeight(), 0.0f);
        assertEquals("Width should be updated to newRight - x", -5, rectangle.getWidth(), 0.0f);
    }
}
