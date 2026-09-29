package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class SubscriptionResponseDatasetCallbackUrlTest {
    private SubscriptionResponseData subscriptionResponseData;

    @Before
    public void setUp() {
        subscriptionResponseData = new SubscriptionResponseData();
    }

    @Test
    public void testSetCallbackUrl() throws Exception {
        String expectedCallbackUrl = "https://example.com/callback";
        subscriptionResponseData.setCallbackUrl(expectedCallbackUrl);

        Field callbackUrlField = SubscriptionResponseData.class.getDeclaredField("callbackUrl");
        callbackUrlField.setAccessible(true);
        String actualCallbackUrl = (String) callbackUrlField.get(subscriptionResponseData);

        assertEquals(expectedCallbackUrl, actualCallbackUrl);
    }

    @Test
    public void testSetCallbackUrlWithNull() throws Exception {
        subscriptionResponseData.setCallbackUrl(null);

        Field callbackUrlField = SubscriptionResponseData.class.getDeclaredField("callbackUrl");
        callbackUrlField.setAccessible(true);
        String actualCallbackUrl = (String) callbackUrlField.get(subscriptionResponseData);

        assertNull(actualCallbackUrl);
    }
}
