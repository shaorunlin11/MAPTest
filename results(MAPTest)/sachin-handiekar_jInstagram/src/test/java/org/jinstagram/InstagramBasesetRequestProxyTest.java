package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.lang.reflect.Field;

public class InstagramBasesetRequestProxyTest {
    private InstagramBase instagramBase;
    private Proxy testProxy;

    @Before
    public void setUp() throws Exception {
        // Create a concrete subclass for testing
        instagramBase = new InstagramBase(new InstagramConfig()) {
            // Empty implementation to make the class concrete
        };
        testProxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("localhost", 8080));
    }

    @After
    public void tearDown() {
        instagramBase = null;
        testProxy = null;
    }

    @Test
    public void testSetRequestProxy_setsProxyCorrectly() throws Exception {
        // Act
        instagramBase.setRequestProxy(testProxy);

        // Assert
        Field requestProxyField = InstagramBase.class.getDeclaredField("requestProxy");
        requestProxyField.setAccessible(true);
        Proxy actualProxy = (Proxy) requestProxyField.get(instagramBase);
        Assert.assertEquals(testProxy, actualProxy);
    }
}
