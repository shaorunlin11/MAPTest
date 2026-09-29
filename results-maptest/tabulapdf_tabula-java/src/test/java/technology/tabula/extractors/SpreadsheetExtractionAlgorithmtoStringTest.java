package technology.tabula.extractors;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SpreadsheetExtractionAlgorithmtoStringTest {
    @Test
    public void testToStringReturnsLattice() {
        SpreadsheetExtractionAlgorithm algorithm = new SpreadsheetExtractionAlgorithm();
        assertEquals("lattice", algorithm.toString());
    }
}
