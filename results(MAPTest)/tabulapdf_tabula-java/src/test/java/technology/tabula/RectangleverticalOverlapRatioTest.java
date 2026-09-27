package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
public class RectangleverticalOverlapRatioTest {
    @Test
    public void testVerticalOverlapRatio_NoOverlap() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(20, 0, 10, 10);
        assertEquals(0.0f, r1.verticalOverlapRatio(r2), 0.0001f);
    }

    @Test
    public void testVerticalOverlapRatio_CompleteContainment_R1ContainsR2() {
        Rectangle r1 = new Rectangle(0, 0, 10, 20);
        Rectangle r2 = new Rectangle(5, 0, 5, 10);
        assertEquals(1.0f, r1.verticalOverlapRatio(r2), 0.0001f);
    }

    @Test
    public void testVerticalOverlapRatio_CompleteContainment_R2ContainsR1() {
        Rectangle r1 = new Rectangle(5, 0, 5, 10);
        Rectangle r2 = new Rectangle(0, 0, 10, 20);
        assertEquals(1.0f, r1.verticalOverlapRatio(r2), 0.0001f);
    }

    @Test
    public void testVerticalOverlapRatio_PartialOverlap_TopOfR2InsideR1() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(5, 0, 5, 15);
        assertEquals(0.5f, r1.verticalOverlapRatio(r2), 0.0001f);
    }




    @Test
    public void testVerticalOverlapRatio_ExactOverlap() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(0, 0, 10, 10);
        assertEquals(1.0f, r1.verticalOverlapRatio(r2), 0.0001f);
    }

}
