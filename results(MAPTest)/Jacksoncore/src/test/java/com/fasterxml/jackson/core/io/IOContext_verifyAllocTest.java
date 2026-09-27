package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContext_verifyAllocTest {
    @Test
    public void testVerifyAllocWithNullBufferDoesNotThrow() {
        IOContext context = new IOContext(new BufferRecycler(), new Object(), false);
        try {
            context._verifyAlloc(null);
        } catch (Exception e) {
            Assert.fail("Should not throw exception with null buffer");
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testVerifyAllocWithNonNullBufferThrowsIllegalStateException() {
        IOContext context = new IOContext(new BufferRecycler(), new Object(), false);
        context._verifyAlloc(new Object());
    }
}
