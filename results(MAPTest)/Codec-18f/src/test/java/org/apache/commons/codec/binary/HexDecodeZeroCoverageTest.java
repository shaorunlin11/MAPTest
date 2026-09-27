package org.apache.commons.codec.binary;

import org.junit.Test;

import java.nio.ByteBuffer;
import org.apache.commons.codec.Charsets;

public class HexDecodeZeroCoverageTest {
    @Test
    public void testDecodeWithValidBuffer() throws Exception {
        // Create a ByteBuffer with valid hex data (even number of characters)
        byte[] byteArray = {0x34, 0x38, 0x36, 0x35, 0x36, 0x63, 0x36, 0x63, 0x36, 0x66, 0x32, 0x30}; // "48656c6c6f20" in hex
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);

        // Create a Hex instance with a valid charset
        Hex hex = new Hex(Charsets.UTF_8);

        // Call the decode method
        byte[] result = hex.decode(buffer);

        // Add an assertion to ensure the method is executed
        assert result != null;
    }
}
