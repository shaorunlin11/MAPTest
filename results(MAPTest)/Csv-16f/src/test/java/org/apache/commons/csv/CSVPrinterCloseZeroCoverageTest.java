package org.apache.commons.csv;

import org.junit.Test;

public class CSVPrinterCloseZeroCoverageTest {
    @Test
    public void testClose() throws Exception {
        // Create a CSVPrinter instance using the constructor
        CSVPrinter csvPrinter = new CSVPrinter(new StringBuilder(), CSVFormat.DEFAULT);

        // Call the close() method
        csvPrinter.close();
    }
}
