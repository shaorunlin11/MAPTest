package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetTrailingDelimiterTest {

    @Test
    public void testGetTrailingDelimiter_Default() {
        CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testGetTrailingDelimiter_EXCEL() {
        CSVFormat format = CSVFormat.EXCEL;
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testGetTrailingDelimiter_INFORMIX_UNLOAD() {
        CSVFormat format = CSVFormat.INFORMIX_UNLOAD;
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testGetTrailingDelimiter_INFORMIX_UNLOAD_CSV() {
        CSVFormat format = CSVFormat.INFORMIX_UNLOAD_CSV;
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testGetTrailingDelimiter_MYSQL() {
        CSVFormat format = CSVFormat.MYSQL;
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testGetTrailingDelimiter_ORACLE() {
        CSVFormat format = CSVFormat.ORACLE;
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testGetTrailingDelimiter_POSTGRESQL_CSV() {
        CSVFormat format = CSVFormat.POSTGRESQL_CSV;
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testGetTrailingDelimiter_POSTGRESQL_TEXT() {
        CSVFormat format = CSVFormat.POSTGRESQL_TEXT;
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testGetTrailingDelimiter_RFC4180() {
        CSVFormat format = CSVFormat.RFC4180;
        assertFalse(format.getTrailingDelimiter());
    }

    @Test
    public void testGetTrailingDelimiter_TDF() {
        CSVFormat format = CSVFormat.TDF;
        assertFalse(format.getTrailingDelimiter());
    }
}
