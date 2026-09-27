package org.jinstagram.realtime;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class SubscriptionResponseDatagetTypeTest {

    @Test
    public void testGetType_ReturnsSetTypeValue() throws Exception {
        SubscriptionResponseData data = new SubscriptionResponseData();
        String expectedType = "test_type";
        Field typeField = SubscriptionResponseData.class.getDeclaredField("type");
        typeField.setAccessible(true);
        typeField.set(data, expectedType);

        String result = data.getType();
        assertEquals(expectedType, result);
    }

    @Test
    public void testGetType_ReturnsNullIfNotSet() throws Exception {
        SubscriptionResponseData data = new SubscriptionResponseData();
        Field typeField = SubscriptionResponseData.class.getDeclaredField("type");
        typeField.setAccessible(true);
        typeField.set(data, null);

        String result = data.getType();
        assertNull(result);
    }
}
