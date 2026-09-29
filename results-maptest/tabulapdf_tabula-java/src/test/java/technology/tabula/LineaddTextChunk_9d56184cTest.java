package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class LineaddTextChunk_9d56184cTest {
    private Line line;

    @Before
    public void setUp() {
        line = new Line();
    }

    @After
    public void tearDown() {
        line = null;
    }

    @Test
    public void testAddTextChunkWithInvalidIndexThrowsIllegalArgumentException() {
        try {
            line.addTextChunk(-1, new TextChunk(0, 0, 0, 0));
            Assert.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testAddTextChunkInsertsAtOutOfBoundIndex() {
        TextChunk chunk = new TextChunk(0, 0, 0, 0);
        line.addTextChunk(5, chunk);

        List<TextChunk> textChunks = line.textChunks;
        Assert.assertEquals(6, textChunks.size());
        for (int i = 0; i < 5; i++) {
            Assert.assertNull(textChunks.get(i));
        }
        Assert.assertEquals(chunk, textChunks.get(5));
    }

    @Test
    public void testAddTextChunkMergesAtValidIndex() {
        TextChunk existingChunk = new TextChunk(0, 0, 0, 0);
        TextChunk newChunk = new TextChunk(0, 0, 0, 0);

        line.textChunks.add(existingChunk);
        line.addTextChunk(0, newChunk);

        Assert.assertTrue(line.textChunks.get(0).equals(existingChunk.merge(newChunk)));
    }
}
