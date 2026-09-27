package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.lang.reflect.Field;

public class SubscriptionResponseObjectsetObjectTest {
    private SubscriptionResponseObject subscriptionResponseObject;
    private Field objectField;

    @Before
    public void setUp() throws Exception {
        subscriptionResponseObject = new SubscriptionResponseObject();
        objectField = SubscriptionResponseObject.class.getDeclaredField("object");
        objectField.setAccessible(true);
    }

    @Test
    public void testSetObjectWithNonNullValue() throws Exception {
        String testObject = "testObject";
        subscriptionResponseObject.setObject(testObject);
        Assert.assertEquals(testObject, objectField.get(subscriptionResponseObject));
    }

    @Test
    public void testSetObjectWithNullValue() throws Exception {
        subscriptionResponseObject.setObject(null);
        Assert.assertNull(objectField.get(subscriptionResponseObject));
    }
}
