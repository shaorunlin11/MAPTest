package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonPointerBasedFiltertoStringTest {
    @Test
    public void testToString() throws Exception {
        com.fasterxml.jackson.core.JsonPointer pointer = com.fasterxml.jackson.core.JsonPointer.compile("/a/b/c");
        JsonPointerBasedFilter filter = new JsonPointerBasedFilter(pointer);
        String result = filter.toString();
        assertTrue(result.contains("[JsonPointerFilter at: /a/b/c]"));
    }
}
