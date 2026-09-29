package org.apache.commons.csv;

import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.charset.Charset;
import org.junit.Test;

import java.io.File;


public class CSVFormatprint_a9b3fd4dTest {

    @Test
    public void testPrint() throws IOException {
        // Arrange
        CSVFormat csvFormat = CSVFormat.DEFAULT;
        Path out = new java.io.File("testfile.csv").toPath();
        Charset charset = Charset.defaultCharset();

        // Act
        CSVPrinter printer = csvFormat.print(out, charset);

        // Assert
        assertNotNull(printer);
    }
}
