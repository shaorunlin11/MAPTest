package technology.tabula;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Test;
import java.io.IOException;

public class TextStripperprocessTest {
    @Test
    public void testProcess() throws IOException {
        PDDocument document = new PDDocument();
        TextStripper textStripper = new TextStripper(document, 1);
        textStripper.process();
    }
}
