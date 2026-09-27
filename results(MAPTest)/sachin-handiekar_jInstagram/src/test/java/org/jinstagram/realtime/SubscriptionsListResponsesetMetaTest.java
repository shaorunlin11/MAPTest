package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.Meta;

public class SubscriptionsListResponsesetMetaTest {

    @Test
    public void testSetMeta() throws Exception {
        SubscriptionsListResponse response = new SubscriptionsListResponse();
        Meta meta = new Meta();

        response.setMeta(meta);

        Assert.assertEquals(meta, response.getMeta());
    }
}
