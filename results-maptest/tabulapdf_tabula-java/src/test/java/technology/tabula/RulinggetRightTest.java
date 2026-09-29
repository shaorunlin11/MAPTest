package technology.tabula;

import org.junit.Test;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

import static org.junit.Assert.assertEquals;

public class RulinggetRightTest {

    @Test
    public void testGetRight() {
        Point2D p1 = new Point2D.Float(10.0f, 20.0f);
        Point2D p2 = new Point2D.Float(30.0f, 40.0f);
        Ruling ruling = new Ruling(p1, p2);
        assertEquals(30.0f, ruling.getRight(), 0.0f);
    }

    @Test
    public void testGetRightWithConstructorWithTopLeftWidthHeight() {
        Ruling ruling = new Ruling(10.0f, 20.0f, 30.0f, 40.0f);
        assertEquals(50.0f, ruling.getRight(), 0.0f);
    }
}
