package org.jinstagram.realtime;

import org.junit.Test;

public class SubscriptionUtilVerifySubscriptionPostRequestSignatureZeroCoverageTest {
    @Test
    public void testVerifySubscriptionPostRequestSignature() throws Exception {
        String clientSecret = "testSecret";
        byte[] rawJsonData = "testData".getBytes();
        String xHubSignature = "testSignature";

        SubscriptionUtil.verifySubscriptionPostRequestSignature(clientSecret, rawJsonData, xHubSignature);
    }
}
