package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

import org.jinstagram.entity.common.Meta;

public class SubscriptionResponsegetMetaTest {

    @Test
    public void testGetMetaReturnsNullWhenNotInitialized() {
        SubscriptionResponse response = new SubscriptionResponse();
        Assert.assertNull(response.getMeta());
    }

    @Test
    public void testGetMetaReturnsSetMetaObject() throws Exception {
        SubscriptionResponse response = new SubscriptionResponse();
        Meta expectedMeta = new Meta();
        Field metaField = SubscriptionResponse.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(response, expectedMeta);

        Assert.assertEquals(expectedMeta, response.getMeta());
    }
}
