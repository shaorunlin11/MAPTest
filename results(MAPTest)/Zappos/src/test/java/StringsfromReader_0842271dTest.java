package com.zappos.json.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringsfromReader_0842271dTest {
    @Test
    public void testFromReader() throws IOException {
        String input = "Hello, World!";
        Reader reader = new StringReader(input);
        String result = Strings.fromReader(reader);
        assertEquals(input, result);
    }
}
