package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class TextElementgetWidthOfSpaceTest {

    @Test
    public void testGetWidthOfSpace() throws Exception {
        // Arrange
        org.apache.pdfbox.pdmodel.font.PDFont font = org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA;
        float y = 0.0f;
        float x = 0.0f;
        float width = 100.0f;
        float height = 20.0f;
        float fontSize = 12.0f;
        String text = "Test";
        float widthOfSpace = 5.0f;

        // Act
        TextElement textElement = new TextElement(y, x, width, height, font, fontSize, text, widthOfSpace);

        // Assert
        assertEquals("getWidthOfSpace should return the initialized widthOfSpace value", widthOfSpace, textElement.getWidthOfSpace(), 0.0f);
    }
}
