package org.jinstagram.http;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

public class RequestsetConnectionKeepAliveTest {
    @Test
    public void testSetConnectionKeepAlive() throws Exception {
        Request request = new Request(Verbs.GET, "http://example.com");
        boolean expectedValue = true;

        request.setConnectionKeepAlive(expectedValue);

        // Use reflection to access private field
        Field field = Request.class.getDeclaredField("connectionKeepAlive");
        field.setAccessible(true);
        Boolean actualValue = (Boolean) field.get(request);

        Assert.assertEquals("The connectionKeepAlive flag should be set to the provided value", expectedValue, actualValue);
    }
}
