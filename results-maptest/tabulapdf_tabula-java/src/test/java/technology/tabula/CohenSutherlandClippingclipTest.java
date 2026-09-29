package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class CohenSutherlandClippingclipTest {
    @Test
    public void testClipLineCompletelyInsideWindow() throws Exception {
        CohenSutherlandClipping clipper = new CohenSutherlandClipping();
        Rectangle2D clipWindow = new Rectangle2D.Double(0, 0, 100, 100);
        clipper.setClip(clipWindow);

        Line2D.Float line = new Line2D.Float(20, 20, 80, 80);
        boolean result = clipper.clip(line);

        assertTrue(result);
        assertEquals(20, line.getX1(), 0.01);
        assertEquals(20, line.getY1(), 0.01);
        assertEquals(80, line.getX2(), 0.01);
        assertEquals(80, line.getY2(), 0.01);
    }

    @Test
    public void testClipLineCompletelyOutsideWindow() throws Exception {
        CohenSutherlandClipping clipper = new CohenSutherlandClipping();
        Rectangle2D clipWindow = new Rectangle2D.Double(0, 0, 100, 100);
        clipper.setClip(clipWindow);

        Line2D.Float line = new Line2D.Float(-20, -20, -10, -10);
        boolean result = clipper.clip(line);

        assertFalse(result);
        assertEquals(-20, line.getX1(), 0.01);
        assertEquals(-20, line.getY1(), 0.01);
        assertEquals(-10, line.getX2(), 0.01);
        assertEquals(-10, line.getY2(), 0.01);
    }

    @Test
    public void testClipLinePartiallyInsideWindow() throws Exception {
        CohenSutherlandClipping clipper = new CohenSutherlandClipping();
        Rectangle2D clipWindow = new Rectangle2D.Double(0, 0, 100, 100);
        clipper.setClip(clipWindow);

        Line2D.Float line = new Line2D.Float(-20, 50, 120, 50);
        boolean result = clipper.clip(line);

        assertTrue(result);
        assertEquals(0, line.getX1(), 0.01);
        assertEquals(50, line.getY1(), 0.01);
        assertEquals(100, line.getX2(), 0.01);
        assertEquals(50, line.getY2(), 0.01);
    }

    @Test
    public void testClipVerticalLine() throws Exception {
        CohenSutherlandClipping clipper = new CohenSutherlandClipping();
        Rectangle2D clipWindow = new Rectangle2D.Double(0, 0, 100, 100);
        clipper.setClip(clipWindow);

        Line2D.Float line = new Line2D.Float(50, -20, 50, 120);
        boolean result = clipper.clip(line);

        assertTrue(result);
        assertEquals(50, line.getX1(), 0.01);
        assertEquals(0, line.getY1(), 0.01);
        assertEquals(50, line.getX2(), 0.01);
        assertEquals(100, line.getY2(), 0.01);
    }

    @Test
    public void testClipLineWithZeroSlope() throws Exception {
        CohenSutherlandClipping clipper = new CohenSutherlandClipping();
        Rectangle2D clipWindow = new Rectangle2D.Double(0, 0, 100, 100);
        clipper.setClip(clipWindow);

        Line2D.Float line = new Line2D.Float(-20, 50, 120, 50);
        boolean result = clipper.clip(line);

        assertTrue(result);
        assertEquals(0, line.getX1(), 0.01);
        assertEquals(50, line.getY1(), 0.01);
        assertEquals(100, line.getX2(), 0.01);
        assertEquals(50, line.getY2(), 0.01);
    }
}
