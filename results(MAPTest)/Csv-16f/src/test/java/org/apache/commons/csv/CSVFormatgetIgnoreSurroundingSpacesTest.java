package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetIgnoreSurroundingSpacesTest {

    @Test
    public void testGetIgnoreSurroundingSpaces() {
        // Test default value
        assertFalse(CSVFormat.DEFAULT.getIgnoreSurroundingSpaces());

        // Test TDF format which explicitly sets ignoreSurroundingSpaces
        assertTrue(CSVFormat.TDF.getIgnoreSurroundingSpaces());

        // Test other formats that don't set ignoreSurroundingSpaces
        assertFalse(CSVFormat.EXCEL.getIgnoreSurroundingSpaces());
        assertFalse(CSVFormat.INFORMIX_UNLOAD.getIgnoreSurroundingSpaces());
        assertFalse(CSVFormat.INFORMIX_UNLOAD_CSV.getIgnoreSurroundingSpaces());
        assertFalse(CSVFormat.MYSQL.getIgnoreSurroundingSpaces());
        assertFalse(CSVFormat.ORACLE.getIgnoreSurroundingSpaces());
        assertFalse(CSVFormat.POSTGRESQL_CSV.getIgnoreSurroundingSpaces());
        assertFalse(CSVFormat.POSTGRESQL_TEXT.getIgnoreSurroundingSpaces());
        assertFalse(CSVFormat.RFC4180.getIgnoreSurroundingSpaces());
    }
}
