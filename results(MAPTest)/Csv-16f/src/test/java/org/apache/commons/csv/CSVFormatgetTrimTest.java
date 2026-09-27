package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetTrimTest {

    @Test
    public void testGetTrim() throws Exception {
        // Create a CSVFormat instance with trim set to true using the DEFAULT format and withTrim()
        CSVFormat formatWithTrim = CSVFormat.DEFAULT.withTrim(true);

        // Verify that getTrim returns true
        assertTrue(formatWithTrim.getTrim());

        // Create a CSVFormat instance with trim set to false using the DEFAULT format and withTrim(false)
        CSVFormat formatWithoutTrim = CSVFormat.DEFAULT.withTrim(false);

        // Verify that getTrim returns false
        assertFalse(formatWithoutTrim.getTrim());
    }
}
