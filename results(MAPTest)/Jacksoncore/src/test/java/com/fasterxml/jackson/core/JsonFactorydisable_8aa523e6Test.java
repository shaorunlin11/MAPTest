package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorydisable_8aa523e6Test {
    @Test
    public void testDisableParserFeature() throws Exception {
        // Create a JsonFactory instance
        JsonFactory factory = new JsonFactory();

        // Get the original parser features value
        int originalFeatures = factory._parserFeatures;

        // Get a sample feature (using a known feature)
        JsonParser.Feature feature = JsonParser.Feature.ALLOW_COMMENTS;

        // Disable the feature
        JsonFactory result = factory.disable(feature);

        // Verify that the same instance is returned
        assertTrue(result == factory);

        // Verify that the feature bit is cleared
        int expectedFeatures = originalFeatures & ~feature.getMask();
        assertEquals(expectedFeatures, factory._parserFeatures);
    }
}
