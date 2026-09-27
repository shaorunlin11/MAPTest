package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;

import com.fasterxml.jackson.core.io.JsonStringEncoder;

public class BufferRecyclersgetJsonStringEncoderTest {

    @Test
    public void testGetJsonStringEncoder_FirstCallCreatesNewInstance() {
        JsonStringEncoder encoder = BufferRecyclers.getJsonStringEncoder();
        assertNotNull("First call should return a non-null JsonStringEncoder", encoder);
    }

    @Test
    public void testGetJsonStringEncoder_SubsequentCallsReturnSameInstance() {
        JsonStringEncoder encoder1 = BufferRecyclers.getJsonStringEncoder();
        JsonStringEncoder encoder2 = BufferRecyclers.getJsonStringEncoder();
        assertSame("Subsequent calls should return the same instance", encoder1, encoder2);
    }

    @Test
    public void testGetJsonStringEncoder_ReturnsNonNull() {
        JsonStringEncoder encoder = BufferRecyclers.getJsonStringEncoder();
        assertNotNull("Method should return a non-null JsonStringEncoder", encoder);
    }
}
