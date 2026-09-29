package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.InstagramConfig;
import org.jinstagram.auth.model.OAuthConstants;
import org.jinstagram.utils.Preconditions;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;

public class InstagramSubscriptionradiusTest {

    @Test
    public void testRadiusMethod() throws Exception {
        InstagramSubscription subscription = new InstagramSubscription();
        String radiusValue = "100";

        InstagramSubscription result = subscription.radius(radiusValue);

        Assert.assertNotNull("subscription should not be null", subscription);
        Assert.assertEquals("method should return this instance", subscription, result);

        // Use reflection to access private params field
        Field paramsField = InstagramSubscription.class.getDeclaredField("params");
        paramsField.setAccessible(true);
        Map<String, String> params = (Map<String, String>) paramsField.get(subscription);

        Assert.assertTrue("params should contain the radius key", params.containsKey(Constants.RADIUS));
        Assert.assertEquals("radius value should be set correctly", radiusValue, params.get(Constants.RADIUS));
    }
}
