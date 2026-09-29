package com.fasterxml.jackson.core.io;

import org.junit.Test;

public class NumberInputParseLongZeroCoverageTest {
    @Test
    public void testParseLongWithLenGe9() {
        char[] ch = new char[18];
        for (int i = 0; i < 18; i++) {
            ch[i] = '1';
        }
        long result = NumberInput.parseLong(ch, 0, 18);
        // This test is designed to reach line 113 of the parseLong method
        // by ensuring len >= 9 and valid input.
    }
}
