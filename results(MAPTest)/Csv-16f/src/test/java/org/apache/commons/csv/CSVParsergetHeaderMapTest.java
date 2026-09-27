package org.apache.commons.csv;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.*;
import java.io.StringReader;

import java.util.LinkedHashMap;

public class CSVParsergetHeaderMapTest {
    @Test
    public void testGetHeaderMapReturnsNullWhenHeaderMapIsNull() throws Exception {
        // Arrange
        CSVParser csvParser = new CSVParser(new StringReader(""), CSVFormat.DEFAULT);

        // Act
        Map<String, Integer> result = csvParser.getHeaderMap();

        // Assert
        assertNull("Expected headerMap to be null", result);
    }

@Test
    public void testGetHeaderMapReturnsCopyWhenHeaderMapIsNotNull() throws Exception {
        // Arrange
        CSVFormat format = CSVFormat.DEFAULT.withHeader("header1", "header2");
        CSVParser csvParser = new CSVParser(new StringReader("header1,header2\nvalue1,value2"), format);

        // Act
        Map<String, Integer> result = csvParser.getHeaderMap();

        // Assert
        assertNotNull("Expected headerMap to be not null", result);
        assertEquals("Expected headerMap to have 2 entries", 2, result.size());
        assertTrue("Expected headerMap to be a LinkedHashMap", result instanceof LinkedHashMap);
    }
}
