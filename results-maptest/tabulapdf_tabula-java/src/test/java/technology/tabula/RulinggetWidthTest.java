package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class RulinggetWidthTest {

    @Test
    public void testGetWidth() {
        // Create a Ruling with known coordinates
        Point2D p1 = new Point2D.Float(10.0f, 20.0f);
        Point2D p2 = new Point2D.Float(30.0f, 40.0f);
        Ruling ruling = new Ruling(p1, p2);

        // Verify that getWidth returns the correct value
        assertEquals(20.0f, ruling.getWidth(), 0.001f);
    }

    @Test
    public void testGetWidthWithSameLeftAndRight() {
        // Create a Ruling where left and right are the same
        Point2D p1 = new Point2D.Float(5.0f, 10.0f);
        Point2D p2 = new Point2D.Float(5.0f, 15.0f);
        Ruling ruling = new Ruling(p1, p2);

        // Verify that getWidth returns 0.0f
        assertEquals(0.0f, ruling.getWidth(), 0.001f);
    }

    @Test
    public void testGetWidthWithNegativeWidth() {
        // Create a Ruling where right is less than left
        Point2D p1 = new Point2D.Float(30.0f, 20.0f);
        Point2D p2 = new Point2D.Float(10.0f, 40.0f);
        Ruling ruling = new Ruling(p1, p2);

        // Verify that getWidth returns a negative value
        assertEquals(-20.0f, ruling.getWidth(), 0.001f);
    }
}
