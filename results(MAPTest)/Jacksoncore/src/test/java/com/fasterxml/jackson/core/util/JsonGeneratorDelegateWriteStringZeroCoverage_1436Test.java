package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class JsonGeneratorDelegateWriteStringZeroCoverage_1436Test {
    @Test
    public void testWriteString() throws Exception {
        // Create a real instance of JsonGenerator (using a concrete implementation)
        com.fasterxml.jackson.core.JsonGenerator mockDelegate = new com.fasterxml.jackson.core.JsonFactory().createGenerator(new java.io.ByteArrayOutputStream());

        // Create an instance of JsonGeneratorDelegate with the real delegate
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);

        // Call the method under test with non-null, non-empty text
        delegate.writeString("test");
    }
}
