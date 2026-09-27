package technology.tabula;

import java.awt.geom.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class RulinggetLeftTest {

    @Test
    public void testGetLeft_ReturnsX1Value() throws Exception {
        // Arrange
        Point2D p1 = new Point2D.Float(10.5f, 20.5f);
        Point2D p2 = new Point2D.Float(30.5f, 40.5f);
        Ruling ruling = new Ruling(p1, p2);

        // Act
        float left = ruling.getLeft();

        // Assert
        assertEquals("Expected x1 value from p1", 10.5f, left, 0.0f);
    }

    @Test
    public void testGetLeft_WithConstructorUsingTopLeftWidthHeight_ReturnsCorrectLeft() throws Exception {
        // Arrange
        Ruling ruling = new Ruling(10.5f, 20.5f, 30.0f, 40.0f);

        // Act
        float left = ruling.getLeft();

        // Assert
        assertEquals("Expected left value from constructor", 20.5f, left, 0.0f);
    }
}
