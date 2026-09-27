package com.zappos.json.util;

import org.junit.Test;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;

public class StringsFromReaderZeroCoverageTest {
    @Test
    public void testFromReaderWithBufferSize() throws IOException {
        String input = "Hello, World!";
        int bufferSize = 10;
        Reader reader = new StringReader(input);
        String result = Strings.fromReader(reader, bufferSize);
        reader.close();
        assert result.equals(input);
    }
}
