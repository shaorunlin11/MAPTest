package org.jinstagram.realtime;

import org.junit.Test;
import static org.junit.Assert.*;

public class SubscriptionUtilVerificationResultTest {

    @Test
    public void testVerificationResultConstructorWithSuccessAndSignature() {
        boolean success = true;
        String signature = "testSignature";
        SubscriptionUtil.VerificationResult result = new SubscriptionUtil.VerificationResult(success, signature);

        assertTrue(result.isSuccess());
        assertEquals(signature, result.getCalculatedSignature());
    }

    @Test
    public void testVerificationResultConstructorWithFailureAndNullSignature() {
        boolean success = false;
        String signature = null;
        SubscriptionUtil.VerificationResult result = new SubscriptionUtil.VerificationResult(success, signature);

        assertFalse(result.isSuccess());
        assertNull(result.getCalculatedSignature());
    }

    @Test
    public void testVerificationResultConstructorWithSuccessAndNullSignature() {
        boolean success = true;
        String signature = null;
        SubscriptionUtil.VerificationResult result = new SubscriptionUtil.VerificationResult(success, signature);

        assertTrue(result.isSuccess());
        assertNull(result.getCalculatedSignature());
    }

    @Test
    public void testVerificationResultConstructorWithFailureAndNonEmptySignature() {
        boolean success = false;
        String signature = "anotherTestSignature";
        SubscriptionUtil.VerificationResult result = new SubscriptionUtil.VerificationResult(success, signature);

        assertFalse(result.isSuccess());
        assertEquals(signature, result.getCalculatedSignature());
    }
}
