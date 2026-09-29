package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class JsonFactorycopyTest {
    @Test
    public void testCopy() throws Exception {
        JsonFactory original = new JsonFactory();
        JsonFactory copy = original.copy();

        assertNotSame("Copy should be a different instance", original, copy);

        // Verify that the copy constructor was called with the original and null codec
        Field srcField = JsonFactory.class.getDeclaredField("_objectCodec");
        srcField.setAccessible(true);
        Object codec = srcField.get(copy);
        assertNull("Copy constructor should have been called with null ObjectCodec", codec);
    }
}
