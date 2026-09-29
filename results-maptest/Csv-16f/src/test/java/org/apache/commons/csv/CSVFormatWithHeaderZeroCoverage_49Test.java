package org.apache.commons.csv;

import org.junit.Test;

import java.sql.ResultSetMetaData;

public class CSVFormatWithHeaderZeroCoverage_49Test {
    @Test
    public void testWithHeaderWithMetaData() throws Exception {
        // Create a mock ResultSetMetaData
        ResultSetMetaData metaData = null;

        // Call the method under test
        CSVFormat format = CSVFormat.DEFAULT.withHeader(metaData);

        // Add assertions if needed to verify the behavior
    }
}
