package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;

public class RulingobliqueTest {

    @Test
    public void testObliqueReturnsTrueWhenNotVerticalOrHorizontal() {
        // Create a diagonal line (neither vertical nor horizontal)
        Point2D p1 = new Point2D.Float(0, 0);
        Point2D p2 = new Point2D.Float(10, 10);
        Ruling ruling = new Ruling(p1, p2);

        assertTrue("Ruling should be oblique", ruling.oblique());
    }

    @Test
    public void testObliqueReturnsFalseWhenVertical() {
        // Create a vertical line
        Point2D p1 = new Point2D.Float(0, 0);
        Point2D p2 = new Point2D.Float(0, 10);
        Ruling ruling = new Ruling(p1, p2);

        assertFalse("Ruling should not be oblique", ruling.oblique());
    }

    @Test
    public void testObliqueReturnsFalseWhenHorizontal() {
        // Create a horizontal line
        Point2D p1 = new Point2D.Float(0, 0);
        Point2D p2 = new Point2D.Float(10, 0);
        Ruling ruling = new Ruling(p1, p2);

        assertFalse("Ruling should not be oblique", ruling.oblique());
    }
}
