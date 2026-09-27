package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.filter.TokenFilterContext;
import java.io.IOException;

public class FilteringGeneratorDelegatewriteOmittedFieldTest {
    private FilteringGeneratorDelegate delegate;
    private JsonGenerator mockDelegate;
    private TokenFilter mockFilter;

    @Before
    public void setUp() throws Exception {
        mockDelegate = new JsonGeneratorDelegate(null, false) {
            @Override
            public void writeOmittedField(String fieldName) throws IOException {
                // No-op for testing
            }
        };
        mockFilter = new TokenFilter() {
            // Empty implementation for testing
        };
        delegate = new FilteringGeneratorDelegate(mockDelegate, mockFilter, false, false);
    }

    @Test
    public void testWriteOmittedFieldWithNonNullItemFilter() throws Exception {
        // Ensure _itemFilter is not null
        Assert.assertNotNull(delegate._itemFilter);

        // Call the method
        delegate.writeOmittedField("testField");

        // Since we're using a mock delegate, we can't verify actual behavior,
        // but we can confirm that the method doesn't throw an exception
        // and that the call is made to the delegate
    }

    @Test
    public void testWriteOmittedFieldWithNullItemFilter() throws Exception {
        // Set _itemFilter to null
        delegate._itemFilter = null;

        // Call the method
        delegate.writeOmittedField("testField");

        // Since _itemFilter is null, the method should do nothing
        // and not throw an exception
    }
}
