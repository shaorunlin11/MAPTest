package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class RulinggetPositionTest {

    @Test
    public void testGetPosition_WhenObliqueThrowsUnsupportedOperationException() throws Exception {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(1, 1));

        // Act & Assert
        try {
            ruling.getPosition();
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // Expected exception
        }
    }

    @Test
    public void testGetPosition_WhenVerticalReturnsLeft() throws Exception {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(0, 10));

        // Act
        float position = ruling.getPosition();

        // Assert
        assertEquals(0.0f, position, 0.0f);
    }

    @Test
    public void testGetPosition_WhenNotVerticalReturnsTop() throws Exception {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 0));

        // Act
        float position = ruling.getPosition();

        // Assert
        assertEquals(0.0f, position, 0.0f);
    }
}
