package org.apache.commons.csv;

import org.junit.Test;

import java.io.StringWriter;
import java.io.IOException;

public class CSVFormatPrintlnZeroCoverageTest {
    @Test
    public void testPrintlnWithTrailingDelimiter() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withTrailingDelimiter(true);
        StringWriter writer = new StringWriter();
        format.println(writer);
    }

@Test
    public void testPrintlnWithRecordSeparator() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator("custom");
        StringWriter writer = new StringWriter();
        format.println(writer);
    }
}
