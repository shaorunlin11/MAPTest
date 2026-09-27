package org.jinstagram.realtime;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.HashMap;

import java.lang.reflect.Field;


public class InstagramSubscriptionobjectIdTest {

    @Test
    public void testObjectIdSetsCorrectValueInParams() throws Exception {
        InstagramSubscription subscription = new InstagramSubscription();
        String objectId = "testObjectId";
        subscription.objectId(objectId);

        Map<String, String> params = getParams(subscription);
        assertTrue(params.containsKey(Constants.OBJECT_ID));
        assertEquals(objectId, params.get(Constants.OBJECT_ID));
    }

    @Test
    public void testObjectIdReturnsThisForChaining() {
        InstagramSubscription subscription = new InstagramSubscription();
        InstagramSubscription result = subscription.objectId("test");
        assertSame(subscription, result);
    }

    private Map<String, String> getParams(InstagramSubscription subscription) throws Exception {
        Field paramsField = InstagramSubscription.class.getDeclaredField("params");
        paramsField.setAccessible(true);
        return (Map<String, String>) paramsField.get(subscription);
    }
}
