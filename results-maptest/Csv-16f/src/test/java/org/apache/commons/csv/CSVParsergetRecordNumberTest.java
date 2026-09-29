package org.apache.commons.csv;

import org.junit.Test;
import java.io.StringReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CSVParsergetRecordNumberTest {

    @Test
    public void testGetRecordNumber() throws IOException {
        String csvData = "header1,header2\nvalue1a,value1b\nvalue2a,value2b";
        CSVFormat format = CSVFormat.DEFAULT.withHeader("header1", "header2");
        StringReader reader = new StringReader(csvData);

        CSVParser parser = new CSVParser(reader, format, 0, 1);

        assertEquals(0, parser.getRecordNumber());

        // Advance to next record
        parser.iterator().next();

        assertEquals(1, parser.getRecordNumber());

        // Advance to next record
        parser.iterator().next();

        assertEquals(2, parser.getRecordNumber());
    }
}
