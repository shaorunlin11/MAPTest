package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;

public class SubscriptionResponseObjectsetChangedAspectTest {

    @Test
    public void testSetChangedAspect() throws Exception {
        SubscriptionResponseObject obj = new SubscriptionResponseObject();
        String expected = "testAspect";
        obj.setChangedAspect(expected);
        Assert.assertEquals(expected, obj.getChangedAspect());
    }
}
