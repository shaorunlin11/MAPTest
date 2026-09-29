package technology.tabula;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TextChunkGroupByLinesZeroCoverageTest {
    @Test
    public void testGroupByLinesWithEmptyTextChunks() {
        List<TextChunk> textChunks = new ArrayList<>();

        List<Line> lines = TextChunk.groupByLines(textChunks);

        // The method should return an empty list when textChunks is empty
        assert lines.isEmpty();
    }
}
