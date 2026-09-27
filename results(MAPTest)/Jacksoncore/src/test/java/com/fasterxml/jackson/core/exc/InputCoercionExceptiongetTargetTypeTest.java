package com.fasterxml.jackson.core.exc;
import org.junit.Test;
import static org.junit.Assert.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class InputCoercionExceptiongetTargetTypeTest {
    @Test
    public void testGetTargetType() throws Exception {
        // Create an InputCoercionException with a non-null _targetType
        Class<?> targetType = String.class;
        InputCoercionException exception = new InputCoercionException(null, "test message", JsonToken.VALUE_STRING, targetType);

        // Verify that getTargetType returns the expected value
        assertEquals(targetType, exception.getTargetType());
    }
}
