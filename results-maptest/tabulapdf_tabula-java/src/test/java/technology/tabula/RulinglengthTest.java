package technology.tabula;

import org.junit.Test;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

import static org.junit.Assert.assertEquals;

public class RulinglengthTest {
    @Test
    public void testLengthForHorizontalLine() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(3, 0));
        assertEquals(3.0, ruling.length(), 0.0001);
    }

    @Test
    public void testLengthForVerticalLine() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(0, 4));
        assertEquals(4.0, ruling.length(), 0.0001);
    }

    @Test
    public void testLengthForDiagonalLine() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(3, 4));
        assertEquals(5.0, ruling.length(), 0.0001);
    }

    @Test
    public void testLengthForIdenticalPoints() {
        Ruling ruling = new Ruling(new Point2D.Float(2, 3), new Point2D.Float(2, 3));
        assertEquals(0.0, ruling.length(), 0.0001);
    }

    @Test
    public void testLengthWithNegativeCoordinates() {
        Ruling ruling = new Ruling(new Point2D.Float(-3, -4), new Point2D.Float(0, 0));
        assertEquals(5.0, ruling.length(), 0.0001);
    }
}
