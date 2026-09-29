package technology.tabula;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.lang.UnsupportedOperationException;
public class RulingsetEndTest {
    private Ruling ruling;

    @Before
    public void setUp() {
        // Create a vertical ruling (left and right points have same x-coordinate)
        Point2D p1 = new Point2D.Float(100, 50);
        Point2D p2 = new Point2D.Float(100, 150);
        ruling = new Ruling(p1, p2);
    }

    @After
    public void tearDown() {
        ruling = null;
    }

    @Test
    public void testSetEndForVerticalRuling() {
        // Verify that setEnd calls setBottom for vertical rulings
        float newValue = 200.0f;
        ruling.setEnd(newValue);
        Assert.assertEquals(newValue, ruling.getY2(), 0.001f);
    }


    @Test(expected = java.lang.UnsupportedOperationException.class)
    public void testSetEndForObliqueRuling() {
        // Create an oblique ruling (not vertical or horizontal)
        Point2D p1 = new Point2D.Float(100, 50);
        Point2D p2 = new Point2D.Float(150, 100);
        Ruling obliqueRuling = new Ruling(p1, p2);

        obliqueRuling.setEnd(200.0f);
    }
}
