package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonPointerhashCodeTest {
    @Test
    public void testHashCode() {
        JsonPointer pointer1 = new JsonPointer("/a/b/c", "c", 2, null);
        JsonPointer pointer2 = new JsonPointer("/a/b/c", "c", 2, null);
        JsonPointer pointer3 = new JsonPointer("/a/b/d", "d", 2, null);

        Assert.assertEquals(pointer1.hashCode(), pointer2.hashCode());
        Assert.assertNotEquals(pointer1.hashCode(), pointer3.hashCode());
    }
}
