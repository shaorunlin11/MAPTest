package org.jinstagram.http;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class RequestgetConnectionKeepAliveTest {

    @Test
    public void testGetConnectionKeepAlive_DefaultValue() throws Exception {
        Request request = new Request(Verbs.GET, "http://example.com");
        assertFalse("Default value of connectionKeepAlive should be false", request.getConnectionKeepAlive());
    }

    @Test
    public void testGetConnectionKeepAlive_SetValue() throws Exception {
        Request request = new Request(Verbs.GET, "http://example.com");
        // Use reflection to set the connectionKeepAlive field directly
        Field field = Request.class.getDeclaredField("connectionKeepAlive");
        field.setAccessible(true);
        field.set(request, true);

        assertTrue("connectionKeepAlive should return true after being set", request.getConnectionKeepAlive());
    }
}
