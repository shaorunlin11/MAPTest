package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import static org.apache.commons.codec.binary.BaseNCodec.EOF;
import org.junit.Assert;

import org.apache.commons.codec.binary.BaseNCodec.Context;

public class BaseNCodecInputStreamread_087411afTest {
    private BaseNCodecInputStream inputStream;
    private InputStream mockInputStream;
    private BaseNCodec mockBaseNCodec;

    @Before
    public void setUp() throws Exception {
        mockInputStream = new ByteArrayInputStream(new byte[0]);
        mockBaseNCodec = new BaseNCodec(0, 0, 0, 0) {
            @Override
            public boolean hasData(Context context) {
                return false;
            }

            @Override
            public int readResults(byte[] b, int bPos, int bAvail, Context context) {
                return 5;
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
            public boolean isInAlphabet(byte b) {
                return false;
            }
        };
        inputStream = new BaseNCodecInputStream(mockInputStream, mockBaseNCodec, false);
    }

    @After
    public void tearDown() {
        inputStream = null;
        mockInputStream = null;
        mockBaseNCodec = null;
    }

    @Test
    public void testRead_ValidInput_ReturnsReadLength() throws IOException {
        byte[] b = new byte[10];
        int offset = 0;
        int len = 10;
        int result = inputStream.read(b, offset, len);
        Assert.assertEquals(5, result);
    }

    @Test
    public void testRead_NullByteArray_ThrowsNullPointerException() throws IOException {
        byte[] b = null;
        int offset = 0;
        int len = 1;
        try {
            inputStream.read(b, offset, len);
        } catch (NullPointerException e) {
            // Expected exception
            return;
        }
        // If we reach here, the test failed
        throw new AssertionError("Expected NullPointerException");
    }

    @Test
    public void testRead_NegativeOffset_ThrowsIndexOutOfBoundsException() throws IOException {
        byte[] b = new byte[1];
        int offset = -1;
        int len = 1;
        try {
            inputStream.read(b, offset, len);
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
            return;
        }
        // If we reach here, the test failed
        throw new AssertionError("Expected IndexOutOfBoundsException");
    }

    @Test
    public void testRead_NegativeLength_ThrowsIndexOutOfBoundsException() throws IOException {
        byte[] b = new byte[1];
        int offset = 0;
        int len = -1;
        try {
            inputStream.read(b, offset, len);
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
            return;
        }
        // If we reach here, the test failed
        throw new AssertionError("Expected IndexOutOfBoundsException");
    }

    @Test
    public void testRead_OffsetExceedsByteArrayLength_ThrowsIndexOutOfBoundsException() throws IOException {
        byte[] b = new byte[1];
        int offset = 2;
        int len = 1;
        try {
            inputStream.read(b, offset, len);
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
            return;
        }
        // If we reach here, the test failed
        throw new AssertionError("Expected IndexOutOfBoundsException");
    }

    @Test
    public void testRead_OffsetPlusLengthExceedsByteArrayLength_ThrowsIndexOutOfBoundsException() throws IOException {
        byte[] b = new byte[1];
        int offset = 0;
        int len = 2;
        try {
            inputStream.read(b, offset, len);
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
            return;
        }
        // If we reach here, the test failed
        throw new AssertionError("Expected IndexOutOfBoundsException");
    }

    @Test
    public void testRead_ZeroLength_ReturnsZero() throws IOException {
        byte[] b = new byte[1];
        int offset = 0;
        int len = 0;
        int result = inputStream.read(b, offset, len);
        Assert.assertEquals(0, result);
    }

    @Test
    public void testRead_HasDataReturnsFalse_ReadsFromInputStream() throws IOException {
        byte[] b = new byte[10];
        int offset = 0;
        int len = 10;
        // Mock in.read(buf) to return a non-zero value
        mockInputStream = new ByteArrayInputStream(new byte[]{1, 2, 3, 4, 5});
        inputStream = new BaseNCodecInputStream(mockInputStream, mockBaseNCodec, false);

        int result = inputStream.read(b, offset, len);
        Assert.assertEquals(5, result);
    }

@Test
    public void testRead_HasDataReturnsTrue_ReadsFromCodec() throws IOException {
        byte[] b = new byte[10];
        int offset = 0;
        int len = 10;
        // Mock baseNCodec.hasData(context) to return true
        mockBaseNCodec = new BaseNCodec(0, 0, 0, 0) {
            @Override
            public boolean hasData(Context context) {
                return true;
            }

            @Override
            public int readResults(byte[] b, int bPos, int bAvail, Context context) {
                return 5;
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
            public boolean isInAlphabet(byte b) {
                return false;
            }
        };
        inputStream = new BaseNCodecInputStream(mockInputStream, mockBaseNCodec, false);

        int result = inputStream.read(b, offset, len);
        Assert.assertEquals(5, result);
    }
}
