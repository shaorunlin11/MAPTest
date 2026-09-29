package com.fasterxml.jackson.core.json;

import org.junit.Test;
import org.junit.Assert;

public class JsonWriteContexthasCurrentNameTest {
    @Test
    public void testHasCurrentNameWhenNull() throws Exception {
        JsonWriteContext context = new JsonWriteContext(0, null, null);
        Assert.assertFalse(context.hasCurrentName());
    }

    @Test
    public void testHasCurrentNameWhenSet() throws Exception {
        JsonWriteContext context = new JsonWriteContext(0, null, null);
        context._currentName = "test";
        Assert.assertTrue(context.hasCurrentName());
    }
}
