package com.fasterxml.jackson.core.exc;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonToken;

public class InputCoercionExceptionGetInputTypeZeroCoverageTest {
    @Test
    public void testGetInputType() {
        JsonToken inputType = JsonToken.VALUE_STRING;
        Class<?> targetType = String.class;
        InputCoercionException exception = new InputCoercionException(null, "test", inputType, targetType);
        assert exception.getInputType() == inputType;
    }
}
