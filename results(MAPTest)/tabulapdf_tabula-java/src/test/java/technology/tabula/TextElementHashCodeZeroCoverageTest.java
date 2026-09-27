package technology.tabula;

import org.junit.Test;

import org.apache.pdfbox.pdmodel.font.PDFont;


public class TextElementHashCodeZeroCoverageTest {
    @Test
    public void testHashCode() {
        // Create a TextElement instance with valid parameters
        PDFont font = null; // or a real PDFont instance if available
        String text = "test";
        float y = 0.0f;
        float x = 0.0f;
        float width = 10.0f;
        float height = 20.0f;
        float fontSize = 12.0f;
        float widthOfSpace = 5.0f;
        float dir = 1.0f;

        TextElement textElement = new TextElement(y, x, width, height, font, fontSize, text, widthOfSpace, dir);

        // Call the hashCode method to execute target lines
        int hashCode = textElement.hashCode();
    }
}
