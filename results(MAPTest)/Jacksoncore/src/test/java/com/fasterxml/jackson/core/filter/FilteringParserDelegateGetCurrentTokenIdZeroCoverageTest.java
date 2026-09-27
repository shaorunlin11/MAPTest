package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonTokenId;

public class FilteringParserDelegateGetCurrentTokenIdZeroCoverageTest {
    @Test
    public void testGetCurrentTokenIdWithNullCurrentToken() {
        // Arrange
        FilteringParserDelegate delegate = new FilteringParserDelegate(null, null, false, false);
        delegate._currToken = null;

        // Act
        int tokenId = delegate.getCurrentTokenId();

        // Assert
        assertEquals(JsonTokenId.ID_NO_TOKEN, tokenId);
    }

    @Test
    public void testGetCurrentTokenIdWithNonNullCurrentToken() throws Exception {
        // Arrange
        FilteringParserDelegate delegate = new FilteringParserDelegate(null, null, false, false);
        delegate._currToken = JsonToken.VALUE_STRING;

        // Act
        int tokenId = delegate.getCurrentTokenId();

        // Assert
        assertEquals(JsonTokenId.ID_STRING, tokenId);
    }
}
