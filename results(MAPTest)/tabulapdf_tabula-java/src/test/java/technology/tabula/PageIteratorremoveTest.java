package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

public class PageIteratorremoveTest {
    @Test
    public void testRemoveThrowsUnsupportedOperationException() {
        // Create a mock PDDocument instance
        // Note: This is a placeholder and may need to be replaced with a real implementation
        // depending on the actual dependencies of ObjectExtractor
        ObjectExtractor objectExtractor = new ObjectExtractor(null); // Assuming a constructor that accepts a PDDocument
        Iterable<Integer> pages = new ArrayList<>();
        PageIterator pageIterator = new PageIterator(objectExtractor, pages);

        try {
            pageIterator.remove();
            fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            // Expected exception
        }
    }
}
