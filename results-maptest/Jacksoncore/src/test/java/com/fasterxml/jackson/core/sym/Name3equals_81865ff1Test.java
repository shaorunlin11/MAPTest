package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class Name3equals_81865ff1Test {
    @Test
    public void testEqualsAlwaysReturnsFalse() {
        Name3 name3 = new Name3("test", 0, 0, 0, 0);
        assertFalse(name3.equals(0));
        assertFalse(name3.equals(123));
        assertFalse(name3.equals(-1));
    }
}
