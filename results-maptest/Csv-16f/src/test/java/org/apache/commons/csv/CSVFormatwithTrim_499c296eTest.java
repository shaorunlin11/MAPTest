package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithTrim_499c296eTest {

    @Test
    public void testWithTrim() {
        CSVFormat format = CSVFormat.DEFAULT;
        CSVFormat newFormat = format.withTrim();

        assertFalse("Original format should not have trim enabled", format.getTrim());
        assertTrue("New format should have trim enabled", newFormat.getTrim());
    }
}
