package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64encodeBase64StringTest {

    @Test
    public void testEncodeBase64StringWithEmptyInput() {
        byte[] binaryData = new byte[0];
        String result = Base64.encodeBase64String(binaryData);
        assertEquals("", result);
    }

    @Test
    public void testEncodeBase64StringWithNullInput() {
        byte[] binaryData = null;
        String result = Base64.encodeBase64String(binaryData);
        assertNull(result);
    }

    @Test
    public void testEncodeBase64StringWithSimpleInput() {
        byte[] binaryData = "Hello, World!".getBytes();
        String result = Base64.encodeBase64String(binaryData);
        assertEquals("SGVsbG8sIFdvcmxkIQ==", result);
    }

    @Test
    public void testEncodeBase64StringWithMultipleBlocks() {
        byte[] binaryData = new byte[]{0x01, 0x02, 0x03, 0x04, 0x05, 0x06};
        String result = Base64.encodeBase64String(binaryData);
        assertEquals("AQIDBAUG", result);
    }

    @Test
    public void testEncodeBase64StringWithPadding() {
        byte[] binaryData = new byte[]{0x01, 0x02};
        String result = Base64.encodeBase64String(binaryData);
        assertEquals("AQI=", result);
    }
}
