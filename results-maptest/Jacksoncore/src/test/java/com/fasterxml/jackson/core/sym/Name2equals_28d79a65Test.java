package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class Name2equals_28d79a65Test {
    @Test
    public void testEqualsWithMatchingQuads() throws Exception {
        Name2 name2 = new Name2("test", 123, 0x12345678, 0x87654321);
        assertTrue(name2.equals(0x12345678, 0x87654321));
    }

    @Test
    public void testEqualsWithNonMatchingQuad1() throws Exception {
        Name2 name2 = new Name2("test", 123, 0x12345678, 0x87654321);
        assertFalse(name2.equals(0x11111111, 0x87654321));
    }

    @Test
    public void testEqualsWithNonMatchingQuad2() throws Exception {
        Name2 name2 = new Name2("test", 123, 0x12345678, 0x87654321);
        assertFalse(name2.equals(0x12345678, 0x22222222));
    }

    @Test
    public void testEqualsWithBothNonMatchingQuads() throws Exception {
        Name2 name2 = new Name2("test", 123, 0x12345678, 0x87654321);
        assertFalse(name2.equals(0x11111111, 0x22222222));
    }
}
