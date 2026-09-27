package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;

public class CSVFormatparseTest {
    private CSVFormat csvFormat;

    @Before
    public void setUp() {
        csvFormat = CSVFormat.DEFAULT;
    }

    @After
    public void tearDown() {
        csvFormat = null;
    }

    @Test
    public void testParseWithValidReader() throws IOException {
        String csvData = "header1,header2\nvalue1,value2";
        Reader reader = new StringReader(csvData);
        CSVParser parser = csvFormat.parse(reader);
        Assert.assertNotNull(parser);
    }

    @Test
    public void testParseWithNullReader() throws IOException {
        try {
            csvFormat.parse(null);
            Assert.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testParseWithEmptyReader() throws IOException {
        Reader reader = new StringReader("");
        CSVParser parser = csvFormat.parse(reader);
        Assert.assertNotNull(parser);
    }

    @Test
    public void testParseWithMultipleLines() throws IOException {
        String csvData = "line1,col1,col2\nline2,col3,col4";
        Reader reader = new StringReader(csvData);
        CSVParser parser = csvFormat.parse(reader);
        Assert.assertNotNull(parser);
    }
}
