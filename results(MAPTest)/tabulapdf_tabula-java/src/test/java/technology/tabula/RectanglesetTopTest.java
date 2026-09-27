package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.awt.geom.Rectangle2D;

public class RectanglesetTopTest {
    private Rectangle rectangle;

    @Before
    public void setUp() {
        rectangle = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
    }

    @After
    public void tearDown() {
        rectangle = null;
    }

    @Test
    public void testSetTop() {
        // Original values
        Assert.assertEquals(10.0f, rectangle.getY(), 0.001f);
        Assert.assertEquals(20.0f, rectangle.getX(), 0.001f);
        Assert.assertEquals(30.0f, rectangle.getWidth(), 0.001f);
        Assert.assertEquals(40.0f, rectangle.getHeight(), 0.001f);

        // Call setTop
        rectangle.setTop(15.0f);

        // Expected values after setting top
        Assert.assertEquals(15.0f, rectangle.getY(), 0.001f);
        Assert.assertEquals(20.0f, rectangle.getX(), 0.001f);
        Assert.assertEquals(30.0f, rectangle.getWidth(), 0.001f);
        Assert.assertEquals(35.0f, rectangle.getHeight(), 0.001f); // 40.0f - (15.0f - 10.0f) = 35.0f
    }
}
