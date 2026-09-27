package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class RulinggetHeightTest {

    @Test
    public void testGetHeight() {
        // Create a Ruling instance with known coordinates
        Point2D p1 = new Point2D.Float(0.0f, 10.0f);
        Point2D p2 = new Point2D.Float(5.0f, 20.0f);
        Ruling ruling = new Ruling(p1, p2);

        // Expected height is bottom (20.0f) - top (10.0f) = 10.0f
        float expectedHeight = 10.0f;
        float actualHeight = ruling.getHeight();

        assertEquals("getHeight should return the correct height", expectedHeight, actualHeight, 0.0f);
    }

    @Test
    public void testGetHeightWithZeroHeight() {
        // Create a Ruling instance where top and bottom are the same
        Point2D p1 = new Point2D.Float(0.0f, 5.0f);
        Point2D p2 = new Point2D.Float(5.0f, 5.0f);
        Ruling ruling = new Ruling(p1, p2);

        // Expected height is 0.0f
        float expectedHeight = 0.0f;
        float actualHeight = ruling.getHeight();

        assertEquals("getHeight should return 0.0f when top equals bottom", expectedHeight, actualHeight, 0.0f);
    }
}
