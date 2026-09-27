package technology.tabula;

import java.awt.geom.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class RectanglegetPointsTest {

    @Test
    public void testGetPoints_returnsFourPoints() {
        Rectangle rectangle = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        Point2D[] points = rectangle.getPoints();
        assertEquals(4, points.length);
    }

    @Test
    public void testGetPoints_returnsCorrectTopLeftPoint() {
        Rectangle rectangle = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        Point2D[] points = rectangle.getPoints();
        assertEquals(20.0f, points[0].getX(), 0.0f);
        assertEquals(10.0f, points[0].getY(), 0.0f);
    }

    @Test
    public void testGetPoints_returnsCorrectTopRightPoint() {
        Rectangle rectangle = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        Point2D[] points = rectangle.getPoints();
        assertEquals(50.0f, points[1].getX(), 0.0f);
        assertEquals(10.0f, points[1].getY(), 0.0f);
    }

    @Test
    public void testGetPoints_returnsCorrectBottomRightPoint() {
        Rectangle rectangle = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        Point2D[] points = rectangle.getPoints();
        assertEquals(50.0f, points[2].getX(), 0.0f);
        assertEquals(50.0f, points[2].getY(), 0.0f);
    }

    @Test
    public void testGetPoints_returnsCorrectBottomLeftPoint() {
        Rectangle rectangle = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        Point2D[] points = rectangle.getPoints();
        assertEquals(20.0f, points[3].getX(), 0.0f);
        assertEquals(50.0f, points[3].getY(), 0.0f);
    }
}
