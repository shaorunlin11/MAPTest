package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class JsonFactoryenable_cbcbaa97Test {
    @Test
    public void testEnable() throws Exception {
        // Create a JsonFactory instance
        JsonFactory factory = new JsonFactory();

        // Get the _parserFeatures field using reflection
        Field parserFeaturesField = JsonFactory.class.getDeclaredField("_parserFeatures");
        parserFeaturesField.setAccessible(true);

        // Get the current value of _parserFeatures
        int originalValue = parserFeaturesField.getInt(factory);

        // Enable a feature
        JsonParser.Feature feature = JsonParser.Feature.ALLOW_COMMENTS;
        factory.enable(feature);

        // Verify that the feature was enabled
        int newValue = parserFeaturesField.getInt(factory);
        assertTrue("Feature should be enabled", ((int) newValue & feature.getMask()) != 0);

        // Verify that the method returns the same instance
        assertSame("Method should return the same instance", factory, factory.enable(feature));
    }
}
