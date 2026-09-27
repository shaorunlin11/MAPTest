package org.apache.commons.codec.net;

import org.junit.Test;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class QuotedPrintableCodecgetCharsetTest {

    @Test
    public void testGetCharset_DefaultConstructor() {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        assertEquals(Charset.forName("UTF-8"), codec.getCharset());
    }

    @Test
    public void testGetCharset_ConstructorWithCharset() {
        Charset customCharset = Charset.forName("ISO-8859-1");
        QuotedPrintableCodec codec = new QuotedPrintableCodec(customCharset);
        assertEquals(customCharset, codec.getCharset());
    }

    @Test
    public void testGetCharset_ConstructorWithCharsetAndStrict() {
        Charset customCharset = Charset.forName("ISO-8859-1");
        QuotedPrintableCodec codec = new QuotedPrintableCodec(customCharset, true);
        assertEquals(customCharset, codec.getCharset());
    }

    @Test
    public void testGetCharset_ConstructorWithStringCharset() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec("ISO-8859-1");
        assertEquals(Charset.forName("ISO-8859-1"), codec.getCharset());
    }

    @Test
    public void testGetCharset_NullCharset() {
        QuotedPrintableCodec codec = new QuotedPrintableCodec((Charset) null);
        assertNull(codec.getCharset());
    }
}
