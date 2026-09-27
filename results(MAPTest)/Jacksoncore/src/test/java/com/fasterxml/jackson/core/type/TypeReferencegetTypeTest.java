package com.fasterxml.jackson.core.type;

import org.junit.Test;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TypeReferencegetTypeTest {

    @Test
    public void testGetTypeReturnsInitializedValue() throws Exception {
        // Create a concrete subclass of TypeReference to test
        TypeReference<String> typeRef = new TypeReference<String>() {};

        // Get the type via the method
        Type result = typeRef.getType();

        // Verify that the result is not null and matches the expected type
        assertNotNull("getType() should not return null", result);
        assertEquals("getType() should return the correct type", String.class, result);
    }
}
