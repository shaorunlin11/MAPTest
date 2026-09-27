package org.jinstagram.realtime;

import org.junit.Test;

import org.jinstagram.InstagramConfig;


public class InstagramSubscriptionRequestConfigurationZeroCoverageTest {
    @Test
    public void testRequestConfigurationWithNonNullConfig() {
        InstagramSubscription subscription = new InstagramSubscription();
        InstagramConfig config = new InstagramConfig();
        subscription.requestConfiguration(config);
    }
}
