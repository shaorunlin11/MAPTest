package com.fasterxml.jackson.core.io;

import org.junit.Test;
import java.io.InputStream;

public class UTF32ReaderReadZeroCoverage_870Test {
    @Test
    public void testReadWithNullBuffer() throws Exception {
        // Arrange
        IOContext context = new IOContext(null, null, false);
        InputStream in = new java.io.ByteArrayInputStream(new byte[0]);
        byte[] buffer = null;
        int ptr = 0;
        int len = 0;
        boolean isBigEndian = false;

        UTF32Reader reader = new UTF32Reader(context, in, buffer, ptr, len, isBigEndian);

        char[] cbuf = new char[1];
        int start = 0;
        int lenParam = 1;

        // Act and Assert
        // The method should return -1 because _buffer is null
        int result = reader.read(cbuf, start, lenParam);
        assert result == -1;
    }

@Test
    public void testReadWithNonNullBufferAndLenGe1() throws Exception {
        // Arrange
        IOContext context = new IOContext(null, null, false);
        InputStream in = new java.io.ByteArrayInputStream(new byte[0]);
        byte[] buffer = new byte[] { (byte) 0x41, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
        int ptr = 0;
        int len = 4;
        boolean isBigEndian = false;

        UTF32Reader reader = new UTF32Reader(context, in, buffer, ptr, len, isBigEndian);

        char[] cbuf = new char[1];
        int start = 0;
        int lenParam = 1;

        // Act and Assert
        // The method should execute target lines 106
        int result = reader.read(cbuf, start, lenParam);
        assert result == 1;
        assert cbuf[0] == 'A';
    }

@Test
    public void testReadWithNonNullBufferAndLenLessThan1() throws Exception {
        // Arrange
        IOContext context = new IOContext(null, null, false);
        InputStream in = new java.io.ByteArrayInputStream(new byte[0]);
        byte[] buffer = new byte[] { (byte) 0x41, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
        int ptr = 0;
        int len = 4;
        boolean isBigEndian = false;

        UTF32Reader reader = new UTF32Reader(context, in, buffer, ptr, len, isBigEndian);

        char[] cbuf = new char[1];
        int start = 0;
        int lenParam = 0;

        // Act and Assert
        // The method should return len (0) because len < 1
        int result = reader.read(cbuf, start, lenParam);
        assert result == 0;
    }

@Test
    public void testReadWithStartNegative() throws Exception {
        // Arrange
        IOContext context = new IOContext(null, null, false);
        InputStream in = new java.io.ByteArrayInputStream(new byte[0]);
        byte[] buffer = new byte[] { (byte) 0x41, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
        int ptr = 0;
        int len = 4;
        boolean isBigEndian = false;

        UTF32Reader reader = new UTF32Reader(context, in, buffer, ptr, len, isBigEndian);

        char[] cbuf = new char[1];
        int start = -1;
        int lenParam = 1;

        // Act and Assert
        // The method should throw ArrayIndexOutOfBoundsException due to negative start
        try {
            reader.read(cbuf, start, lenParam);
            // If no exception is thrown, fail the test
            assert false : "Expected ArrayIndexOutOfBoundsException";
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected exception, test passes
        }
    }
}
