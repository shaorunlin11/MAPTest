package technology.tabula;

import org.junit.Test;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

import static org.junit.Assert.assertEquals;

public class RulinggetAngleTest {

    @Test
    public void testGetAngleHorizontalRight() {
        Point2D p1 = new Point2D.Float(0, 0);
        Point2D p2 = new Point2D.Float(1, 0);
        Ruling ruling = new Ruling(p1, p2);
        double angle = ruling.getAngle();
        assertEquals(0.0, angle, 0.0001);
    }

    @Test
    public void testGetAngleHorizontalLeft() {
        Point2D p1 = new Point2D.Float(1, 0);
        Point2D p2 = new Point2D.Float(0, 0);
        Ruling ruling = new Ruling(p1, p2);
        double angle = ruling.getAngle();
        assertEquals(180.0, angle, 0.0001);
    }

    @Test
    public void testGetAngleVerticalUp() {
        Point2D p1 = new Point2D.Float(0, 0);
        Point2D p2 = new Point2D.Float(0, 1);
        Ruling ruling = new Ruling(p1, p2);
        double angle = ruling.getAngle();
        assertEquals(90.0, angle, 0.0001);
    }

    @Test
    public void testGetAngleVerticalDown() {
        Point2D p1 = new Point2D.Float(0, 1);
        Point2D p2 = new Point2D.Float(0, 0);
        Ruling ruling = new Ruling(p1, p2);
        double angle = ruling.getAngle();
        assertEquals(270.0, angle, 0.0001);
    }

    @Test
    public void testGetAngleDiagonalUpRight() {
        Point2D p1 = new Point2D.Float(0, 0);
        Point2D p2 = new Point2D.Float(1, 1);
        Ruling ruling = new Ruling(p1, p2);
        double angle = ruling.getAngle();
        assertEquals(45.0, angle, 0.0001);
    }

    @Test
    public void testGetAngleDiagonalDownRight() {
        Point2D p1 = new Point2D.Float(0, 1);
        Point2D p2 = new Point2D.Float(1, 0);
        Ruling ruling = new Ruling(p1, p2);
        double angle = ruling.getAngle();
        assertEquals(315.0, angle, 0.0001);
    }

    @Test
    public void testGetAngleNegativeAngle() {
        Point2D p1 = new Point2D.Float(1, 0);
        Point2D p2 = new Point2D.Float(0, 1);
        Ruling ruling = new Ruling(p1, p2);
        double angle = ruling.getAngle();
        assertEquals(135.0, angle, 0.0001);
    }

    @Test
    public void testGetAngleZeroLengthLine() {
        Point2D p1 = new Point2D.Float(0, 0);
        Point2D p2 = new Point2D.Float(0, 0);
        Ruling ruling = new Ruling(p1, p2);
        double angle = ruling.getAngle();
        assertEquals(0.0, angle, 0.0001);
    }
}
