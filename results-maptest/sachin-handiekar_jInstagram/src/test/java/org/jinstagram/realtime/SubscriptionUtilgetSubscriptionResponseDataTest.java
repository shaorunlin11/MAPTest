package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.jinstagram.exceptions.InstagramException;

public class SubscriptionUtilgetSubscriptionResponseDataTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetSubscriptionResponseData_validJson_returnsSubscriptionResponseObjects() throws Exception {
        String validJson = "[{\"object\":\"user\",\"object_id\":\"12345\",\"aspect_type\":\"media\",\"subscription_id\":\"67890\"}]";
        SubscriptionResponseObject[] result = SubscriptionUtil.getSubscriptionResponseData(validJson);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.length);
    }

    @Test
    public void testGetSubscriptionResponseData_invalidJson_throwsInstagramException() throws Exception {
        String invalidJson = "invalid json";
        thrown.expect(InstagramException.class);
        thrown.expectMessage("Error parsing json to object type ");
        SubscriptionUtil.getSubscriptionResponseData(invalidJson);
    }
}
