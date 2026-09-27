package technology.tabula.extractors;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import technology.tabula.Page;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import technology.tabula.extractors.BasicExtractionAlgorithm;

public class SpreadsheetExtractionAlgorithmIsTabularZeroCoverageTest {
    @Test
    public void testIsTabularWithEmptyTextReturnsFalse() {
        Page page = mock(Page.class);
        when(page.getText()).thenReturn(java.util.Collections.emptyList());

        SpreadsheetExtractionAlgorithm algorithm = new SpreadsheetExtractionAlgorithm();
        assertFalse(algorithm.isTabular(page));
    }
}
