package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharsToNameCanonicalizercreateRoot_8c2e5670Test {

    @Test
    public void testCreateRootWithValidSeed() {
        int seed = 12345;
        CharsToNameCanonicalizer result = CharsToNameCanonicalizer.createRoot(seed);
        assertNotNull("createRoot should return a non-null instance", result);
        assertEquals("seed should be initialized correctly", seed, getPrivateField(result, "_seed"));
    }

    @Test
    public void testCreateRootWithNegativeSeed() {
        int seed = -1;
        CharsToNameCanonicalizer result = CharsToNameCanonicalizer.createRoot(seed);
        assertNotNull("createRoot should return a non-null instance", result);
        assertEquals("seed should be initialized correctly", seed, getPrivateField(result, "_seed"));
    }

    @Test
    public void testCreateRootWithZeroSeed() {
        int seed = 0;
        CharsToNameCanonicalizer result = CharsToNameCanonicalizer.createRoot(seed);
        assertNotNull("createRoot should return a non-null instance", result);
        assertEquals("seed should be initialized correctly", seed, getPrivateField(result, "_seed"));
    }

    private int getPrivateField(CharsToNameCanonicalizer instance, String fieldName) {
        try {
            java.lang.reflect.Field field = CharsToNameCanonicalizer.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.getInt(instance);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access private field: " + fieldName, e);
        }
    }
}
