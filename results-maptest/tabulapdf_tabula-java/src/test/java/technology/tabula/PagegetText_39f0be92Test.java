package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;


public class PagegetText_39f0be92Test {

    @Test
    public void testGetTextWithValidArea() throws Exception {
        // Create a mock Page instance with a spatialIndex
        Page page = new Page(0, 0, 100, 100, 0, 1, null, null);

        // Create a mock RectangleSpatialIndex
        RectangleSpatialIndex<TextElement> spatialIndex = new RectangleSpatialIndex<TextElement>() {
            @Override
            public List<TextElement> contains(Rectangle r) {
                return new ArrayList<>();
            }
        };

        // Use reflection to set the spatialIndex field
        Field spatialIndexField = Page.class.getDeclaredField("spatialIndex");
        spatialIndexField.setAccessible(true);
        spatialIndexField.set(page, spatialIndex);

        // Create a test area
        Rectangle area = new Rectangle(10, 10, 50, 50);

        // Call the method
        List<TextElement> result = page.getText(area);

        // Verify the result
        assertNotNull(result);
        assertTrue(result instanceof ArrayList);
    }

    @Test
    public void testGetTextWithNullArea() throws Exception {
        // Create a mock Page instance
        Page page = new Page(0, 0, 100, 100, 0, 1, null, null);

        // Use reflection to set the spatialIndex field
        Field spatialIndexField = Page.class.getDeclaredField("spatialIndex");
        spatialIndexField.setAccessible(true);
        spatialIndexField.set(page, new RectangleSpatialIndex<TextElement>() {
            @Override
            public List<TextElement> contains(Rectangle r) {
                return new ArrayList<>();
            }
        });

        // Call the method with null area
        List<TextElement> result = page.getText(null);

        // Verify the result
        assertNotNull(result);
        assertTrue(result instanceof ArrayList);
    }
}
