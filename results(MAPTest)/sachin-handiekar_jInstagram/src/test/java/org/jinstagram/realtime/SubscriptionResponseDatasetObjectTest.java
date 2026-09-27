package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class SubscriptionResponseDatasetObjectTest {
    private SubscriptionResponseData subscriptionResponseData;

    @Before
    public void setUp() {
        subscriptionResponseData = new SubscriptionResponseData();
    }

    @After
    public void tearDown() {
        subscriptionResponseData = null;
    }

    @Test
    public void testSetObject() throws Exception {
        String expectedObject = "testObject";
        subscriptionResponseData.setObject(expectedObject);

        java.lang.reflect.Field objectField = SubscriptionResponseData.class.getDeclaredField("object");
        objectField.setAccessible(true);
        String actualObject = (String) objectField.get(subscriptionResponseData);

        Assert.assertEquals(expectedObject, actualObject);
    }
}
