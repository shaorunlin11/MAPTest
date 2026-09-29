package technology.tabula;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TextElementMergeWordsZeroCoverageTest {
    @Test
    public void testMergeWordsWithNonEmptyList() {
        // Create a list of TextElement objects
        List<TextElement> textElements = new ArrayList<>();

        // Add some TextElement instances to the list
        TextElement element1 = new TextElement(0, 0, 0, 0, null, 0, "Hello", 0);
        TextElement element2 = new TextElement(0, 0, 0, 0, null, 0, "World", 0);
        textElements.add(element1);
        textElements.add(element2);

        // Call the method under test
        List<TextChunk> result = TextElement.mergeWords(textElements);

        // Verify that the result is not null
        assertEquals("Result should not be null", 2, result.size());
    }
}
