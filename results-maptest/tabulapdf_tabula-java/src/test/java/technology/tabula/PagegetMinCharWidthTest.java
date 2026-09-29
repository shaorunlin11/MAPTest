package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;


public class PagegetMinCharWidthTest {

    @Test
    public void testGetMinCharWidth() throws Exception {
        // Create a Page instance with a known minCharWidth value
        Page page = new Page(0, 0, 100, 100, 0, 1, null, null, new ArrayList<>(), new ArrayList<>(), 5.5f, 7.0f, null);

        // Verify that the method returns the expected value
        assertEquals(5.5f, page.getMinCharWidth(), 0.0001f);
    }
}
