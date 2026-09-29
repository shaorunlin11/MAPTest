package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
public class RectangleoverlapRatioTest {
    @Test
    public void testOverlapRatioNoOverlap() {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(2, 2, 1, 1);
        assertEquals(0.0f, r1.overlapRatio(r2), 0.001f);
    }

    @Test
    public void testOverlapRatioCompleteOverlap() {
        Rectangle r1 = new Rectangle(0, 0, 2, 2);
        Rectangle r2 = new Rectangle(0, 0, 2, 2);
        assertEquals(1.0f, r1.overlapRatio(r2), 0.001f);
    }

    @Test
    public void testOverlapRatioPartialOverlap() {
        Rectangle r1 = new Rectangle(0, 0, 2, 2);
        Rectangle r2 = new Rectangle(1, 1, 2, 2);
        float ratio = r1.overlapRatio(r2);
        assertTrue(ratio > 0.0f && ratio < 1.0f);
    }


    @Test
    public void testOverlapRatioZeroAreaAndNonZero() {
        Rectangle r1 = new Rectangle(0, 0, 0, 0);
        Rectangle r2 = new Rectangle(0, 0, 2, 2);
        assertEquals(0.0f, r1.overlapRatio(r2), 0.001f);
    }
}
