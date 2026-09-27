package technology.tabula;

import org.junit.Test;

public class RulingNearlyIntersectsZeroCoverage_125Test {
    @Test
    public void testNearlyIntersectsWithNonNullAnother() {
        Ruling ruling1 = new Ruling(0.0f, 0.0f, 100.0f, 100.0f);
        Ruling ruling2 = new Ruling(50.0f, 50.0f, 100.0f, 100.0f);

        // Call the method with a non-null 'another' parameter
        ruling1.nearlyIntersects(ruling2);
    }
}
