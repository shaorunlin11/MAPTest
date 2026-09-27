package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.apache.commons.codec.DecoderException;

public class HextoDigitTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testToDigit_ValidLowercaseHexCharacter() throws Exception {
        int result = Hex.toDigit('a', 0);
        Assert.assertEquals(10, result);
    }

    @Test
    public void testToDigit_ValidUppercaseHexCharacter() throws Exception {
        int result = Hex.toDigit('A', 0);
        Assert.assertEquals(10, result);
    }

    @Test
    public void testToDigit_ValidDigitCharacter() throws Exception {
        int result = Hex.toDigit('5', 0);
        Assert.assertEquals(5, result);
    }

    @Test
    public void testToDigit_InvalidCharacter() throws Exception {
        thrown.expect(DecoderException.class);
        thrown.expectMessage("Illegal hexadecimal character x at index 0");
        Hex.toDigit('x', 0);
    }

    @Test
    public void testToDigit_CharacterAtSpecificIndex() throws Exception {
        int result = Hex.toDigit('f', 15);
        Assert.assertEquals(15, result);
    }
}
