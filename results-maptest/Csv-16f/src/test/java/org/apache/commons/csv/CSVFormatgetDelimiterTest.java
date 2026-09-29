package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CSVFormatgetDelimiterTest {

    @Test
    public void testGetDelimiter_DEFAULT() {
        assertEquals(',', CSVFormat.DEFAULT.getDelimiter());
    }

    @Test
    public void testGetDelimiter_EXCEL() {
        assertEquals(',', CSVFormat.EXCEL.getDelimiter());
    }

    @Test
    public void testGetDelimiter_INFORMIX_UNLOAD() {
        assertEquals('|', CSVFormat.INFORMIX_UNLOAD.getDelimiter());
    }

    @Test
    public void testGetDelimiter_INFORMIX_UNLOAD_CSV() {
        assertEquals(',', CSVFormat.INFORMIX_UNLOAD_CSV.getDelimiter());
    }

    @Test
    public void testGetDelimiter_MYSQL() {
        assertEquals('\t', CSVFormat.MYSQL.getDelimiter());
    }

    @Test
    public void testGetDelimiter_ORACLE() {
        assertEquals(',', CSVFormat.ORACLE.getDelimiter());
    }

    @Test
    public void testGetDelimiter_POSTGRESQL_CSV() {
        assertEquals(',', CSVFormat.POSTGRESQL_CSV.getDelimiter());
    }

    @Test
    public void testGetDelimiter_POSTGRESQL_TEXT() {
        assertEquals('\t', CSVFormat.POSTGRESQL_TEXT.getDelimiter());
    }

    @Test
    public void testGetDelimiter_RFC4180() {
        assertEquals(',', CSVFormat.RFC4180.getDelimiter());
    }

    @Test
    public void testGetDelimiter_TDF() {
        assertEquals('\t', CSVFormat.TDF.getDelimiter());
    }
}
