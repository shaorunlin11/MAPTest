package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonPointerappendTest {
    @Test
    public void testAppendWithEmptyThis() {
        JsonPointer empty = JsonPointer.EMPTY;
        JsonPointer tail = new JsonPointer("/a/b", "b", 1, null);
        JsonPointer result = empty.append(tail);
        Assert.assertEquals(tail, result);
    }

    @Test
    public void testAppendWithEmptyTail() {
        JsonPointer thisPtr = new JsonPointer("/a/b", "b", 1, null);
        JsonPointer empty = JsonPointer.EMPTY;
        JsonPointer result = thisPtr.append(empty);
        Assert.assertEquals(thisPtr, result);
    }

    @Test
    public void testAppendWithoutTrailingSlash() {
        JsonPointer thisPtr = new JsonPointer("/a/b", "b", 1, null);
        JsonPointer tail = new JsonPointer("/c/d", "d", 3, null);
        JsonPointer result = thisPtr.append(tail);
        Assert.assertEquals("/a/b/c/d", result._asString);
    }

    @Test
    public void testAppendWithTrailingSlash() {
        JsonPointer thisPtr = new JsonPointer("/a/b/", "b", 1, null);
        JsonPointer tail = new JsonPointer("/c/d", "d", 3, null);
        JsonPointer result = thisPtr.append(tail);
        Assert.assertEquals("/a/b/c/d", result._asString);
    }

    @Test
    public void testAppendWithEmptyThisAndEmptyTail() {
        JsonPointer empty = JsonPointer.EMPTY;
        JsonPointer result = empty.append(empty);
        Assert.assertEquals(empty, result);
    }

    @Test
    public void testAppendWithNonEmptyThisAndNonEmptyTail() {
        JsonPointer thisPtr = new JsonPointer("/a/b", "b", 1, null);
        JsonPointer tail = new JsonPointer("/c/d", "d", 3, null);
        JsonPointer result = thisPtr.append(tail);
        Assert.assertEquals("/a/b/c/d", result._asString);
    }
}
