package technology.tabula;

import org.junit.Test;

public class TextElementToStringZeroCoverageTest {
    @Test
    public void testToString() {
        // Create a TextElement instance with non-null text
        TextElement textElement = new TextElement(0, 0, 0, 0, null, 0, "test", 0);

        // Call the toString method to execute target lines
        textElement.toString();
    }
}
