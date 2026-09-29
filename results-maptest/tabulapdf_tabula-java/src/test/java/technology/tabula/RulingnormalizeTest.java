package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
public class RulingnormalizeTest {
    @Test
    public void testNormalizeHorizontalLine() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 0));
        ruling.normalize();
        assertEquals(0.0, ruling.getAngle(), 0.01);
        assertEquals(0.0, ruling.getY2(), 0.01);
    }

    @Test
    public void testNormalizeVerticalLine() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(0, 10));
        ruling.normalize();
        assertEquals(90.0, ruling.getAngle(), 0.01);
        assertEquals(0.0, ruling.getX2(), 0.01);
    }



    @Test
    public void testNormalizeNoChangeForNonCardinalAngles() {
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(1, 1));
        ruling.normalize();
        assertNotEquals(0.0, ruling.getAngle(), 0.01);
        assertNotEquals(0.0, ruling.getY2(), 0.01);
        assertNotEquals(0.0, ruling.getX2(), 0.01);
    }
}
