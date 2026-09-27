package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringUtilsgetBytesUtf16Test {

    @Test
    public void testGetBytesUtf16() {
        String input = "Hello, World!";
        byte[] result = StringUtils.getBytesUtf16(input);

        // Verify that the result is not null
        Assert.assertNotNull(result);

        // Verify that the result has a non-zero length
        Assert.assertTrue(result.length > 0);

        // Verify that the encoding is using UTF-16
        Charset expectedCharset = StandardCharsets.UTF_16;
        byte[] expectedBytes = input.getBytes(expectedCharset);

        // Compare the results
        Assert.assertArrayEquals(expectedBytes, result);
    }
}
