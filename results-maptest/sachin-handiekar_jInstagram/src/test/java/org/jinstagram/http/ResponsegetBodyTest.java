package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

public class ResponsegetBodyTest {
    private Response response;
    private HttpURLConnection connection;

    @Before
    public void setUp() throws Exception {
        connection = (HttpURLConnection) new java.net.URL("http://example.com").openConnection();
        response = new Response(connection);
    }

    @Test
    public void testGetBody_returnsNonNullString() throws Exception {
        String body = response.getBody();
        Assert.assertNotNull(body);
    }

    @Test
    public void testGetBody_initializesBodyOnFirstAccess() throws Exception {
        // Ensure body is null initially
        java.lang.reflect.Field bodyField = Response.class.getDeclaredField("body");
        bodyField.setAccessible(true);
        bodyField.set(response, null);

        // Call getBody and verify it initializes the body
        String body = response.getBody();
        Assert.assertNotNull(body);
    }

    @Test
    public void testGetBody_returnsSameBodyAfterInitialization() throws Exception {
        // Initialize body
        String initialBody = response.getBody();
        Assert.assertNotNull(initialBody);

        // Verify that subsequent calls return the same value
        String secondBody = response.getBody();
        Assert.assertEquals(initialBody, secondBody);
    }
}
