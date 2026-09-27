package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.filter.TokenFilterContext;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;

import java.io.IOException;

public class FilteringGeneratorDelegate_checkRawValueWriteTest {
    private FilteringGeneratorDelegate delegate;
    private TokenFilter mockFilter;
    private TokenFilterContext mockContext;

    @Before
    public void setUp() throws Exception {
        mockFilter = new TokenFilter() {
            @Override
            public boolean includeRawValue() {
                return false;
            }
        };
        mockContext = TokenFilterContext.createRootContext(mockFilter);
        delegate = new FilteringGeneratorDelegate(new JsonGeneratorDelegate(null, false), mockFilter, false, false);
    }

    @Test
    public void testCheckRawValueWrite_ItemFilterNull_ReturnsFalse() throws Exception {
        delegate._itemFilter = null;
        boolean result = delegate._checkRawValueWrite();
        Assert.assertFalse(result);
    }

    @Test
    public void testCheckRawValueWrite_ItemFilterIncludeAll_ReturnsTrue() throws Exception {
        delegate._itemFilter = TokenFilter.INCLUDE_ALL;
        boolean result = delegate._checkRawValueWrite();
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckRawValueWrite_IncludeRawValueTrue_CallsCheckParentPath() throws Exception {
        final boolean[] called = {false};
        // Use a mock object to verify method call
        delegate = new FilteringGeneratorDelegate(new JsonGeneratorDelegate(null, false), new TokenFilter() {
            @Override
            public boolean includeRawValue() {
                return true;
            }
        }, false, false) {
            @Override
            protected void _checkParentPath() throws IOException {
                called[0] = true;
            }
        };

        boolean result = delegate._checkRawValueWrite();
        Assert.assertTrue(result);
        Assert.assertTrue(called[0]);
    }

    @Test
    public void testCheckRawValueWrite_IncludeRawValueFalse_ReturnsFalse() throws Exception {
        delegate._itemFilter = new TokenFilter() {
            @Override
            public boolean includeRawValue() {
                return false;
            }
        };
        boolean result = delegate._checkRawValueWrite();
        Assert.assertFalse(result);
    }
}
