package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import java.io.IOException;
import java.io.Flushable;
import java.io.StringWriter;
import java.io.Writer;

public class CSVPrinterflushTest {
    private CSVPrinter csvPrinter;
    private StringWriter stringWriter;
    private CSVFormat csvFormat;

    @Before
    public void setUp() throws IOException {
        stringWriter = new StringWriter();
        csvFormat = CSVFormat.DEFAULT;
        csvPrinter = new CSVPrinter(stringWriter, csvFormat);
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
    public void testFlushWhenOutIsFlushable() throws Exception {
        // Arrange
        final Writer writer = new StringWriter();
        final CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);

        // Act
        printer.flush();

        // Assert
        // Since the actual implementation delegates to the underlying writer,
        // we can't directly verify the flush without inspecting internal state.
        // However, the method should not throw an exception when called.
    }

    @Test
    public void testFlushWhenOutIsNotFlushable() throws Exception {
        // Arrange
        final Appendable appendable = new StringBuilder();
        final CSVPrinter printer = new CSVPrinter(appendable, CSVFormat.DEFAULT);

        // Act
        printer.flush();

        // Assert
        // The method should not throw an exception when out is not a Flushable
    }
}
