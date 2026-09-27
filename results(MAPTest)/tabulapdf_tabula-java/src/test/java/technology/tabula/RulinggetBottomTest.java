package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;

public class RulinggetBottomTest {
    @Test
    public void testGetBottom() {
        // Create a Ruling object with specific coordinates
        Point2D p1 = new Point2D.Float(10.0f, 20.0f);
        Point2D p2 = new Point2D.Float(30.0f, 40.0f);
        Ruling ruling = new Ruling(p1, p2);

        // Verify that getBottom returns the expected y2 value
        assertEquals(40.0f, ruling.getBottom(), 0.0f);
    }

    @Test
    public void testGetBottomWithTopLeftWidthHeightConstructor() {
        // Create a Ruling object using the constructor with top, left, width, height
        Ruling ruling = new Ruling(10.0f, 20.0f, 30.0f, 40.0f);

        // Verify that getBottom returns the expected y2 value
        assertEquals(50.0f, ruling.getBottom(), 0.0f);
    }
}
