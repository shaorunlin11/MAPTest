package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;

public class SubscriptionResponseObjectgetObjectIdTest {
    @Test
    public void testGetObjectId() throws Exception {
        SubscriptionResponseObject obj = new SubscriptionResponseObject();
        String expected = "test_object_id";

        // Use reflection to set the private field
        java.lang.reflect.Field objectIdField = SubscriptionResponseObject.class.getDeclaredField("objectId");
        objectIdField.setAccessible(true);
        objectIdField.set(obj, expected);

        Assert.assertEquals(expected, obj.getObjectId());
    }
}
