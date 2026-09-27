package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;

import java.math.BigInteger;

public class Base64EncodeIntegerZeroCoverageTest {
    @Test
    public void testEncodeIntegerWithNullBigInteger() {
        // This test is designed to execute line 736 of the encodeInteger method
        // which is the null check for bigInt.
        // The test will pass if the method throws a NullPointerException as expected.
        try {
            Base64.encodeInteger(null);
            // If no exception is thrown, the test fails
            throw new AssertionError("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected exception, test passes
        }
    }

    @Test
    public void testEncodeIntegerWithNonZeroBigInteger() {
        // This test is designed to execute line 739 of the encodeInteger method
        // which is the return statement after the null check.
        // The test will pass if the method returns a byte array as expected.
        BigInteger bigInt = new BigInteger("1234567890");
        byte[] result = Base64.encodeInteger(bigInt);
        // Ensure the result is not null and has a valid length
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have a valid length", result.length > 0);
    }
}
