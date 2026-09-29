package com.fasterxml.jackson.core.util;
import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;
import java.lang.ref.SoftReference;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
public class BufferRecyclersencodeAsUTF8Test {
    @Test
    public void testEncodeAsUTF8() throws Exception {
        // Arrange
        String input = "Hello, World!";

        // Act
        byte[] result = BufferRecyclers.encodeAsUTF8(input);

        // Assert
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertEquals("Length of encoded bytes should match UTF-8 encoding of input", 
            input.getBytes("UTF-8").length, result.length);
        Assert.assertArrayEquals("Encoded bytes should match UTF-8 encoding of input", 
            input.getBytes("UTF-8"), result);
    }

}
