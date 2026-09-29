package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;

public class SubscriptionResponseDatagetAspectTest {

    @Test
    public void testGetAspect() throws Exception {
        SubscriptionResponseData subscriptionResponseData = new SubscriptionResponseData();
        String expectedAspect = "testAspect";

        // Use reflection to set the private field 'aspect'
        java.lang.reflect.Field aspectField = SubscriptionResponseData.class.getDeclaredField("aspect");
        aspectField.setAccessible(true);
        aspectField.set(subscriptionResponseData, expectedAspect);

        String actualAspect = subscriptionResponseData.getAspect();
        Assert.assertEquals(expectedAspect, actualAspect);
    }
}
