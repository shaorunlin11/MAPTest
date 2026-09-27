package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import java.lang.reflect.Field;


public class RulingsetStartTest {
    private Ruling ruling;

    @Before
    public void setUp() {
        ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 10));
    }

    @After
    public void tearDown() {
        ruling = null;
    }

    @Test
    public void testSetStart_ThrowsUnsupportedOperationExceptionWhenOblique() {
        // Arrange
        Ruling mockRuling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 10)) {
            @Override
            public boolean oblique() {
                return true;
            }
        };

        // Act & Assert
        try {
            mockRuling.setStart(5.0f);
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Expected exception
        }
    }

    @Test
    public void testSetStart_CallsSetTopWhenVertical() throws Exception {
        // Arrange
        Ruling mockRuling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(0, 10)) {
            @Override
            public boolean vertical() {
                return true;
            }
        };

        // Act
        mockRuling.setStart(5.0f);

        // Assert
        Field topField = Line2D.Float.class.getDeclaredField("y1");
        topField.setAccessible(true);
        float topValue = (float) topField.get(mockRuling);
        Assert.assertEquals(5.0f, topValue, 0.001f);
    }

    @Test
    public void testSetStart_CallsSetLeftWhenNotVertical() throws Exception {
        // Arrange
        Ruling mockRuling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(10, 0)) {
            @Override
            public boolean vertical() {
                return false;
            }
        };

        // Act
        mockRuling.setStart(5.0f);

        // Assert
        Field leftField = Line2D.Float.class.getDeclaredField("x1");
        leftField.setAccessible(true);
        float leftValue = (float) leftField.get(mockRuling);
        Assert.assertEquals(5.0f, leftValue, 0.001f);
    }
}
