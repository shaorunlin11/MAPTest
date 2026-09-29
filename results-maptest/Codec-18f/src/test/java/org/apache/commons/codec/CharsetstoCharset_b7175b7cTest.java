package org.apache.commons.codec;

import org.junit.Test;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CharsetstoCharset_b7175b7cTest {

    @Test
    public void testToCharset_NullInput_ReturnsDefaultCharset() {
        Charset result = Charsets.toCharset((Charset) null);
        assertEquals(Charset.defaultCharset(), result);
    }

    @Test
    public void testToCharset_NonNullInput_ReturnsSameObject() {
        Charset input = Charset.forName("UTF-8");
        Charset result = Charsets.toCharset(input);
        assertSame(input, result);
    }
}
