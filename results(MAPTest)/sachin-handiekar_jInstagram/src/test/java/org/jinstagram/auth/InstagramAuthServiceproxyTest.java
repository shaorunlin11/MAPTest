package org.jinstagram.auth;

import org.junit.Test;
import static org.junit.Assert.*;

import java.net.InetSocketAddress;
import java.net.Proxy;

import java.lang.reflect.Field;


public class InstagramAuthServiceproxyTest {

    @Test
    public void testProxySetsRequestProxyAndReturnsThis() throws Exception {
        // Arrange
        InstagramAuthService authService = new InstagramAuthService();
        Proxy mockProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));

        // Act
        InstagramAuthService result = authService.proxy(mockProxy);

        // Assert
        assertEquals(authService, result);

        // Use reflection to verify the private field is set
        Field requestProxyField = InstagramAuthService.class.getDeclaredField("requestProxy");
        requestProxyField.setAccessible(true);
        assertEquals(mockProxy, requestProxyField.get(authService));
    }
}
