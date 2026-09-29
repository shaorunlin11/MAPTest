package com.fasterxml.jackson.core.io;

import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CharacterEscapesstandardAsciiEscapesForJSONTest {
    @Test
    public void testStandardAsciiEscapesForJSON() {
        int[] result = CharacterEscapes.standardAsciiEscapesForJSON();
        assertNotNull("Result array should not be null", result);

        // Verify that the returned array is a copy (not the same instance as the original)
        int[] original = CharTypes.get7BitOutputEscapes();
        assertNotSame("Returned array should be a copy, not the same instance", result, original);

        // Verify that the contents of the arrays are equal
        assertTrue("Returned array should have the same elements as the original", Arrays.equals(result, original));
    }
}
