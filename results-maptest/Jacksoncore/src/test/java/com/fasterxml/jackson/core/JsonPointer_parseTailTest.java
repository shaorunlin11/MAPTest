package com.fasterxml.jackson.core;
import org.junit.Test;
import org.junit.Assert;

public class JsonPointer_parseTailTest {

    @Test
    public void testParseTail() {
        // Input conditions: input is not null, input.length() > 1, input.charAt(1) == '/'
        String input = "/a/b/c";

        // This test will execute line 431 of the _parseTail method
        JsonPointer result = JsonPointer._parseTail(input);

        // Basic assertion to verify the method execution
        Assert.assertNotNull(result);
    }
}
