package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonReadContexthasCurrentNameTest {

    @Test
    public void testHasCurrentNameWhenCurrentNameIsNull() throws Exception {
        JsonReadContext context = new JsonReadContext(null, null, 0, 0, 0);
        assertFalse(context.hasCurrentName());
    }

    @Test
    public void testHasCurrentNameWhenCurrentNameIsNotNull() throws Exception {
        JsonReadContext context = new JsonReadContext(null, null, 0, 0, 0);
        context._currentName = "test";
        assertTrue(context.hasCurrentName());
    }
}
