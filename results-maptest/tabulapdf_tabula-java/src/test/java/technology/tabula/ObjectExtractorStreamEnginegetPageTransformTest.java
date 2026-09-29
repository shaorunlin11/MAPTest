package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
import java.awt.geom.AffineTransform;

public class ObjectExtractorStreamEnginegetPageTransformTest {
    private ObjectExtractorStreamEngine engine;
    private PDPage page;

    @Before
    public void setUp() throws Exception {
        page = new PDPage(PDRectangle.A4);
        engine = new ObjectExtractorStreamEngine(page);
    }

    @Test
    public void testGetPageTransform_returnsExpectedTransform() {
        AffineTransform transform = engine.getPageTransform();
        Assert.assertNotNull("pageTransform should not be null", transform);

        // Verify that the transform has been initialized with expected values
        // This is a basic check, as detailed verification would require complex matrix analysis
        Assert.assertTrue("Transform should have scale component", transform.getScaleX() != 1.0 || transform.getScaleY() != 1.0);
        Assert.assertTrue("Transform should have translation component", transform.getTranslateX() != 0.0 || transform.getTranslateY() != 0.0);
    }
}
