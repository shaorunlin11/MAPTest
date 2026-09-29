package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.realtime.SubscriptionResponseData;

public class SubscriptionsListResponsegetDataTest {

    @Test
    public void testGetData() throws Exception {
        // Arrange
        SubscriptionsListResponse response = new SubscriptionsListResponse();
        List<SubscriptionResponseData> expectedData = new ArrayList();
        expectedData.add(new SubscriptionResponseData());
        expectedData.add(new SubscriptionResponseData());

        // Use reflection to set the private 'data' field
        java.lang.reflect.Field dataField = SubscriptionsListResponse.class.getDeclaredField("data");
        dataField.setAccessible(true);
        dataField.set(response, expectedData);

        // Act
        List<SubscriptionResponseData> actualData = response.getData();

        // Assert
        Assert.assertEquals(expectedData, actualData);
    }
}
