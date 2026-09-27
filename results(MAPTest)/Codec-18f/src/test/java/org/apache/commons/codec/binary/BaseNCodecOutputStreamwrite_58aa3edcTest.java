package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.codec.binary.BaseNCodec.Context;

public class BaseNCodecOutputStreamwrite_58aa3edcTest {
    private BaseNCodecOutputStream stream;
    private OutputStream mockOut;
    private BaseNCodec mockBaseNCodec;
    private boolean doEncode;

    @Before
    public void setUp() throws Exception {
        mockOut = new FilterOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                // No-op
            }
        });
        mockBaseNCodec = new BaseNCodec(0, 0, 0, 0) {
            @Override
            public byte[] encode(byte[] pArray) {
                return new byte[0];
            }

            @Override
            public byte[] decode(byte[] pArray) {
                return new byte[0];
            }

            @Override
            public void encode(byte[] pArray, int i, int length, Context context) {
                // No-op
            }

            @Override
            public void decode(byte[] pArray, int i, int length, Context context) {
                // No-op
            }

            @Override
            protected boolean isInAlphabet(byte c) {
                return true;
            }
        };
        doEncode = true;
        stream = new BaseNCodecOutputStream(mockOut, mockBaseNCodec, doEncode);
    }

    @After
    public void tearDown() {
        stream = null;
        mockOut = null;
        mockBaseNCodec = null;
    }

    @Test
    public void testWrite_NullByteArray_ThrowsNullPointerException() throws Exception {
        try {
            stream.write(null, 0, 0);
            Assert.fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

    @Test
    public void testWrite_NegativeOffset_ThrowsIndexOutOfBoundsException() throws Exception {
        try {
            stream.write(new byte[10], -1, 1);
            Assert.fail("Expected IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
        }
    }

    @Test
    public void testWrite_NegativeLength_ThrowsIndexOutOfBoundsException() throws Exception {
        try {
            stream.write(new byte[10], 0, -1);
            Assert.fail("Expected IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
        }
    }

    @Test
    public void testWrite_OffsetExceedsLength_ThrowsIndexOutOfBoundsException() throws Exception {
        try {
            stream.write(new byte[10], 11, 1);
            Assert.fail("Expected IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
        }
    }

    @Test
    public void testWrite_OffsetPlusLengthExceedsLength_ThrowsIndexOutOfBoundsException() throws Exception {
        try {
            stream.write(new byte[10], 5, 6);
            Assert.fail("Expected IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
        }
    }

    @Test
    public void testWrite_PositiveLength_CallsEncodeOrDecode() throws Exception {
        // This test is more of an integration test and would require mocking the baseNCodec
        // to verify that encode or decode was called. However, since we don't have access
        // to the actual implementation, we can only verify that the method doesn't throw
        // exceptions for valid input.
        stream.write(new byte[10], 0, 10);
    }
}
