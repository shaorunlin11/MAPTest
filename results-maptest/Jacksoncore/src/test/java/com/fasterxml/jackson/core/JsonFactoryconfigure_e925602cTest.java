package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactoryconfigure_e925602cTest {

    @Test
    public void testConfigure_EnableFeature() throws Exception {
        JsonFactory factory = new JsonFactory();
        JsonParser.Feature feature = JsonParser.Feature.ALLOW_COMMENTS;
        JsonFactory result = factory.configure(feature, true);

        // Verify that configure returns a non-null instance
        assertNotNull("configure() should return a non-null JsonFactory instance", result);
    }

    @Test
    public void testConfigure_DisableFeature() throws Exception {
        JsonFactory factory = new JsonFactory();
        JsonParser.Feature feature = JsonParser.Feature.ALLOW_COMMENTS;
        JsonFactory result = factory.configure(feature, false);

        // Verify that configure returns a non-null instance
        assertNotNull("configure() should return a non-null JsonFactory instance", result);
    }
}
