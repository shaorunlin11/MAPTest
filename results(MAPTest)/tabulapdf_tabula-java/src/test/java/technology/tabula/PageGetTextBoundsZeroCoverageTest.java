package technology.tabula;

import org.junit.Test;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PageGetTextBoundsZeroCoverageTest {
    @Test
    public void testGetTextBoundsWhenTextsIsEmpty() {
        // Create a Page instance with empty textElements
        List<TextElement> textElements = new ArrayList<>();
        Page page = new Page(0, 0, 0, 0, 0, 0, null, null, textElements, new ArrayList<>());

        // Execute the method under test
        Rectangle2D bounds = page.getTextBounds();

        // Verify that the returned bounds is an empty rectangle
        assertEquals(0.0, bounds.getX(), 0.0);
        assertEquals(0.0, bounds.getY(), 0.0);
        assertEquals(0.0, bounds.getWidth(), 0.0);
        assertEquals(0.0, bounds.getHeight(), 0.0);
    }
}
