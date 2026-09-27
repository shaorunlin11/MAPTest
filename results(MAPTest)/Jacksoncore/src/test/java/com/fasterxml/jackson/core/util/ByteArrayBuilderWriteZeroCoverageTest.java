package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class ByteArrayBuilderWriteZeroCoverageTest {
    @Test
    public void testWriteWithValidInput() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        byte[] input = {1, 2, 3, 4, 5};
        int off = 0;
        int len = 5;

        builder.write(input, off, len);
    }

@Test
    public void testWriteWithZeroLength() {
        ByteArrayBuilder builder = new ByteArrayBuilder();
        byte[] input = {1, 2, 3, 4, 5};
        int off = 0;
        int len = 0;

        builder.write(input, off, len);
    }
}
