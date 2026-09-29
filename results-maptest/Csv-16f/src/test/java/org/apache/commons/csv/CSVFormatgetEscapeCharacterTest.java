package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetEscapeCharacterTest {

    @Test
    public void testGetEscapeCharacter() {
        // Test default format
        assertEquals(null, CSVFormat.DEFAULT.getEscapeCharacter());

        // Test Excel format
        assertEquals(null, CSVFormat.EXCEL.getEscapeCharacter());

        // Test INFORMIX_UNLOAD format
        assertEquals(Character.valueOf('\\'), CSVFormat.INFORMIX_UNLOAD.getEscapeCharacter());

        // Test INFORMIX_UNLOAD_CSV format
        assertEquals(null, CSVFormat.INFORMIX_UNLOAD_CSV.getEscapeCharacter());

        // Test MYSQL format
        assertEquals(Character.valueOf('\\'), CSVFormat.MYSQL.getEscapeCharacter());

        // Test ORACLE format
        assertEquals(Character.valueOf('\\'), CSVFormat.ORACLE.getEscapeCharacter());

        // Test POSTGRESQL_CSV format
        assertEquals(Character.valueOf('"'), CSVFormat.POSTGRESQL_CSV.getEscapeCharacter());

        // Test POSTGRESQL_TEXT format
        assertEquals(Character.valueOf('"'), CSVFormat.POSTGRESQL_TEXT.getEscapeCharacter());

        // Test RFC4180 format
        assertEquals(null, CSVFormat.RFC4180.getEscapeCharacter());

        // Test TDF format
        assertEquals(null, CSVFormat.TDF.getEscapeCharacter());
    }
}
