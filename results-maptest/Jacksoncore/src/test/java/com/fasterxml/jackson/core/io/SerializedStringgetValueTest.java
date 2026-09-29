package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerializedStringgetValueTest {
    @Test
    public void testGetValue() throws Exception {
        String testValue = "test string";
        SerializedString serializedString = new SerializedString(testValue);
        assertEquals("Expected the getValue method to return the initialized value", testValue, serializedString.getValue());
    }
}
