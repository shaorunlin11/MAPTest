package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilsisAvailableTest {

    @Test
    public void testIsAvailable_ValidAlgorithm_ReturnsTrue() {
        boolean result = DigestUtils.isAvailable("SHA-256");
        assertTrue(result);
    }

    @Test
    public void testIsAvailable_InvalidAlgorithm_ReturnsFalse() {
        boolean result = DigestUtils.isAvailable("INVALID_ALGORITHM");
        assertFalse(result);
    }

    @Test
    public void testIsAvailable_NullAlgorithm_ReturnsFalse() {
        boolean result = DigestUtils.isAvailable(null);
        assertFalse(result);
    }

    @Test
    public void testIsAvailable_EmptyStringAlgorithm_ReturnsFalse() {
        boolean result = DigestUtils.isAvailable("");
        assertFalse(result);
    }
}
