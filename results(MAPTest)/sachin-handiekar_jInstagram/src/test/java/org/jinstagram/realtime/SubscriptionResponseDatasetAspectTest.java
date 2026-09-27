package org.jinstagram.realtime;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class SubscriptionResponseDatasetAspectTest {
    private SubscriptionResponseData subscriptionResponseData;

    @Before
    public void setUp() {
        subscriptionResponseData = new SubscriptionResponseData();
    }

    @Test
    public void testSetAspectSetsAspectField() throws Exception {
        String expectedAspect = "testAspect";
        subscriptionResponseData.setAspect(expectedAspect);

        Field aspectField = SubscriptionResponseData.class.getDeclaredField("aspect");
        aspectField.setAccessible(true);
        String actualAspect = (String) aspectField.get(subscriptionResponseData);

        assertEquals(expectedAspect, actualAspect);
    }

    @Test
    public void testSetAspectWithNullValue() throws Exception {
        subscriptionResponseData.setAspect(null);

        Field aspectField = SubscriptionResponseData.class.getDeclaredField("aspect");
        aspectField.setAccessible(true);
        String actualAspect = (String) aspectField.get(subscriptionResponseData);

        assertNull(actualAspect);
    }

    @Test
    public void testSetAspectWithEmptyString() throws Exception {
        String emptyString = "";
        subscriptionResponseData.setAspect(emptyString);

        Field aspectField = SubscriptionResponseData.class.getDeclaredField("aspect");
        aspectField.setAccessible(true);
        String actualAspect = (String) aspectField.get(subscriptionResponseData);

        assertEquals(emptyString, actualAspect);
    }
}
