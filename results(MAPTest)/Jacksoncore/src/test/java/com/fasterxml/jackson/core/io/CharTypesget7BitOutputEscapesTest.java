package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharTypesget7BitOutputEscapesTest {
    @Test
    public void testGet7BitOutputEscapesReturnsNonEmptyArray() throws Exception {
        int[] result = CharTypes.get7BitOutputEscapes();
        assertNotNull("The returned array should not be null", result);
        assertTrue("The returned array should not be empty", result.length > 0);
    }

    @Test
    public void testGet7BitOutputEscapesReturnsSameInstance() throws Exception {
        int[] firstCall = CharTypes.get7BitOutputEscapes();
        int[] secondCall = CharTypes.get7BitOutputEscapes();
        assertSame("The method should return the same instance on multiple calls", firstCall, secondCall);
    }
}
