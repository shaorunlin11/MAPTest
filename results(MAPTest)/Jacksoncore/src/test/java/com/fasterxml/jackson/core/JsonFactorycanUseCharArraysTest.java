package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorycanUseCharArraysTest {
    @Test
    public void testCanUseCharArraysReturnsTrue() {
        JsonFactory factory = new JsonFactory();
        assertTrue(factory.canUseCharArrays());
    }
}
