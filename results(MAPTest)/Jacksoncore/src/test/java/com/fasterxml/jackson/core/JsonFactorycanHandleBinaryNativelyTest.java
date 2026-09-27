package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorycanHandleBinaryNativelyTest {
    @Test
    public void testCanHandleBinaryNativelyReturnsFalse() {
        JsonFactory factory = new JsonFactory();
        assertFalse(factory.canHandleBinaryNatively());
    }
}
