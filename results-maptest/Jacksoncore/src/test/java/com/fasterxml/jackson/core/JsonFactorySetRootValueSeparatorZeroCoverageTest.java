package com.fasterxml.jackson.core;

import org.junit.Test;

public class JsonFactorySetRootValueSeparatorZeroCoverageTest {
    @Test
    public void testSetRootValueSeparatorWithNull() {
        JsonFactory factory = new JsonFactory();
        factory.setRootValueSeparator(null);
        // Verify that the field is set to null
        assert factory._rootValueSeparator == null;
    }

    @Test
    public void testSetRootValueSeparatorWithNonNull() {
        JsonFactory factory = new JsonFactory();
        factory.setRootValueSeparator("test");
        // Verify that the field is set to a SerializedString with "test"
        assert factory._rootValueSeparator.toString().equals("test");
    }
}
