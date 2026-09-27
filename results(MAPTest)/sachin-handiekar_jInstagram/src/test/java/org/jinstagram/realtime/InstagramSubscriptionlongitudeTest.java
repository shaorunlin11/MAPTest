package org.jinstagram.realtime;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;

public class InstagramSubscriptionlongitudeTest {

    @Test
    public void testLongitudeSetsValidLongitude() throws NoSuchFieldException, IllegalAccessException {
        InstagramSubscription subscription = new InstagramSubscription();
        String validLongitude = "123.456";
        subscription.longitude(validLongitude);

        Field field = subscription.getClass().getDeclaredField("params");
        field.setAccessible(true);
        Map<String, String> params = (Map<String, String>) field.get(subscription);
        assertEquals(validLongitude, params.get(Constants.LONGITUDE));
    }

    @Test
    public void testLongitudeReturnsThisForChaining() {
        InstagramSubscription subscription = new InstagramSubscription();
        Object result = subscription.longitude("123.456");
        assertTrue(result instanceof InstagramSubscription);
    }

    @Test
    public void testLongitudeWithInvalidLongitudeThrowsException() {
        InstagramSubscription subscription = new InstagramSubscription();
        try {
            subscription.longitude("invalid");
            fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}
