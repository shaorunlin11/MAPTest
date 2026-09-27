package com.fasterxml.jackson.core.util;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JsonGeneratorDelegateWriteRawZeroCoverageTest {
    @Test
    public void testWriteRaw() throws Exception {
        // Create a real instance of JsonGenerator (using a concrete implementation)
        JsonGenerator mockDelegate = new JsonFactory().createGenerator(new ByteArrayOutputStream());

        // Create an instance of JsonGeneratorDelegate with the mock delegate
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);

        // Call the method under test
        delegate.writeRaw("test text");
    }
}
