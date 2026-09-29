package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class RequestaddHeaderTest {
    private Request request;

    @Before
    public void setUp() {
        request = new Request(Verbs.GET, "http://example.com");
    }

    @Test
    public void testAddHeader() {
        String key = "X-Test-Key";
        String value = "Test-Value";

        request.addHeader(key, value);

        Map<String, String> headers = getHeadersField(request);
        Assert.assertEquals(value, headers.get(key));
    }

    private Map<String, String> getHeadersField(Request request) {
        try {
            java.lang.reflect.Field field = Request.class.getDeclaredField("headers");
            field.setAccessible(true);
            return (Map<String, String>) field.get(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
