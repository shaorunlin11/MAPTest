package org.apache.commons.codec.binary;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.codec.Charsets;


public class HexEncodeZeroCoverageTest {
    @Test
    public void testEncodeWithValidCharset() {
        // Create a Hex instance with a valid charset
        Hex hex = new Hex(Charsets.UTF_8);

        // Create a ByteBuffer with some data
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x48); // 'H'
        buffer.put((byte) 0x65); // 'e'
        buffer.put((byte) 0x6C); // 'l'
        buffer.put((byte) 0x6C); // 'l'
        buffer.put((byte) 0x6F); // 'o'
        buffer.flip();

        // Call the encode method
        byte[] result = hex.encode(buffer);

        // This test is designed to execute line 426 of the Hex class, which is part of the encode method.
        // The line in question is: return encodeHexString(array).getBytes(this.getCharset());
        // By calling encode with a valid ByteBuffer and a valid Charset, we ensure that this line is executed.
    }
}
