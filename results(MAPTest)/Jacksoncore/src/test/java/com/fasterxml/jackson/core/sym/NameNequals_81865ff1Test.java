package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class NameNequals_81865ff1Test {
    @Test
    public void testEqualsAlwaysReturnsFalse() throws Exception {
        NameN nameN = new NameN("test", 0, 0, 0, 0, 0, new int[0], 0);
        assertFalse(nameN.equals(0));
        assertFalse(nameN.equals(123));
        assertFalse(nameN.equals(Integer.MIN_VALUE));
        assertFalse(nameN.equals(Integer.MAX_VALUE));
    }
}
