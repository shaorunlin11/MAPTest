package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.Map;
import java.util.HashMap;

public class InstagramErrorResponsesetHeadersTest {
    private InstagramErrorResponse errorResponse;
    private Meta meta;

    @Before
    public void setUp() {
        meta = new Meta();
        errorResponse = new InstagramErrorResponse(meta);
    }

    @Test
    public void testSetHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-RateLimit-Remaining", "10");

        errorResponse.setHeaders(headers);

        // Verify that the headers were set correctly
        Assert.assertEquals("application/json", headers.get("Content-Type"));
        Assert.assertEquals("10", headers.get("X-RateLimit-Remaining"));
    }
}
