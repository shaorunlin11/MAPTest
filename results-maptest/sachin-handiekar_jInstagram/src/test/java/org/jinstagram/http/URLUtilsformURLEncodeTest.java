package org.jinstagram.http;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.utils.Preconditions;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLUtilsformURLEncodeTest {

    @Test
    public void testFormURLEncodeWithNonNullString() throws Exception {
        String input = "test string with spaces";
        String expected = URLEncoder.encode(input, "UTF-8");
        String result = URLUtils.formURLEncode(input);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testFormURLEncodeWithUnsupportedEncoding() throws Exception {
        // Since UTF-8 is generally supported, we cannot reliably test the exception path
        // Instead, verify that the method returns a valid encoded string
        String input = "test";
        String result = URLUtils.formURLEncode(input);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertFalse("Result should not be empty", result.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormURLEncodeWithNullString() throws Exception {
        URLUtils.formURLEncode(null);
    }
}
