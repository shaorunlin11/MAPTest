package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonFactory.Feature;

public class JsonFactoryFeatureTest {

    @Test
    public void testFeatureCollectDefaults() {
        int flags = Feature.collectDefaults();

        // Check that each enum value's default state is correctly reflected in the bitmask
        for (Feature feature : Feature.values()) {
            boolean isEnabledByDefault = feature.enabledByDefault();
            int mask = feature.getMask();
            if (isEnabledByDefault) {
                assertTrue("Feature " + feature + " should be enabled by default", (flags & mask) != 0);
            } else {
                assertFalse("Feature " + feature + " should not be enabled by default", (flags & mask) != 0);
            }
        }
    }
}
