package com.fasterxml.jackson.core;

import org.junit.Test;

public class JsonPointer_constructHeadZeroCoverage_369Test {
    @Test
    public void testConstructHeadWithThisEqualToLast() {
        // Create a JsonPointer instance where this == last
        JsonPointer last = new JsonPointer();
        JsonPointer head = last._constructHead(0, last);

        // Verify that the method returns EMPTY when this == last
        assert head == JsonPointer.EMPTY;
    }
}
