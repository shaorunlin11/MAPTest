package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorybuilderTest {
    @Test
    public void testBuilderMethodReturnsNewJsonFactoryBuilderInstance() {
        TSFBuilder<?,?> builder = JsonFactory.builder();
        assertTrue("Expected instance of JsonFactoryBuilder", builder instanceof JsonFactoryBuilder);
    }
}
