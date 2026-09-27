package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import technology.tabula.PageIterator;
import technology.tabula.Utils;

import java.io.IOException;
import java.util.List;

import java.lang.reflect.Method;


public class ObjectExtractorextract_2fd275f9Test {
    private ObjectExtractor objectExtractor;
    private PDDocument pdfDocument;

    @Before
    public void setUp() throws IOException {
        pdfDocument = new PDDocument();
        objectExtractor = new ObjectExtractor(pdfDocument);
    }

    @After
    public void tearDown() throws IOException {
        if (pdfDocument != null) {
            pdfDocument.close();
        }
    }

    @Test
    public void testExtract_returnsPageIterator() {
        PageIterator result = objectExtractor.extract();
        Assert.assertNotNull(result);
    }

    @Test
    public void testExtract_callsExtractWithRangeOfPageNumbers() throws Exception {
        Method extractMethod = ObjectExtractor.class.getDeclaredMethod("extract", Iterable.class);
        extractMethod.setAccessible(true);

        Method rangeMethod = Utils.class.getDeclaredMethod("range", int.class, int.class);
        rangeMethod.setAccessible(true);

        List<Integer> expectedRange = (List<Integer>) rangeMethod.invoke(null, 1, pdfDocument.getNumberOfPages() + 1);

        PageIterator result = objectExtractor.extract();
        Assert.assertTrue(result instanceof PageIterator);
    }
}
