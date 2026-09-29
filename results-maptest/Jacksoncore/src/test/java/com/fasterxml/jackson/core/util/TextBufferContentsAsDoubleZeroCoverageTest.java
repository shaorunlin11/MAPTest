package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class TextBufferContentsAsDoubleZeroCoverageTest {
    @Test
    public void testContentsAsDouble() throws Exception {
        TextBuffer textBuffer = new TextBuffer(null);
        textBuffer.resetWithString("123.45");
        double result = textBuffer.contentsAsDouble();
        // This test ensures that the target lines are executed and the method is covered
        // The actual value is not asserted as per the requirements
    }
}
