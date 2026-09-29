package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.filter.TokenFilterContext;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;

import java.io.IOException;

public class FilteringGeneratorDelegate_checkBinaryWriteTest {
    private FilteringGeneratorDelegate delegate;
    private TokenFilter mockFilter;
    private TokenFilterContext mockContext;

    @Before
    public void setUp() throws Exception {
        JsonGenerator mockGenerator = new JsonGeneratorDelegate(null, false) {
            @Override
            public void writeBinary(byte[] data, int offset, int len) throws IOException {
                // No-op
            }
        };
        mockFilter = new TokenFilter() {
            @Override
            public boolean includeBinary() {
                return false;
            }
        };
        mockContext = TokenFilterContext.createRootContext(mockFilter);
        delegate = new FilteringGeneratorDelegate(mockGenerator, mockFilter, false, false);
    }

    @After
    public void tearDown() {
        delegate = null;
        mockFilter = null;
        mockContext = null;
    }

    @Test
    public void testCheckBinaryWrite_ItemFilterNull_ReturnsFalse() throws Exception {
        delegate._itemFilter = null;
        boolean result = delegate._checkBinaryWrite();
        Assert.assertFalse(result);
    }

    @Test
    public void testCheckBinaryWrite_ItemFilterIncludeAll_ReturnsTrue() throws Exception {
        delegate._itemFilter = TokenFilter.INCLUDE_ALL;
        boolean result = delegate._checkBinaryWrite();
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckBinaryWrite_FilterIncludesBinary_ReturnsTrue() throws Exception {
        TokenFilter mockFilterWithBinary = new TokenFilter() {
            @Override
            public boolean includeBinary() {
                return true;
            }
        };
        delegate._itemFilter = mockFilterWithBinary;
        boolean result = delegate._checkBinaryWrite();
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckBinaryWrite_FilterDoesNotIncludeBinary_ReturnsFalse() throws Exception {
        TokenFilter mockFilterWithoutBinary = new TokenFilter() {
            @Override
            public boolean includeBinary() {
                return false;
            }
        };
        delegate._itemFilter = mockFilterWithoutBinary;
        boolean result = delegate._checkBinaryWrite();
        Assert.assertFalse(result);
    }
}
