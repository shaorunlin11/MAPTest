package com.fasterxml.jackson.core.util;
import org.junit.Test;
import static org.junit.Assert.*;
import com.fasterxml.jackson.core.io.JsonStringEncoder;

public class BufferRecyclersquoteAsJsonUTF8Test {

    @Test
    public void testQuoteAsJsonUTF8() {
        // Arrange
        String rawText = "test";

        // Act
        byte[] result = BufferRecyclers.quoteAsJsonUTF8(rawText);

        // Assert
        assertNotNull(result);
        assertTrue(result.length > 0);
    }
}
