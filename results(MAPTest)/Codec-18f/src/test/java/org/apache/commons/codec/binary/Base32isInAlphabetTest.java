package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base32isInAlphabetTest {

    @Test
    public void testIsInAlphabet_ValidCharacter() {
        Base32 base32 = new Base32();
        // Test a valid character from the alphabet
        assertTrue(base32.isInAlphabet((byte) 'A'));
        assertTrue(base32.isInAlphabet((byte) 'Z'));
        assertTrue(base32.isInAlphabet((byte) '2'));
        assertTrue(base32.isInAlphabet((byte) '7'));
    }

    @Test
    public void testIsInAlphabet_InvalidCharacter() {
        Base32 base32 = new Base32();
        // Test an invalid character not in the alphabet
        assertFalse(base32.isInAlphabet((byte) 'a'));
        assertFalse(base32.isInAlphabet((byte) '0'));
        assertFalse(base32.isInAlphabet((byte) '@'));
    }

    @Test
    public void testIsInAlphabet_OutOfBounds() {
        Base32 base32 = new Base32();
        // Test a byte out of the decodeTable bounds
        assertFalse(base32.isInAlphabet((byte) -1));
        assertFalse(base32.isInAlphabet((byte) 256));
    }

    @Test
    public void testIsInAlphabet_NegativeValue() {
        Base32 base32 = new Base32();
        // Test a negative byte value
        assertFalse(base32.isInAlphabet((byte) -1));
    }

    @Test
    public void testIsInAlphabet_Zero() {
        Base32 base32 = new Base32();
        // Test zero byte
        assertFalse(base32.isInAlphabet((byte) 0));
    }

    @Test
    public void testIsInAlphabet_PadCharacter() {
        Base32 base32 = new Base32();
        // Test pad character (should be invalid)
        assertFalse(base32.isInAlphabet(Base32.PAD_DEFAULT));
    }
}
