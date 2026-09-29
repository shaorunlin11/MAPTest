package com.fasterxml.jackson.core;

import org.junit.Test;
import java.io.Reader;
import java.io.StringReader;

public class JsonFactoryCreateParserZeroCoverage_82Test {
    @Test
    public void testCreateParserWithNonNullReader() throws Exception {
        JsonFactory factory = new JsonFactory();
        Reader reader = new StringReader("{}");
        JsonParser parser = factory.createParser(reader);
        // Additional assertions can be added if needed
    }
}
