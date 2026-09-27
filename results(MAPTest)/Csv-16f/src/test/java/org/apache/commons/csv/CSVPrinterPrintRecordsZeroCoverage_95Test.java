package org.apache.commons.csv;

import org.junit.Test;

import java.io.StringWriter;
import java.util.Arrays;

public class CSVPrinterPrintRecordsZeroCoverage_95Test {
    @Test
    public void testPrintRecordsWithObjectArray() throws Exception {
        StringWriter writer = new StringWriter();
        CSVFormat format = CSVFormat.DEFAULT;
        CSVPrinter printer = new CSVPrinter(writer, format);

        Object[] values = new Object[] { "value1", "value2", "value3" };
        printer.printRecords(values);

        // This test is designed to cover line 327 of the printRecords method
        // by ensuring that the value parameter is an instance of Object[]
        // and that the code path for printing records from an Object array is executed.
    }
}
