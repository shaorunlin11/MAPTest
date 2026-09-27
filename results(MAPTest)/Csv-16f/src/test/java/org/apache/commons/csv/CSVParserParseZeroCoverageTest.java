package org.apache.commons.csv;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class CSVParserParseZeroCoverageTest {
    @Test
    public void testParse() throws IOException {
        Path path = Files.createTempFile("test", ".csv");
        Charset charset = Charset.defaultCharset();
        CSVFormat format = CSVFormat.DEFAULT;
        CSVParser.parse(path, charset, format);
    }
}
