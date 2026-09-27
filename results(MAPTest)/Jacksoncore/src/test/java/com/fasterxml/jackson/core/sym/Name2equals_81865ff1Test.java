package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class Name2equals_81865ff1Test {

    @Test
    public void testEqualsWithQuad() {
        Name2 name2 = new Name2("test", 0, 0, 0);
        assertFalse(name2.equals(0));
        assertFalse(name2.equals(1));
        assertFalse(name2.equals(Integer.MIN_VALUE));
        assertFalse(name2.equals(Integer.MAX_VALUE));
    }
}
