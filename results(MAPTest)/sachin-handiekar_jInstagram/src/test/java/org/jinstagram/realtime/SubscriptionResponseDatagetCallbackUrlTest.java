package org.jinstagram.realtime;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class SubscriptionResponseDatagetCallbackUrlTest {

    @Test
    public void testGetCallbackUrl() throws Exception {
        SubscriptionResponseData response = new SubscriptionResponseData();
        String expected = "https://example.com/callback";
        Field field = SubscriptionResponseData.class.getDeclaredField("callbackUrl");
        field.setAccessible(true);
        field.set(response, expected);
        assertEquals(expected, response.getCallbackUrl());
    }
}
