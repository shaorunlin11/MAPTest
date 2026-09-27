package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class PageDimsofTest {

    @Test
    public void testOfMethod() {
        float top = 10.5f;
        float left = 20.5f;
        float width = 30.5f;
        float height = 40.5f;

        PageDims result = PageDims.of(top, left, width, height);

        assertEquals("Top value should match", top, result.getTop(), 0.001f);
        assertEquals("Left value should match", left, result.getLeft(), 0.001f);
        assertEquals("Width value should match", width, result.getWidth(), 0.001f);
        assertEquals("Height value should match", height, result.getHeight(), 0.001f);
    }
}
