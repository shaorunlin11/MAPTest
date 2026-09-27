package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharTypesgetInputCodeLatin1Test {
    @Test
    public void testGetInputCodeLatin1ReturnsNonNullArray() {
        int[] result = CharTypes.getInputCodeLatin1();
        assertNotNull("getInputCodeLatin1 should return a non-null array", result);
    }

    @Test
    public void testGetInputCodeLatin1ReturnsExpectedLength() {
        int[] result = CharTypes.getInputCodeLatin1();
        assertEquals("getInputCodeLatin1 should return an array of length 256", 256, result.length);
    }

    @Test
    public void testGetInputCodeLatin1ReturnsSameInstanceOnMultipleCalls() {
        int[] firstCall = CharTypes.getInputCodeLatin1();
        int[] secondCall = CharTypes.getInputCodeLatin1();
        assertSame("getInputCodeLatin1 should return the same instance on multiple calls", firstCall, secondCall);
    }
}
