package org.jinstagram.auth.model;

import org.junit.Test;
import org.junit.Before;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class OAuthConfigsetRequestProxyTest {
    private OAuthConfig oauthConfig;

    @Before
    public void setUp() {
        oauthConfig = new OAuthConfig("key", "secret");
    }

    @Test
    public void testSetRequestProxyWithNonNullProxy() throws Exception {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("localhost", 8080));
        oauthConfig.setRequestProxy(proxy);
        Field field = oauthConfig.getClass().getDeclaredField("requestProxy");
        field.setAccessible(true);
        assertEquals(proxy, field.get(oauthConfig));
    }

    @Test
    public void testSetRequestProxyWithNull() throws Exception {
        oauthConfig.setRequestProxy(null);
        Field field = oauthConfig.getClass().getDeclaredField("requestProxy");
        field.setAccessible(true);
        assertNull(field.get(oauthConfig));
    }
}
