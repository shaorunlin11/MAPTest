package technology.tabula;

import org.junit.Test;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

import static org.junit.Assert.assertEquals;

public class RulingsetLeftTest {

    @Test
    public void testSetLeft() {
        // Arrange
        Ruling ruling = new Ruling(new Point2D.Float(10, 20), new Point2D.Float(30, 40));

        // Act
        ruling.setLeft(50);

        // Assert
        assertEquals(50, ruling.getX1(), 0.0001);
        assertEquals(20, ruling.getY1(), 0.0001);
        assertEquals(30, ruling.getX2(), 0.0001);
        assertEquals(40, ruling.getY2(), 0.0001);
    }
}
