package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonPointervalueOfTest {
    @Test
    public void testValueOf() {
        String input = "/a/b/c";
        JsonPointer result = JsonPointer.valueOf(input);
        Assert.assertNotNull(result);
    }
}
