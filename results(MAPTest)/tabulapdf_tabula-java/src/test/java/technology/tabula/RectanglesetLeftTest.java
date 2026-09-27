package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
public class RectanglesetLeftTest {
    @Test
    public void testSetLeft() {
        Rectangle rect = new Rectangle(0, 0, 10, 20);
        assertEquals(0.0f, rect.x, 0.0f);
        assertEquals(10.0f, rect.width, 0.0f);

        rect.setLeft(5.0f);
        assertEquals(5.0f, rect.x, 0.0f);
        assertEquals(5.0f, rect.width, 0.0f);
    }


    @Test
    public void testSetLeftWithPositiveDeltaWidth() {
        Rectangle rect = new Rectangle(0, 5, 10, 20);
        assertEquals(5.0f, rect.x, 0.0f);
        assertEquals(10.0f, rect.width, 0.0f);

        rect.setLeft(10.0f);
        assertEquals(10.0f, rect.x, 0.0f);
        assertEquals(5.0f, rect.width, 0.0f);
    }
}
