package technology.tabula;

import org.junit.Test;

public class PageDimsGetWidthZeroCoverageTest {
    @Test
    public void testGetWidth() {
        // Create a PageDims instance with valid float values
        PageDims pageDims = PageDims.of(1.0f, 2.0f, 3.0f, 4.0f);

        // Call the method under test
        float width = pageDims.getWidth();

        // Assert that the returned value is as expected
        assert width == 3.0f;
    }
}
