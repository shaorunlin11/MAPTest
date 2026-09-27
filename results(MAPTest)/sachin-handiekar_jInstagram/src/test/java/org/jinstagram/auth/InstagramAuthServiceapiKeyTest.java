package org.jinstagram.auth;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.utils.Preconditions;

public class InstagramAuthServiceapiKeyTest {
    @Test
    public void testApiKeyWithValidValue() throws Exception {
        InstagramAuthService service = new InstagramAuthService();
        String apiKey = "valid_api_key";
        InstagramAuthService result = service.apiKey(apiKey);

        Assert.assertEquals(service, result);
        Assert.assertEquals(apiKey, getFieldValue(service, "apiKey"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApiKeyWithEmptyString() throws Exception {
        InstagramAuthService service = new InstagramAuthService();
        service.apiKey("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApiKeyWithNull() throws Exception {
        InstagramAuthService service = new InstagramAuthService();
        service.apiKey(null);
    }

    private Object getFieldValue(Object obj, String fieldName) throws Exception {
        java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
