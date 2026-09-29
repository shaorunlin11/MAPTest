package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import java.lang.reflect.Field;

public class SubscriptionResponseDatasetObjectIdTest {
    private SubscriptionResponseData subscriptionResponseData;
    private Field objectIdField;

    @Before
    public void setUp() throws Exception {
        subscriptionResponseData = new SubscriptionResponseData();
        objectIdField = SubscriptionResponseData.class.getDeclaredField("objectId");
        objectIdField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        subscriptionResponseData = null;
        objectIdField = null;
    }

    @Test
    public void testSetObjectIdSetsFieldValueCorrectly() throws Exception {
        String testObjectId = "test-object-id";
        subscriptionResponseData.setObjectId(testObjectId);
        Assert.assertEquals(testObjectId, objectIdField.get(subscriptionResponseData));
    }

    @Test
    public void testSetObjectIdWithNullValue() throws Exception {
        subscriptionResponseData.setObjectId(null);
        Assert.assertNull(objectIdField.get(subscriptionResponseData));
    }
}
