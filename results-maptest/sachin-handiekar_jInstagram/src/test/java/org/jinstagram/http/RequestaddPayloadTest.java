package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class RequestaddPayloadTest {
    private Request request;

    @Before
    public void setUp() {
        request = new Request(Verbs.GET, "http://example.com");
    }

    @Test
    public void testAddPayloadSetsPayloadField() throws Exception {
        String expectedPayload = "test payload";
        request.addPayload(expectedPayload);

        // Use reflection to access private field
        java.lang.reflect.Field payloadField = Request.class.getDeclaredField("payload");
        payloadField.setAccessible(true);
        String actualPayload = (String) payloadField.get(request);

        Assert.assertEquals("Payload should be set correctly", expectedPayload, actualPayload);
    }
}
