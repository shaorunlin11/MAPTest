package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleMetaphoneisDoubleMetaphoneEqual_8329cd6bTest {

    @Test
    public void testIsDoubleMetaphoneEqualWithSameValuesAndAlternateFalse() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        boolean result = doubleMetaphone.isDoubleMetaphoneEqual("test", "test", false);
        assertTrue(result);
    }

    @Test
    public void testIsDoubleMetaphoneEqualWithDifferentValuesAndAlternateFalse() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        boolean result = doubleMetaphone.isDoubleMetaphoneEqual("test", "different", false);
        assertFalse(result);
    }

    @Test
    public void testIsDoubleMetaphoneEqualWithSameValuesAndAlternateTrue() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        boolean result = doubleMetaphone.isDoubleMetaphoneEqual("test", "test", true);
        assertTrue(result);
    }

    @Test
    public void testIsDoubleMetaphoneEqualWithDifferentValuesAndAlternateTrue() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        boolean result = doubleMetaphone.isDoubleMetaphoneEqual("test", "different", true);
        assertFalse(result);
    }

    @Test
    public void testIsDoubleMetaphoneEqualWithNullValues() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        boolean result = doubleMetaphone.isDoubleMetaphoneEqual(null, null, false);
        assertTrue(result);
    }

    @Test
    public void testIsDoubleMetaphoneEqualWithOneNullValue() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        boolean result = doubleMetaphone.isDoubleMetaphoneEqual(null, "test", false);
        assertFalse(result);
    }

    @Test
    public void testIsDoubleMetaphoneEqualWithEmptyValues() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        boolean result = doubleMetaphone.isDoubleMetaphoneEqual("", "", false);
        assertTrue(result);
    }

    @Test
    public void testIsDoubleMetaphoneEqualWithOneEmptyValue() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        boolean result = doubleMetaphone.isDoubleMetaphoneEqual("", "test", false);
        assertFalse(result);
    }
}
