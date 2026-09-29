package com.fasterxml.jackson.core.filter;

import org.junit.Test;

import java.io.IOException;

public class FilteringGeneratorDelegateWriteNumberZeroCoverage_624Test {
    @Test
    public void testWriteNumberWithNullItemFilter() throws IOException {
        // Arrange
        TokenFilterContext filterContext = null;
        TokenFilter itemFilter = null;
        TokenFilter rootFilter = null;
        boolean includePath = false;
        boolean allowMultipleMatches = false;

        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(null, rootFilter, includePath, allowMultipleMatches);
        delegate._filterContext = filterContext;
        delegate._itemFilter = itemFilter;

        // Act
        delegate.writeNumber(0.0);

        // Assert
        // The method should return immediately since _itemFilter is null
    }
}
