package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.realtime.SubscriptionResponseData;

public class SubscriptionResponsesetDataTest {

    @Test
    public void testSetData() throws Exception {
        SubscriptionResponse response = new SubscriptionResponse();
        SubscriptionResponseData testData = new SubscriptionResponseData();

        response.setData(testData);

        // Verify that the data field was set correctly
        java.lang.reflect.Field dataField = SubscriptionResponse.class.getDeclaredField("data");
        dataField.setAccessible(true);
        Object actualData = dataField.get(response);

        Assert.assertEquals(testData, actualData);
    }
}
