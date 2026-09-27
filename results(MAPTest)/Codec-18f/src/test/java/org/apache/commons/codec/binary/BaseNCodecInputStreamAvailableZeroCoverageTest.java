package org.apache.commons.codec.binary;

import org.junit.Test;

public class BaseNCodecInputStreamAvailableZeroCoverageTest {
    @Test
    public void testAvailableWithEofTrue() throws Exception {
        // This test is designed to execute target line 64 with context.eof = true
        // The method returns 0 when context.eof is true
        BaseNCodecInputStream inputStream = new BaseNCodecInputStream(null, null, false);
        // Use reflection to set the context.eof field
        java.lang.reflect.Field contextField = BaseNCodecInputStream.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Object context = contextField.get(inputStream);
        java.lang.reflect.Field eofField = context.getClass().getDeclaredField("eof");
        eofField.setAccessible(true);
        eofField.set(context, true);
        int available = inputStream.available();
        // Assertion to verify the behavior
        assert available == 0;
    }

    @Test
    public void testAvailableWithEofFalse() throws Exception {
        // This test is designed to execute target line 64 with context.eof = false
        // The method returns 1 when context.eof is false
        BaseNCodecInputStream inputStream = new BaseNCodecInputStream(null, null, false);
        // Use reflection to set the context.eof field
        java.lang.reflect.Field contextField = BaseNCodecInputStream.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Object context = contextField.get(inputStream);
        java.lang.reflect.Field eofField = context.getClass().getDeclaredField("eof");
        eofField.setAccessible(true);
        eofField.set(context, false);
        int available = inputStream.available();
        // Assertion to verify the behavior
        assert available == 1;
    }
}
