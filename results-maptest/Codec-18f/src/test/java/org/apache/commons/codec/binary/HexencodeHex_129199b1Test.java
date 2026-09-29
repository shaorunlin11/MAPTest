package org.apache.commons.codec.binary;

import org.junit.Test;
import java.nio.ByteBuffer;

import static org.junit.Assert.assertArrayEquals;

public class HexencodeHex_129199b1Test {

    @Test
    public void testEncodeHex() {
        byte[] input = {0x1a, 0x2b, 0x3c, 0x4d};
        ByteBuffer buffer = ByteBuffer.wrap(input);
        char[] result = Hex.encodeHex(buffer);

        // Expected hexadecimal representation of the input bytes
        // 0x1a -> '1a', 0x2b -> '2b', 0x3c -> '3c', 0x4d -> '4d'
        char[] expected = {'1', 'a', '2', 'b', '3', 'c', '4', 'd'};

        assertArrayEquals(expected, result);
    }
}
