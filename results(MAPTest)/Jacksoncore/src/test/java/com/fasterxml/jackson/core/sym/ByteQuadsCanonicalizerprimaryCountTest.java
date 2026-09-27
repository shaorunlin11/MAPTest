package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;


public class ByteQuadsCanonicalizerprimaryCountTest {
    private ByteQuadsCanonicalizer canonicalizer;

    @Before
    public void setUp() throws Exception {
        // Create a ByteQuadsCanonicalizer instance with default parameters
        Constructor<ByteQuadsCanonicalizer> constructor = ByteQuadsCanonicalizer.class.getDeclaredConstructor(int.class, boolean.class, int.class, boolean.class);
        constructor.setAccessible(true);
        canonicalizer = constructor.newInstance(64, false, 0, false);
    }

    @Test
    public void testPrimaryCount() throws Exception {
        // Get the _hashArea field
        Field hashAreaField = ByteQuadsCanonicalizer.class.getDeclaredField("_hashArea");
        hashAreaField.setAccessible(true);

        // Get the _secondaryStart field
        Field secondaryStartField = ByteQuadsCanonicalizer.class.getDeclaredField("_secondaryStart");
        secondaryStartField.setAccessible(true);

        // Initialize _hashArea with some values
        int[] hashArea = new int[100];
        for (int i = 0; i < hashArea.length; i++) {
            hashArea[i] = 0;
        }

        // Set non-zero values at positions 3, 7, 11, etc.
        hashArea[3] = 1;
        hashArea[7] = 2;
        hashArea[11] = 3;
        hashArea[15] = 4;
        hashArea[19] = 5;

        // Set _hashArea and _secondaryStart
        hashAreaField.set(canonicalizer, hashArea);
        secondaryStartField.set(canonicalizer, 20);

        // Call primaryCount and verify the result
        int result = canonicalizer.primaryCount();
        assertEquals(5, result);
    }
}
