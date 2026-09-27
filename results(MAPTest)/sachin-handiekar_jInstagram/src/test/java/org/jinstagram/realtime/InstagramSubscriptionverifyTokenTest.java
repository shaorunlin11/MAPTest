package org.jinstagram.realtime;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;

public class InstagramSubscriptionverifyTokenTest {

    @Test
    public void testVerifyTokenWithValidToken() {
        InstagramSubscription subscription = new InstagramSubscription();
        String testToken = "test-token";

        InstagramSubscription result = subscription.verifyToken(testToken);

        assertEquals(subscription, result);
        Map<String, String> params = getParams(subscription);
        assertNotNull(params);
        assertEquals(testToken, params.get(Constants.VERIFY_TOKEN));
    }

    private Map<String, String> getParams(InstagramSubscription subscription) {
        try {
            java.lang.reflect.Field field = InstagramSubscription.class.getDeclaredField("params");
            field.setAccessible(true);
            return (Map<String, String>) field.get(subscription);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access private field 'params'", e);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyTokenWithEmptyToken() {
        InstagramSubscription subscription = new InstagramSubscription();
        subscription.verifyToken("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyTokenWithNullToken() {
        InstagramSubscription subscription = new InstagramSubscription();
        subscription.verifyToken(null);
    }
}
