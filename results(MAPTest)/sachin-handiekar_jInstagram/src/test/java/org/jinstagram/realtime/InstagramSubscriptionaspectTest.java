package org.jinstagram.realtime;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

public class InstagramSubscriptionaspectTest {

    @Test
    public void testAspectMethodWithValidAspect() {
        InstagramSubscription subscription = new InstagramSubscription();
        String aspect = "media";
        InstagramSubscription result = subscription.aspect(aspect);

        assertEquals(subscription, result);
        try {
            // Use reflection to access private field
            java.lang.reflect.Field paramsField = InstagramSubscription.class.getDeclaredField("params");
            paramsField.setAccessible(true);
            Map<String, String> params = (Map<String, String>) paramsField.get(subscription);
            assertEquals(aspect, params.get(Constants.ASPECT));
        } catch (Exception e) {
            fail("Failed to access private field: " + e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAspectMethodWithEmptyAspect() {
        InstagramSubscription subscription = new InstagramSubscription();
        subscription.aspect("");
    }
}
