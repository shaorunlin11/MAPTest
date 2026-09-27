package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonParserFeatureTest {
    @Test
    public void testCollectDefaults() {
        int defaultFeatures = JsonParser.Feature.collectDefaults();

        // Check if AUTO_CLOSE_SOURCE is enabled by default
        assertTrue("AUTO_CLOSE_SOURCE should be enabled by default", 
                   JsonParser.Feature.AUTO_CLOSE_SOURCE.enabledIn(defaultFeatures));

        // Check if ALLOW_COMMENTS is not enabled by default
        assertFalse("ALLOW_COMMENTS should not be enabled by default", 
                    JsonParser.Feature.ALLOW_COMMENTS.enabledIn(defaultFeatures));

        // Check if ALLOW_YAML_COMMENTS is not enabled by default
        assertFalse("ALLOW_YAML_COMMENTS should not be enabled by default", 
                    JsonParser.Feature.ALLOW_YAML_COMMENTS.enabledIn(defaultFeatures));

        // Check if INCLUDE_SOURCE_IN_LOCATION is enabled by default
        assertTrue("INCLUDE_SOURCE_IN_LOCATION should be enabled by default", 
                   JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION.enabledIn(defaultFeatures));
    }
}
