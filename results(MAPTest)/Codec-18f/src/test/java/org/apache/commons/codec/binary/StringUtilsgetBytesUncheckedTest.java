package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.UnsupportedEncodingException;

public class StringUtilsgetBytesUncheckedTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetBytesUncheckedWithNullString() {
        byte[] result = StringUtils.getBytesUnchecked(null, "UTF-8");
        Assert.assertNull(result);
    }

    @Test
    public void testGetBytesUncheckedWithValidCharset() throws UnsupportedEncodingException {
        String input = "Hello, World!";
        String charsetName = "UTF-8";
        byte[] result = StringUtils.getBytesUnchecked(input, charsetName);
        Assert.assertNotNull(result);
        Assert.assertEquals(input, new String(result, charsetName));
    }

    @Test
    public void testGetBytesUncheckedWithInvalidCharset() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("invalidCharset: java.io.UnsupportedEncodingException: invalidCharset");
        StringUtils.getBytesUnchecked("test", "invalidCharset");
    }
}
