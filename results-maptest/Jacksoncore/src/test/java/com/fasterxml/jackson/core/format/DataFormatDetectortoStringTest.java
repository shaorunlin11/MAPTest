package com.fasterxml.jackson.core.format;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.format.DataFormatDetector;
import com.fasterxml.jackson.core.format.MatchStrength;

public class DataFormatDetectortoStringTest {

    @Test
    public void testToStringWithNoDetectors() throws Exception {
        DataFormatDetector detector = new DataFormatDetector(new JsonFactory[0]);
        assertEquals("[]", detector.toString());
    }

    @Test
    public void testToStringWithSingleDetector() throws Exception {
        JsonFactory mockFactory = new JsonFactory() {
            @Override
            public String getFormatName() {
                return "MockFormat";
            }
        };
        DataFormatDetector detector = new DataFormatDetector(new JsonFactory[]{mockFactory});
        assertEquals("[MockFormat]", detector.toString());
    }

    @Test
    public void testToStringWithMultipleDetectors() throws Exception {
        JsonFactory mockFactory1 = new JsonFactory() {
            @Override
            public String getFormatName() {
                return "Format1";
            }
        };
        JsonFactory mockFactory2 = new JsonFactory() {
            @Override
            public String getFormatName() {
                return "Format2";
            }
        };
        DataFormatDetector detector = new DataFormatDetector(new JsonFactory[]{mockFactory1, mockFactory2});
        assertEquals("[Format1, Format2]", detector.toString());
    }

    @Test
    public void testToStringWithCollectionOfDetectors() throws Exception {
        JsonFactory mockFactory1 = new JsonFactory() {
            @Override
            public String getFormatName() {
                return "FormatA";
            }
        };
        JsonFactory mockFactory2 = new JsonFactory() {
            @Override
            public String getFormatName() {
                return "FormatB";
            }
        };
        Collection<JsonFactory> detectors = new ArrayList<JsonFactory>();
        detectors.add(mockFactory1);
        detectors.add(mockFactory2);
        DataFormatDetector detector = new DataFormatDetector(detectors);
        assertEquals("[FormatA, FormatB]", detector.toString());
    }
}
