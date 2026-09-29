package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class RequestgetConnectTimeoutInMillisTest {
    private Request request;

    @Before
    public void setUp() {
        request = new Request(Verbs.GET, "http://example.com");
    }

    @Test
    public void testGetConnectTimeoutInMillisReturnsDefaultValue() {
        // Default value for connectTimeout is 0
        assertEquals(0, request.getConnectTimeoutInMillis());
    }

    @Test
    public void testGetConnectTimeoutInMillisReturnsSetValues() throws Exception {
        // Set connectTimeout using reflection to verify getter
        Field connectTimeoutField = Request.class.getDeclaredField("connectTimeout");
        connectTimeoutField.setAccessible(true);
        connectTimeoutField.setInt(request, 5000);

        assertEquals(5000, request.getConnectTimeoutInMillis());
    }
}
