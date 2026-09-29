package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;

import java.nio.charset.Charset;

public class StringUtilsgetBytesIso8859_1Test {

    @Test
    public void testGetBytesIso8859_1() {
        String input = "Hello, World!";
        byte[] result = StringUtils.getBytesIso8859_1(input);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertEquals("Length should match input string length", input.length(), result.length);
    }

    @Test
    public void testGetBytesIso8859_1_EmptyString() {
        String input = "";
        byte[] result = StringUtils.getBytesIso8859_1(input);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertEquals("Length should be 0 for empty string", 0, result.length);
    }

    @Test
    public void testGetBytesIso8859_1_NullInput() {
        byte[] result = StringUtils.getBytesIso8859_1(null);
        Assert.assertNull("Result should be null for null input", result);
    }

    @Test
    public void testGetBytesIso8859_1_SpecialCharacters() {
        String input = "Café";
        byte[] result = StringUtils.getBytesIso8859_1(input);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertEquals("Length should match input string length", input.length(), result.length);
    }
}
