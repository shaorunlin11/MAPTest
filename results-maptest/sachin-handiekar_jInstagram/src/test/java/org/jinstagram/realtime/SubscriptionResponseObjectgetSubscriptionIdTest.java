package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Ignore;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;


public class SubscriptionResponseObjectgetSubscriptionIdTest {

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
    public void testGetSubscriptionId() throws Exception {
        String expectedSubscriptionId = "test_subscription_id";
        Field subscriptionIdField = SubscriptionResponseObject.class.getDeclaredField("subscriptionId");
        subscriptionIdField.setAccessible(true);
        subscriptionIdField.set(subscriptionResponseObject, expectedSubscriptionId);

        String actualSubscriptionId = subscriptionResponseObject.getSubscriptionId();
        Assert.assertEquals(expectedSubscriptionId, actualSubscriptionId);
    }
}
