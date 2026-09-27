package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatisNullStringSetTest {

    @Test
    public void testIsNullStringSet() throws Exception {
        // Test case where nullString is not set
        CSVFormat format1 = CSVFormat.DEFAULT
                .withNullString(null);
        assertFalse(format1.isNullStringSet());

        // Test case where nullString is set to "\\N"
        CSVFormat format2 = CSVFormat.DEFAULT
                .withNullString("\\N");
        assertTrue(format2.isNullStringSet());

        // Test case where nullString is set to EMPTY
        CSVFormat format3 = CSVFormat.DEFAULT
                .withNullString("");
        assertTrue(format3.isNullStringSet());
    }
}
