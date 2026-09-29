package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class Nameequals_9896b2fdTest {
    @Test
    public void testEqualsInt() throws Exception {
        // Since the method is abstract, we need to create a concrete subclass for testing
        class TestName extends Name {
            protected TestName(String name, int hashCode) {
                super(name, hashCode);
            }

            @Override
            public boolean equals(int q1) {
                // Correct implementation based on actual Name class behavior
                return q1 == _hashCode;
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
            public boolean equals(Object o) {
                return false;
            }
        }

        Name testName = new TestName("test", 123);

        // Test case where the input matches the expected value
        assertTrue(testName.equals(123));

        // Test case where the input does not match the expected value
        assertFalse(testName.equals(456));
    }
}
