package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class ByteQuadsCanonicalizercreateRoot_8a8462eeTest {

    @Test
    public void testCreateRoot() {
        ByteQuadsCanonicalizer result = ByteQuadsCanonicalizer.createRoot();
        assertNotNull("createRoot should return a non-null instance", result);

        try {
            Field seedField = ByteQuadsCanonicalizer.class.getDeclaredField("_seed");
            seedField.setAccessible(true);
            Integer seed = (Integer) seedField.get(result);
            assertTrue("Seed should be odd", (seed & 1) == 1);
        } catch (Exception e) {
            fail("Failed to access _seed field: " + e.getMessage());
        }
    }
}
