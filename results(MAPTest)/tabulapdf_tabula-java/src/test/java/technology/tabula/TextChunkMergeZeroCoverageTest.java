package technology.tabula;

import org.junit.Test;

public class TextChunkMergeZeroCoverageTest {
    @Test
    public void testMerge() {
        TextChunk chunk = new TextChunk(0, 0, 100, 100);
        TextChunk other = new TextChunk(0, 0, 100, 100);
        chunk.merge(other);
    }
}
