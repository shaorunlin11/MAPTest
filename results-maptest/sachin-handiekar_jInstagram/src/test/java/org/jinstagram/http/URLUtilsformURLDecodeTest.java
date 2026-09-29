package org.jinstagram.http;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.jinstagram.utils.Preconditions;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLUtilsformURLDecodeTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testFormURLDecodeWithNullString() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Cannot decode null string");
        URLUtils.formURLDecode(null);
    }

    @Test
    public void testFormURLDecodeWithValidString() throws UnsupportedEncodingException {
        String encoded = "hello%20world";
        String decoded = URLUtils.formURLDecode(encoded);
        Assert.assertEquals("hello world", decoded);
    }

    @Test
    public void testFormURLDecodeWithUnsupportedEncoding() throws Exception {
        // This test is not directly possible with the current method implementation,
        // as the method uses a fixed UTF-8 encoding which is always supported.
        // The exception is only thrown if the encoding is not found, which is not the case here.
        // Therefore, this test cannot be implemented without modifying the method or using reflection.
    }
}
