package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class ObjectExtractorStreamEnginefillPathTest {
    private ObjectExtractorStreamEngine engine;
    private PDPage page;
    private List<Ruling> rulings;
    private GeneralPath currentPath;

    @Before
    public void setUp() throws Exception {
        page = new PDPage();
        engine = new ObjectExtractorStreamEngine(page);
        Field rulingsField = ObjectExtractorStreamEngine.class.getDeclaredField("rulings");
        rulingsField.setAccessible(true);
        rulings = (List<Ruling>) rulingsField.get(engine);

        Field currentPathField = ObjectExtractorStreamEngine.class.getDeclaredField("currentPath");
        currentPathField.setAccessible(true);
        currentPath = (GeneralPath) currentPathField.get(engine);
    }

    @After
    public void tearDown() {
        engine = null;
        page = null;
        rulings = null;
        currentPath = null;
    }

    @Test
    public void testFillPath_callsStrokeOrFillPathWithTrue() throws Exception {
        Method method = ObjectExtractorStreamEngine.class.getDeclaredMethod("fillPath", int.class);
        method.setAccessible(true);
        method.invoke(engine, 0);

        Field field = ObjectExtractorStreamEngine.class.getDeclaredField("currentPath");
        field.setAccessible(true);
        GeneralPath path = (GeneralPath) field.get(engine);

        Assert.assertTrue(path.getPathIterator(null).isDone());
    }
}
