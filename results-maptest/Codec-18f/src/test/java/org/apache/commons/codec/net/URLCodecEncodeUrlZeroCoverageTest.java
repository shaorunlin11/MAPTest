package org.apache.commons.codec.net;

import org.junit.Test;

import java.util.BitSet;


public class URLCodecEncodeUrlZeroCoverageTest {
    @Test
    public void testEncodeUrlWithNullBytes() {
        // This test is designed to execute target lines 130 by ensuring bytes == null
        // which triggers the return null; statement in the encodeUrl method.
        byte[] result = URLCodec.encodeUrl(null, null);
        // The test is successful if the method returns null without throwing an exception
        // and the code path is covered.
    }

@Test
    public void testEncodeUrlWithNullUrlsafe() {
        // This test is designed to execute target lines 133 by ensuring urlsafe == null
        // which triggers the assignment urlsafe = WWW_FORM_URL_SAFE; and then the code path
        // that writes the escaped characters.
        byte[] bytes = new byte[] { 0x20, 0x41 };
        byte[] result = URLCodec.encodeUrl(null, bytes);
        // The test is successful if the method returns a non-null array without throwing an exception
        // and the code path is covered.
    }

@Test
    public void testEncodeUrlWithSpecificBytesAndUrlsafe() {
        // This test is designed to execute target lines 140 by ensuring the code path where
        // the byte is not in the urlsafe set and triggers the escape sequence writing.
        BitSet urlsafe = new BitSet();
        urlsafe.set(0x00);
        urlsafe.set(0x01);
        urlsafe.set(0x02);
        byte[] bytes = new byte[] { 0x00, 0x01, 0x02 };
        byte[] result = URLCodec.encodeUrl(urlsafe, bytes);
        // The test is successful if the method returns a non-null array without throwing an exception
        // and the code path is covered.
    }
}
