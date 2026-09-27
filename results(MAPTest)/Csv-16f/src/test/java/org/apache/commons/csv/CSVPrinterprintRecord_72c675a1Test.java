package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.StringWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

public class CSVPrinterprintRecord_72c675a1Test {
    private CSVPrinter csvPrinter;
    private StringWriter stringWriter;

    @Before
    public void setUp() throws IOException {
        stringWriter = new StringWriter();
        CSVFormat format = CSVFormat.DEFAULT;
        csvPrinter = new CSVPrinter(stringWriter, format);
    }

    @After
    public void tearDown() throws IOException {
        if (csvPrinter != null) {
            csvPrinter.close();
        }
        if (stringWriter != null) {
            stringWriter.close();
        }
    }

    @Test
    public void testPrintRecordWithSingleValue() throws IOException {
        csvPrinter.printRecord("test");
        csvPrinter.flush();

        Assert.assertEquals("test" + CSVFormat.DEFAULT.getRecordSeparator(), stringWriter.toString());
    }

    @Test
    public void testPrintRecordWithMultipleValues() throws IOException {
        csvPrinter.printRecord("a", "b", "c");
        csvPrinter.flush();

        Assert.assertEquals("a" + CSVFormat.DEFAULT.getDelimiter() + "b" + CSVFormat.DEFAULT.getDelimiter() + "c" + CSVFormat.DEFAULT.getRecordSeparator(), stringWriter.toString());
    }

    @Test
    public void testPrintRecordWithEmptyValues() throws IOException {
        csvPrinter.printRecord();
        csvPrinter.flush();

        Assert.assertEquals(CSVFormat.DEFAULT.getRecordSeparator(), stringWriter.toString());
    }

    @Test
    public void testPrintRecordWithNullValues() throws IOException {
        csvPrinter.printRecord(new Object[0]);
        csvPrinter.flush();

        Assert.assertEquals(CSVFormat.DEFAULT.getRecordSeparator(), stringWriter.toString());
    }

    @Test
    public void testNewRecordFlagIsSetToTrueAfterPrintRecord() throws IOException, NoSuchFieldException, IllegalAccessException {
        csvPrinter.printRecord("test");
        csvPrinter.flush();

        Field newRecordField = CSVPrinter.class.getDeclaredField("newRecord");
        newRecordField.setAccessible(true);
        boolean newRecord = (boolean) newRecordField.get(csvPrinter);
        Assert.assertTrue(newRecord);
    }
}
