package technology.tabula;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UtilspageConvertToImage_8ce72823Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPageConvertToImage() throws IOException {
        // Arrange
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        int dpi = 300;
        ImageType imageType = ImageType.RGB;

        // Act
        BufferedImage result = Utils.pageConvertToImage(doc, page, dpi, imageType);

        // Assert
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getWidth() > 0);
        Assert.assertTrue(result.getHeight() > 0);
    }
}
