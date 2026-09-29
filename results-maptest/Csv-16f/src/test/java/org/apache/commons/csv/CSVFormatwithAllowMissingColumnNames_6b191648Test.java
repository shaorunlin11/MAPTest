package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithAllowMissingColumnNames_6b191648Test {

    @Test
    public void testWithAllowMissingColumnNames() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT;
        CSVFormat newFormat = format.withAllowMissingColumnNames();

        assertFalse("Original format should have allowMissingColumnNames set to false", format.getAllowMissingColumnNames());
        assertTrue("New format should have allowMissingColumnNames set to true", newFormat.getAllowMissingColumnNames());
    }
}
