package org.jinstagram.realtime;

import org.junit.Test;

public class SubscriptionResponseObjectSetEpochTimeZeroCoverageTest {
    @Test
    public void testSetEpochTime() {
        SubscriptionResponseObject responseObject = new SubscriptionResponseObject();
        long epochTime = 1234567890L;
        responseObject.setEpochTime(epochTime);
        // The target line is executed, and no further assertions are needed as per the requirements.
    }
}
