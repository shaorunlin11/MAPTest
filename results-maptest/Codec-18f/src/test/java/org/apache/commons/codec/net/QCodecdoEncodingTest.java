package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.nio.charset.Charset;
import java.util.BitSet;
import java.lang.reflect.Field;

public class QCodecdoEncodingTest {
    private QCodec qCodec;

    @Before
    public void setUp() throws Exception {
        qCodec = new QCodec();
        // Use reflection to set encodeBlanks flag since it's private
        Field encodeBlanksField = QCodec.class.getDeclaredField("encodeBlanks");
        encodeBlanksField.setAccessible(true);
        encodeBlanksField.set(qCodec, false);
    }

    @After
    public void tearDown() {
        qCodec = null;
    }

    @Test
    public void testDoEncoding_NullInput_ReturnsNull() {
        byte[] result = qCodec.doEncoding(null);
        Assert.assertNull(result);
    }

    @Test
    public void testDoEncoding_EmptyInput_ReturnsEmptyArray() {
        byte[] result = qCodec.doEncoding(new byte[0]);
        Assert.assertArrayEquals(new byte[0], result);
    }

    @Test
    public void testDoEncoding_WithEncodeBlanksFalse_ReplacesBlanks() throws Exception {
        // Use reflection to set encodeBlanks flag
        Field encodeBlanksField = QCodec.class.getDeclaredField("encodeBlanks");
        encodeBlanksField.setAccessible(true);
        encodeBlanksField.set(qCodec, false);

        byte[] input = { 'a', ' ', 'b' };
        byte[] result = qCodec.doEncoding(input);
        Assert.assertArrayEquals(new byte[] { 97, 32, 98 }, result);
    }

    @Test
    public void testDoEncoding_WithEncodeBlanksTrue_ReplacesBlanksWithUnderscores() throws Exception {
        // Use reflection to set encodeBlanks flag
        Field encodeBlanksField = QCodec.class.getDeclaredField("encodeBlanks");
        encodeBlanksField.setAccessible(true);
        encodeBlanksField.set(qCodec, true);

        byte[] input = { 'a', ' ', 'b' };
        byte[] result = qCodec.doEncoding(input);
        Assert.assertArrayEquals(new byte[] { 97, 95, 98 }, result);
    }

    @Test
    public void testDoEncoding_WithPrintableChars_EncodesCorrectly() throws Exception {
        // Use reflection to set encodeBlanks flag
        Field encodeBlanksField = QCodec.class.getDeclaredField("encodeBlanks");
        encodeBlanksField.setAccessible(true);
        encodeBlanksField.set(qCodec, false);

        byte[] input = { 'a', 'b', 'c' };
        byte[] result = qCodec.doEncoding(input);
        // Assuming QuotedPrintableCodec.encodeQuotedPrintable returns the same bytes for printable chars
        Assert.assertArrayEquals(input, result);
    }
}
