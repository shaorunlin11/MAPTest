package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class RulinggetEndTest {

    @Test
    public void testGetEnd_WhenOblique_ThrowsUnsupportedOperationException() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(1, 1));
        try {
            ruling.getEnd();
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // Expected exception
        }
    }

    @Test
    public void testGetEnd_WhenVertical_ReturnsBottom() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(0, 10));
        assertEquals(10.0f, ruling.getEnd(), 0.001f);
    }

    @Test
    public void testGetEnd_WhenNotVertical_ReturnsRight() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 0));
        assertEquals(10.0f, ruling.getEnd(), 0.001f);
    }
}
