package technology.tabula;

import org.junit.Test;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

public class ObjectExtractorCloseZeroCoverageTest {
    @Test
    public void testClose() throws IOException {
        PDDocument pdfDocument = new PDDocument();
        ObjectExtractor extractor = new ObjectExtractor(pdfDocument);
        extractor.close();
        pdfDocument.close();
    }
}
