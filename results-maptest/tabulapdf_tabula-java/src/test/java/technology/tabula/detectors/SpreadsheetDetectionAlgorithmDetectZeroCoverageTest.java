package technology.tabula.detectors;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import technology.tabula.Page;
import technology.tabula.Ruling;
import technology.tabula.Cell;
import technology.tabula.Rectangle;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SpreadsheetDetectionAlgorithmDetectZeroCoverageTest {
    @Test
    public void testDetect() {
        // Arrange
        Page page = mock(Page.class);
        List<Ruling> horizontalRulings = new ArrayList<>();
        List<Ruling> verticalRulings = new ArrayList<>();
        List<Cell> cells = new ArrayList<>();
        List<Rectangle> tables = new ArrayList<>();

        when(page.getHorizontalRulings()).thenReturn(horizontalRulings);
        when(page.getVerticalRulings()).thenReturn(verticalRulings);

        // Act
        SpreadsheetDetectionAlgorithm algorithm = new SpreadsheetDetectionAlgorithm();
        List<Rectangle> result = algorithm.detect(page);

        // Assert
        assertEquals(tables, result);
    }
}
