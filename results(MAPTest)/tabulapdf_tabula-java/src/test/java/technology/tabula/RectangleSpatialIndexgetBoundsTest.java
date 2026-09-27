package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

public class RectangleSpatialIndexgetBoundsTest {
    private RectangleSpatialIndex<Rectangle> index;

    @Before
    public void setUp() {
        index = new RectangleSpatialIndex<>();
        // Add some test rectangles to the list
        List<Rectangle> testRectangles = new ArrayList<>();
        testRectangles.add(new Rectangle(0, 0, 10, 10));
        testRectangles.add(new Rectangle(5, 5, 15, 15));
        // Use reflection to set the private 'rectangles' field
        try {
            java.lang.reflect.Field field = RectangleSpatialIndex.class.getDeclaredField("rectangles");
            field.setAccessible(true);
            field.set(index, testRectangles);
        } catch (Exception e) {
            Assert.fail("Failed to set up test data: " + e.getMessage());
        }
    }

    @Test
    public void testGetBoundsReturnsCorrectBoundingBox() {
        Rectangle bounds = index.getBounds();
        Assert.assertNotNull("Bounds should not be null", bounds);
        // Verify that the bounding box encloses all test rectangles
        Assert.assertTrue("Bounding box should contain first rectangle", bounds.contains(new Rectangle(0, 0, 10, 10)));
        Assert.assertTrue("Bounding box should contain second rectangle", bounds.contains(new Rectangle(5, 5, 15, 15)));
    }
}
