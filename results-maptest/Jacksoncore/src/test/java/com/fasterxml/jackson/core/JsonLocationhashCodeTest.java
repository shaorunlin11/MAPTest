package com.fasterxml.jackson.core;
import org.junit.Test;
import static org.junit.Assert.*;
public class JsonLocationhashCodeTest {


    @Test
    public void testHashCodeWithNAInstance() {
        JsonLocation location = JsonLocation.NA;
        int hashCode = location.hashCode();
        // Verify that the NA instance has a consistent hash code
        int expectedHash = 1 ^ -1 + -1 ^ (int) -1L + (int) -1L;
        assertEquals(expectedHash, hashCode);
    }
}
