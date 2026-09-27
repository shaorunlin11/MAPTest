package technology.tabula;

import org.junit.Test;
import java.awt.geom.Point2D;
import static org.junit.Assert.assertEquals;

public class RulingtoStringTest {
    @Test
    public void testToString() {
        Point2D p1 = new Point2D.Float(1.0f, 2.0f);
        Point2D p2 = new Point2D.Float(3.0f, 4.0f);
        Ruling ruling = new Ruling(p1, p2);
        String result = ruling.toString();
        String expected = "class technology.tabula.Ruling[x1=1.000000 y1=2.000000 x2=3.000000 y2=4.000000]";
        assertEquals(expected, result);
    }
}
