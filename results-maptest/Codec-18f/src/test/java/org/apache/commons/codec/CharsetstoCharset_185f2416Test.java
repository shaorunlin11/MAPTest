package org.apache.commons.codec;

import org.junit.Test;
import java.nio.charset.Charset;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CharsetstoCharset_185f2416Test {

    @Test
    public void testToCharset_NullInput_ReturnsDefaultCharset() {
        Charset result = Charsets.toCharset((String) null);
        assertNotNull("Result should not be null", result);
        assertEquals("Should return the default charset", Charset.defaultCharset().name(), result.name());
    }

    @Test
    public void testToCharset_ValidCharset_ReturnsExpectedCharset() {
        Charset result = Charsets.toCharset("UTF-8");
        assertNotNull("Result should not be null", result);
        assertEquals("Should return the UTF-8 charset", Charset.forName("UTF-8"), result);
    }

    @Test
    public void testToCharset_InvalidCharset_ThrowsException() {
        try {
            Charsets.toCharset("INVALID_CHARSET");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
            return;
        }
        // If no exception was thrown, test fails
        throw new AssertionError("Expected IllegalArgumentException for invalid charset name");
    }
}
