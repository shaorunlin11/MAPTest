package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetRecordSeparatorTest {
    private static final String CRLF = "\r\n";
    private static final String LF = "\n";

    @Test
    public void testGetRecordSeparator() {
        // Test default format
        assertEquals(CRLF, CSVFormat.DEFAULT.getRecordSeparator());

        // Test Excel format
        assertEquals(CRLF, CSVFormat.EXCEL.getRecordSeparator());

        // Test Informix Unload format
        assertEquals(LF, CSVFormat.INFORMIX_UNLOAD.getRecordSeparator());

        // Test Informix Unload CSV format
        assertEquals(LF, CSVFormat.INFORMIX_UNLOAD_CSV.getRecordSeparator());

        // Test MySQL format
        assertEquals(LF, CSVFormat.MYSQL.getRecordSeparator());

        // Test Oracle format
        assertEquals(CRLF, CSVFormat.ORACLE.getRecordSeparator());

        // Test PostgreSQL CSV format
        assertEquals(LF, CSVFormat.POSTGRESQL_CSV.getRecordSeparator());

        // Test PostgreSQL Text format
        assertEquals(LF, CSVFormat.POSTGRESQL_TEXT.getRecordSeparator());

        // Test RFC4180 format
        assertEquals(CRLF, CSVFormat.RFC4180.getRecordSeparator());

        // Test TDF format
        assertEquals(CRLF, CSVFormat.TDF.getRecordSeparator());
    }
}
