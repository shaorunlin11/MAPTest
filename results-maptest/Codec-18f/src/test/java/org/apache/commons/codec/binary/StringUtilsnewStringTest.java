package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.UnsupportedEncodingException;

public class StringUtilsnewStringTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNewStringWithNullBytes() throws UnsupportedEncodingException {
        String result = StringUtils.newString(null, "UTF-8");
        Assert.assertNull(result);
    }

    @Test
    public void testNewStringWithValidCharset() throws UnsupportedEncodingException {
        byte[] bytes = "test".getBytes("UTF-8");
        String result = StringUtils.newString(bytes, "UTF-8");
        Assert.assertEquals("test", result);
    }

    @Test
    public void testNewStringWithInvalidCharset() throws UnsupportedEncodingException {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("invalid-encoding: java.io.UnsupportedEncodingException: invalid-encoding");
        byte[] bytes = "test".getBytes("UTF-8");
        StringUtils.newString(bytes, "invalid-encoding");
    }
}
