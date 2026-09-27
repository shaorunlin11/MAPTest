package com.fasterxml.jackson.core;

import org.junit.Test;

import com.fasterxml.jackson.core.json.JsonWriteFeature;

public class JsonFactoryBuilderDisableZeroCoverage_115Test {
    @Test
    public void testDisableWithOtherFeatures() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonWriteFeature feature1 = JsonWriteFeature.QUOTE_FIELD_NAMES;
        JsonWriteFeature feature2 = JsonWriteFeature.ESCAPE_NON_ASCII;

        // Ensure that the features are initialized with valid values
        // This is required for mappedFeature() to return a valid value
        builder.disable(feature1, feature2);
    }
}
