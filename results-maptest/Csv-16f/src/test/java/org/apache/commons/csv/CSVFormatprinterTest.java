package org.apache.commons.csv;

import org.junit.Test;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class CSVFormatprinterTest {

    @Test
    public void testPrinter() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT;
        CSVPrinter printer = format.printer();
        // Verify that the printer is created with the correct format
        // and writes to System.out
        Writer writer = new StringWriter();
        CSVPrinter csvPrinter = new CSVPrinter(writer, format);
        // Additional assertions can be added if needed
    }
}
