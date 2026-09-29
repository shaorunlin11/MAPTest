package com.fasterxml.jackson.core.filter;

import org.junit.Test;

public class FilteringGeneratorDelegateWriteNumberZeroCoverage_627Test {
    @Test
    public void testWriteNumberWithNullItemFilter() throws Exception {
        // Arrange
        FilteringGeneratorDelegate delegate = new FilteringGeneratorDelegate(null, TokenFilter.INCLUDE_ALL, false, false);
        delegate._itemFilter = null;

        // Act
        delegate.writeNumber("123");

        // Assert
        // No assertions needed as the method should return without executing any code
    }
}
