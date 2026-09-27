package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.StringWriter;
import java.io.IOException;
import java.io.Closeable;
import java.io.Flushable;
import java.lang.reflect.Field;

public class CSVPrinterprintlnTest {
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
    public void testPrintln() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Act
        csvPrinter.println();

        // Assert
        Assert.assertTrue(getNewRecordValue());
    }

    @Test
    public void testPrintlnWithHeader() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Arrange
        CSVFormat format = CSVFormat.DEFAULT.withHeader("Name", "Age");
        csvPrinter = new CSVPrinter(stringWriter, format);

        // Act
        csvPrinter.println();

        // Assert
        Assert.assertTrue(getNewRecordValue());
    }

    @Test
    public void testPrintlnWithComments() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Arrange
        CSVFormat format = CSVFormat.DEFAULT.withHeaderComments("This is a comment");
        csvPrinter = new CSVPrinter(stringWriter, format);

        // Act
        csvPrinter.println();

        // Assert
        Assert.assertTrue(getNewRecordValue());
    }

    @Test
    public void testPrintlnAfterPrintRecord() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Arrange
        csvPrinter.printRecord("John", "30");

        // Act
        csvPrinter.println();

        // Assert
        Assert.assertTrue(getNewRecordValue());
    }

    private boolean getNewRecordValue() throws IOException, NoSuchFieldException, IllegalAccessException {
        // Use reflection to access the private newRecord field
        Field field = CSVPrinter.class.getDeclaredField("newRecord");
        field.setAccessible(true);
        return (boolean) field.get(csvPrinter);
    }
}
