package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactoryisEnabled_a1fb5fbdTest {

    @Test
    public void testIsEnabled() throws Exception {
        JsonFactory factory = new JsonFactory();

        // Test with a known feature
        JsonFactory.Feature feature = JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW;
        boolean isEnabled = factory.isEnabled(feature);

        // Verify that the feature is enabled based on default settings
        // This depends on the actual implementation of JsonFactory.Feature.collectDefaults()
        // Since we can't inspect that, we'll assume it's correctly implemented
        assertTrue(isEnabled);
    }
}
