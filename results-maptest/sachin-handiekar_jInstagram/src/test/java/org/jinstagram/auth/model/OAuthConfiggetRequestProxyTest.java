package org.jinstagram.auth.model;

import org.junit.Test;
import org.junit.Assert;

import java.net.Proxy;
import java.net.InetSocketAddress;

public class OAuthConfiggetRequestProxyTest {

    @Test
    public void testGetRequestProxyReturnsNullWhenNotSet() {
        OAuthConfig config = new OAuthConfig("key", "secret");
        Proxy result = config.getRequestProxy();
        Assert.assertNull(result);
    }

    @Test
    public void testGetRequestProxyReturnsSetProxy() throws Exception {
        OAuthConfig config = new OAuthConfig("key", "secret");
        Proxy expectedProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));

        // Use reflection to set the private field
        java.lang.reflect.Field requestProxyField = OAuthConfig.class.getDeclaredField("requestProxy");
        requestProxyField.setAccessible(true);
        requestProxyField.set(config, expectedProxy);

        Proxy result = config.getRequestProxy();
        Assert.assertEquals(expectedProxy, result);
    }
}
