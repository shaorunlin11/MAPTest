package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64isBase64_cd7abbc8Test {

    @Test
    public void testIsBase64_ValidStandardCharacters() {
        assertTrue(Base64.isBase64((byte) 'A'));
        assertTrue(Base64.isBase64((byte) 'Z'));
        assertTrue(Base64.isBase64((byte) 'a'));
        assertTrue(Base64.isBase64((byte) 'z'));
        assertTrue(Base64.isBase64((byte) '0'));
        assertTrue(Base64.isBase64((byte) '9'));
        assertTrue(Base64.isBase64((byte) '+'));
        assertTrue(Base64.isBase64((byte) '/'));
    }

    @Test
    public void testIsBase64_ValidUrlSafeCharacters() {
        assertTrue(Base64.isBase64((byte) 'A'));
        assertTrue(Base64.isBase64((byte) 'Z'));
        assertTrue(Base64.isBase64((byte) 'a'));
        assertTrue(Base64.isBase64((byte) 'z'));
        assertTrue(Base64.isBase64((byte) '0'));
        assertTrue(Base64.isBase64((byte) '9'));
        assertTrue(Base64.isBase64((byte) '-'));
        assertTrue(Base64.isBase64((byte) '_'));
    }

    @Test
    public void testIsBase64_PaddingCharacter() {
        assertTrue(Base64.isBase64(Base64.PAD_DEFAULT));
    }

    @Test
    public void testIsBase64_InvalidCharacters() {
        assertFalse(Base64.isBase64((byte) 0));
        assertFalse(Base64.isBase64((byte) 32)); // Space
        assertFalse(Base64.isBase64((byte) 64)); // '@'
        assertFalse(Base64.isBase64((byte) 91)); // '['
        assertFalse(Base64.isBase64((byte) 123)); // '{'
    }

    @Test
    public void testIsBase64_OutOfBounds() {
        assertFalse(Base64.isBase64((byte) 255));
        assertFalse(Base64.isBase64((byte) -1));
    }

    @Test
    public void testIsBase64_NonBase64Characters() {
        assertFalse(Base64.isBase64((byte) '!'));
        assertFalse(Base64.isBase64((byte) '$'));
        assertFalse(Base64.isBase64((byte) '%'));
        assertFalse(Base64.isBase64((byte) '^'));
        assertFalse(Base64.isBase64((byte) '&'));
    }
}
