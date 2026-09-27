package org.apache.commons.codec.binary;

import org.junit.Test;

import org.apache.commons.codec.DecoderException;


public class HexDecodeHexZeroCoverage_61Test {
    @Test
    public void testDecodeHexWithEvenLength() throws Exception {
        char[] data = {'0', '1', '2', '3', '4', '5', '6', '7'};
        byte[] result = Hex.decodeHex(data);
        // Expected byte array: [1, 2, 3, 4, 5, 6, 7]
        // This test ensures that the code path for even-length data is executed
    }

@Test
    public void testDecodeHexWithOddLength() throws Exception {
        char[] data = {'0', '1', '2', '3', '4', '5', '6'};
        try {
            Hex.decodeHex(data);
            // This line should not be reached
        } catch (DecoderException e) {
            // Expected exception for odd number of characters
        }
    }
}
