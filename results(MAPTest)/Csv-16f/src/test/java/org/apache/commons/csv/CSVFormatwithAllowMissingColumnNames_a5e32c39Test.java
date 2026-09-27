package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithAllowMissingColumnNames_a5e32c39Test {

    @Test
    public void testWithAllowMissingColumnNames() throws Exception {
        // Create a CSVFormat instance
        CSVFormat format = CSVFormat.DEFAULT;

        // Test with true
        CSVFormat newFormatTrue = format.withAllowMissingColumnNames(true);
        assertTrue(newFormatTrue.getAllowMissingColumnNames());

        // Test with false
        CSVFormat newFormatFalse = format.withAllowMissingColumnNames(false);
        assertFalse(newFormatFalse.getAllowMissingColumnNames());

        // Ensure original format is unchanged
        assertFalse(format.getAllowMissingColumnNames());
    }
}
