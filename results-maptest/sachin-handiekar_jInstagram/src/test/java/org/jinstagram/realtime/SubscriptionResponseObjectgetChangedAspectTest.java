package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class SubscriptionResponseObjectgetChangedAspectTest {
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
    public void testGetChangedAspectReturnsNullWhenNotSet() {
        Assert.assertNull(subscriptionResponseObject.getChangedAspect());
    }

    @Test
    public void testGetChangedAspectReturnsSetStringValue() throws Exception {
        String expectedChangedAspect = "test_aspect";
        Field changedAspectField = SubscriptionResponseObject.class.getDeclaredField("changedAspect");
        changedAspectField.setAccessible(true);
        changedAspectField.set(subscriptionResponseObject, expectedChangedAspect);

        Assert.assertEquals(expectedChangedAspect, subscriptionResponseObject.getChangedAspect());
    }
}
