package org.jinstagram.utils;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.jinstagram.exceptions.InstagramException;

public class EnforceSignedRequestUtilsSignatureZeroCoverageTest {
    @Test
    public void testSignatureMethod() {
        String endpoint = "testEndpoint";
        Map<String, String> params = new HashMap();
        params.put("key1", "value1");
        params.put("key2", "value2");
        String clientSecret = "testClientSecret";

        try {
            String result = EnforceSignedRequestUtils.signature(endpoint, params, clientSecret);
            assertNotNull("Result should not be null", result);
        } catch (InstagramException e) {
            fail("Signature method should not throw InstagramException: " + e.getMessage());
        }
    }
}
