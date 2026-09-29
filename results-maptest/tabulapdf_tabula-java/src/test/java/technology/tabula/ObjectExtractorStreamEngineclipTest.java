package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class ObjectExtractorStreamEngineclipTest {
    private ObjectExtractorStreamEngine engine;
    private PDPage mockPage;

    @Before
    public void setUp() throws Exception {
        mockPage = new PDPage(new PDRectangle(612, 792));
        engine = new ObjectExtractorStreamEngine(mockPage);
    }

    @Test
    public void testClipSetsClipWindingRule() throws Exception {
        int expectedWindingRule = 1;
        engine.clip(expectedWindingRule);

        // Use reflection to access private field
        java.lang.reflect.Field clipWindingRuleField = ObjectExtractorStreamEngine.class.getDeclaredField("clipWindingRule");
        clipWindingRuleField.setAccessible(true);
        int actualWindingRule = clipWindingRuleField.getInt(engine);

        Assert.assertEquals(expectedWindingRule, actualWindingRule);
    }
}
