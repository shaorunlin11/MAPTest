package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.utils.Preconditions;
import java.util.Map;
import java.util.HashMap;

import java.lang.reflect.Field;


public class InstagramSubscriptionclientSecretTest {

    @Test
    public void testClientSecretWithNonEmptyValue() throws Exception {
        InstagramSubscription subscription = new InstagramSubscription();
        String clientSecret = "testSecret";
        InstagramSubscription result = subscription.clientSecret(clientSecret);

        Assert.assertEquals(subscription, result);
        Map<String, String> params = getParams(subscription);
        Assert.assertTrue(params.containsKey(Constants.CLIENT_SECRET));
        Assert.assertEquals(clientSecret, params.get(Constants.CLIENT_SECRET));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientSecretWithEmptyValue() throws Exception {
        InstagramSubscription subscription = new InstagramSubscription();
        subscription.clientSecret("");
    }

    private Map<String, String> getParams(InstagramSubscription subscription) throws Exception {
        Field paramsField = InstagramSubscription.class.getDeclaredField("params");
        paramsField.setAccessible(true);
        return (Map<String, String>) paramsField.get(subscription);
    }
}
