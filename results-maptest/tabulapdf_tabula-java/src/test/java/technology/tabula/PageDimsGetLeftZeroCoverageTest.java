package technology.tabula;

import org.junit.Test;

public class PageDimsGetLeftZeroCoverageTest {
    @Test
    public void testGetLeft() {
        float top = 10.0f;
        float left = 20.0f;
        float width = 30.0f;
        float height = 40.0f;

        PageDims pageDims = PageDims.of(top, left, width, height);

        float result = pageDims.getLeft();

        // This assertion is required to cover line 25 of the method
        assert result == left;
    }
}
