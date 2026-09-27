package technology.tabula;

import org.junit.Test;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RulinggetStartTest {

    @Test
    public void testGetStart_WhenOblique_ShouldThrowUnsupportedOperationException() throws Exception {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(1, 1));

        // Act & Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            ruling.getStart();
        });
    }

    @Test
    public void testGetStart_WhenVertical_ReturnsTop() throws Exception {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(10, 5), new Point2D.Float(10, 15));

        // Act
        float result = ruling.getStart();

        // Assert
        assertEquals(5.0f, result, 0.0001f);
    }

    @Test
    public void testGetStart_WhenNotVertical_ReturnsLeft() throws Exception {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(10, 5), new Point2D.Float(20, 5));

        // Act
        float result = ruling.getStart();

        // Assert
        assertEquals(10.0f, result, 0.0001f);
    }
}
