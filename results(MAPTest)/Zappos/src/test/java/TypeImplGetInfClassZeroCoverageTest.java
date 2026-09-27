package com.zappos.json.util;

import org.junit.Test;

public class TypeImplGetInfClassZeroCoverageTest {
    @Test
    public void testGetInfClass() {
        // Create a TypeImpl instance with a non-null infClass
        Class<?> infClass = String.class;
        Class<?> implClass = String.class;
        TypeImpl typeImpl = TypeImpl.getMapImpl(infClass);

        // Verify that getInfClass returns the expected value
        assert typeImpl.getInfClass() == infClass;
    }
}
