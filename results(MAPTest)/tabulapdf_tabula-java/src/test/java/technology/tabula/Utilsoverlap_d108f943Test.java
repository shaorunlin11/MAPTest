package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class Utilsoverlap_d108f943Test {

    @Test
    public void testOverlapExactMatch() {
        boolean result = Utils.overlap(100.0, 50.0, 100.0, 50.0, 0.01);
        assertTrue(result);
    }

    @Test
    public void testOverlapWithinVariance() {
        boolean result = Utils.overlap(100.0, 50.0, 100.05, 50.0, 0.01);
        assertTrue(result);
    }

    @Test
    public void testOverlapY2InY1Range() {
        boolean result = Utils.overlap(100.0, 50.0, 120.0, 50.0, 0.01);
        assertTrue(result);
    }

    @Test
    public void testOverlapY1InY2Range() {
        boolean result = Utils.overlap(120.0, 50.0, 100.0, 50.0, 0.01);
        assertTrue(result);
    }

    @Test
    public void testOverlapNoOverlap() {
        boolean result = Utils.overlap(100.0, 50.0, 160.0, 50.0, 0.01);
        assertFalse(result);
    }

    @Test
    public void testOverlapEdgeCaseWithVariance() {
        boolean result = Utils.overlap(100.0, 50.0, 150.0, 50.0, 0.01);
        assertTrue(result);
    }

    @Test
    public void testOverlapWithZeroVariance() {
        boolean result = Utils.overlap(100.0, 50.0, 100.0, 50.0, 0.0);
        assertTrue(result);
    }

    @Test
    public void testOverlapWithNegativeHeight() {
        boolean result = Utils.overlap(100.0, -50.0, 100.0, 50.0, 0.01);
        assertTrue(result);
    }

    @Test
    public void testOverlapWithZeroHeight() {
        boolean result = Utils.overlap(100.0, 0.0, 100.0, 50.0, 0.01);
        assertTrue(result);
    }
}
