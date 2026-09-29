package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;


public class SubscriptionResponseDatagetObjectTest {

    @Test
    public void testGetObject_ReturnsExpectedValue() throws Exception {
        SubscriptionResponseData data = new SubscriptionResponseData();
        String expectedObject = "testObject";
        Field objectField = SubscriptionResponseData.class.getDeclaredField("object");
        objectField.setAccessible(true);
        objectField.set(data, expectedObject);

        String result = data.getObject();

        Assert.assertEquals(expectedObject, result);
    }

    @Test
    public void testGetObject_ReturnsNullWhenNotSet() throws Exception {
        SubscriptionResponseData data = new SubscriptionResponseData();
        Field objectField = SubscriptionResponseData.class.getDeclaredField("object");
        objectField.setAccessible(true);
        objectField.set(data, null);

        String result = data.getObject();

        Assert.assertNull(result);
    }
}
