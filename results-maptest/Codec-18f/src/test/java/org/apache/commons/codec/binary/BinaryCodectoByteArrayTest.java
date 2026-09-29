package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;

public class BinaryCodectoByteArrayTest {

    @Test
    public void testToByteArray_NullInput_ReturnsEmptyByteArray() {
        BinaryCodec codec = new BinaryCodec();
        byte[] result = codec.toByteArray(null);
        Assert.assertArrayEquals(new byte[0], result);
    }

    @Test
    public void testToByteArray_NonNullInput_DelegatesToFromAscii() throws Exception {
        BinaryCodec codec = new BinaryCodec();
        String input = "test";
        byte[] result = codec.toByteArray(input);
        byte[] expected = BinaryCodec.fromAscii(input.toCharArray());
        Assert.assertArrayEquals(expected, result);
    }
}
