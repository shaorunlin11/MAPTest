package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class RequestgetHeadersTest {
    private Request request;

    @Before
    public void setUp() {
        request = new Request(Verbs.GET, "http://example.com");
    }

    @Test
    public void testGetHeadersReturnsInternalHeadersMap() {
        Map<String, String> expectedHeaders = new HashMap();
        expectedHeaders.put("Content-Type", "application/json");

        // Use reflection to set the headers field
        try {
            java.lang.reflect.Field headersField = Request.class.getDeclaredField("headers");
            headersField.setAccessible(true);
            headersField.set(request, expectedHeaders);
        } catch (Exception e) {
            Assert.fail("Failed to set headers field: " + e.getMessage());
        }

        Map<String, String> actualHeaders = request.getHeaders();

        Assert.assertEquals(expectedHeaders, actualHeaders);
        Assert.assertSame(expectedHeaders, actualHeaders);
    }
}
