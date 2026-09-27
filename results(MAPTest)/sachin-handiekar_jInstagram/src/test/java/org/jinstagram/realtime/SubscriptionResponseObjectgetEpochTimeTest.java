package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import java.lang.reflect.Field;


public class SubscriptionResponseObjectgetEpochTimeTest {
    private SubscriptionResponseObject subscriptionResponseObject;

    @Before
    public void setUp() {
        subscriptionResponseObject = new SubscriptionResponseObject();
    }

    @After
    public void tearDown() {
        subscriptionResponseObject = null;
    }

    @Test
    public void testGetEpochTime_ReturnsInitializedValue() throws Exception {
        long expectedEpochTime = 1625145600000L; // Example epoch time in milliseconds
        Field epochTimeField = SubscriptionResponseObject.class.getDeclaredField("epochTime");
        epochTimeField.setAccessible(true);
        epochTimeField.set(subscriptionResponseObject, expectedEpochTime);

        long actualEpochTime = subscriptionResponseObject.getEpochTime();
        Assert.assertEquals(expectedEpochTime, actualEpochTime);
    }
}
