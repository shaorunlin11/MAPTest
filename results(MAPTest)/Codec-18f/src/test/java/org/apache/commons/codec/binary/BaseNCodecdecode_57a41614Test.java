package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;

import org.apache.commons.codec.DecoderException;

public class BaseNCodecdecode_57a41614Test {

    @Test
    public void testDecode_NullInput() {
        BaseNCodec codec = new BaseNCodec(1, 1, 0, 0) {
            @Override
            void decode(byte[] pArray, int i, int length, Context context) {
                // Empty implementation for testing
            }

            @Override
            public Object decode(Object obj) throws DecoderException {
                return null;
            }

            @Override
            public byte[] decode(String pArray) {
                return null;
            }

            @Override
            protected boolean isInAlphabet(byte b) {
                return true;
            }

            @Override
            public void encode(byte[] pArray, int i, int length, Context context) {
                // Required by the interface
            }
        };

        byte[] result = codec.decode((String) null);
        Assert.assertNull(result);
    }

    @Test
    public void testDecode_EmptyInput() {
        BaseNCodec codec = new BaseNCodec(1, 1, 0, 0) {
            @Override
            void decode(byte[] pArray, int i, int length, Context context) {
                // Empty implementation for testing
            }

            @Override
            public Object decode(Object obj) throws DecoderException {
                return null;
            }

            @Override
            public byte[] decode(String pArray) {
                return new byte[0];
            }

            @Override
            protected boolean isInAlphabet(byte b) {
                return true;
            }

            @Override
            public void encode(byte[] pArray, int i, int length, Context context) {
                // Required by the interface
            }
        };

        byte[] result = codec.decode((byte[]) new byte[0]);
        Assert.assertArrayEquals(new byte[0], result);
    }

    @Test
    public void testDecode_NormalInput() {
        BaseNCodec codec = new BaseNCodec(1, 1, 0, 0) {
            @Override
            void decode(byte[] pArray, int i, int length, Context context) {
                context.pos = 1;
            }

            @Override
            public Object decode(Object obj) throws DecoderException {
                return null;
            }

            @Override
            public byte[] decode(String pArray) {
                return new byte[0];
            }

            @Override
            protected boolean isInAlphabet(byte b) {
                return true;
            }

            @Override
            public void encode(byte[] pArray, int i, int length, Context context) {
                // Required by the interface
            }
        };

        byte[] input = { 'a' };
        byte[] result = codec.decode(input);
        Assert.assertEquals(1, result.length);
    }

@Test
    public void testDecode_NullOrEmptyInput() {
        BaseNCodec codec = new BaseNCodec(1, 1, 0, 0) {
            @Override
            void decode(byte[] pArray, int i, int length, Context context) {
                // Empty implementation for testing
            }

            @Override
            public Object decode(Object obj) throws DecoderException {
                return null;
            }

            @Override
            public byte[] decode(String pArray) {
                return null;
            }

            @Override
            protected boolean isInAlphabet(byte b) {
                return true;
            }

            @Override
            public void encode(byte[] pArray, int i, int length, Context context) {
                // Required by the interface
            }
        };

        // Test null input
        byte[] resultNull = codec.decode((byte[]) null);
        Assert.assertNull(resultNull);

        // Test empty input
        byte[] resultEmpty = codec.decode(new byte[0]);
        Assert.assertArrayEquals(new byte[0], resultEmpty);
    }
}
