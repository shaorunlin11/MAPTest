package technology.tabula;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
public class ObjectExtractorStreamEnginelineToTest {
    private ObjectExtractorStreamEngine engine;
    private PDPage page;

    @Before
    public void setUp() throws Exception {
        page = new PDPage();
        engine = new ObjectExtractorStreamEngine(page);
    }
}
