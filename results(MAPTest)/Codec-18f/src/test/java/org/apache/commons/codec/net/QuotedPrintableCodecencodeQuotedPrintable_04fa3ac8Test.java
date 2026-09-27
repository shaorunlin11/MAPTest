package org.apache.commons.codec.net;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.BitSet;

public class QuotedPrintableCodecencodeQuotedPrintable_04fa3ac8Test {

    @Test
    public void testEncodeQuotedPrintableWithNullBytes() {
        BitSet printable = new BitSet(256);
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, null, false);
        assert result == null;
    }

    @Test
    public void testEncodeQuotedPrintableWithNullPrintable() {
        byte[] bytes = "test".getBytes();
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(null, bytes, false);
        assert result != null;
    }

    @Test
    public void testEncodeQuotedPrintableWithStrictMode() {
        BitSet printable = new BitSet(256);
        byte[] bytes = "This is a test string with some special characters: !@#$%^&*()".getBytes();
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, true);
        assert result != null;
    }

    @Test
    public void testEncodeQuotedPrintableWithNonStrictMode() {
        BitSet printable = new BitSet(256);
        byte[] bytes = "This is a test string with some special characters: !@#$%^&*()".getBytes();
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, false);
        assert result != null;
    }

    @Test
    public void testEncodeQuotedPrintableWithNegativeBytes() {
        BitSet printable = new BitSet(256);
        byte[] bytes = {-1, -2, -3};
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, false);
        assert result != null;
    }

    @Test
    public void testEncodeQuotedPrintableWithWhitespaceInStrictMode() {
        BitSet printable = new BitSet(256);
        byte[] bytes = "Hello   World".getBytes();
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, true);
        assert result != null;
    }

@Test
    public void testEncodeQuotedPrintableTargetLines289() {
        BitSet printable = new BitSet(256);
        byte[] bytes = "a b c d e f g h i j k l m n o p q r s t u v w x y z".getBytes();
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, true);
        assert result != null;
    }
}
