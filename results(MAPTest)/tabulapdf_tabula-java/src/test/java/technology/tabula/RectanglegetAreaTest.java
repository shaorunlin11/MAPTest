package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectanglegetAreaTest {
    @Test
    public void testGetArea() {
        Rectangle rect = new Rectangle(0, 0, 5.0f, 10.0f);
        assertEquals(50.0f, rect.getArea(), 0.001f);
    }

    @Test
    public void testGetAreaWithZeroDimensions() {
        Rectangle rect = new Rectangle(0, 0, 0.0f, 10.0f);
        assertEquals(0.0f, rect.getArea(), 0.001f);
    }

    @Test
    public void testGetAreaWithNegativeDimensions() {
        Rectangle rect = new Rectangle(0, 0, -5.0f, 10.0f);
        assertEquals(-50.0f, rect.getArea(), 0.001f);
    }

    @Test
    public void testGetAreaWithLargeValues() {
        Rectangle rect = new Rectangle(0, 0, 1000.0f, 2000.0f);
        assertEquals(2000000.0f, rect.getArea(), 0.001f);
    }
}
