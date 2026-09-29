package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetHeaderTest {

    @Test
    public void testGetHeaderReturnsNullWhenHeaderIsNotSet() {
        CSVFormat format = CSVFormat.DEFAULT;
        String[] header = format.getHeader();
        assertNull("getHeader should return null when header is not set", header);
    }

    @Test
    public void testGetHeaderReturnsClonedArrayWhenHeaderIsSet() {
        String[] expectedHeader = {"col1", "col2", "col3"};
        CSVFormat format = CSVFormat.DEFAULT.withHeader(expectedHeader);
        String[] header = format.getHeader();
        assertNotNull("getHeader should return non-null array when header is set", header);
        assertNotSame("getHeader should return a new array instance", expectedHeader, header);
        assertArrayEquals("getHeader should return the same elements as the original header", expectedHeader, header);
    }

    @Test
    public void testGetHeaderDoesNotModifyOriginalHeader() {
        String[] expectedHeader = {"col1", "col2", "col3"};
        CSVFormat format = CSVFormat.DEFAULT.withHeader(expectedHeader);
        String[] header = format.getHeader();
        header[0] = "modified";
        assertNotEquals("Modifying the returned array should not affect the original header", "modified", expectedHeader[0]);
    }
}
