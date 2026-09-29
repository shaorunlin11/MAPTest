package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectanglegetBottomTest {

    @Test
    public void testGetBottom() {
        Rectangle rect = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        assertEquals(50.0f, rect.getBottom(), 0.001f);
    }

    @Test
    public void testGetBottomWithNegativeValues() {
        Rectangle rect = new Rectangle(-5.0f, -10.0f, 20.0f, 30.0f);
        assertEquals(25.0f, rect.getBottom(), 0.001f);
    }

    @Test
    public void testGetBottomWithZeroDimensions() {
        Rectangle rect = new Rectangle(0.0f, 0.0f, 0.0f, 0.0f);
        assertEquals(0.0f, rect.getBottom(), 0.001f);
    }
}
