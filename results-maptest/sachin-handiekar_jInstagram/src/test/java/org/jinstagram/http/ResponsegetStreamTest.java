package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import java.lang.reflect.Field;
import java.io.IOException;

public class ResponsegetStreamTest {
    private Response response;
    private HttpURLConnection mockConnection;

    @Before
    public void setUp() throws Exception {
        mockConnection = new MockHttpURLConnection();
        response = new Response(mockConnection);
    }

    @After
    public void tearDown() {
        response = null;
        mockConnection = null;
    }

    @Test
    public void testGetStream_returnsInitializedStream() {
        InputStream result = response.getStream();
        Assert.assertNotNull("getStream() should return a non-null InputStream", result);
    }

    @Test
    public void testGetStream_returnsSameStreamAsSetInConstructor() {
        InputStream expectedStream = new MockInputStream();
        // Directly set the stream field using reflection to simulate constructor behavior
        try {
            java.lang.reflect.Field streamField = Response.class.getDeclaredField("stream");
            streamField.setAccessible(true);
            streamField.set(response, expectedStream);
        } catch (Exception e) {
            Assert.fail("Failed to set stream field: " + e.getMessage());
        }

        InputStream result = response.getStream();
        Assert.assertEquals("getStream() should return the same InputStream as set in constructor", expectedStream, result);
    }

    // Helper classes for testing
    private static class MockHttpURLConnection extends HttpURLConnection {
        protected MockHttpURLConnection() {
            super(null);
        }

        @Override
        public void connect() throws IOException {
            // No-op
        }

        @Override
        public int getResponseCode() throws IOException {
            return 200;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new MockInputStream();
        }

        @Override
        public InputStream getErrorStream() {
            return new MockInputStream();
        }

        @Override
        public URL getURL() {
            try {
                return new URL("http://example.com");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean usingProxy() {
            return false;
        }

        @Override
        public void disconnect() {
            // Empty implementation to satisfy superclass requirement
        }
    }

    private static class MockInputStream extends InputStream {
        @Override
        public int read() {
            return -1;
        }
    }
}
