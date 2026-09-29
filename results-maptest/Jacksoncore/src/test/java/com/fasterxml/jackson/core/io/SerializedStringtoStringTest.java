package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerializedStringtoStringTest {
    @Test
    public void testToStringReturnsValue() throws Exception {
        String testValue = "test string";
        SerializedString serializedString = new SerializedString(testValue);
        assertEquals(testValue, serializedString.toString());
    }
}
