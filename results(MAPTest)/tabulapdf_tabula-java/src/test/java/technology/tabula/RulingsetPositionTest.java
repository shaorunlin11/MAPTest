package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class RulingsetPositionTest {
    private Ruling ruling;

    @Before
    public void setUp() {
        ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(100, 100));
    }

    @After
    public void tearDown() {
        ruling = null;
    }

    @Test
    public void testSetPosition_ThrowsExceptionIfOblique() {
        // Given: a ruling that is oblique
        // When: setPosition is called
        // Then: an UnsupportedOperationException is thrown
        Assert.assertThrows("Expected UnsupportedOperationException when oblique", UnsupportedOperationException.class, () -> {
            ruling.setPosition(50.0f);
        });
    }

    @Test
    public void testSetPosition_SetsLeftAndRightIfVertical() {
        // Given: a vertical ruling
        ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(0, 100));

        // When: setPosition is called with a value
        ruling.setPosition(50.0f);

        // Then: left and right should be set to the same value
        Assert.assertEquals("Left should be 50.0", 50.0f, ruling.getX1(), 0.0f);
        Assert.assertEquals("Right should be 50.0", 50.0f, ruling.getX2(), 0.0f);
    }

    @Test
    public void testSetPosition_SetsTopAndBottomIfNotVertical() {
        // Given: a non-vertical ruling
        ruling = new Ruling(new Point2D.Float(0, 0), new Point2D.Float(100, 0));

        // When: setPosition is called with a value
        ruling.setPosition(50.0f);

        // Then: top and bottom should be set to the same value
        Assert.assertEquals("Top should be 50.0", 50.0f, ruling.getY1(), 0.0f);
        Assert.assertEquals("Bottom should be 50.0", 50.0f, ruling.getY2(), 0.0f);
    }
}
