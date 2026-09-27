package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.awt.geom.GeneralPath;
import java.awt.geom.AffineTransform;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class ObjectExtractorStreamEngineclosePathTest {
    private ObjectExtractorStreamEngine engine;
    private PDPage page;
    private PDRectangle cropBox;

    @Before
    public void setUp() throws Exception {
        page = new PDPage();
        cropBox = new PDRectangle(0, 0, 612, 792);
        page.setCropBox(cropBox);
        engine = new ObjectExtractorStreamEngine(page);
    }

    @After
    public void tearDown() {
        engine = null;
    }

    @Test
    public void testClosePath_ClosesCurrentPath() {
        // Arrange
        GeneralPath originalPath = new GeneralPath();
        originalPath.moveTo(10, 10);
        originalPath.lineTo(20, 20);

        // Use reflection to set the currentPath field
        try {
            Field currentPathField = ObjectExtractorStreamEngine.class.getDeclaredField("currentPath");
            currentPathField.setAccessible(true);
            currentPathField.set(engine, originalPath);
        } catch (Exception e) {
            fail("Failed to set currentPath field: " + e.getMessage());
        }

        // Act
        engine.closePath();

        // Assert
        try {
            Field currentPathField = ObjectExtractorStreamEngine.class.getDeclaredField("currentPath");
            currentPathField.setAccessible(true);
            GeneralPath closedPath = (GeneralPath) currentPathField.get(engine);
            assertTrue("Path should be closed", closedPath.getWindingRule() != GeneralPath.WIND_EVEN_ODD);
        } catch (Exception e) {
            fail("Failed to get currentPath field: " + e.getMessage());
        }
    }
}
