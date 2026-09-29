package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;

public class TextStrippergetMinCharWidthTest {
    private TextStripper textStripper;
    private PDDocument document;

    @Before
    public void setUp() throws IOException {
        document = new PDDocument();
        textStripper = new TextStripper(document, 1);
    }

    @After
    public void tearDown() throws IOException {
        if (document != null) {
            document.close();
        }
    }

    @Test
    public void testGetMinCharWidth_InitialValue() {
        float result = textStripper.getMinCharWidth();
        Assert.assertEquals(Float.MAX_VALUE, result, 0.0f);
    }
}
