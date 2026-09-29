package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatisEscapeCharacterSetTest {

    @Test
    public void testIsEscapeCharacterSet_ReturnsFalseWhenEscapeCharacterIsNull() {
        CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.isEscapeCharacterSet());
    }

    @Test
    public void testIsEscapeCharacterSet_ReturnsTrueWhenEscapeCharacterIsNotNull() {
        CSVFormat format = CSVFormat.INFORMIX_UNLOAD;
        assertTrue(format.isEscapeCharacterSet());
    }

    @Test
    public void testIsEscapeCharacterSet_ReturnsTrueForOracleFormat() {
        CSVFormat format = CSVFormat.ORACLE;
        assertTrue(format.isEscapeCharacterSet());
    }

    @Test
    public void testIsEscapeCharacterSet_ReturnsTrueForMysqlFormat() {
        CSVFormat format = CSVFormat.MYSQL;
        assertTrue(format.isEscapeCharacterSet());
    }

    @Test
    public void testIsEscapeCharacterSet_ReturnsTrueForPostgreSQLCSVFormat() {
        CSVFormat format = CSVFormat.POSTGRESQL_CSV;
        assertTrue(format.isEscapeCharacterSet());
    }

    @Test
    public void testIsEscapeCharacterSet_ReturnsTrueForPostgreSQLTextFormat() {
        CSVFormat format = CSVFormat.POSTGRESQL_TEXT;
        assertTrue(format.isEscapeCharacterSet());
    }
}
