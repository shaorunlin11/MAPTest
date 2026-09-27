package org.apache.commons.csv;

import org.junit.Test;
import java.io.StringWriter;
import java.io.IOException;

public class CSVFormatprint_cb49c4b5Test {

    @Test
    public void testPrintWithStringWriter() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT;
        StringWriter writer = new StringWriter();
        CSVPrinter printer = format.print(writer);
        // Verify that the printer is not null
        assert printer != null;
    }

    @Test
    public void testPrintWithDifferentCSVFormatConfigurations() throws IOException {
        // Test with EXCEL format
        CSVFormat excelFormat = CSVFormat.EXCEL;
        StringWriter excelWriter = new StringWriter();
        CSVPrinter excelPrinter = excelFormat.print(excelWriter);
        assert excelPrinter != null;

        // Test with INFORMIX_UNLOAD format
        CSVFormat informixUnloadFormat = CSVFormat.INFORMIX_UNLOAD;
        StringWriter informixUnloadWriter = new StringWriter();
        CSVPrinter informixUnloadPrinter = informixUnloadFormat.print(informixUnloadWriter);
        assert informixUnloadPrinter != null;

        // Test with MYSQL format
        CSVFormat mysqlFormat = CSVFormat.MYSQL;
        StringWriter mysqlWriter = new StringWriter();
        CSVPrinter mysqlPrinter = mysqlFormat.print(mysqlWriter);
        assert mysqlPrinter != null;

        // Test with ORACLE format
        CSVFormat oracleFormat = CSVFormat.ORACLE;
        StringWriter oracleWriter = new StringWriter();
        CSVPrinter oraclePrinter = oracleFormat.print(oracleWriter);
        assert oraclePrinter != null;

        // Test with POSTGRESQL_CSV format
        CSVFormat postgresqlCsvFormat = CSVFormat.POSTGRESQL_CSV;
        StringWriter postgresqlCsvWriter = new StringWriter();
        CSVPrinter postgresqlCsvPrinter = postgresqlCsvFormat.print(postgresqlCsvWriter);
        assert postgresqlCsvPrinter != null;

        // Test with RFC4180 format
        CSVFormat rfc4180Format = CSVFormat.RFC4180;
        StringWriter rfc4180Writer = new StringWriter();
        CSVPrinter rfc4180Printer = rfc4180Format.print(rfc4180Writer);
        assert rfc4180Printer != null;

        // Test with TDF format
        CSVFormat tdfFormat = CSVFormat.TDF;
        StringWriter tdfWriter = new StringWriter();
        CSVPrinter tdfPrinter = tdfFormat.print(tdfWriter);
        assert tdfPrinter != null;
    }
}
