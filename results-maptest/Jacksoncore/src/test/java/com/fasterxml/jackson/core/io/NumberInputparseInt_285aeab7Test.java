package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberInputparseInt_285aeab7Test {

    @Test
    public void testParseInt_1Digit() {
        char[] ch = {'5'};
        int result = NumberInput.parseInt(ch, 0, 1);
        assertEquals(5, result);
    }

    @Test
    public void testParseInt_2Digits() {
        char[] ch = {'1', '2'};
        int result = NumberInput.parseInt(ch, 0, 2);
        assertEquals(12, result);
    }

    @Test
    public void testParseInt_3Digits() {
        char[] ch = {'1', '2', '3'};
        int result = NumberInput.parseInt(ch, 0, 3);
        assertEquals(123, result);
    }

    @Test
    public void testParseInt_4Digits() {
        char[] ch = {'1', '2', '3', '4'};
        int result = NumberInput.parseInt(ch, 0, 4);
        assertEquals(1234, result);
    }

    @Test
    public void testParseInt_5Digits() {
        char[] ch = {'1', '2', '3', '4', '5'};
        int result = NumberInput.parseInt(ch, 0, 5);
        assertEquals(12345, result);
    }

    @Test
    public void testParseInt_6Digits() {
        char[] ch = {'1', '2', '3', '4', '5', '6'};
        int result = NumberInput.parseInt(ch, 0, 6);
        assertEquals(123456, result);
    }

    @Test
    public void testParseInt_7Digits() {
        char[] ch = {'1', '2', '3', '4', '5', '6', '7'};
        int result = NumberInput.parseInt(ch, 0, 7);
        assertEquals(1234567, result);
    }

    @Test
    public void testParseInt_8Digits() {
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8'};
        int result = NumberInput.parseInt(ch, 0, 8);
        assertEquals(12345678, result);
    }

    @Test
    public void testParseInt_9Digits() {
        char[] ch = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int result = NumberInput.parseInt(ch, 0, 9);
        assertEquals(123456789, result);
    }

    @Test
    public void testParseInt_OffsetAndLength() {
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int result = NumberInput.parseInt(ch, 2, 5);
        assertEquals(23456, result);
    }
}
