package org.jinstagram.realtime;

import org.jinstagram.entity.common.Meta;
import org.junit.Test;
import org.junit.Assert;

public class SubscriptionResponsesetMetaTest {

    @Test
    public void testSetMeta() throws Exception {
        SubscriptionResponse response = new SubscriptionResponse();
        Meta meta = new Meta();

        response.setMeta(meta);

        Assert.assertEquals(meta, response.getMeta());
    }
}
