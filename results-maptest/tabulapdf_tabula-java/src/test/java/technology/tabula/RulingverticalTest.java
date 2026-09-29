package technology.tabula;

import java.awt.geom.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class RulingverticalTest {

    @Test
    public void testVerticalWithNonZeroLengthAndEqualXCoordinates() {
        Ruling ruling = new Ruling(new Point2D.Float(10, 20), new Point2D.Float(10, 30));
        assertTrue(ruling.vertical());
    }

    @Test
    public void testVerticalWithZeroLength() {
        Ruling ruling = new Ruling(new Point2D.Float(10, 20), new Point2D.Float(10, 20));
        assertFalse(ruling.vertical());
    }

    @Test
    public void testVerticalWithNonZeroLengthAndDifferentXCoordinates() {
        Ruling ruling = new Ruling(new Point2D.Float(10, 20), new Point2D.Float(15, 30));
        assertFalse(ruling.vertical());
    }

    @Test
    public void testVerticalWithNonZeroLengthAndXCoordinatesWithinFeqTolerance() {
        Ruling ruling = new Ruling(new Point2D.Float(10.0001f, 20), new Point2D.Float(10.0002f, 30));
        assertTrue(ruling.vertical());
    }
}
