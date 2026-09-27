package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetSkipHeaderRecordTest {

    @Test
    public void testGetSkipHeaderRecord_Default() {
        CSVFormat format = CSVFormat.DEFAULT;
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testGetSkipHeaderRecord_EXCEL() {
        CSVFormat format = CSVFormat.EXCEL;
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testGetSkipHeaderRecord_INFORMIX_UNLOAD() {
        CSVFormat format = CSVFormat.INFORMIX_UNLOAD;
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testGetSkipHeaderRecord_INFORMIX_UNLOAD_CSV() {
        CSVFormat format = CSVFormat.INFORMIX_UNLOAD_CSV;
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testGetSkipHeaderRecord_MYSQL() {
        CSVFormat format = CSVFormat.MYSQL;
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testGetSkipHeaderRecord_ORACLE() {
        CSVFormat format = CSVFormat.ORACLE;
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testGetSkipHeaderRecord_POSTGRESQL_CSV() {
        CSVFormat format = CSVFormat.POSTGRESQL_CSV;
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testGetSkipHeaderRecord_POSTGRESQL_TEXT() {
        CSVFormat format = CSVFormat.POSTGRESQL_TEXT;
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testGetSkipHeaderRecord_RFC4180() {
        CSVFormat format = CSVFormat.RFC4180;
        assertFalse(format.getSkipHeaderRecord());
    }

    @Test
    public void testGetSkipHeaderRecord_TDF() {
        CSVFormat format = CSVFormat.TDF;
        assertFalse(format.getSkipHeaderRecord());
    }
}
