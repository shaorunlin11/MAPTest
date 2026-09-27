package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class TextBufferAppendZeroCoverage_1597Test {
    @Test
    public void testAppendWithInputStartGe0() {
        TextBuffer textBuffer = new TextBuffer(new BufferRecycler());
        char[] input = {'a', 'b', 'c', 'd', 'e'};
        // Use public method to set input start if available
        // If no public method exists, consider using reflection or refactoring
        // For this example, we'll assume a public method exists for demonstration
        textBuffer.resetWithCopy(input, 0, 3);
        textBuffer.append(input, 0, 3);
    }
}
