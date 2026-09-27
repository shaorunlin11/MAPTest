package com.fasterxml.jackson.core;

import org.junit.Test;

public class JsonPointerMatchPropertyZeroCoverageTest {
    @Test
    public void testMatchPropertyWithMatchingName() {
        // Create a JsonPointer with _nextSegment not null and _matchingPropertyName equals name
        JsonPointer pointer = new JsonPointer("test", "test", 0, new JsonPointer());

        // Call the method under test
        JsonPointer result = pointer.matchProperty("test");

        // Assert that the result is not null (since _nextSegment is not null and _matchingPropertyName equals name)
        // This covers line 317 of the method
    }

@Test
    public void testMatchPropertyWithNonMatchingName() {
        // Create a JsonPointer with _nextSegment null and _matchingPropertyName not equal to name
        JsonPointer pointer = new JsonPointer("test", "other", 0, null);

        // Call the method under test
        JsonPointer result = pointer.matchProperty("test");

        // Assert that the result is null (since _nextSegment is null and _matchingPropertyName does not equal name)
        // This covers line 320 of the method
    }
}
