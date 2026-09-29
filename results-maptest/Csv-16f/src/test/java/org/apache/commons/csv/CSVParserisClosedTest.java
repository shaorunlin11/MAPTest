package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CSVParserisClosedTest {
    private CSVParser csvParser;
    private Reader reader;
    private CSVFormat format;

    @Before
    public void setUp() throws Exception {
        reader = new StringReader("a,b,c");
        format = CSVFormat.DEFAULT;
        csvParser = new CSVParser(reader, format);
    }

    @After
    public void tearDown() throws Exception {
        if (csvParser != null) {
            csvParser.close();
        }
        if (reader != null) {
            reader.close();
        }
    }

    @Test
    public void testIsClosedInitiallyFalse() throws Exception {
        assertFalse(csvParser.isClosed());
    }

    @Test
    public void testIsClosedAfterClose() throws Exception {
        csvParser.close();
        assertTrue(csvParser.isClosed());
    }
}
