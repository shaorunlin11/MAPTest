package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

public class CSVPrintergetOutTest {

    @Test
    public void testGetOut() throws Exception {
        // Arrange
        StringWriter stringWriter = new StringWriter();
        CSVFormat csvFormat = CSVFormat.DEFAULT;

        // Act
        CSVPrinter csvPrinter = new CSVPrinter(stringWriter, csvFormat);
        Appendable out = csvPrinter.getOut();

        // Assert
        assertNotNull("getOut() should not return null", out);
        assertEquals("getOut() should return the same instance as passed to constructor", stringWriter, out);
    }
}
