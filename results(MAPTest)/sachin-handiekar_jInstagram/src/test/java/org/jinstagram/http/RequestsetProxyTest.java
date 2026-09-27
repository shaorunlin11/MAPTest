package org.jinstagram.http;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.net.Proxy;
import java.net.InetSocketAddress;

public class RequestsetProxyTest {
    @Test
    public void testSetProxy() throws Exception {
        Request request = new Request(Verbs.GET, "http://example.com");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8080));

        request.setProxy(proxy);

        Field proxyField = Request.class.getDeclaredField("proxy");
        proxyField.setAccessible(true);
        Proxy result = (Proxy) proxyField.get(request);

        assertEquals(proxy, result);
    }

    @Test
    public void testSetProxyWithNull() throws Exception {
        Request request = new Request(Verbs.GET, "http://example.com");

        request.setProxy(null);

        Field proxyField = Request.class.getDeclaredField("proxy");
        proxyField.setAccessible(true);
        Proxy result = (Proxy) proxyField.get(request);

        assertNull(result);
    }
}
