package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

public class RulingintersectTest {
    @Test
    public void testIntersect_ClippingSuccessful_ReturnsClippedRuling() {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(100, 100));
        Rectangle2D clip = new Rectangle2D.Float(20, 20, 60, 60);

        // Act
        Ruling result = ruling.intersect(clip);

        // Assert
        assertNotNull(result);
        assertNotSame(ruling, result);
        assertEquals(20.0f, result.getX1(), 0.001);
        assertEquals(20.0f, result.getY1(), 0.001);
        assertEquals(80.0f, result.getX2(), 0.001);
        assertEquals(80.0f, result.getY2(), 0.001);
    }

    @Test
    public void testIntersect_ClippingFailed_ReturnsOriginalRuling() {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(100, 100));
        Rectangle2D clip = new Rectangle2D.Float(200, 200, 60, 60);

        // Act
        Ruling result = ruling.intersect(clip);

        // Assert
        assertNotNull(result);
        assertSame(ruling, result);
    }

    @Test
    public void testIntersect_ClippingWithExactIntersection_ReturnsClippedRuling() {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(50, 50), new Point2D.Float(150, 150));
        Rectangle2D clip = new Rectangle2D.Float(100, 100, 50, 50);

        // Act
        Ruling result = ruling.intersect(clip);

        // Assert
        assertNotNull(result);
        assertNotSame(ruling, result);
        assertEquals(100.0f, result.getX1(), 0.001);
        assertEquals(100.0f, result.getY1(), 0.001);
        assertEquals(150.0f, result.getX2(), 0.001);
        assertEquals(150.0f, result.getY2(), 0.001);
    }

    @Test
    public void testIntersect_ClippingWithNoIntersection_ReturnsOriginalRuling() {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(100, 100));
        Rectangle2D clip = new Rectangle2D.Float(200, 200, 60, 60);

        // Act
        Ruling result = ruling.intersect(clip);

        // Assert
        assertNotNull(result);
        assertSame(ruling, result);
    }
}
