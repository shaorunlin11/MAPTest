package technology.tabula.extractors;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import technology.tabula.Ruling;

public class BasicExtractionAlgorithmtoStringTest {

    @Test
    public void testToStringReturnsStream() {
        BasicExtractionAlgorithm algorithm = new BasicExtractionAlgorithm();
        assertEquals("stream", algorithm.toString());
    }

    @Test
    public void testToStringWithVerticalRulingsReturnsStream() {
        List<Ruling> verticalRulings = new ArrayList<>();
        BasicExtractionAlgorithm algorithm = new BasicExtractionAlgorithm(verticalRulings);
        assertEquals("stream", algorithm.toString());
    }
}
