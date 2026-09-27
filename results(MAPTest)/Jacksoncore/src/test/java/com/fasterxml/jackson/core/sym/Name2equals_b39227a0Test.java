package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class Name2equals_b39227a0Test {
    @Test
    public void testEqualsWithThreeIntsAlwaysReturnsFalse() throws Exception {
        Name2 name2 = new Name2("test", 0, 0, 0);
        assertFalse(name2.equals(0, 0, 0));
        assertFalse(name2.equals(1, 2, 3));
        assertFalse(name2.equals(-1, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
