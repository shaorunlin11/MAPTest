package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;

public class BaseNCodecencode_856cced1Test {

    @Test
    public void testEncode_NullArray() {
        BaseNCodec codec = new BaseNCodec(1, 1, 0, 0) {
            @Override
            void encode(byte[] pArray, int i, int length, Context context) {
                // Dummy implementation for testing
            }

            @Override
            protected boolean isInAlphabet(byte b) {
                return true; // Required by abstract class
            }

            @Override
            void decode(byte[] pArray, int i, int length, Context context) {
                // Dummy implementation for testing
            }
        };
        byte[] result = codec.encode(null, 0, 0);
        Assert.assertNull(result);
    }

    @Test
    public void testEncode_EmptyArray() {
        BaseNCodec codec = new BaseNCodec(1, 1, 0, 0) {
            @Override
            void encode(byte[] pArray, int i, int length, Context context) {
                // Dummy implementation for testing
            }

            @Override
            protected boolean isInAlphabet(byte b) {
                return true; // Required by abstract class
            }

            @Override
            void decode(byte[] pArray, int i, int length, Context context) {
                // Dummy implementation for testing
            }
        };
        byte[] result = codec.encode(new byte[0], 0, 0);
        Assert.assertArrayEquals(new byte[0], result);
    }

    @Test
    public void testEncode_NormalCase() {
        BaseNCodec codec = new BaseNCodec(1, 1, 0, 0) {
            @Override
            void encode(byte[] pArray, int i, int length, Context context) {
                // Dummy implementation for testing
                context.pos = 1;
                context.readPos = 0;
            }

            @Override
            protected boolean isInAlphabet(byte b) {
                return true; // Required by abstract class
            }

            @Override
            void decode(byte[] pArray, int i, int length, Context context) {
                // Dummy implementation for testing
            }
        };
        byte[] input = { (byte) 0x48 };
        byte[] result = codec.encode(input, 0, 1);
        Assert.assertEquals(1, result.length);
    }
}
