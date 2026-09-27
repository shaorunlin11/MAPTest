package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Assert;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class QuotedPrintableCodecencode_29dc2856Test {

    @Test
    public void testEncode_NullInput_ReturnsNull() {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String result = codec.encode(null, StandardCharsets.UTF_8);
        Assert.assertNull(result);
    }

    @Test
    public void testEncode_NonNullInput_EncodesCorrectly() {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String input = "Hello, World! =";
        String result = codec.encode(input, StandardCharsets.UTF_8);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(input, result);
    }
}
