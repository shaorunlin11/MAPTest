package technology.tabula;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LinesetTextElementsTest {
    @Test
    public void testSetTextElements() throws Exception {
        Line line = new Line();
        List<TextChunk> textChunks = new ArrayList<>();
        textChunks.add(new TextChunk(0, 0, 0, 0));

        line.setTextElements(textChunks);

        assertEquals(textChunks, line.textChunks);
    }
}
