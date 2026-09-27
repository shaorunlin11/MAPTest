package org.apache.commons.csv;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVParserparse_a9028720Test {
    @Test
    public void testParseWithFileAndCharsetAndFormat() throws IOException {
        // Arrange
        File file = Files.createTempFile("test", ".csv").toFile();
        Charset charset = Charset.defaultCharset();
        CSVFormat format = CSVFormat.DEFAULT;

        // Act
        CSVParser parser = CSVParser.parse(file, charset, format);

        // Assert
        assertNotNull("CSVParser should not be null", parser);
    }
}
