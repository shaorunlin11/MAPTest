package org.jinstagram.http;

import org.junit.Test;
import static org.junit.Assert.*;

public class RequestgetReadTimeoutInMillisTest {

    @Test
    public void testGetReadTimeoutInMillis() throws Exception {
        // Arrange
        Request request = new Request(Verbs.GET, "http://example.com");
        int expectedReadTimeout = 10000;

        // Use reflection to set the private field
        java.lang.reflect.Field readTimeoutField = Request.class.getDeclaredField("readTimeout");
        readTimeoutField.setAccessible(true);
        readTimeoutField.set(request, expectedReadTimeout);

        // Act
        int actualReadTimeout = request.getReadTimeoutInMillis();

        // Assert
        assertEquals(expectedReadTimeout, actualReadTimeout);
    }
}
