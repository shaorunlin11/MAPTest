package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageDigestAlgorithmsvaluesTest {
    @Test
    public void testValuesMethod() {
        String[] result = MessageDigestAlgorithms.values();
        assertEquals("Expected 11 algorithm names", 11, result.length);
        assertEquals("MD2", result[0]);
        assertEquals("MD5", result[1]);
        assertEquals("SHA-1", result[2]);
        assertEquals("SHA-224", result[3]);
        assertEquals("SHA-256", result[4]);
        assertEquals("SHA-384", result[5]);
        assertEquals("SHA-512", result[6]);
        assertEquals("SHA3-224", result[7]);
        assertEquals("SHA3-256", result[8]);
        assertEquals("SHA3-384", result[9]);
        assertEquals("SHA3-512", result[10]);
    }
}
