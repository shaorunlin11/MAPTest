package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetIgnoreEmptyLinesTest {

    @Test
    public void testGetIgnoreEmptyLines_Default() {
        CSVFormat format = CSVFormat.DEFAULT;
        assertTrue(format.getIgnoreEmptyLines());
    }

    @Test
    public void testGetIgnoreEmptyLines_EXCEL() {
        CSVFormat format = CSVFormat.EXCEL;
        assertFalse(format.getIgnoreEmptyLines());
    }

    @Test
    public void testGetIgnoreEmptyLines_MYSQL() {
        CSVFormat format = CSVFormat.MYSQL;
        assertFalse(format.getIgnoreEmptyLines());
    }

    @Test
    public void testGetIgnoreEmptyLines_ORACLE() {
        CSVFormat format = CSVFormat.ORACLE;
        assertFalse(format.getIgnoreEmptyLines());
    }

    @Test
    public void testGetIgnoreEmptyLines_POSTGRESQL_CSV() {
        CSVFormat format = CSVFormat.POSTGRESQL_CSV;
        assertFalse(format.getIgnoreEmptyLines());
    }

    @Test
    public void testGetIgnoreEmptyLines_POSTGRESQL_TEXT() {
        CSVFormat format = CSVFormat.POSTGRESQL_TEXT;
        assertFalse(format.getIgnoreEmptyLines());
    }

    @Test
    public void testGetIgnoreEmptyLines_RFC4180() {
        CSVFormat format = CSVFormat.RFC4180;
        assertFalse(format.getIgnoreEmptyLines());
    }

    @Test
    public void testGetIgnoreEmptyLines_TDF() {
        CSVFormat format = CSVFormat.TDF;
        assertTrue(format.getIgnoreEmptyLines());
    }
}
