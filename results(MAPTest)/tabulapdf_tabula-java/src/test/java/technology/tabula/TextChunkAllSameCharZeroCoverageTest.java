package technology.tabula;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class TextChunkAllSameCharZeroCoverageTest {
    @Test
    public void testAllSameCharWithSingleTextChunk() {
        // Given: a list with a single TextChunk
        List<TextChunk> textChunks = new ArrayList<>();
        TextChunk tc = new TextChunk(0, 0, 0, 0);
        textChunks.add(tc);

        // When: calling allSameChar with the list
        boolean result = TextChunk.allSameChar(textChunks);

        // Then: the method returns false (as per the target line 307)
        assertFalse(result);
    }
}
