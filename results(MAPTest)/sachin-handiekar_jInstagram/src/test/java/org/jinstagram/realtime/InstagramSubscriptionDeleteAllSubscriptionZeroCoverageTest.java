package org.jinstagram.realtime;

import org.junit.Test;
import static org.junit.Assert.*;
import org.jinstagram.auth.model.OAuthRequest;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.http.Response;
import org.jinstagram.http.Verbs;
import org.jinstagram.utils.Preconditions;
import java.io.IOException;

public class InstagramSubscriptionDeleteAllSubscriptionZeroCoverageTest {
    @Test
    public void testDeleteAllSubscription_ThrowsIOException() throws Exception {
        InstagramSubscription subscription = new InstagramSubscription();
        subscription.clientId("testClientId");
        subscription.clientSecret("testClientSecret");

        // Set Constants.SUBSCRIPTION_TYPE to a non-null and non-empty string
        // Since Constants.SUBSCRIPTION_TYPE is not directly accessible, we assume it's set in the environment or via configuration
        // For this test, we'll assume it's already set correctly

        try {
            subscription.deleteAllSubscription();
            fail("Expected InstagramException to be thrown");
        } catch (InstagramException e) {
            assertTrue(e.getMessage().contains("Failed to delete all subscriptions"));
            assertTrue(e.getCause() instanceof IOException);
        }
    }
}
