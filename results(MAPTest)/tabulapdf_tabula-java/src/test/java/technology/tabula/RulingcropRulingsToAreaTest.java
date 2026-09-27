package technology.tabula;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
public class RulingcropRulingsToAreaTest {
    private List<Ruling> testRulings;
    private Rectangle2D testArea;

    @Before
    public void setUp() {
        testRulings = new ArrayList<>();
        testArea = new Rectangle2D.Float(100, 100, 200, 200);

        // Create some test rulings
        testRulings.add(new Ruling(50, 50, 100, 100)); // Partially overlaps
        testRulings.add(new Ruling(150, 150, 100, 100)); // Fully inside
        testRulings.add(new Ruling(300, 300, 100, 100)); // No overlap
    }

    @After
    public void tearDown() {
        testRulings = null;
        testArea = null;
    }



    @Test
    public void testCropRulingsToArea_OriginalListUnmodified() {
        List<Ruling> original = new ArrayList<>(testRulings);
        Ruling.cropRulingsToArea(original, testArea);

        Assert.assertEquals("Original list should remain unchanged", 3, original.size());
    }
}
