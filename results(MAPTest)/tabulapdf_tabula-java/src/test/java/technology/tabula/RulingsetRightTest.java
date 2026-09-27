package technology.tabula;

import org.junit.Test;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

import static org.junit.Assert.assertEquals;

public class RulingsetRightTest {
    @Test
    public void testSetRight() throws Exception {
        // Create a Ruling object with initial coordinates
        Point2D p1 = new Point2D.Float(0, 0);
        Point2D p2 = new Point2D.Float(10, 20);
        Ruling ruling = new Ruling(p1, p2);

        // Verify initial state
        assertEquals(0.0f, ruling.getX1(), 0.001f);
        assertEquals(0.0f, ruling.getY1(), 0.001f);
        assertEquals(10.0f, ruling.getX2(), 0.001f);
        assertEquals(20.0f, ruling.getY2(), 0.001f);

        // Call setRight with a new value
        ruling.setRight(15.0f);

        // Verify that the right coordinate has been updated
        assertEquals(0.0f, ruling.getX1(), 0.001f);
        assertEquals(0.0f, ruling.getY1(), 0.001f);
        assertEquals(15.0f, ruling.getX2(), 0.001f);
        assertEquals(20.0f, ruling.getY2(), 0.001f);
    }
}
