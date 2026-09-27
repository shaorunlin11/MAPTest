package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetAutoFlushTest {
    @Test
    public void testGetAutoFlush() {
        // Create a CSVFormat instance with autoFlush set to true
        CSVFormat formatWithAutoFlushTrue = CSVFormat.DEFAULT.withAutoFlush(true);

        // Verify that getAutoFlush returns true
        assertTrue(formatWithAutoFlushTrue.getAutoFlush());

        // Create a CSVFormat instance with autoFlush set to false
        CSVFormat formatWithAutoFlushFalse = CSVFormat.DEFAULT.withAutoFlush(false);

        // Verify that getAutoFlush returns false
        assertFalse(formatWithAutoFlushFalse.getAutoFlush());
    }
}
