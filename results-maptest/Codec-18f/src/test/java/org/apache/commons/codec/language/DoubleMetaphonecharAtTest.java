package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleMetaphonecharAtTest {

    @Test
    public void testCharAtWithValidIndex() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        char result = doubleMetaphone.charAt("test", 2);
        assertEquals('s', result);
    }

    @Test
    public void testCharAtWithNegativeIndex() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        char result = doubleMetaphone.charAt("test", -1);
        assertEquals(Character.MIN_VALUE, result);
    }

    @Test
    public void testCharAtWithIndexEqualToLength() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        char result = doubleMetaphone.charAt("test", 4);
        assertEquals(Character.MIN_VALUE, result);
    }

    @Test
    public void testCharAtWithIndexGreaterThanLength() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        char result = doubleMetaphone.charAt("test", 5);
        assertEquals(Character.MIN_VALUE, result);
    }

    @Test
    public void testCharAtWithEmptyString() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        char result = doubleMetaphone.charAt("", 0);
        assertEquals(Character.MIN_VALUE, result);
    }
}
