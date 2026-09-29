package org.apache.commons.codec.binary;

import org.junit.Test;

import org.apache.commons.codec.DecoderException;


public class HexDecodeHexZeroCoverageTest {
    @Test
    public void testDecodeHex() throws DecoderException {
        String data = "48656c6c6f20576f726c64";
        byte[] result = Hex.decodeHex(data);
        // This test is designed to cover line 80 of the decodeHex method
        // by ensuring the method is called with a non-null String parameter.
    }
}
