package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.filter.TokenFilterContext;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.core.JsonFactory;
import java.io.StringWriter;

import java.io.StringWriter;

public class FilteringGeneratorDelegatewriteString_2d8bdefcTest {
    private FilteringGeneratorDelegate delegate;
    private JsonGenerator mockDelegate;
    private TokenFilter mockFilter;
    private TokenFilterContext mockContext;

    @Before
    public void setUp() throws Exception {
        JsonFactory factory = new JsonFactory();
        mockDelegate = new JsonGeneratorDelegate(factory.createGenerator(new StringWriter()), false) {
            @Override
            public void writeString(char[] text, int offset, int len) throws IOException {
                // Mock implementation
            }
        };
        mockFilter = new TokenFilter() {
            @Override
            public boolean includeString(String value) {
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
    public void testWriteStringWithNullItemFilter() throws IOException {
        delegate._itemFilter = null;
        char[] text = "test".toCharArray();
        delegate.writeString(text, 0, text.length);
        // No exception expected, method should return early
    }

    @Test
    public void testWriteStringWithIncludeAllFilter() throws IOException {
        delegate._itemFilter = TokenFilter.INCLUDE_ALL;
        char[] text = "test".toCharArray();
        delegate.writeString(text, 0, text.length);
        // Should delegate without filtering
    }

    @Test
    public void testWriteStringWithFilteredValue() throws IOException {
        delegate._itemFilter = new TokenFilter() {
            @Override
            public boolean includeString(String value) {
                return value.equals("test");
            }
        };
        char[] text = "test".toCharArray();
        delegate.writeString(text, 0, text.length);
        // Should delegate after filtering
    }

    @Test
    public void testWriteStringWithExcludedValue() throws IOException {
        delegate._itemFilter = new TokenFilter() {
            @Override
            public boolean includeString(String value) {
                return false;
            }
        };
        char[] text = "test".toCharArray();
        delegate.writeString(text, 0, text.length);
        // Should not delegate
    }
}
