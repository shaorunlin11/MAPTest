package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.filter.TokenFilterContext;
import java.io.IOException;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FilteringGeneratorDelegatewriteNumber_a4fd0b82Test {
    private FilteringGeneratorDelegate delegate;
    private JsonGenerator mockDelegate;
    private TokenFilter mockFilter;
    private TokenFilterContext mockFilterContext;

    @Before
    public void setUp() throws Exception {
        mockDelegate = new JsonGeneratorDelegate(null, false) {
            @Override
            public void writeNumber(long v) throws IOException {
                // No-op for testing
            }
        };
        mockFilter = new TokenFilter() {
            @Override
            public boolean includeNumber(int v) {
                return true;
            }

            @Override
            public boolean includeNumber(long v) {
                return true;
            }

            @Override
            public boolean includeNumber(float v) {
                return true;
            }

            @Override
            public boolean includeNumber(double v) {
                return true;
            }

            @Override
            public boolean includeNumber(BigDecimal v) {
                return true;
            }

            @Override
            public boolean includeNumber(BigInteger v) {
                return true;
            }
        };
        mockFilterContext = TokenFilterContext.createRootContext(mockFilter);
        delegate = new FilteringGeneratorDelegate(mockDelegate, mockFilter, false, false);
    }

    @After
    public void tearDown() {
        delegate = null;
        mockDelegate = null;
        mockFilter = null;
        mockFilterContext = null;
    }

    @Test
    public void testWriteNumberWithNullItemFilter() throws Exception {
        delegate._itemFilter = null;
        delegate.writeNumber(123L);
        // No exception expected, method should return immediately
    }

    @Test
    public void testWriteNumberWithIncludeAllFilter() throws Exception {
        delegate._itemFilter = TokenFilter.INCLUDE_ALL;
        delegate.writeNumber(123L);
        // No exception expected, method should delegate to underlying generator
    }

    @Test
    public void testWriteNumberWithFilteredValue() throws Exception {
        delegate._itemFilter = new TokenFilter() {
            @Override
            public boolean includeNumber(long v) {
                return v == 123L;
            }

            // Other methods can be stubbed as needed
            @Override
            public boolean includeNumber(int v) {
                return false;
            }

            @Override
            public boolean includeNumber(float v) {
                return false;
            }

            @Override
            public boolean includeNumber(double v) {
                return false;
            }

            @Override
            public boolean includeNumber(BigDecimal v) {
                return false;
            }

            @Override
            public boolean includeNumber(BigInteger v) {
                return false;
            }
        };
        delegate.writeNumber(123L);
        // No exception expected, method should delegate to underlying generator
    }

    @Test
    public void testWriteNumberWithExcludedValue() throws Exception {
        delegate._itemFilter = new TokenFilter() {
            @Override
            public boolean includeNumber(long v) {
                return false;
            }

            // Other methods can be stubbed as needed
            @Override
            public boolean includeNumber(int v) {
                return false;
            }

            @Override
            public boolean includeNumber(float v) {
                return false;
            }

            @Override
            public boolean includeNumber(double v) {
                return false;
            }

            @Override
            public boolean includeNumber(BigDecimal v) {
                return false;
            }

            @Override
            public boolean includeNumber(BigInteger v) {
                return false;
            }
        };
        delegate.writeNumber(123L);
        // No exception expected, method should return immediately
    }
}
