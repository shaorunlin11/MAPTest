package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

import org.jinstagram.entity.common.Meta;

public class SubscriptionsListResponsegetMetaTest {

    @Test
    public void testGetMeta() throws Exception {
        SubscriptionsListResponse response = new SubscriptionsListResponse();
        Meta expectedMeta = new Meta();

        // Use reflection to set the private 'meta' field
        java.lang.reflect.Field metaField = SubscriptionsListResponse.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(response, expectedMeta);

        Meta actualMeta = response.getMeta();
        Assert.assertEquals(expectedMeta, actualMeta);
    }
}
