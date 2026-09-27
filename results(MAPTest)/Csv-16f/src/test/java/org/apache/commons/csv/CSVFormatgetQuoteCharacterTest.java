package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetQuoteCharacterTest {

    @Test
    public void testGetQuoteCharacter_Default() {
        CSVFormat format = CSVFormat.DEFAULT;
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
    }

    @Test
    public void testGetQuoteCharacter_Excel() {
        CSVFormat format = CSVFormat.EXCEL;
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
    }

    @Test
    public void testGetQuoteCharacter_InformixUnload() {
        CSVFormat format = CSVFormat.INFORMIX_UNLOAD;
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
    }

    @Test
    public void testGetQuoteCharacter_InformixUnloadCsv() {
        CSVFormat format = CSVFormat.INFORMIX_UNLOAD_CSV;
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
    }

    @Test
    public void testGetQuoteCharacter_MySQL() {
        CSVFormat format = CSVFormat.MYSQL;
        assertNull(format.getQuoteCharacter());
    }

    @Test
    public void testGetQuoteCharacter_Oracle() {
        CSVFormat format = CSVFormat.ORACLE;
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
    }

    @Test
    public void testGetQuoteCharacter_PostgreSQLCsv() {
        CSVFormat format = CSVFormat.POSTGRESQL_CSV;
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
    }

    @Test
    public void testGetQuoteCharacter_PostgreSQLText() {
        CSVFormat format = CSVFormat.POSTGRESQL_TEXT;
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
    }

    @Test
    public void testGetQuoteCharacter_Rfc4180() {
        CSVFormat format = CSVFormat.RFC4180;
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
    }

    @Test
    public void testGetQuoteCharacter_Tdf() {
        CSVFormat format = CSVFormat.TDF;
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
    }
}
