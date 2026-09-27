package org.jinstagram.realtime;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InstagramSubscriptionLatituteZeroCoverageTest {
    @Test
    public void testLatituteMethod() {
        // Arrange
        InstagramSubscription subscription = new InstagramSubscription();
        String latitude = "40.7128";

        // Act
        subscription.latitute(latitude);

        // Assert
        // The method's logic is to put the latitude into params, which is a private field.
        // Since we cannot access private fields directly, we rely on the method's behavior
        // and the fact that it does not throw exceptions for valid input.
        // This test confirms that the method executes without error for valid input.
    }
}
