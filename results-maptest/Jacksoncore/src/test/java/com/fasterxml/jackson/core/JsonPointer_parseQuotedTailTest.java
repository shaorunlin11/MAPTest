package com.fasterxml.jackson.core;
import org.junit.Test;
import static org.junit.Assert.*;
public class JsonPointer_parseQuotedTailTest {
    @Test
    public void testParseQuotedTailWithSlash() {
        String input = "/test~0value/another";
        int i = 1;
        JsonPointer result = JsonPointer._parseQuotedTail(input, i);
        assertNotNull(result);
        assertEquals("~test~value", result._matchingPropertyName);
    }




    @Test
    public void testParseQuotedTailWithEmptyInput() {
        String input = "";
        int i = 0;
        try {
            JsonPointer._parseQuotedTail(input, i);
            fail("Expected exception not thrown");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
        }
    }
}
