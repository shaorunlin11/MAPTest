package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class RulinggetTopTest {

    @Test
    public void testGetTop_ReturnsY1Value() throws Exception {
        // Arrange
        Point2D p1 = new Point2D.Float(10.0f, 20.0f);
        Point2D p2 = new Point2D.Float(30.0f, 40.0f);
        Ruling ruling = new Ruling(p1, p2);

        // Act
        float top = ruling.getTop();

        // Assert
        assertEquals("getTop should return the y1 value of the Ruling", 20.0f, top, 0.0f);
    }

    @Test
    public void testGetTopWithConstructorUsingTopLeftWidthHeight() throws Exception {
        // Arrange
        Ruling ruling = new Ruling(5.0f, 10.0f, 20.0f, 30.0f);

        // Act
        float top = ruling.getTop();

        // Assert
        assertEquals("getTop should return the top value passed to the constructor", 5.0f, top, 0.0f);
    }
}
