package org.apache.commons.csv;

import org.junit.Test;

public class CSVFormatWithHeaderCommentsZeroCoverageTest {
    @Test
    public void testWithHeaderComments() {
        // Create a CSVFormat instance with the required conditions
        CSVFormat format = CSVFormat.DEFAULT
                .withSkipHeaderRecord()
                .withAllowMissingColumnNames();

        // Call the method under test with non-null headerComments
        format.withHeaderComments("This is a comment");
    }
}
