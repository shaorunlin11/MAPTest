package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonFactorycanParseAsyncTest {
    @Test
    public void testCanParseAsync() throws Exception {
        JsonFactory factory = new JsonFactory();
        boolean result = factory.canParseAsync();
        Assert.assertTrue(result);
    }
}
