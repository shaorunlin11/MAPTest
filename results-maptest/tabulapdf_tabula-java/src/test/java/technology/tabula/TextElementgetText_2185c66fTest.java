package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class TextElementgetText_2185c66fTest {

    @Test
    public void testGetText_returnsTextField() throws Exception {
        // Arrange
        org.apache.pdfbox.pdmodel.font.PDFont font = org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA;
        String text = "Hello, World!";
        float fontSize = 12.0f;
        float widthOfSpace = 10.0f;
        float dir = 0.0f;

        // Act
        TextElement element = new TextElement(0, 0, 100, 100, font, fontSize, text, widthOfSpace, dir);

        // Assert
        assertEquals(text, element.getText(false));
        assertEquals(text, element.getText(true));
    }
}
