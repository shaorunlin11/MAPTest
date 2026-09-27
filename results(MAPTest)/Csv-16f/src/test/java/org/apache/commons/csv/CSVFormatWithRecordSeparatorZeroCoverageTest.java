package org.apache.commons.csv;

import org.junit.Test;

public class CSVFormatWithRecordSeparatorZeroCoverageTest {
    @Test
    public void testWithRecordSeparator() {
        // Create a CSVFormat instance with a non-null record separator
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator("\n");

        // Verify that the record separator is set correctly
        assert format.getRecordSeparator().equals("\n");
    }
}
