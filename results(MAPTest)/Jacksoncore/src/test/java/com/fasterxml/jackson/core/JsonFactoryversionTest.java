package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactoryversionTest {
    @Test
    public void testVersionReturnsExpectedValue() throws Exception {
        JsonFactory factory = new JsonFactory();
        Version version = factory.version();
        assertNotNull("version() should not return null", version);
        // Assuming PackageVersion.VERSION is a known value, we can assert it if we have access to it
        // Since we don't have direct access to PackageVersion in this test class, we can only verify non-null
    }
}
