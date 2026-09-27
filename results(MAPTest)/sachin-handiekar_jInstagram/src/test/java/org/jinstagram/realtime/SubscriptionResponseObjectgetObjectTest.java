package org.jinstagram.realtime;
import org.junit.Test;
import org.junit.Assert;
public class SubscriptionResponseObjectgetObjectTest {
    @Test
    public void testGetObject_ReturnsNullWhenNotSet() throws Exception {
        SubscriptionResponseObject obj = new SubscriptionResponseObject();
        String result = obj.getObject();
        Assert.assertNull(result);
    }
}
