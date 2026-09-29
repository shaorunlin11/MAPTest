package com.fasterxml.jackson.core;

import org.junit.Test;

public class JsonFactoryCreateParserZeroCoverage_87Test {
    @Test
    public void testCreateParserWithNullInputDecorator() throws Exception {
        JsonFactory factory = new JsonFactory();
        char[] content = {'{', '}', '"', 'k', 'e', 'y', '"', ':', '"', 'v', 'a', 'l', 'u', 'e', '"'};
        int offset = 0;
        int len = content.length;

        JsonParser parser = factory.createParser(content, offset, len);
    }
}
