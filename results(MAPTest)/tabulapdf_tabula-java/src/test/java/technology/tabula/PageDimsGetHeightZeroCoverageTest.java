package technology.tabula;

import org.junit.Test;

public class PageDimsGetHeightZeroCoverageTest {
    @Test
    public void testGetHeight() {
        PageDims pageDims = PageDims.of(1.0f, 2.0f, 3.0f, 4.0f);
        float height = pageDims.getHeight();
    }
}
