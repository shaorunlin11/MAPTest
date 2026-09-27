package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class FormatFeatureenabledInTest {
    @Test
    public void testEnabledIn() throws Exception {
        // Since FormatFeature is an interface and the method is abstract,
        // we need to create a mock implementation to test it
        FormatFeature mockFeature = new FormatFeature() {
            @Override
            public boolean enabledIn(int flags) {
                // Example implementation: check if a specific flag is set
                return (flags & 0x01) != 0;
            }

            @Override
            public int getMask() {
                // Implementing the missing method from the interface
                return 0;
            }

            @Override
            public boolean enabledByDefault() {
                // Implementing the missing method from the interface
                return false;
            }
        };

        // Test case 1: Flag with bit 0 set
        Assert.assertTrue(mockFeature.enabledIn(0x01));

        // Test case 2: Flag with bit 0 not set
        Assert.assertFalse(mockFeature.enabledIn(0x00));
    }
}
