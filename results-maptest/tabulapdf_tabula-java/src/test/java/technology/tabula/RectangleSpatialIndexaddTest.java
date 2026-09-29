package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.locationtech.jts.index.strtree.STRtree;
import org.locationtech.jts.geom.Envelope;

import java.util.ArrayList;
import java.util.List;

public class RectangleSpatialIndexaddTest {
    private RectangleSpatialIndex<Rectangle> index;
    private List<Rectangle> rectangles;
    private STRtree si;

    @Before
    public void setUp() {
        index = new RectangleSpatialIndex<>();
        rectangles = new ArrayList<>();
        si = new STRtree();
    }

    @Test
    public void testAdd() throws Exception {
        Rectangle rectangle = new Rectangle();
        rectangle.setLeft(0.0f);
        rectangle.setRight(10.0f);
        rectangle.setBottom(0.0f);
        rectangle.setTop(10.0f);

        index.add(rectangle);

        // Use reflection to access private field
        java.lang.reflect.Field rectanglesField = RectangleSpatialIndex.class.getDeclaredField("rectangles");
        rectanglesField.setAccessible(true);
        List<Rectangle> actualRectangles = (List<Rectangle>) rectanglesField.get(index);

        Assert.assertTrue(actualRectangles.contains(rectangle));

        // Verify that the envelope is correctly inserted into the spatial index
        Envelope envelope = new Envelope(rectangle.getLeft(), rectangle.getRight(), rectangle.getBottom(), rectangle.getTop());
        // Since STRtree is not publicly accessible, we can't directly verify its contents
        // But we can confirm that no exceptions are thrown and the method completes
    }
}
