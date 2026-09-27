package technology.tabula;

import java.awt.geom.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class RulingcolinearTest {
    @Test
    public void testColinearWithinBounds() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 10));
        Point2D point = new Point2D.Float(5, 5);
        assertTrue(ruling.colinear(point));
    }

    @Test
    public void testColinearOnX1() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 10));
        Point2D point = new Point2D.Float(0, 5);
        assertTrue(ruling.colinear(point));
    }

    @Test
    public void testColinearOnX2() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 10));
        Point2D point = new Point2D.Float(10, 5);
        assertTrue(ruling.colinear(point));
    }

    @Test
    public void testColinearOnY1() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 10));
        Point2D point = new Point2D.Float(5, 0);
        assertTrue(ruling.colinear(point));
    }

    @Test
    public void testColinearOnY2() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 10));
        Point2D point = new Point2D.Float(5, 10);
        assertTrue(ruling.colinear(point));
    }

    @Test
    public void testColinearOutsideX() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 10));
        Point2D point = new Point2D.Float(-1, 5);
        assertFalse(ruling.colinear(point));
    }

    @Test
    public void testColinearOutsideY() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 10));
        Point2D point = new Point2D.Float(5, -1);
        assertFalse(ruling.colinear(point));
    }

    @Test
    public void testColinearOutsideBoth() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 10));
        Point2D point = new Point2D.Float(-1, -1);
        assertFalse(ruling.colinear(point));
    }

    @Test
    public void testColinearWithZeroLengthLine() {
        Ruling ruling = new Ruling(new Point2D.Float(5, 5), new Point2D.Float(5, 5));
        Point2D point = new Point2D.Float(5, 5);
        assertTrue(ruling.colinear(point));
    }

    @Test
    public void testColinearWithZeroLengthLineOutside() {
        Ruling ruling = new Ruling(new Point2D.Float(5, 5), new Point2D.Float(5, 5));
        Point2D point = new Point2D.Float(6, 5);
        assertFalse(ruling.colinear(point));
    }
}
