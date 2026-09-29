package org.jinstagram.utils;

import org.junit.Test;

import org.jinstagram.exceptions.InstagramException;


public class EnforceSignedHeaderUtilsSignatureZeroCoverageTest {
    @Test
    public void testSignatureMethod() throws InstagramException {
        String clientSecret = "testSecret";
        String message = "testMessage";
        String result = EnforceSignedHeaderUtils.signature(clientSecret, message);
    }
}
