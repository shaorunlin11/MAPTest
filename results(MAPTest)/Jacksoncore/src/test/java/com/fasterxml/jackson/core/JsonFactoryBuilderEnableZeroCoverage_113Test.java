package com.fasterxml.jackson.core;

import org.junit.Test;

import com.fasterxml.jackson.core.json.JsonWriteFeature;

public class JsonFactoryBuilderEnableZeroCoverage_113Test {
    @Test
    public void testEnableWithNonNullFeatures() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonWriteFeature first = JsonWriteFeature.QUOTE_FIELD_NAMES;
        JsonWriteFeature other = JsonWriteFeature.ESCAPE_NON_ASCII;
        builder.enable(first, other);
    }
}
