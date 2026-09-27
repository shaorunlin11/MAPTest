package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContext_verifyRelease_6f086b69Test {
    @Test
    public void testVerifyRelease_SameArray_NoException() throws Exception {
        IOContext context = new IOContext(new BufferRecycler(), new Object(), false);
        char[] src = new char[10];
        char[] toRelease = src;
        context._verifyRelease(toRelease, src);
    }

    @Test
    public void testVerifyRelease_LengthGreaterOrEqual_NoException() throws Exception {
        IOContext context = new IOContext(new BufferRecycler(), new Object(), false);
        char[] src = new char[10];
        char[] toRelease = new char[10];
        context._verifyRelease(toRelease, src);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyRelease_DifferentArrayAndShorter_ThrowsException() throws Exception {
        IOContext context = new IOContext(new BufferRecycler(), new Object(), false);
        char[] src = new char[10];
        char[] toRelease = new char[5];
        context._verifyRelease(toRelease, src);
    }
}
