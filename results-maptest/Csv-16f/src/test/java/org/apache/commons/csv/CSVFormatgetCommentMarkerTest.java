package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatgetCommentMarkerTest {

    @Test
    public void testGetCommentMarker_Default() {
        CSVFormat format = CSVFormat.DEFAULT;
        assertNull(format.getCommentMarker());
    }

    @Test
    public void testGetCommentMarker_EXCEL() {
        CSVFormat format = CSVFormat.EXCEL;
        assertNull(format.getCommentMarker());
    }

    @Test
    public void testGetCommentMarker_INFORMIX_UNLOAD() {
        CSVFormat format = CSVFormat.INFORMIX_UNLOAD;
        assertNull(format.getCommentMarker());
    }

    @Test
    public void testGetCommentMarker_INFORMIX_UNLOAD_CSV() {
        CSVFormat format = CSVFormat.INFORMIX_UNLOAD_CSV;
        assertNull(format.getCommentMarker());
    }

    @Test
    public void testGetCommentMarker_MYSQL() {
        CSVFormat format = CSVFormat.MYSQL;
        assertNull(format.getCommentMarker());
    }

    @Test
    public void testGetCommentMarker_ORACLE() {
        CSVFormat format = CSVFormat.ORACLE;
        assertNull(format.getCommentMarker());
    }

    @Test
    public void testGetCommentMarker_POSTGRESQL_CSV() {
        CSVFormat format = CSVFormat.POSTGRESQL_CSV;
        assertNull(format.getCommentMarker());
    }

    @Test
    public void testGetCommentMarker_POSTGRESQL_TEXT() {
        CSVFormat format = CSVFormat.POSTGRESQL_TEXT;
        assertNull(format.getCommentMarker());
    }

    @Test
    public void testGetCommentMarker_RFC4180() {
        CSVFormat format = CSVFormat.RFC4180;
        assertNull(format.getCommentMarker());
    }

    @Test
    public void testGetCommentMarker_TDF() {
        CSVFormat format = CSVFormat.TDF;
        assertNull(format.getCommentMarker());
    }
}
