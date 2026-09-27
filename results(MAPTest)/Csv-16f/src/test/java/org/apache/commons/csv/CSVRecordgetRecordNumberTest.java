package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;


public class CSVRecordgetRecordNumberTest {

    @Test
    public void testGetRecordNumber() throws Exception {
        // Create a CSVRecord instance with a known record number
        CSVRecord record = new CSVRecord(new String[] {"value1", "value2"}, new HashMap<>(), "comment", 12345, 0);

        // Verify that the getRecordNumber method returns the correct value
        assertEquals(12345, record.getRecordNumber());
    }
}
