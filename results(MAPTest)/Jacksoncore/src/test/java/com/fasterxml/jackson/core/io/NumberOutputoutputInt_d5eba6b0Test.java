package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOutputoutputInt_d5eba6b0Test {

    @Test
    public void testOutputInt_PositiveSingleDigit() {
        byte[] buffer = new byte[10];
        int off = 0;
        int result = NumberOutput.outputInt(5, buffer, off);
        assertEquals("Should write '5' at offset 0", 1, result);
        assertEquals("Should write '5' as byte", '5', buffer[0]);
    }

    @Test
    public void testOutputInt_PositiveTwoDigits() {
        byte[] buffer = new byte[10];
        int off = 0;
        int result = NumberOutput.outputInt(25, buffer, off);
        assertEquals("Should write '25' starting at offset 0", 2, result);
        assertEquals("Should write '2' as byte", '2', buffer[0]);
        assertEquals("Should write '5' as byte", '5', buffer[1]);
    }

    @Test
    public void testOutputInt_PositiveThreeDigits() {
        byte[] buffer = new byte[10];
        int off = 0;
        int result = NumberOutput.outputInt(123, buffer, off);
        assertEquals("Should write '123' starting at offset 0", 3, result);
        assertEquals("Should write '1' as byte", '1', buffer[0]);
        assertEquals("Should write '2' as byte", '2', buffer[1]);
        assertEquals("Should write '3' as byte", '3', buffer[2]);
    }

    @Test
    public void testOutputInt_PositiveFourDigits() {
        byte[] buffer = new byte[10];
        int off = 0;
        int result = NumberOutput.outputInt(1234, buffer, off);
        assertEquals("Should write '1234' starting at offset 0", 4, result);
        assertEquals("Should write '1' as byte", '1', buffer[0]);
        assertEquals("Should write '2' as byte", '2', buffer[1]);
        assertEquals("Should write '3' as byte", '3', buffer[2]);
        assertEquals("Should write '4' as byte", '4', buffer[3]);
    }

    @Test
    public void testOutputInt_NegativeSingleDigit() {
        byte[] buffer = new byte[10];
        int off = 0;
        int result = NumberOutput.outputInt(-5, buffer, off);
        assertEquals("Should write '-5' starting at offset 0", 2, result);
        assertEquals("Should write '-' as byte", '-', buffer[0]);
        assertEquals("Should write '5' as byte", '5', buffer[1]);
    }

    @Test
    public void testOutputInt_NegativeThreeDigits() {
        byte[] buffer = new byte[10];
        int off = 0;
        int result = NumberOutput.outputInt(-123, buffer, off);
        assertEquals("Should write '-123' starting at offset 0", 4, result);
        assertEquals("Should write '-' as byte", '-', buffer[0]);
        assertEquals("Should write '1' as byte", '1', buffer[1]);
        assertEquals("Should write '2' as byte", '2', buffer[2]);
        assertEquals("Should write '3' as byte", '3', buffer[3]);
    }

    @Test
    public void testOutputInt_IntegerMinValue() {
        byte[] buffer = new byte[11];
        int off = 0;
        int result = NumberOutput.outputInt(Integer.MIN_VALUE, buffer, off);
        assertEquals("Should write the string representation of Integer.MIN_VALUE", 
                     NumberOutput.SMALLEST_INT.length(), result - off);
        for (int i = 0; i < NumberOutput.SMALLEST_INT.length(); i++) {
            assertEquals("Should match the string representation of Integer.MIN_VALUE", 
                         NumberOutput.SMALLEST_INT.charAt(i), buffer[off + i]);
        }
    }

    @Test
    public void testOutputInt_Billion() {
        byte[] buffer = new byte[10];
        int off = 0;
        int result = NumberOutput.outputInt(1000000000, buffer, off);
        assertEquals("Should write '1000000000' starting at offset 0", 10, result);
        String expected = "1000000000";
        for (int i = 0; i < expected.length(); i++) {
            assertEquals("Should match '" + expected + "'", expected.charAt(i), buffer[off + i]);
        }
    }

    @Test
    public void testOutputInt_TwoBillions() {
        byte[] buffer = new byte[10];
        int off = 0;
        int result = NumberOutput.outputInt(2000000000, buffer, off);
        assertEquals("Should write '2000000000' starting at offset 0", 10, result);
        String expected = "2000000000";
        for (int i = 0; i < expected.length(); i++) {
            assertEquals("Should match '" + expected + "'", expected.charAt(i), buffer[off + i]);
        }
    }

@Test
    public void testOutputInt_MillionToBillion() {
        byte[] buffer = new byte[10];
        int off = 0;
        int v = 500000000; // Between MILLION and BILLION
        int result = NumberOutput.outputInt(v, buffer, off);
        assertEquals("Should process value between MILLION and BILLION", 9, result);
        String expected = "500000000";
        for (int i = 0; i < expected.length(); i++) {
            assertEquals("Should match '" + expected + "'", expected.charAt(i), buffer[off + i]);
        }
    }
}
