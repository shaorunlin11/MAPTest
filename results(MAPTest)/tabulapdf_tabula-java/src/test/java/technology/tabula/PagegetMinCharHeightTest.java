package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;


public class PagegetMinCharHeightTest {

    @Test
    public void testGetMinCharHeight() throws Exception {
        // Create a Page instance with known minCharHeight value
        Page page = new Page(0, 0, 100, 100, 0, 1, null, null, new ArrayList<>(), new ArrayList<>(), 1.0f, 2.0f, null);

        // Verify that the method returns the expected value
        assertEquals(2.0f, page.getMinCharHeight(), 0.001f);
    }
}
