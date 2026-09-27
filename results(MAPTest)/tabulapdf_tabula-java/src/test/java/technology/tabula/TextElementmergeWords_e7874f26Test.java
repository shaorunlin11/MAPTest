package technology.tabula;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class TextElementmergeWords_e7874f26Test {

    @Test
    public void testMergeWordsWithEmptyList() {
        List<TextElement> textElements = new ArrayList<>();
        List<TextChunk> result = TextElement.mergeWords(textElements);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testMergeWordsWithNonEmptyList() {
        // Create mock PDFont implementation using PDType1Font
        PDType1Font mockFont = PDType1Font.HELVETICA;

        TextElement element1 = new TextElement(0, 0, 100, 20, mockFont, 12, "Hello", 5f);
        TextElement element2 = new TextElement(0, 0, 100, 20, mockFont, 12, "World", 5f);

        List<TextElement> textElements = new ArrayList<>();
        textElements.add(element1);
        textElements.add(element2);

        List<TextChunk> result = TextElement.mergeWords(textElements);
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
    }
}
