package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactoryrebuildTest {
    @Test
    public void testRebuildReturnsJsonFactoryBuilder() throws Exception {
        JsonFactory factory = new JsonFactory();
        TSFBuilder<?,?> builder = factory.rebuild();
        assertTrue(builder instanceof JsonFactoryBuilder);
    }
}
