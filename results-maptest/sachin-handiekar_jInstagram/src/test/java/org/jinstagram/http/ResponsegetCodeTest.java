package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

public class ResponsegetCodeTest {
    private Response response;
    private HttpURLConnection mockConnection;

    @Before
    public void setUp() throws Exception {
        // Create a mock HttpURLConnection
        mockConnection = new MockHttpURLConnection();

        // Initialize the Response object
        response = new Response(mockConnection);
    }

    @After
    public void tearDown() {
        response = null;
        mockConnection = null;
    }

    @Test
    public void testGetCode_ReturnsExpectedValue() throws Exception {
        // Arrange: Set up the mock connection to return a specific status code
        ((MockHttpURLConnection) mockConnection).setResponseCode(200);

        // Act: Call the getCode method
        int result = response.getCode();

        // Assert: Verify the returned value matches the expected status code
        Assert.assertEquals("The code should match the expected value", 200, result);
    }

    // Mock implementation of HttpURLConnection for testing purposes
    private static class MockHttpURLConnection extends HttpURLConnection {
        private int responseCode = 200;

        protected MockHttpURLConnection() {
            super(null);
        }

        @Override
        public void connect() throws IOException {
            // No-op
        }

        @Override
        public int getResponseCode() throws IOException {
            return responseCode;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public InputStream getErrorStream() {
            return null;
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
        public void disconnect() {
            // No-op
        }

        public void setResponseCode(int responseCode) {
            this.responseCode = responseCode;
        }

        @Override
        public boolean usingProxy() {
            return false;
        }

        @Override
        public void setRequestProperty(String key, String value) {
            // No-op
        }

        @Override
        public void setUseCaches(boolean useCaches) {
            // No-op
        }

        @Override
        public void setConnectTimeout(int timeout) {
            // No-op
        }

        @Override
        public void setReadTimeout(int timeout) {
            // No-op
        }
    }
}
