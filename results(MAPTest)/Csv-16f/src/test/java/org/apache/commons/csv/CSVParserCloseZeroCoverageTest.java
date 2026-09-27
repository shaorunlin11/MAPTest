package org.apache.commons.csv;

import org.junit.Test;

import java.io.StringReader;
import java.io.IOException;

public class CSVParserCloseZeroCoverageTest {
    @Test
    public void testClose() throws IOException {
        // Create a CSVParser instance with a non-null lexer
        CSVParser parser = new CSVParser(new StringReader("test"), CSVFormat.DEFAULT);

        // Call the close method to execute target line 383
        parser.close();
    }
}
