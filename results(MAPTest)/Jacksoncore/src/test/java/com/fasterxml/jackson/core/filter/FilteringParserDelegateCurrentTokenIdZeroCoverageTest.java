package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonTokenId;

public class FilteringParserDelegateCurrentTokenIdZeroCoverageTest {
    @Test
    public void testCurrentTokenId_Null() throws Exception {
        // Arrange
        FilteringParserDelegate delegate = new FilteringParserDelegate(null, null, false, false);
        delegate._currToken = null;

        // Act
        int result = delegate.currentTokenId();

        // Assert
        assertEquals(JsonTokenId.ID_NO_TOKEN, result);
    }

    @Test
    public void testCurrentTokenId_NotNull() throws Exception {
        // Arrange
        FilteringParserDelegate delegate = new FilteringParserDelegate(null, null, false, false);
        JsonToken mockToken = JsonToken.VALUE_STRING;
        delegate._currToken = mockToken;

        // Act
        int result = delegate.currentTokenId();

        // Assert
        assertEquals(mockToken.id(), result);
    }
}
