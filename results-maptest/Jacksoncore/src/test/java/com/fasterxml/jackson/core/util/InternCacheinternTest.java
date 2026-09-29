package com.fasterxml.jackson.core.util;
import org.junit.Test;
import static org.junit.Assert.*;
public class InternCacheinternTest {
    @Test
    public void testIntern_ReturnsCachedValueIfPresent() {
        InternCache cache = InternCache.instance;
        String input = "test";
        cache.put(input, input);
        assertEquals(input, cache.intern(input));
    }

    @Test
    public void testIntern_InternsNewValueAndStoresInCache() {
        InternCache cache = InternCache.instance;
        String input = "newString";
        String result = cache.intern(input);
        assertEquals(input, result);
        assertEquals(input, cache.get(input));
    }


    @Test
    public void testIntern_UsesStringIntern() {
        InternCache cache = InternCache.instance;
        String input = "unique";
        String result = cache.intern(input);
        assertTrue(result == input.intern());
    }
}
