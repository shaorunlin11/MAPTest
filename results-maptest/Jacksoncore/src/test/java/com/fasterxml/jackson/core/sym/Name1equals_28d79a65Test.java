package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class Name1equals_28d79a65Test {

    @Test
    public void testEqualsWithMatchingQuadAndZeroSecond() throws Exception {
        Name1 name1 = new Name1("test", 0, 123);
        assertTrue(name1.equals(123, 0));
    }

    @Test
    public void testEqualsWithNonMatchingQuad() throws Exception {
        Name1 name1 = new Name1("test", 0, 123);
        assertFalse(name1.equals(456, 0));
    }

    @Test
    public void testEqualsWithNonZeroSecondQuad() throws Exception {
        Name1 name1 = new Name1("test", 0, 123);
        assertFalse(name1.equals(123, 1));
    }

    @Test
    public void testEqualsWithBothNonMatching() throws Exception {
        Name1 name1 = new Name1("test", 0, 123);
        assertFalse(name1.equals(456, 1));
    }

    @Test
    public void testEqualsWithEmptyInstance() throws Exception {
        Name1 empty = new Name1("", 0, 0);
        assertTrue(empty.equals(0, 0));
    }

    @Test
    public void testEqualsWithEmptyInstanceAndNonZeroSecond() throws Exception {
        Name1 empty = new Name1("", 0, 0);
        assertFalse(empty.equals(0, 1));
    }

@Test
    public void testEqualsWithMatchingQuad() throws Exception {
        Name1 name1 = new Name1("test", 0, 123);
        assertTrue(name1.equals(123));
    }

@Test
    public void testEqualsWithNonMatchingQuad2() throws Exception {
        Name1 name1 = new Name1("test", 0, 123);
        assertFalse(name1.equals(456));
    }
}
