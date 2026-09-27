package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class FormatFeaturegetMaskTest {
    @Test
    public void testGetMask() throws Exception {
        // Since FormatFeature is an interface and getMask() is a declared method,
        // we need to create a concrete implementation for testing
        final FormatFeature feature = new FormatFeature() {
            private final int mask = 0x1;

            @Override
            public int getMask() {
                return mask;
            }

            @Override
            public boolean enabledIn(int flags) {
                return false; // Provide a default implementation for the missing method
            }

            @Override
            public boolean enabledByDefault() {
                return false; // Implement the missing method
            }
        };

        assertEquals("getMask should return the expected mask value", 0x1, feature.getMask());
    }
}
