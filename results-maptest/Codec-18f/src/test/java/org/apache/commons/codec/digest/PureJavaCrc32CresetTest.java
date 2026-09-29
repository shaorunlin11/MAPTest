package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class PureJavaCrc32CresetTest {
    @Test
    public void testReset() throws Exception {
        PureJavaCrc32C crc = new PureJavaCrc32C();
        assertEquals("Initial crc value should be 0xFFFFFFFF", 0xFFFFFFFF, getCrcValue(crc));

        crc.reset();
        assertEquals("After reset, crc should be 0xFFFFFFFF", 0xFFFFFFFF, getCrcValue(crc));

        // Test that multiple resets work correctly
        crc.reset();
        assertEquals("After second reset, crc should still be 0xFFFFFFFF", 0xFFFFFFFF, getCrcValue(crc));
    }

    private int getCrcValue(PureJavaCrc32C crc) throws Exception {
        java.lang.reflect.Field field = PureJavaCrc32C.class.getDeclaredField("crc");
        field.setAccessible(true);
        return field.getInt(crc);
    }
}
