package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;

public class SubscriptionResponsegetDataTest {

    @Test
    public void testGetDataReturnsDataField() throws Exception {
        SubscriptionResponse response = new SubscriptionResponse();
        SubscriptionResponseData expectedData = new SubscriptionResponseData();

        // Use reflection to set the data field
        java.lang.reflect.Field dataField = SubscriptionResponse.class.getDeclaredField("data");
        dataField.setAccessible(true);
        dataField.set(response, expectedData);

        SubscriptionResponseData result = response.getData();
        Assert.assertEquals(expectedData, result);
    }

    @Test
    public void testGetDataReturnsNullWhenDataNotSet() throws Exception {
        SubscriptionResponse response = new SubscriptionResponse();

        // Use reflection to check the data field
        java.lang.reflect.Field dataField = SubscriptionResponse.class.getDeclaredField("data");
        dataField.setAccessible(true);
        Object result = dataField.get(response);

        Assert.assertNull(result);
    }
}
