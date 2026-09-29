package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorygetGeneratorFeaturesTest {
    @Test
    public void testGetGeneratorFeatures() throws Exception {
        JsonFactory factory = new JsonFactory();
        int expected = JsonGenerator.Feature.collectDefaults();
        assertEquals("Default generator features should match collected defaults", expected, factory.getGeneratorFeatures());

        // Test with custom features
        JsonFactory customFactory = new JsonFactory();
        customFactory._generatorFeatures = 0x12345678;
        assertEquals("Custom generator features should be returned correctly", 0x12345678, customFactory.getGeneratorFeatures());
    }
}
