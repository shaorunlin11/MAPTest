package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class RulingequalsTest {

    @Test
    public void testEqualsWithSameObject() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(1, 1));
        assertTrue(ruling.equals(ruling));
    }

    @Test
    public void testEqualsWithNull() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(1, 1));
        assertFalse(ruling.equals(null));
    }

    @Test
    public void testEqualsWithNonRulingObject() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(1, 1));
        assertFalse(ruling.equals("not a ruling"));
    }

    @Test
    public void testEqualsWithSamePoints() {
        Ruling ruling1 = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(1, 1));
        Ruling ruling2 = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(1, 1));
        assertTrue(ruling1.equals(ruling2));
    }

    @Test
    public void testEqualsWithDifferentPoints() {
        Ruling ruling1 = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(1, 1));
        Ruling ruling2 = new Ruling(new Point2D.Float(0, 1), new Point2D.Float(1, 0));
        assertFalse(ruling1.equals(ruling2));
    }
}
