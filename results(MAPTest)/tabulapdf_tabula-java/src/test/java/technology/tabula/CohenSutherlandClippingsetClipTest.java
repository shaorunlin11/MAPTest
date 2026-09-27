package technology.tabula;

import java.awt.geom.Rectangle2D;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.lang.reflect.Field;


public class CohenSutherlandClippingsetClipTest {
    @Test
    public void testSetClip() throws Exception {
        CohenSutherlandClipping clipping = new CohenSutherlandClipping();
        Rectangle2D clipWindow = new Rectangle2D.Double(10.0, 20.0, 30.0, 40.0);

        // Invoke the method under test
        Method method = CohenSutherlandClipping.class.getDeclaredMethod("setClip", Rectangle2D.class);
        method.setAccessible(true);
        method.invoke(clipping, clipWindow);

        // Verify the internal state
        Field xMinField = CohenSutherlandClipping.class.getDeclaredField("xMin");
        xMinField.setAccessible(true);
        assertEquals(10.0, xMinField.getDouble(clipping), 0.001);

        Field xMaxField = CohenSutherlandClipping.class.getDeclaredField("xMax");
        xMaxField.setAccessible(true);
        assertEquals(40.0, xMaxField.getDouble(clipping), 0.001);

        Field yMinField = CohenSutherlandClipping.class.getDeclaredField("yMin");
        yMinField.setAccessible(true);
        assertEquals(20.0, yMinField.getDouble(clipping), 0.001);

        Field yMaxField = CohenSutherlandClipping.class.getDeclaredField("yMax");
        yMaxField.setAccessible(true);
        assertEquals(60.0, yMaxField.getDouble(clipping), 0.001);
    }
}
