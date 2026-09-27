package com.fasterxml.jackson.core.sym;

import org.junit.Test;

public class Name3EqualsZeroCoverageTest {
    @Test
    public void testEqualsWithQuadsAndQlen() {
        Name3 name3 = new Name3("test", 0, 1, 2, 3);
        int[] quads = {1, 2, 3};
        int qlen = 3;
        boolean result = name3.equals(quads, qlen);
        // This test is designed to execute target line 34 of the equals method.
        // The conditions qlen == 3, quads[0] == q1, quads[1] == q2, and quads[2] == q3 are met.
        // The result of the method call is not asserted, as the goal is to cover the target line.
    }
}
