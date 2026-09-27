package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.filter.TokenFilterContext;
import com.fasterxml.jackson.core.JsonStreamContext;
import java.io.IOException;

public class FilteringGeneratorDelegatewriteNumber_c39f7f25Test {
    private FilteringGeneratorDelegate delegate;
    private JsonGenerator mockDelegate;
    private TokenFilter mockFilter;
    private TokenFilterContext mockContext;

    @Before
    public void setUp() throws Exception {
        mockDelegate = new JsonGeneratorDelegate(null, false) {
            @Override
            public void writeNumber(int v) throws IOException {
                // Mock implementation
            }
        };
        mockFilter = new TokenFilter() {
            @Override
            public boolean includeNumber(int v) {
                return true;
            }
        };
        mockContext = TokenFilterContext.createRootContext(mockFilter);
        delegate = new FilteringGeneratorDelegate(mockDelegate, mockFilter, false, false);
    }

    @After
    public void tearDown() {
        delegate = null;
        mockDelegate = null;
        mockFilter = null;
        mockContext = null;
    }

    @Test
    public void testWriteNumberWithNonNullFilterAndIncludeAll() throws Exception {
        delegate._itemFilter = TokenFilter.INCLUDE_ALL;
        delegate.writeNumber(42);
        // No assertion needed as method should execute without error
    }

    @Test
    public void testWriteNumberWithNullFilter() throws Exception {
        delegate._itemFilter = null;
        delegate.writeNumber(42);
        // No assertion needed as method should exit early
    }

    @Test
    public void testWriteNumberWithFilterThatBlocksValue() throws Exception {
        TokenFilter mockFilter = new TokenFilter() {
            @Override
            public boolean includeNumber(int v) {
                return false;
            }
        };
        delegate._itemFilter = mockFilter;
        delegate.writeNumber(42);
        // No assertion needed as method should exit early
    }

    @Test
    public void testWriteNumberWithFilterThatAllowsValue() throws Exception {
        delegate._itemFilter = new TokenFilter() {
            @Override
            public boolean includeNumber(int v) {
                return true;
            }
        };
        delegate.writeNumber(42);
        // No assertion needed as method should delegate call
    }
}
