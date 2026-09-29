package org.apache.commons.csv;

import org.junit.Test;

import java.io.StringWriter;


public class CSVFormatPrintRecordZeroCoverageTest {
    @Test
    public void testPrintRecordWithNonNullValues() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT;
        Appendable out = new StringWriter();
        Object[] values = {"test1", "test2", "test3"};

        format.printRecord(out, values);
    }
}
