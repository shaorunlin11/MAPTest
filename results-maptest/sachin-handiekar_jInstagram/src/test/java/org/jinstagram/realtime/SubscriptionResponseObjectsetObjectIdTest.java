package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class SubscriptionResponseObjectsetObjectIdTest {
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
    public void testSetObjectId() throws Exception {
        String expectedObjectId = "testObjectId";
        subscriptionResponseObject.setObjectId(expectedObjectId);

        Assert.assertEquals(expectedObjectId, subscriptionResponseObject.getObjectId());
    }
}
