package org.apache.commons.codec.binary;

import org.junit.Test;

public class Base64EncodeZeroCoverageTest {
    @Test
    public void testEncodeWithEofTrue() {
        Base64 base64 = new Base64();
        byte[] in = new byte[0];
        int inPos = 0;
        int inAvail = -1;
        BaseNCodec.Context context = new BaseNCodec.Context();
        context.eof = true;
        base64.encode(in, inPos, inAvail, context);
    }

@Test
    public void testEncodeWithEofFalseAndInAvailNegative() {
        Base64 base64 = new Base64();
        byte[] in = new byte[0];
        int inPos = 0;
        int inAvail = -1;
        BaseNCodec.Context context = new BaseNCodec.Context();
        context.eof = false;
        base64.encode(in, inPos, inAvail, context);
    }

@Test
    public void testEncodeWithEofFalseAndInAvailNegativeAndModulusNotZero() {
        Base64 base64 = new Base64();
        byte[] in = new byte[0];
        int inPos = 0;
        int inAvail = -1;
        BaseNCodec.Context context = new BaseNCodec.Context();
        context.eof = false;
        context.modulus = 1;
        base64.encode(in, inPos, inAvail, context);
    }
}
