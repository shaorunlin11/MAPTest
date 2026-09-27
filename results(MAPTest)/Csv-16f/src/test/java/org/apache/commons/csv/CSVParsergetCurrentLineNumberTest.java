package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.StringReader;
import java.io.Reader;
import java.io.IOException;
import static org.junit.Assert.*;

public class CSVParsergetCurrentLineNumberTest {
    private CSVParser csvParser;
    private Reader reader;

    @Before
    public void setUp() throws IOException {
        reader = new StringReader("header1,header2\nvalue1,value2");
        csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    }

    @After
    public void tearDown() throws IOException {
        if (csvParser != null) {
            csvParser.close();
        }
        if (reader != null) {
            reader.close();
        }
    }

    @Test
    public void testGetCurrentLineNumber() throws Exception {
        // Initial line number should be 0 before any records are read
        assertEquals(0, csvParser.getCurrentLineNumber());

        // Read the first record
        csvParser.iterator().next();

        // Line number should now be 1 (after header)
        assertEquals(1, csvParser.getCurrentLineNumber());
    }
}
