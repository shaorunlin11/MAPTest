package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class Name3equals_e586fb32Test {

    @Test
    public void testEqualsWithMatchingQuads() throws Exception {
        Name3 name3 = new Name3("test", 0, 1, 2, 3);
        assertTrue(name3.equals(1, 2, 3));
    }

    @Test
    public void testEqualsWithNonMatchingQuads() throws Exception {
        Name3 name3 = new Name3("test", 0, 1, 2, 3);
        assertFalse(name3.equals(1, 2, 4));
        assertFalse(name3.equals(1, 3, 3));
        assertFalse(name3.equals(2, 2, 3));
    }

    @Test
    public void testEqualsWithZeroValues() throws Exception {
        Name3 name3 = new Name3("zero", 0, 0, 0, 0);
        assertTrue(name3.equals(0, 0, 0));
    }

    @Test
    public void testEqualsWithNegativeValues() throws Exception {
        Name3 name3 = new Name3("negative", 0, -1, -2, -3);
        assertTrue(name3.equals(-1, -2, -3));
    }
}
