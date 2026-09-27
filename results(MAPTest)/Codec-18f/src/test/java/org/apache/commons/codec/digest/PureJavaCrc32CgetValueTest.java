package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class PureJavaCrc32CgetValueTest {
    @Test
    public void testGetValue() throws Exception {
        PureJavaCrc32C crc = new PureJavaCrc32C();

        // Test with initial value (0)
        assertEquals(0, crc.getValue());

        // Test with a known value
        Field crcField = PureJavaCrc32C.class.getDeclaredField("crc");
        crcField.setAccessible(true);
        crcField.setInt(crc, 0x12345678);

        long expectedValue = (~0x12345678) & 0xFFFFFFFFL;
        assertEquals(expectedValue, crc.getValue());

        // Test with maximum 32-bit value
        crcField.setInt(crc, 0xFFFFFFFF);
        expectedValue = (~0xFFFFFFFF) & 0xFFFFFFFFL;
        assertEquals(expectedValue, crc.getValue());

        // Test with minimum 32-bit value
        crcField.setInt(crc, 0x00000000);
        expectedValue = (~0x00000000) & 0xFFFFFFFFL;
        assertEquals(expectedValue, crc.getValue());
    }
}
