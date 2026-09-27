package org.jinstagram.http;

import org.junit.Test;
import static org.junit.Assert.*;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.lang.reflect.Field;

public class RequestgetProxyTest {

    @Test
    public void testGetProxyReturnsNullWhenNotSet() {
        Request request = new Request(Verbs.GET, "http://example.com");
        assertNull("getProxy should return null when proxy is not set", request.getProxy());
    }

    @Test
    public void testGetProxyReturnsSetProxy() throws Exception {
        Request request = new Request(Verbs.GET, "http://example.com");
        Proxy expectedProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8080));
        Field proxyField = Request.class.getDeclaredField("proxy");
        proxyField.setAccessible(true);
        proxyField.set(request, expectedProxy);

        assertEquals("getProxy should return the set proxy", expectedProxy, request.getProxy());
    }
}
