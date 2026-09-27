package com.fasterxml.jackson.core;

import org.junit.Test;

import com.fasterxml.jackson.core.io.SerializedString;


public class JsonFactoryBuilderRootValueSeparatorZeroCoverage_119Test {
    @Test
    public void testRootValueSeparator() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        SerializableString separator = new SerializedString(",");
        builder.rootValueSeparator(separator);
    }
}
