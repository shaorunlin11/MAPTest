package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class RequestaddBodyParameterTest {
    private Request request;

    @Before
    public void setUp() {
        request = new Request(Verbs.GET, "http://example.com");
    }

    @Test
    public void testAddBodyParameter_AddsKeyWithValueToBodyParams() {
        String key = "testKey";
        String value = "testValue";

        request.addBodyParameter(key, value);

        Map<String, String> bodyParams = getBodyParamsField(request);
        assertNotNull("bodyParams should not be null", bodyParams);
        assertEquals("bodyParams should contain the added key-value pair", value, bodyParams.get(key));
    }

    @Test
    public void testAddBodyParameter_OverwritesExistingKey() {
        String key = "testKey";
        String value1 = "value1";
        String value2 = "value2";

        request.addBodyParameter(key, value1);
        request.addBodyParameter(key, value2);

        Map<String, String> bodyParams = getBodyParamsField(request);
        assertNotNull("bodyParams should not be null", bodyParams);
        assertEquals("bodyParams should overwrite existing key", value2, bodyParams.get(key));
    }

    private Map<String, String> getBodyParamsField(Request request) {
        try {
            java.lang.reflect.Field field = Request.class.getDeclaredField("bodyParams");
            field.setAccessible(true);
            return (Map<String, String>) field.get(request);
        } catch (Exception e) {
            fail("Could not access bodyParams field: " + e.getMessage());
            return null;
        }
    }
}
