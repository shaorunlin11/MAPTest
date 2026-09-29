package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharTypescopyHexBytesTest {
    @Test
    public void testCopyHexBytesReturnsClonedArray() {
        byte[] copy = CharTypes.copyHexBytes();

        byte[] original = CharTypes.copyHexBytes();
        byte[] copy2 = CharTypes.copyHexBytes();

        assertNotSame("Returned array should be a different instance", original, copy);
        assertArrayEquals("Returned array should have the same contents as HB", original, copy);
    }
}
