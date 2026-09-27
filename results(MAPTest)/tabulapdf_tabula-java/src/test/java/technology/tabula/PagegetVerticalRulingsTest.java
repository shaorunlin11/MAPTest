package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.Ruling;
import technology.tabula.Page;
import technology.tabula.TextElement;
import technology.tabula.RectangleSpatialIndex;

import java.lang.reflect.Field;


public class PagegetVerticalRulingsTest {
    private Page page;
    private List<Ruling> verticalRulingLines;

    @Before
    public void setUp() throws Exception {
        // Create mock objects
        PDPage pdPage = new PDPage();
        PDDocument pdDoc = new PDDocument();
        List<TextElement> textElements = new ArrayList<>();
        List<Ruling> rulings = new ArrayList<>();
        RectangleSpatialIndex<TextElement> spatialIndex = new RectangleSpatialIndex<>();

        // Initialize Page with constructor that sets up the necessary fields
        page = new Page(0, 0, 100, 100, 0, 1, pdPage, pdDoc, textElements, rulings);

        // Set up verticalRulingLines for testing
        verticalRulingLines = new ArrayList<>();
        Field field = Page.class.getDeclaredField("verticalRulingLines");
        field.setAccessible(true);
        field.set(page, verticalRulingLines);
    }

    @After
    public void tearDown() throws Exception {
        page = null;
        verticalRulingLines = null;
    }

    @Test
    public void testGetVerticalRulings_returnsPrecomputedValue() throws Exception {
        // Arrange: Ensure verticalRulingLines is already initialized
        List<Ruling> result = page.getVerticalRulings();

        // Assert: Should return the precomputed value
        Assert.assertEquals(verticalRulingLines, result);
    }

    @Test
    public void testGetVerticalRulings_callsGetRulingsWhenNotInitialized() throws Exception {
        // Arrange: Clear verticalRulingLines to force getRulings() call
        Field field = Page.class.getDeclaredField("verticalRulingLines");
        field.setAccessible(true);
        field.set(page, null);

        // Act: Call getVerticalRulings()
        List<Ruling> result = page.getVerticalRulings();

        // Assert: Should have called getRulings() and returned the result
        // Note: We cannot verify the actual call to getRulings() without mocking
        // But we can verify that the result is not null if getRulings() initializes it
        Assert.assertNotNull(result);
    }
}
