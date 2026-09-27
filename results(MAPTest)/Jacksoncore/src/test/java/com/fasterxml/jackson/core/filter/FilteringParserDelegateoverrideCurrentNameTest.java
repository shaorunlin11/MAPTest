package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;

public class FilteringParserDelegateoverrideCurrentNameTest {

    @Test
    public void testOverrideCurrentNameThrowsUnsupportedOperationException() {
        FilteringParserDelegate delegate = new FilteringParserDelegate(null, null, false, false);
        try {
            delegate.overrideCurrentName("test");
            Assert.fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            Assert.assertEquals("Can not currently override name during filtering read", e.getMessage());
        }
    }
}
