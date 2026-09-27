package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;


public class PagegetPDPageTest {

    @Test
    public void testGetPDPage() throws Exception {
        // Create a PDPage instance
        PDDocument pdDoc = new PDDocument();
        PDPage pdPage = new PDPage();
        pdDoc.addPage(pdPage);

        // Create a Page instance using the constructor that initializes pdPage
        Page page = new Page(0, 0, 100, 100, 0, 1, pdPage, pdDoc);

        // Call the method under test
        PDPage result = page.getPDPage();

        // Verify the result
        assertEquals(pdPage, result);

        // Clean up
        pdDoc.close();
    }
}
