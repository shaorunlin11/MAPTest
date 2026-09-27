package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOutputoutputInt_ae0f1cf9Test {

    @Test
    public void testOutputInt_PositiveSingleDigit() {
        char[] buffer = new char[10];
        int result = NumberOutput.outputInt(5, buffer, 0);
        assertEquals("5", new String(buffer, 0, result));
        assertEquals(1, result);
    }

    @Test
    public void testOutputInt_PositiveThreeDigits() {
        char[] buffer = new char[10];
        int result = NumberOutput.outputInt(123, buffer, 0);
        assertEquals("123", new String(buffer, 0, result));
        assertEquals(3, result);
    }

    @Test
    public void testOutputInt_PositiveFourDigits() {
        char[] buffer = new char[10];
        int result = NumberOutput.outputInt(1234, buffer, 0);
        assertEquals("1234", new String(buffer, 0, result));
        assertEquals(4, result);
    }

    @Test
    public void testOutputInt_NegativeSingleDigit() {
        char[] buffer = new char[10];
        int result = NumberOutput.outputInt(-5, buffer, 0);
        assertEquals("-5", new String(buffer, 0, result));
        assertEquals(2, result);
    }

    @Test
    public void testOutputInt_NegativeThreeDigits() {
        char[] buffer = new char[10];
        int result = NumberOutput.outputInt(-123, buffer, 0);
        assertEquals("-123", new String(buffer, 0, result));
        assertEquals(4, result);
    }

    @Test
    public void testOutputInt_NegativeFourDigits() {
        char[] buffer = new char[10];
        int result = NumberOutput.outputInt(-1234, buffer, 0);
        assertEquals("-1234", new String(buffer, 0, result));
        assertEquals(5, result);
    }

    @Test
    public void testOutputInt_MinValue() {
        char[] buffer = new char[12];
        int result = NumberOutput.outputInt(Integer.MIN_VALUE, buffer, 0);
        assertEquals(NumberOutput.SMALLEST_INT, new String(buffer, 0, result));
        assertEquals(NumberOutput.SMALLEST_INT.length(), result);
    }

    @Test
    public void testOutputInt_Billion() {
        char[] buffer = new char[10];
        int result = NumberOutput.outputInt(1000000000, buffer, 0);
        assertEquals("1000000000", new String(buffer, 0, result));
        assertEquals(10, result);
    }

    @Test
    public void testOutputInt_TwoBillions() {
        char[] buffer = new char[10];
        int result = NumberOutput.outputInt(2000000000, buffer, 0);
        assertEquals("2000000000", new String(buffer, 0, result));
        assertEquals(10, result);
    }

@Test
    public void testOutputInt_MillionToBillion() {
        char[] buffer = new char[10];
        int result = NumberOutput.outputInt(123456789, buffer, 0);
        assertEquals("123456789", new String(buffer, 0, result));
        assertEquals(9, result);
    }
}
