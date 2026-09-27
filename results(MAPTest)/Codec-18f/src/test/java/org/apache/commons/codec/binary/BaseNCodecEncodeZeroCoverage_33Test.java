package org.apache.commons.codec.binary;

import org.junit.Test;

public class BaseNCodecEncodeZeroCoverage_33Test {
    @Test
    public void testEncodeWithNullInput() {
        BaseNCodec codec = new Base64();
        byte[] result = codec.encode(null);
        // Target line 426 is executed when pArray is null
        // No further assertions needed as per requirements
    }

    @Test
    public void testEncodeWithEmptyInput() {
        BaseNCodec codec = new Base64();
        byte[] result = codec.encode(new byte[0]);
        // Target line 426 is executed when pArray.length is 0
        // No further assertions needed as per requirements
    }

@Test
    public void testEncodeWithNonEmptyInput() {
        BaseNCodec codec = new Base64();
        byte[] input = {1, 2, 3};
        byte[] result = codec.encode(input);
        // Target line 429 is executed when pArray is not null and pArray.length is not zero
        // No further assertions needed as per requirements
    }
}
