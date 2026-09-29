package org.apache.commons.codec.net;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class QCodecgetDefaultCharsetTest {

    @Test
    public void testGetDefaultCharsetWithDefaultConstructor() {
        QCodec qCodec = new QCodec();
        assertEquals("UTF-8", qCodec.getDefaultCharset());
    }

    @Test
    public void testGetDefaultCharsetWithCharsetConstructor() {
        QCodec qCodec = new QCodec(Charset.forName("ISO-8859-1"));
        assertEquals("ISO-8859-1", qCodec.getDefaultCharset());
    }

    @Test
    public void testGetDefaultCharsetWithStringConstructor() {
        QCodec qCodec = new QCodec("UTF-16");
        assertEquals("UTF-16", qCodec.getDefaultCharset());
    }

    @Test
    public void testGetDefaultCharsetAfterInitialization() throws Exception {
        QCodec qCodec = new QCodec();
        Field charsetField = QCodec.class.getDeclaredField("charset");
        charsetField.setAccessible(true);
        Charset originalCharset = (Charset) charsetField.get(qCodec);
        assertEquals("UTF-8", qCodec.getDefaultCharset());

        // Change the charset using reflection to verify it's reflected in the method
        Charset newCharset = Charset.forName("UTF-16");
        charsetField.set(qCodec, newCharset);
        assertEquals("UTF-16", qCodec.getDefaultCharset());
    }
}
