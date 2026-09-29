package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.filter.TokenFilterContext;

public class FilteringGeneratorDelegatewriteStartArray_683abddcTest {
    private FilteringGeneratorDelegate delegate;
    private JsonGenerator mockDelegate;
    private TokenFilterContext mockFilterContext;
    private TokenFilter mockItemFilter;
    private TokenFilter mockRootFilter;

    @Before
    public void setUp() throws Exception {
        mockDelegate = new JsonGeneratorDelegate(null, false) {
            @Override
            public void writeStartArray() throws IOException {
                // No-op for testing
            }

            @Override
            public void writeStartArray(int size) throws IOException {
                // No-op for testing
            }
        };
        mockFilterContext = new TokenFilterContext(0, null, null, false) {
            @Override
            public TokenFilterContext createChildArrayContext(TokenFilter filter, boolean writeStart) {
                return this;
            }

            @Override
            public TokenFilter checkValue(TokenFilter filter) {
                return filter;
            }
        };
        mockItemFilter = new TokenFilter() {
            @Override
            public TokenFilter filterStartArray() {
                return this;
            }
        };
        mockRootFilter = new TokenFilter() {
            @Override
            public TokenFilter filterStartArray() {
                return this;
            }
        };
        delegate = new FilteringGeneratorDelegate(mockDelegate, mockRootFilter, false, false);
    }

    @After
    public void tearDown() {
        delegate = null;
        mockDelegate = null;
        mockFilterContext = null;
        mockItemFilter = null;
        mockRootFilter = null;
    }

    @Test
    public void testWriteStartArray_ItemFilterIsNull() throws Exception {
        delegate._itemFilter = null;
        delegate._filterContext = mockFilterContext;

        delegate.writeStartArray();

        Assert.assertNull(delegate._itemFilter);
        Assert.assertNotNull(delegate._filterContext);
    }

    @Test
    public void testWriteStartArray_ItemFilterIsIncludeAll() throws Exception {
        final TokenFilter includeAll = TokenFilter.INCLUDE_ALL;
        delegate._itemFilter = includeAll;
        delegate._filterContext = mockFilterContext;

        delegate.writeStartArray();

        Assert.assertEquals(includeAll, delegate._itemFilter);
        Assert.assertNotNull(delegate._filterContext);
    }

    @Test
    public void testWriteStartArray_ItemFilterNotIncludeAll() throws Exception {
        final TokenFilter includeAll = TokenFilter.INCLUDE_ALL;
        delegate._itemFilter = new TokenFilter() {
            @Override
            public TokenFilter filterStartArray() {
                return includeAll;
            }
        };
        delegate._filterContext = mockFilterContext;

        delegate.writeStartArray();

        Assert.assertEquals(includeAll, delegate._itemFilter);
        Assert.assertNotNull(delegate._filterContext);
    }
}
