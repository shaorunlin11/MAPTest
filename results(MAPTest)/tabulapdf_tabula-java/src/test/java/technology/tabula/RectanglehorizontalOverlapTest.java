package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectanglehorizontalOverlapTest {

    @Test
    public void testHorizontalOverlapNoOverlap() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 10);
        Rectangle rect2 = new Rectangle(0, 20, 10, 10);
        assertEquals(0.0f, rect1.horizontalOverlap(rect2), 0.001f);
    }

    @Test
    public void testHorizontalOverlapFullOverlap() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 10);
        Rectangle rect2 = new Rectangle(0, 0, 10, 10);
        assertEquals(10.0f, rect1.horizontalOverlap(rect2), 0.001f);
    }

    @Test
    public void testHorizontalOverlapPartialOverlap() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 10);
        Rectangle rect2 = new Rectangle(0, 5, 10, 10);
        assertEquals(5.0f, rect1.horizontalOverlap(rect2), 0.001f);
    }

    @Test
    public void testHorizontalOverlapLeftToRight() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 10);
        Rectangle rect2 = new Rectangle(0, 15, 10, 10);
        assertEquals(0.0f, rect1.horizontalOverlap(rect2), 0.001f);
    }

    @Test
    public void testHorizontalOverlapRightToLeft() {
        Rectangle rect1 = new Rectangle(0, 15, 10, 10);
        Rectangle rect2 = new Rectangle(0, 0, 10, 10);
        assertEquals(0.0f, rect1.horizontalOverlap(rect2), 0.001f);
    }

    @Test
    public void testHorizontalOverlapWithNegativeCoordinates() {
        Rectangle rect1 = new Rectangle(0, -5, 10, 10);
        Rectangle rect2 = new Rectangle(0, 0, 10, 10);
        assertEquals(5.0f, rect1.horizontalOverlap(rect2), 0.001f);
    }

    @Test
    public void testHorizontalOverlapWithZeroWidth() {
        Rectangle rect1 = new Rectangle(0, 0, 0, 10);
        Rectangle rect2 = new Rectangle(0, 0, 10, 10);
        assertEquals(0.0f, rect1.horizontalOverlap(rect2), 0.001f);
    }

    @Test
    public void testHorizontalOverlapWithZeroHeight() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 0);
        Rectangle rect2 = new Rectangle(0, 0, 10, 10);
        assertEquals(10.0f, rect1.horizontalOverlap(rect2), 0.001f);
    }
}
