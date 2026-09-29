package com.fasterxml.jackson.core;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class JsonFactoryCreateParserZeroCoverage_80Test {
    @Test
    public void testCreateParserWithUrl() throws IOException {
        JsonFactory factory = new JsonFactory();
        URL url = new URL("http://example.com");
        JsonParser parser = factory.createParser(url);
        // This test is designed to reach line 908 of the createParser method by ensuring that _createContext is called with the provided URL and true.
        // The actual execution of line 908 is verified through the successful creation of the parser.
    }
}
