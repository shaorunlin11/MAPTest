package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class RulingsetBottomTest {

    @Test
    public void testSetBottom() throws Exception {
        // Create a Ruling instance with initial coordinates
        Point2D p1 = new Point2D.Float(10.0f, 20.0f);
        Point2D p2 = new Point2D.Float(30.0f, 40.0f);
        Ruling ruling = new Ruling(p1, p2);

        // Call setBottom with a new value
        float newBottom = 50.0f;
        ruling.setBottom(newBottom);

        // Verify that the line was updated correctly
        Line2D.Float line = (Line2D.Float) ruling;
        assertEquals("Left coordinate should remain unchanged", p1.getX(), line.getX1(), 0.0f);
        assertEquals("Top coordinate should remain unchanged", p1.getY(), line.getY1(), 0.0f);
        assertEquals("Right coordinate should remain unchanged", p2.getX(), line.getX2(), 0.0f);
        assertEquals("Bottom coordinate should be updated", newBottom, line.getY2(), 0.0f);
    }
}
