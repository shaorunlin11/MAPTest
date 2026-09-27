package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class TokenFilterContextisStartHandledTest {

    @Test
    public void testIsStartHandledReturnsInitialValue() throws Exception {
        // Create a TokenFilterContext with startHandled set to true
        TokenFilterContext context = new TokenFilterContext(0, null, null, true);
        assertTrue("isStartHandled should return true when initialized with true", context.isStartHandled());

        // Create a TokenFilterContext with startHandled set to false
        TokenFilterContext context2 = new TokenFilterContext(0, null, null, false);
        assertFalse("isStartHandled should return false when initialized with false", context2.isStartHandled());
    }

    @Test
    public void testIsStartHandledAfterModification() throws Exception {
        // Create a TokenFilterContext with startHandled set to false
        TokenFilterContext context = new TokenFilterContext(0, null, null, false);

        // Modify the _startHandled field using reflection
        Field field = TokenFilterContext.class.getDeclaredField("_startHandled");
        field.setAccessible(true);
        field.set(context, true);

        assertTrue("isStartHandled should return true after modification", context.isStartHandled());
    }
}
