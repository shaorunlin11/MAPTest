package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;

public class UTF8WriterconvertSurrogateTest {
    @Test
    public void testConvertSurrogateWithValidSurrogatePair() throws Exception {
        // Use reflection to create IOContext with required parameters
        Class<?> ioContextClass = Class.forName("com.fasterxml.jackson.core.io.IOContext");
        Object ioContext = ioContextClass.getConstructor(
            com.fasterxml.jackson.core.util.BufferRecycler.class,
            Object.class,
            boolean.class
        ).newInstance(
            new com.fasterxml.jackson.core.util.BufferRecycler(),
            new Object(),
            false
        );

        UTF8Writer writer = new UTF8Writer((com.fasterxml.jackson.core.io.IOContext) ioContext, new ByteArrayOutputStream());

        // Use reflection to set _surrogate field
        Field surrogateField = UTF8Writer.class.getDeclaredField("_surrogate");
        surrogateField.setAccessible(true);
        surrogateField.setInt(writer, 0xD800); // Valid first surrogate

        int result = writer.convertSurrogate(0xDC00); // Valid second surrogate
        Assert.assertEquals(0x10000, result);
    }

    @Test(expected = IOException.class)
    public void testConvertSurrogateWithInvalidSecondPart() throws Exception {
        // Use reflection to create IOContext with required parameters
        Class<?> ioContextClass = Class.forName("com.fasterxml.jackson.core.io.IOContext");
        Object ioContext = ioContextClass.getConstructor(
            com.fasterxml.jackson.core.util.BufferRecycler.class,
            Object.class,
            boolean.class
        ).newInstance(
            new com.fasterxml.jackson.core.util.BufferRecycler(),
            new Object(),
            false
        );

        UTF8Writer writer = new UTF8Writer((com.fasterxml.jackson.core.io.IOContext) ioContext, new ByteArrayOutputStream());

        // Use reflection to set _surrogate field
        Field surrogateField = UTF8Writer.class.getDeclaredField("_surrogate");
        surrogateField.setAccessible(true);
        surrogateField.setInt(writer, 0xD800); // Valid first surrogate

        writer.convertSurrogate(0xDBFF); // Invalid second surrogate
    }
}
