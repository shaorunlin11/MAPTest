package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.codec.binary.StringUtils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class URLCodecencode_75d5b654Test {

    @Test
    public void testEncode() throws UnsupportedEncodingException {
        URLCodec codec = new URLCodec();
        byte[] input = "test with space".getBytes(StandardCharsets.UTF_8);
        byte[] result = codec.encode(input);

        // Verify that the result is not null
        Assert.assertNotNull(result);

        // Verify that the result contains the expected encoded value
        String encodedString = StringUtils.newStringUtf8(result);
        Assert.assertEquals("test+with+space", encodedString);
    }
}
