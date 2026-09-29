package technology.tabula;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.index.strtree.STRtree;
import java.util.ArrayList;
import java.util.List;
public class RectangleSpatialIndexcontainsTest {
    private RectangleSpatialIndex<Rectangle> index;
    private STRtree strTree;

    @Before
    public void setUp() throws Exception {
        index = new RectangleSpatialIndex<>();
        strTree = new STRtree();
        // Use reflection to set the private field 'si' in RectangleSpatialIndex
        java.lang.reflect.Field siField = RectangleSpatialIndex.class.getDeclaredField("si");
        siField.setAccessible(true);
        siField.set(index, strTree);

        // Use reflection to set the private field 'rectangles' in RectangleSpatialIndex
        java.lang.reflect.Field rectanglesField = RectangleSpatialIndex.class.getDeclaredField("rectangles");
        rectanglesField.setAccessible(true);
        rectanglesField.set(index, new ArrayList<>());
    }

    @After
    public void tearDown() throws Exception {
        index = null;
        strTree = null;
    }
}
