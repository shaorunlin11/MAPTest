package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;

public class SubscriptionResponseDatagetIdTest {

    @Test
    public void testGetId() throws Exception {
        SubscriptionResponseData data = new SubscriptionResponseData();
        String expectedId = "testId";

        // Use reflection to set the private id field
        Field idField = SubscriptionResponseData.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(data, expectedId);

        String actualId = data.getId();
        Assert.assertEquals(expectedId, actualId);
    }
}
