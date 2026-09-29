package org.jinstagram.http;

import org.junit.Test;
import java.nio.charset.Charset;
import static org.junit.Assert.assertEquals;

public class RequestGetBodyContentsZeroCoverageTest {
    @Test
    public void testGetBodyContents() throws Exception {
        // Create a mock Request object with the required state
        Request request = new Request(Verbs.GET, "http://example.com");

        // Set the payload
        request.addPayload("test payload");

        // Set the charset
        request.setCharset("UTF-8");

        // Ensure getByteBodyContents() returns a non-null byte array
        byte[] byteBodyContents = "test payload".getBytes(Charset.forName("UTF-8"));

        // Ensure getCharset() returns a non-null charset value
        Charset charset = Charset.forName("UTF-8");

        // Execute the method under test
        String result = request.getBodyContents();

        // Verify the result
        assertEquals("test payload", result);
    }
}
