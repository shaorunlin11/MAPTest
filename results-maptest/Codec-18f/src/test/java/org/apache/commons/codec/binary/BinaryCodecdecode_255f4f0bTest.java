package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;

public class BinaryCodecdecode_255f4f0bTest {

    @Test
    public void testDecode() {
        BinaryCodec codec = new BinaryCodec();
        byte[] input = {0x31, 0x32, 0x33};
        byte[] result = codec.decode(input);
        Assert.assertNotNull(result);
    }

    @Test
    public void testDecodeWithNullInput() {
        BinaryCodec codec = new BinaryCodec();
        byte[] input = null;
        byte[] result = codec.decode(input);
        Assert.assertArrayEquals(new byte[0], result);
    }

    @Test
    public void testDecodeWithEmptyInput() {
        BinaryCodec codec = new BinaryCodec();
        byte[] input = new byte[0];
        byte[] result = codec.decode(input);
        Assert.assertArrayEquals(new byte[0], result);
    }
}
