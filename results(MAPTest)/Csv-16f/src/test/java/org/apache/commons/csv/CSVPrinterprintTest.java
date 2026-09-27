package org.apache.commons.csv;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class CSVPrinterprintTest {

    @Test
    public void testPrint() throws IOException {
        // Create a mock CSVFormat
        CSVFormat format = CSVFormat.DEFAULT;

        // Create a StringWriter to act as the Appendable
        StringWriter writer = new StringWriter();

        // Create a CSVPrinter instance
        CSVPrinter printer = new CSVPrinter(writer, format);

        // Test printing a value
        printer.print("testValue");

        // Verify that the value was written
        assertEquals("testValue", writer.toString().trim());
    }

    @Test
    public void testPrintWithNewRecordState() throws IOException {
        // Create a mock CSVFormat
        CSVFormat format = CSVFormat.DEFAULT;

        // Create a StringWriter to act as the Appendable
        StringWriter writer = new StringWriter();

        // Create a CSVPrinter instance
        CSVPrinter printer = new CSVPrinter(writer, format);

        // First print should set newRecord to false
        printer.print("firstValue");
        assertFalse(printer.toString().contains("\n")); // Check if new record started

        // Second print should not start a new record
        printer.print("secondValue");
        assertFalse(printer.toString().contains("\n")); // Check if new record started
    }
}
