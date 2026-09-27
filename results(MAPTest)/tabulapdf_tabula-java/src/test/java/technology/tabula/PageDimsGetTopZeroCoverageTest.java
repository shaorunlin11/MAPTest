package technology.tabula;

import org.junit.Test;

public class PageDimsGetTopZeroCoverageTest {
    @Test
    public void testGetTop() {
        PageDims pageDims = PageDims.of(10.0f, 20.0f, 30.0f, 40.0f);
        float top = pageDims.getTop();
    }
}
