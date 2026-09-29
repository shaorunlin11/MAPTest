package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharTypesgetInputCodeLatin1JsNamesTest {
    @Test
    public void testGetInputCodeLatin1JsNames() {
        int[] result = CharTypes.getInputCodeLatin1JsNames();
        assertNotNull("The returned array should not be null", result);
        // Since the actual content of sInputCodesJsNames is not visible in the source,
        // we can only verify that it's a non-null array.
    }
}
