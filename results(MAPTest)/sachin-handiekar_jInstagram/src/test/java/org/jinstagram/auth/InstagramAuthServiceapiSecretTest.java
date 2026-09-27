package org.jinstagram.auth;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.jinstagram.utils.Preconditions;

public class InstagramAuthServiceapiSecretTest {
    private InstagramAuthService authService;

    @Before
    public void setUp() {
        authService = new InstagramAuthService();
    }

    @Test
    public void testApiSecretSetsValueWhenValid() throws Exception {
        String testApiSecret = "valid_api_secret";
        InstagramAuthService result = authService.apiSecret(testApiSecret);

        Assert.assertEquals("apiSecret should be set correctly", testApiSecret, getPrivateField(authService, "apiSecret"));
        Assert.assertSame("Should return the same instance for method chaining", authService, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApiSecretThrowsExceptionWhenEmpty() throws Exception {
        authService.apiSecret("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApiSecretThrowsExceptionWhenNull() throws Exception {
        authService.apiSecret(null);
    }

    private Object getPrivateField(Object obj, String fieldName) throws Exception {
        java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
