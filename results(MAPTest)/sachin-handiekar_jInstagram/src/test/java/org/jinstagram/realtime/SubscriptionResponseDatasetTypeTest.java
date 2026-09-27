package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class SubscriptionResponseDatasetTypeTest {
    private SubscriptionResponseData subscriptionResponseData;

    @Before
    public void setUp() {
        subscriptionResponseData = new SubscriptionResponseData();
    }

    @After
    public void tearDown() {
        subscriptionResponseData = null;
    }

    @Test
    public void testSetType() throws Exception {
        String expectedType = "testType";
        subscriptionResponseData.setType(expectedType);
        Assert.assertEquals(expectedType, subscriptionResponseData.getType());
    }

    @Test
    public void testSetTypeWithNull() throws Exception {
        subscriptionResponseData.setType(null);
        Assert.assertNull(subscriptionResponseData.getType());
    }
}
