package technology.tabula;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;

public class UtilsPageConvertToImageZeroCoverageTest {
    @Test
    public void testPageConvertToImage() throws IOException {
        PDPage page = new PDPage();
        int dpi = 300;
        ImageType imageType = ImageType.RGB;

        BufferedImage result = Utils.pageConvertToImage(page, dpi, imageType);
    }
}
