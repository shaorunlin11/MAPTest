package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;

public class TextStrippergetSpatialIndexTest {
    private TextStripper textStripper;
    private PDDocument document;

    @Before
    public void setUp() throws IOException {
        document = new PDDocument();
        textStripper = new TextStripper(document, 1);
    }

    @Test
    public void testGetSpatialIndexReturnsNonNullInstance() {
        RectangleSpatialIndex<TextElement> spatialIndex = textStripper.getSpatialIndex();
        Assert.assertNotNull("spatialIndex should not be null", spatialIndex);
    }

    @Test
    public void testGetSpatialIndexReturnsCorrectType() {
        RectangleSpatialIndex<TextElement> spatialIndex = textStripper.getSpatialIndex();
        Assert.assertTrue("spatialIndex should be an instance of RectangleSpatialIndex", 
            spatialIndex instanceof RectangleSpatialIndex);
    }
}
