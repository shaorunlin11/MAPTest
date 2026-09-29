package org.apache.commons.csv;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CSVParserparse_9a8487cdTest {

    @Test
    public void testParseWithValidParameters() throws Exception {
        // Arrange
        String input = "header1,header2\nvalue1,value2";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Charset charset = Charset.defaultCharset();
        CSVFormat format = CSVFormat.DEFAULT;

        // Act
        CSVParser parser = CSVParser.parse(inputStream, charset, format);

        // Assert
        assertNotNull(parser);
    }
}
