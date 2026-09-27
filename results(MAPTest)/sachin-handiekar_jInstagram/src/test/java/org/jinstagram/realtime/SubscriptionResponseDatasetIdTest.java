package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class SubscriptionResponseDatasetIdTest {
    private SubscriptionResponseData subscriptionResponseData;

    @Before
    public void setUp() {
        subscriptionResponseData = new SubscriptionResponseData();
    }

    @Test
    public void testSetId() throws Exception {
        String expectedId = "test-id";
        subscriptionResponseData.setId(expectedId);

        Assert.assertEquals("The id should be set correctly", expectedId, subscriptionResponseData.getId());
    }
}
