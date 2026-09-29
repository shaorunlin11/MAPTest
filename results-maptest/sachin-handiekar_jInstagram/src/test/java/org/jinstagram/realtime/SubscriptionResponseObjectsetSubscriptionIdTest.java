package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;

public class SubscriptionResponseObjectsetSubscriptionIdTest {

    @Test
    public void testSetSubscriptionId() throws Exception {
        SubscriptionResponseObject obj = new SubscriptionResponseObject();
        String testValue = "test_subscription_id";

        obj.setSubscriptionId(testValue);

        // Use reflection to verify the field was set
        java.lang.reflect.Field field = SubscriptionResponseObject.class.getDeclaredField("subscriptionId");
        field.setAccessible(true);
        String result = (String) field.get(obj);

        Assert.assertEquals(testValue, result);
    }

    @Test
    public void testSetSubscriptionIdWithNull() throws Exception {
        SubscriptionResponseObject obj = new SubscriptionResponseObject();

        obj.setSubscriptionId(null);

        // Use reflection to verify the field was set to null
        java.lang.reflect.Field field = SubscriptionResponseObject.class.getDeclaredField("subscriptionId");
        field.setAccessible(true);
        Object result = field.get(obj);

        Assert.assertNull(result);
    }
}
