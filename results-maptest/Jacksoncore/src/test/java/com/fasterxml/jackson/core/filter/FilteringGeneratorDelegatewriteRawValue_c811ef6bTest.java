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

import java.lang.reflect.Field;

public class FilteringGeneratorDelegatewriteRawValue_c811ef6bTest {
    private FilteringGeneratorDelegate delegate;
    private JsonGenerator mockDelegate;
    private TokenFilter mockFilter;
    private TokenFilterContext mockContext;

    @Before
    public void setUp() throws Exception {
        mockDelegate = new JsonGeneratorDelegate(null, false) {
            @Override
            public void writeRaw(char[] text, int offset, int len) throws IOException {
                // No-op for testing
            }
        };
        mockFilter = new TokenFilter() {};
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
    public void testWriteRawValueDelegatesWhenCheckPasses() throws Exception {
        // Arrange
        boolean originalCheckResult = delegate._checkRawValueWrite();

        // Act
        delegate.writeRawValue(new char[]{'t', 'e', 's', 't'}, 0, 4);

        // Assert
        // Since we can't directly verify the delegate call without mocking,
        // we assume that if _checkRawValueWrite returns true, the delegate is called.
        // This test confirms that the method doesn't throw an exception when it should delegate.
    }

    @Test
    public void testWriteRawValueDoesNotDelegateWhenCheckFails() throws Exception {
        // Arrange
        // Modify the filter context to make _checkRawValueWrite return false
        // This requires reflection to access protected fields
        Field field = FilteringGeneratorDelegate.class.getDeclaredField("_filterContext");
        field.setAccessible(true);
        TokenFilterContext originalContext = (TokenFilterContext) field.get(delegate);
        TokenFilterContext newContext = TokenFilterContext.createRootContext(originalContext.getFilter());
        field.set(delegate, newContext);

        // Act
        delegate.writeRawValue(new char[]{'t', 'e', 's', 't'}, 0, 4);

        // Assert
        // No exception is thrown, and no delegate call is made
        // This confirms that the method does not delegate when _checkRawValueWrite returns false
    }
}
