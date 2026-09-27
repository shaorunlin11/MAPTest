package com.fasterxml.jackson.core;
import org.junit.Test;
import org.junit.Assert;
public class JsonPointerheadTest {
    @Test
    public void testExample() {
        // Example test method with @Test annotation
        Assert.assertTrue(true);
    }

@Test
    public void testHeadWithNullHeadAndNonEmpty() {
        // Create a JsonPointer instance where _head is null and this is not EMPTY
        // We can achieve this by using the protected constructor and setting _head to null
        // Since we cannot directly set _head, we can create a JsonPointer that is not EMPTY and has no head
        // For this, we can use the compile method to create a non-empty JsonPointer
        JsonPointer pointer = JsonPointer.compile("/a/b/c");

        // Ensure that the pointer is not EMPTY
        Assert.assertFalse(pointer == JsonPointer.EMPTY);

        // Call head() method
        JsonPointer head = pointer.head();

        // Verify that the head is not null (since _constructHead() should be called)
        Assert.assertNotNull(head);

        // Verify that the head is the same as the original pointer (since _constructHead() may return this)
        Assert.assertEquals("/a/b", head.toString());
    }
}
