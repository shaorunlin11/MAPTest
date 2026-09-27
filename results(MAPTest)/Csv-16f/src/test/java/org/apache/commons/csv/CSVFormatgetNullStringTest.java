package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetNullStringTest {

    @Test
    public void testGetNullString() {
        // Test default format
        assertEquals(null, CSVFormat.DEFAULT.getNullString());

        // Test MySQL format which has "\\N" as null string
        assertEquals("\\N", CSVFormat.MYSQL.getNullString());

        // Test Oracle format which has "\\N" as null string
        assertEquals("\\N", CSVFormat.ORACLE.getNullString());

        // Test PostgreSQL CSV format which has EMPTY as null string
        assertEquals("", CSVFormat.POSTGRESQL_CSV.getNullString());

        // Test PostgreSQL Text format which has "\\N" as null string
        assertEquals("\\N", CSVFormat.POSTGRESQL_TEXT.getNullString());

        // Test with custom null string
        CSVFormat customFormat = CSVFormat.DEFAULT.withNullString("CUSTOM_NULL");
        assertEquals("CUSTOM_NULL", customFormat.getNullString());
    }
}
