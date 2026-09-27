package technology.tabula;

import org.junit.Test;

public class TextChunkIsSameCharZeroCoverage_164Test {
    @Test
    public void testIsSameChar() {
        TextChunk textChunk = new TextChunk(0, 0, 0, 0);
        Character c = 'a';
        textChunk.isSameChar(c);
    }
}
