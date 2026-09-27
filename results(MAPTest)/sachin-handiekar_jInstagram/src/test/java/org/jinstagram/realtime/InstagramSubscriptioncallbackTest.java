package org.jinstagram.realtime;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

import java.lang.reflect.Field;


public class InstagramSubscriptioncallbackTest {

    @Test
    public void testCallbackSetsValidUrl() throws Exception {
        InstagramSubscription subscription = new InstagramSubscription();
        String validUrl = "https://example.com/callback";
        InstagramSubscription result = subscription.callback(validUrl);

        assertEquals(subscription, result);

        Map<String, String> params = getParams(subscription);
        assertEquals(validUrl, params.get(Constants.CALLBACK_URL));
    }

    @Test
    public void testCallbackWithNullUrl() throws Exception {
        InstagramSubscription subscription = new InstagramSubscription();
        try {
            subscription.callback(null);
            fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testCallbackWithInvalidUrl() throws Exception {
        InstagramSubscription subscription = new InstagramSubscription();
        try {
            subscription.callback("invalid-url");
            fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    private Map<String, String> getParams(InstagramSubscription subscription) throws Exception {
        Field paramsField = InstagramSubscription.class.getDeclaredField("params");
        paramsField.setAccessible(true);
        return (Map<String, String>) paramsField.get(subscription);
    }
}
