package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetIgnoreHeaderCaseTest {

    @Test
    public void testGetIgnoreHeaderCase() {
        // Test with default format
        CSVFormat defaultFormat = CSVFormat.DEFAULT;
        assertFalse(defaultFormat.getIgnoreHeaderCase());

        // Test with Excel format
        CSVFormat excelFormat = CSVFormat.EXCEL;
        assertFalse(excelFormat.getIgnoreHeaderCase());

        // Test with INFORMIX_UNLOAD format
        CSVFormat informixUnloadFormat = CSVFormat.INFORMIX_UNLOAD;
        assertFalse(informixUnloadFormat.getIgnoreHeaderCase());

        // Test with INFORMIX_UNLOAD_CSV format
        CSVFormat informixUnloadCsvFormat = CSVFormat.INFORMIX_UNLOAD_CSV;
        assertFalse(informixUnloadCsvFormat.getIgnoreHeaderCase());

        // Test with MYSQL format
        CSVFormat mysqlFormat = CSVFormat.MYSQL;
        assertFalse(mysqlFormat.getIgnoreHeaderCase());

        // Test with ORACLE format
        CSVFormat oracleFormat = CSVFormat.ORACLE;
        assertFalse(oracleFormat.getIgnoreHeaderCase());

        // Test with POSTGRESQL_CSV format
        CSVFormat postgresqlCsvFormat = CSVFormat.POSTGRESQL_CSV;
        assertFalse(postgresqlCsvFormat.getIgnoreHeaderCase());

        // Test with POSTGRESQL_TEXT format
        CSVFormat postgresqlTextFormat = CSVFormat.POSTGRESQL_TEXT;
        assertFalse(postgresqlTextFormat.getIgnoreHeaderCase());

        // Test with RFC4180 format
        CSVFormat rfc4180Format = CSVFormat.RFC4180;
        assertFalse(rfc4180Format.getIgnoreHeaderCase());

        // Test with TDF format
        CSVFormat tdfFormat = CSVFormat.TDF;
        assertFalse(tdfFormat.getIgnoreHeaderCase());
    }
}
