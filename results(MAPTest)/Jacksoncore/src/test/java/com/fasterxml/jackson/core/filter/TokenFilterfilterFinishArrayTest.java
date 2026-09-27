package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class TokenFilterfilterFinishArrayTest {
    private TokenFilter tokenFilter;

    @Before
    public void setUp() {
        tokenFilter = new TokenFilter();
    }

    @After
    public void tearDown() {
        tokenFilter = null;
    }

    @Test
    public void testFilterFinishArray() {
        // Since the method is empty, we can only verify that it does not throw an exception
        try {
            tokenFilter.filterFinishArray();
        } catch (Exception e) {
            Assert.fail("filterFinishArray() should not throw an exception");
        }
    }
}
