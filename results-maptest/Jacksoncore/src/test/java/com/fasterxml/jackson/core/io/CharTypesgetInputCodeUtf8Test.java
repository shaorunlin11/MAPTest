package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharTypesgetInputCodeUtf8Test {
    @Test
    public void testGetInputCodeUtf8() {
        int[] result = CharTypes.getInputCodeUtf8();
        assertNotNull("The returned array should not be null", result);
        // Additional assertions can be added if the expected content of sInputCodesUTF8 is known
    }
}
