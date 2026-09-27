package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonPointerequalsTest {

    @Test
    public void testEqualsWithSameInstance() {
        JsonPointer pointer = new JsonPointer();
        assertTrue(pointer.equals(pointer));
    }

    @Test
    public void testEqualsWithNull() {
        JsonPointer pointer = new JsonPointer();
        assertFalse(pointer.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        JsonPointer pointer = new JsonPointer();
        assertFalse(pointer.equals("test"));
    }

    @Test
    public void testEqualsWithSameAsString() {
        JsonPointer pointer1 = new JsonPointer("/test", "test", null);
        JsonPointer pointer2 = new JsonPointer("/test", "test", null);
        assertTrue(pointer1.equals(pointer2));
    }

    @Test
    public void testEqualsWithDifferentAsString() {
        JsonPointer pointer1 = new JsonPointer("/test", "test", null);
        JsonPointer pointer2 = new JsonPointer("/other", "other", null);
        assertFalse(pointer1.equals(pointer2));
    }
}
