package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class Nameequals_a2b5205cTest {

    @Test
    public void testEquals_SameInstance() {
        Name name = new Name("test", 123) {
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
        };
        assertTrue(name.equals(name));
    }

    @Test
    public void testEquals_Null() {
        Name name = new Name("test", 123) {
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
        };
        assertFalse(name.equals(null));
    }

    @Test
    public void testEquals_DifferentInstance() {
        Name name1 = new Name("test", 123) {
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
        };
        Name name2 = new Name("test", 123) {
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
        };
        assertFalse(name1.equals(name2));
    }
}
