package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectanglegetTopTest {

    @Test
    public void testGetTop() {
        // Test with default constructor
        Rectangle rect1 = new Rectangle();
        assertEquals(0.0f, rect1.getTop(), 0.0f);

        // Test with parameterized constructor
        Rectangle rect2 = new Rectangle(10.5f, 20.5f, 30.0f, 40.0f);
        assertEquals(10.5f, rect2.getTop(), 0.0f);

        // Test with negative top value
        Rectangle rect3 = new Rectangle(-5.0f, 10.0f, 20.0f, 30.0f);
        assertEquals(-5.0f, rect3.getTop(), 0.0f);

        // Test with zero top value
        Rectangle rect4 = new Rectangle(0.0f, 5.0f, 10.0f, 15.0f);
        assertEquals(0.0f, rect4.getTop(), 0.0f);
    }
}
