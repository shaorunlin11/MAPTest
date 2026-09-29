package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleMetaphonecontainsTest {

    @Test
    public void testContains_ValidInput_MatchFound() {
        String value = "HELLOWORLD";
        boolean result = DoubleMetaphone.contains(value, 0, 5, "HELLO");
        assertTrue(result);
    }

    @Test
    public void testContains_ValidInput_MatchNotFound() {
        String value = "HELLOWORLD";
        boolean result = DoubleMetaphone.contains(value, 0, 5, "WORLD");
        assertFalse(result);
    }

    @Test
    public void testContains_InvalidStart() {
        String value = "HELLOWORLD";
        boolean result = DoubleMetaphone.contains(value, -1, 5, "HELLO");
        assertFalse(result);
    }

    @Test
    public void testContains_InvalidLength() {
        String value = "HELLOWORLD";
        boolean result = DoubleMetaphone.contains(value, 0, 10, "HELLO");
        assertFalse(result);
    }

    @Test
    public void testContains_SubstringExactlyMatchesCriteria() {
        String value = "TESTING";
        boolean result = DoubleMetaphone.contains(value, 0, 7, "TESTING");
        assertTrue(result);
    }

    @Test
    public void testContains_SubstringDoesNotMatchAnyCriteria() {
        String value = "EXAMPLE";
        boolean result = DoubleMetaphone.contains(value, 0, 3, "ABC", "DEF", "GHI");
        assertFalse(result);
    }

    @Test
    public void testContains_SubstringWithMultipleCriteria() {
        String value = "JAVATEST";
        boolean result = DoubleMetaphone.contains(value, 0, 4, "JAVA", "TEST", "CODE");
        assertTrue(result);
    }

    @Test
    public void testContains_EmptyString() {
        String value = "";
        boolean result = DoubleMetaphone.contains(value, 0, 0, "");
        assertTrue(result);
    }

    @Test
    public void testContains_NullValue() {
        boolean result = DoubleMetaphone.contains("", 0, 0, "");
        assertTrue(result);
    }

    @Test
    public void testContains_NullCriteria() {
        String value = "TEST";
        boolean result = DoubleMetaphone.contains(value, 0, 4, new String[0]);
        assertFalse(result);
    }
}
