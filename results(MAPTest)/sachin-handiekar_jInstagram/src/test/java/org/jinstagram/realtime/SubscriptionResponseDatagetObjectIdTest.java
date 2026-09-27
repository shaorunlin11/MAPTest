package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;

public class SubscriptionResponseDatagetObjectIdTest {

    @Test
    public void testGetObjectId() throws Exception {
        SubscriptionResponseData response = new SubscriptionResponseData();
        String expectedObjectId = "testObjectId";

        Field objectIdField = SubscriptionResponseData.class.getDeclaredField("objectId");
        objectIdField.setAccessible(true);
        objectIdField.set(response, expectedObjectId);

        String actualObjectId = response.getObjectId();
        Assert.assertEquals(expectedObjectId, actualObjectId);
    }

    @Test
    public void testGetObjectIdWhenNotSet() throws Exception {
        SubscriptionResponseData response = new SubscriptionResponseData();

        String actualObjectId = response.getObjectId();
        Assert.assertNull(actualObjectId);
    }
}
