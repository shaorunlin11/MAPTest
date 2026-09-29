package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;

public class RulingperpendicularToTest {

    @Test
    public void testPerpendicularTo() {
        // Create two Rulings: one vertical and one horizontal
        Ruling verticalRuling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(0, 10));
        Ruling horizontalRuling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 0));

        // Check if they are perpendicular
        assertTrue(verticalRuling.perpendicularTo(horizontalRuling));
    }

    @Test
    public void testNotPerpendicular() {
        // Create two Rulings: both horizontal
        Ruling horizontalRuling1 = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 0));
        Ruling horizontalRuling2 = new Ruling(new Point2D.Float(0, 5), new Point2D.Float(10, 5));

        // Check if they are not perpendicular
        assertFalse(horizontalRuling1.perpendicularTo(horizontalRuling2));
    }

    @Test
    public void testNullInput() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(0, 10));
        try {
            ruling.perpendicularTo(null);
            fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
