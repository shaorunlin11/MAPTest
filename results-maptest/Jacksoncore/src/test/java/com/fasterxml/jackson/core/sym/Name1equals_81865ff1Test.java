package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class Name1equals_81865ff1Test {

    @Test
    public void testEqualsWithMatchingQuad() {
        Name1 name1 = new Name1("test", 0, 1234);
        assertTrue(name1.equals(1234));
    }

    @Test
    public void testEqualsWithNonMatchingQuad() {
        Name1 name1 = new Name1("test", 0, 1234);
        assertFalse(name1.equals(5678));
    }

    @Test
    public void testEqualsWithZeroQuad() {
        Name1 name1 = new Name1("test", 0, 0);
        assertTrue(name1.equals(0));
    }

    @Test
    public void testEqualsWithNegativeQuad() {
        Name1 name1 = new Name1("test", 0, -1234);
        assertTrue(name1.equals(-1234));
    }

    @Test
    public void testEqualsWithDifferentQuads() {
        Name1 name1 = new Name1("test", 0, 9876);
        assertFalse(name1.equals(6789));
    }
}
