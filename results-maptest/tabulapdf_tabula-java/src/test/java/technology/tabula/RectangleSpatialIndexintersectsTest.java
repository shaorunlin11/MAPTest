package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.index.strtree.STRtree;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;


public class RectangleSpatialIndexintersectsTest {
    private RectangleSpatialIndex<Rectangle> index;
    private STRtree strTree;

    @Before
    public void setUp() throws Exception {
        index = new RectangleSpatialIndex<>();
        strTree = new STRtree();
        // Use reflection to set the private 'si' field
        Field siField = RectangleSpatialIndex.class.getDeclaredField("si");
        siField.setAccessible(true);
        siField.set(index, strTree);
    }

    @After
    public void tearDown() {
        index = null;
        strTree = null;
    }

    @Test
    public void testIntersectsWithValidRectangle() {
        // Arrange
        Rectangle r = new Rectangle();
        r.setRect(0, 0, 10, 10);
        Envelope envelope = new Envelope(r.getLeft(), r.getRight(), r.getTop(), r.getBottom());

        // Act
        List<Rectangle> result = index.intersects(r);

        // Assert
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }
}
