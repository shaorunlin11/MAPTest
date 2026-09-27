package com.fasterxml.jackson.core;
import org.junit.Test;
import org.junit.Assert;
public class JsonPointermayMatchPropertyTest {
    @Test
    public void testMayMatchPropertyWhenPropertyNameIsNotNull() throws Exception {
        // Create a JsonPointer with a non-null matching property name
        JsonPointer pointer = new JsonPointer("test", "property", null);

        // Verify that mayMatchProperty returns true
        Assert.assertTrue(pointer.mayMatchProperty());
    }
}
