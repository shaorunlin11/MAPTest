package org.apache.commons.csv;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVPrinterprintRecord_97b04434Test {

    @Test
    public void testPrintRecord() throws Exception {
        // Create a CSVFormat with no header
        CSVFormat format = CSVFormat.DEFAULT.withHeader();

        // Create a StringWriter to capture output
        StringWriter writer = new StringWriter();

        // Create a CSVPrinter with the writer and format
        CSVPrinter printer = new CSVPrinter(writer, format);

        // Create an Iterable of values
        List<String> values = new ArrayList<>();
        values.add("value1");
        values.add("value2");
        values.add("value3");

        // Call printRecord
        printer.printRecord(values);

        // Flush and close to ensure all data is written
        printer.flush();
        printer.close();

        // Verify the output
        String expectedOutput = System.lineSeparator() + "value1,value2,value3" + System.lineSeparator();
        assertEquals(expectedOutput, writer.toString());
    }
}
