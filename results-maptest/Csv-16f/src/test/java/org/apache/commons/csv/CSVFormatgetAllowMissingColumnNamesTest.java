package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetAllowMissingColumnNamesTest {

    @Test
    public void testGetAllowMissingColumnNames() {
        // Test default value
        CSVFormat defaultFormat = CSVFormat.DEFAULT;
        assertFalse("DEFAULT should not allow missing column names", defaultFormat.getAllowMissingColumnNames());

        // Test EXCEL format which explicitly enables allowMissingColumnNames
        CSVFormat excelFormat = CSVFormat.EXCEL;
        assertTrue("EXCEL should allow missing column names", excelFormat.getAllowMissingColumnNames());

        // Test INFORMIX_UNLOAD which does not set allowMissingColumnNames
        CSVFormat informixUnloadFormat = CSVFormat.INFORMIX_UNLOAD;
        assertFalse("INFORMIX_UNLOAD should not allow missing column names", informixUnloadFormat.getAllowMissingColumnNames());

        // Test INFORMIX_UNLOAD_CSV which does not set allowMissingColumnNames
        CSVFormat informixUnloadCsvFormat = CSVFormat.INFORMIX_UNLOAD_CSV;
        assertFalse("INFORMIX_UNLOAD_CSV should not allow missing column names", informixUnloadCsvFormat.getAllowMissingColumnNames());

        // Test MYSQL which does not set allowMissingColumnNames
        CSVFormat mysqlFormat = CSVFormat.MYSQL;
        assertFalse("MYSQL should not allow missing column names", mysqlFormat.getAllowMissingColumnNames());

        // Test ORACLE which does not set allowMissingColumnNames
        CSVFormat oracleFormat = CSVFormat.ORACLE;
        assertFalse("ORACLE should not allow missing column names", oracleFormat.getAllowMissingColumnNames());

        // Test POSTGRESQL_CSV which does not set allowMissingColumnNames
        CSVFormat postgresqlCsvFormat = CSVFormat.POSTGRESQL_CSV;
        assertFalse("POSTGRESQL_CSV should not allow missing column names", postgresqlCsvFormat.getAllowMissingColumnNames());

        // Test POSTGRESQL_TEXT which does not set allowMissingColumnNames
        CSVFormat postgresqlTextFormat = CSVFormat.POSTGRESQL_TEXT;
        assertFalse("POSTGRESQL_TEXT should not allow missing column names", postgresqlTextFormat.getAllowMissingColumnNames());

        // Test RFC4180 which does not set allowMissingColumnNames
        CSVFormat rfc4180Format = CSVFormat.RFC4180;
        assertFalse("RFC4180 should not allow missing column names", rfc4180Format.getAllowMissingColumnNames());

        // Test TDF which does not set allowMissingColumnNames
        CSVFormat tdfFormat = CSVFormat.TDF;
        assertFalse("TDF should not allow missing column names", tdfFormat.getAllowMissingColumnNames());
    }
}
