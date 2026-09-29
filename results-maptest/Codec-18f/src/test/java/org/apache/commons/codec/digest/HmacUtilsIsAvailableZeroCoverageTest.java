package org.apache.commons.codec.digest;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;


public class HmacUtilsIsAvailableZeroCoverageTest {
    @Test
    public void testIsAvailableWithValidAlgorithm() {
        // Test with a valid algorithm name that should be available
        boolean result = HmacUtils.isAvailable("HmacSHA256");
        // This test is designed to execute line 70 of HmacUtils#isAvailable
        // by using a valid algorithm name that should not throw an exception
    }

@Test
    public void testIsAvailableWithInvalidAlgorithm() {
        // Test with an invalid algorithm name that should cause Mac.getInstance to throw NoSuchAlgorithmException
        boolean result = HmacUtils.isAvailable("InvalidAlgorithm");
        // This test is designed to execute line 72 of HmacUtils#isAvailable
        // by using an invalid algorithm name that should throw an exception
    }
}
