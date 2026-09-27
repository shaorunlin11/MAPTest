package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class RectanglesetBottomTest {
    private Rectangle rectangle;

    @Before
    public void setUp() {
        rectangle = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
    }

    @Test
    public void testSetBottomUpdatesBottomCoordinate() {
        float newBottom = 50.0f;
        rectangle.setBottom(newBottom);

        assertEquals("The bottom coordinate should be updated", newBottom, rectangle.getBottom(), 0.001f);
    }

    @Test
    public void testSetBottomDoesNotAffectXAndWidth() {
        float newBottom = 60.0f;
        rectangle.setBottom(newBottom);

        assertEquals("The x coordinate should remain unchanged", 20.0f, rectangle.getX(), 0.001f);
        assertEquals("The width should remain unchanged", 30.0f, rectangle.getWidth(), 0.001f);
    }

    @Test
    public void testSetBottomWithBottomLessThanY() {
        float newBottom = 5.0f;
        rectangle.setBottom(newBottom);

        assertEquals("The bottom coordinate should be updated", newBottom, rectangle.getBottom(), 0.001f);
    }

    @Test
    public void testSetBottomWithBottomGreaterThanY() {
        float newBottom = 70.0f;
        rectangle.setBottom(newBottom);

        assertEquals("The bottom coordinate should be updated", newBottom, rectangle.getBottom(), 0.001f);
    }
}
