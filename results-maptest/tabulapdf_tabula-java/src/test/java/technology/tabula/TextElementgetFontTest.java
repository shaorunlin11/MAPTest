package technology.tabula;

import org.junit.Test;
import org.junit.Assert;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class TextElementgetFontTest {
    @Test
    public void testGetFont() throws Exception {
        // Create a concrete PDFont instance
        PDFont mockFont = PDType1Font.HELVETICA;

        // Create a TextElement with the mock font
        TextElement textElement = new TextElement(0, 0, 0, 0, mockFont, 12, "test", 0);

        // Call the getFont method
        PDFont result = textElement.getFont();

        // Assert that the returned font is the same as the one provided
        Assert.assertEquals(mockFont, result);
    }
}
