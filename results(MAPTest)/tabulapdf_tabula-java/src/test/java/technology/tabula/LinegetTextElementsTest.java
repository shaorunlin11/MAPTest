package technology.tabula;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
public class LinegetTextElementsTest {
    private Line line;

    @Before
    public void setUp() {
        line = new Line();
    }

    @Test
    public void testGetTextElementsReturnsEmptyListWhenNoTextChunks() {
        List<TextChunk> result = line.getTextElements();
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should be empty", result.isEmpty());
    }

    @Test
    public void testGetTextElementsReturnsInitializedTextChunks() {
        TextChunk chunk1 = new TextChunk(0, 0, 0, 0);
        TextChunk chunk2 = new TextChunk(0, 0, 0, 0);

        line.textChunks.add(chunk1);
        line.textChunks.add(chunk2);

        List<TextChunk> result = line.getTextElements();
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertEquals("Should contain 2 text chunks", 2, result.size());
        Assert.assertTrue("Should contain chunk1", result.contains(chunk1));
        Assert.assertTrue("Should contain chunk2", result.contains(chunk2));
    }
}
