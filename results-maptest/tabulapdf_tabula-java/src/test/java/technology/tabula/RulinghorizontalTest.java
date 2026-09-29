package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class RulinghorizontalTest {

    @Test
    public void testHorizontalWithZeroLength() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(0, 0));
        assertFalse(ruling.horizontal());
    }

    @Test
    public void testHorizontalWithEqualYCoordinates() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 1), new Point2D.Float(5, 1));
        assertTrue(ruling.horizontal());
    }

    @Test
    public void testHorizontalWithDifferentYCoordinates() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 1), new Point2D.Float(5, 2));
        assertFalse(ruling.horizontal());
    }

    @Test
    public void testHorizontalWithSmallYDifference() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 1.0000001f), new Point2D.Float(5, 1.0000002f));
        assertTrue(ruling.horizontal());
    }

    @Test
    public void testHorizontalWithLargeYDifference() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 1), new Point2D.Float(5, 100));
        assertFalse(ruling.horizontal());
    }
}
