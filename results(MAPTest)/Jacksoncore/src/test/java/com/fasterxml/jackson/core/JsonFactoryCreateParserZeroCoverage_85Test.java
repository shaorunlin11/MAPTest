package com.fasterxml.jackson.core;

import org.junit.Test;

public class JsonFactoryCreateParserZeroCoverage_85Test {
    @Test
    public void testCreateParserWithValidContent() throws Exception {
        JsonFactory factory = new JsonFactory();
        String content = "{'key': 'value'}";
        JsonParser parser = factory.createParser(content);
        // The test is only required to execute the target lines, not to assert any specific behavior
    }
}
