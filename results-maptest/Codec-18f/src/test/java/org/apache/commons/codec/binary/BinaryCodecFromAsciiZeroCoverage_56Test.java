package org.apache.commons.codec.binary;

import org.junit.Test;

public class BinaryCodecFromAsciiZeroCoverage_56Test {
    @Test
    public void testFromAsciiWithEmptyInput() {
        // Test case to cover line 199: if (isEmpty(ascii)) { return EMPTY_BYTE_ARRAY; }
        byte[] result = BinaryCodec.fromAscii((byte[]) null);
        assert result.length == 0;

        byte[] emptyArray = new byte[0];
        result = BinaryCodec.fromAscii(emptyArray);
        assert result.length == 0;
    }

@Test
    public void testFromAsciiWithValidInput() {
        // Test case to cover line 209: ii < l_raw.length is true
        // Create an ascii array with length > 3 (so ascii.length >> 3 > 0)
        byte[] ascii = new byte[]{'1', '0', '1', '0', '1', '0', '1', '0', '1'};
        byte[] result = BinaryCodec.fromAscii(ascii);
        assert result.length > 0;
    }
}
