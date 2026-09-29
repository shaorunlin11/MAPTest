package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.realtime.SubscriptionResponseData;

import java.lang.reflect.Method;
import java.lang.reflect.Field;


public class SubscriptionsListResponsesetDataTest {

    @Test
    public void testSetDataWithNonNullValue() throws Exception {
        SubscriptionsListResponse response = new SubscriptionsListResponse();
        List<SubscriptionResponseData> dataList = new ArrayList<SubscriptionResponseData>();
        dataList.add(new SubscriptionResponseData());

        Method setDataMethod = SubscriptionsListResponse.class.getDeclaredMethod("setData", List.class);
        setDataMethod.setAccessible(true);
        setDataMethod.invoke(response, dataList);

        Field dataField = SubscriptionsListResponse.class.getDeclaredField("data");
        dataField.setAccessible(true);
        List<SubscriptionResponseData> result = (List<SubscriptionResponseData>) dataField.get(response);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testSetDataWithNullValue() throws Exception {
        SubscriptionsListResponse response = new SubscriptionsListResponse();

        Method setDataMethod = SubscriptionsListResponse.class.getDeclaredMethod("setData", List.class);
        setDataMethod.setAccessible(true);
        setDataMethod.invoke(response, (List<SubscriptionResponseData>) null);

        Field dataField = SubscriptionsListResponse.class.getDeclaredField("data");
        dataField.setAccessible(true);
        List<SubscriptionResponseData> result = (List<SubscriptionResponseData>) dataField.get(response);

        Assert.assertNull(result);
    }
}
