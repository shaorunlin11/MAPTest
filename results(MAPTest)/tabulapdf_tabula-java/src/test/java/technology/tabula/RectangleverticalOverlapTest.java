package technology.tabula;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
public class RectangleverticalOverlapTest {
    private Rectangle rect1;
    private Rectangle rect2;

    @Before
    public void setUp() {
        rect1 = new Rectangle(0, 0, 100, 100);
        rect2 = new Rectangle(50, 50, 100, 100);
    }

    @After
    public void tearDown() {
        rect1 = null;
        rect2 = null;
    }

    @Test
    public void testVerticalOverlapWithOverlappingRectangles() {
        float expected = 50.0f;
        float actual = rect1.verticalOverlap(rect2);
        Assert.assertEquals(expected, actual, 0.001f);
    }

    @Test
    public void testVerticalOverlapWithNoOverlapAbove() {
        Rectangle rect3 = new Rectangle(150, 0, 100, 100);
        float expected = 0.0f;
        float actual = rect1.verticalOverlap(rect3);
        Assert.assertEquals(expected, actual, 0.001f);
    }


    @Test
    public void testVerticalOverlapWithExactTopEdgeTouch() {
        Rectangle rect3 = new Rectangle(100, 0, 100, 100);
        float expected = 0.0f;
        float actual = rect1.verticalOverlap(rect3);
        Assert.assertEquals(expected, actual, 0.001f);
    }

    @Test
    public void testVerticalOverlapWithExactBottomEdgeTouch() {
        Rectangle rect3 = new Rectangle(-100, 0, 100, 100);
        float expected = 0.0f;
        float actual = rect1.verticalOverlap(rect3);
        Assert.assertEquals(expected, actual, 0.001f);
    }

    @Test
    public void testVerticalOverlapWithFullyContained() {
        Rectangle rect3 = new Rectangle(25, 25, 50, 50);
        float expected = 50.0f;
        float actual = rect1.verticalOverlap(rect3);
        Assert.assertEquals(expected, actual, 0.001f);
    }

    @Test
    public void testVerticalOverlapWithPartialOverlap() {
        Rectangle rect3 = new Rectangle(75, 0, 100, 100);
        float expected = 25.0f;
        float actual = rect1.verticalOverlap(rect3);
        Assert.assertEquals(expected, actual, 0.001f);
    }
}
