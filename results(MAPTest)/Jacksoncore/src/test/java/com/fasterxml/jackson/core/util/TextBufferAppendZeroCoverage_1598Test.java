package com.fasterxml.jackson.core.util;

import org.junit.Test;

import java.lang.reflect.Field;

public class TextBufferAppendZeroCoverage_1598Test {
    @Test
    public void testAppendWithInputStartNonNegative() {
        // Create a TextBuffer instance with a BufferRecycler
        BufferRecycler allocator = new BufferRecycler();
        TextBuffer textBuffer = new TextBuffer(allocator);

        // Set _inputStart to a non-negative value to trigger the unshare call
        // Using public method if available, otherwise reflection
        try {
            Field inputStartField = TextBuffer.class.getDeclaredField("_inputStart");
            inputStartField.setAccessible(true);
            inputStartField.setInt(textBuffer, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set _resultString and _resultArray to null as required
        // Using public method if available, otherwise reflection
        try {
            Field resultStringField = TextBuffer.class.getDeclaredField("_resultString");
            resultStringField.setAccessible(true);
            resultStringField.set(textBuffer, null);

            Field resultArrayField = TextBuffer.class.getDeclaredField("_resultArray");
            resultArrayField.setAccessible(true);
            resultArrayField.set(textBuffer, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Call the append method with a string, offset, and len
        textBuffer.append("test", 0, 4);
    }

    @Test
    public void testAppendWithInputStartNegativeAndMaxGeLen() {
        // Create a TextBuffer instance with a BufferRecycler
        BufferRecycler allocator = new BufferRecycler();
        TextBuffer textBuffer = new TextBuffer(allocator);

        // Set _inputStart to a negative value to bypass the unshare check
        // Using public method if available, otherwise reflection
        try {
            Field inputStartField = TextBuffer.class.getDeclaredField("_inputStart");
            inputStartField.setAccessible(true);
            inputStartField.setInt(textBuffer, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set _currentSegment and _currentSize to ensure curr.length - _currentSize >= len
        // Using public method if available, otherwise reflection
        try {
            Field currentSegmentField = TextBuffer.class.getDeclaredField("_currentSegment");
            currentSegmentField.setAccessible(true);
            char[] currentSegment = new char[10];
            currentSegmentField.set(textBuffer, currentSegment);

            Field currentSizeField = TextBuffer.class.getDeclaredField("_currentSize");
            currentSizeField.setAccessible(true);
            currentSizeField.setInt(textBuffer, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Call the append method with a string, offset, and len
        textBuffer.append("test", 0, 4);
    }

    @Test
    public void testAppendWithInputStartNegativeAndMaxLtLen() {
        // Create a TextBuffer instance with a BufferRecycler
        BufferRecycler allocator = new BufferRecycler();
        TextBuffer textBuffer = new TextBuffer(allocator);

        // Set _inputStart to a negative value to bypass the unshare check
        // Using public method if available, otherwise reflection
        try {
            Field inputStartField = TextBuffer.class.getDeclaredField("_inputStart");
            inputStartField.setAccessible(true);
            inputStartField.setInt(textBuffer, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set _currentSegment and _currentSize to ensure curr.length - _currentSize < len
        // Using public method if available, otherwise reflection
        try {
            Field currentSegmentField = TextBuffer.class.getDeclaredField("_currentSegment");
            currentSegmentField.setAccessible(true);
            char[] currentSegment = new char[5];
            currentSegmentField.set(textBuffer, currentSegment);

            Field currentSizeField = TextBuffer.class.getDeclaredField("_currentSize");
            currentSizeField.setAccessible(true);
            currentSizeField.setInt(textBuffer, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Call the append method with a string, offset, and len
        textBuffer.append("test", 0, 4);
    }
}
