package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class Name1equals_6c793dacTest {
    @Test
    public void testEquals_returnsFalse() {
        Name1 name1 = new Name1("test", 0, 0);
        boolean result = name1.equals(1, 2, 3);
        assertFalse(result);
    }

@Test
    public void testEquals_returnsTrue() {
        Name1 name1 = new Name1("test", 0, 5);
        boolean result = name1.equals(5);
        assertTrue(result);
    }
}
