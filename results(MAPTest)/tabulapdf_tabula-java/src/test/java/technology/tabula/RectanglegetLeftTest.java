package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectanglegetLeftTest {
    @Test
    public void testGetLeft() {
        Rectangle rectangle = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        assertEquals(20.0f, rectangle.getLeft(), 0.0f);
    }
}
