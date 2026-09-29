package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import org.junit.Assert;

public class NameGetNameZeroCoverageTest {
    @Test
    public void testGetName() {
        Name name = new Name("testName", 123) {
            // Concrete implementation to satisfy the abstract class
            @Override
            public boolean equals(int q1) {
                return false;
            }

            @Override
            public boolean equals(int q1, int q2) {
                return false;
            }

            @Override
            public boolean equals(int q1, int q2, int q3) {
                return false;
            }

            @Override
            public boolean equals(int[] quads, int qlen) {
                return false;
            }

            @Override
            public String toString() {
                return "";
            }
        };
        Assert.assertEquals("testName", name.getName());
    }
}
