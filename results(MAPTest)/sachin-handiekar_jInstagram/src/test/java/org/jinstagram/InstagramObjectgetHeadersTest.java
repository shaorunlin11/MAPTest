package org.jinstagram;

import java.util.Map;
import java.util.HashMap;
import org.jinstagram.http.APILimitUtils;
import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;

public class InstagramObjectgetHeadersTest {
    @Test
    public void testGetHeadersReturnsHeadersMap() throws Exception {
        // Create a concrete subclass of InstagramObject to test
        InstagramObject testObject = new InstagramObject() {
            private Map<String, String> headers = new HashMap<String, String>();

            public void parseResponse(String response) {
                // Not used in this test
            }
        };

        // Set some headers
        Map<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Type", "application/json");
        expectedHeaders.put("Authorization", "Bearer token123");

        // Use reflection to set the headers field
        Field headersField = InstagramObject.class.getDeclaredField("headers");
        headersField.setAccessible(true);
        headersField.set(testObject, expectedHeaders);

        // Call the method under test
        Map<String, String> result = testObject.getHeaders();

        // Verify the result
        Assert.assertEquals(expectedHeaders, result);
    }
}
