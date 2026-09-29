package com.fasterxml.jackson.core.sym;

import org.junit.Test;

public class NameNEqualsZeroCoverage_1298Test {
    @Test
    public void testEqualsWithLenNotEqualQlen() {
        int q1 = 1;
        int q2 = 2;
        int q3 = 3;
        int q4 = 4;
        int qlen = 5;
        int[] q = {1, 2, 3, 4, 5};
        NameN nameN = new NameN("test", 0, q1, q2, q3, q4, q, qlen);

        int[] quads = {1, 2, 3, 4, 5, 6};
        int len = 6;

        nameN.equals(quads, len);
    }

@Test
    public void testEqualsWithLenEqualQlenAndQuads0NotEqualQ1() {
        int q1 = 1;
        int q2 = 2;
        int q3 = 3;
        int q4 = 4;
        int qlen = 4;
        int[] q = {1, 2, 3, 4};
        NameN nameN = new NameN("test", 0, q1, q2, q3, q4, q, qlen);

        int[] quads = {2, 2, 3, 4};
        int len = 4;

        nameN.equals(quads, len);
    }

@Test
    public void testEqualsWithLenEqualQlenAndQuads0EqualQ1() {
        int q1 = 1;
        int q2 = 2;
        int q3 = 3;
        int q4 = 4;
        int qlen = 4;
        int[] q = {1, 2, 3, 4};
        NameN nameN = new NameN("test", 0, q1, q2, q3, q4, q, qlen);

        int[] quads = {1, 2, 3, 4};
        int len = 4;

        nameN.equals(quads, len);
    }

@Test
    public void testEqualsWithLenEqualQlenAndQuads1NotEqualQ2() {
        int q1 = 1;
        int q2 = 2;
        int q3 = 3;
        int q4 = 4;
        int qlen = 4;
        int[] q = {1, 2, 3, 4};
        NameN nameN = new NameN("test", 0, q1, q2, q3, q4, q, qlen);

        int[] quads = {1, 3, 3, 4};
        int len = 4;

        nameN.equals(quads, len);
    }

@Test
    public void testEqualsWithLenEqualQlenAndQuads2EqualQ3() {
        int q1 = 1;
        int q2 = 2;
        int q3 = 3;
        int q4 = 4;
        int qlen = 5;
        int[] q = {1, 2, 3, 4, 5};
        NameN nameN = new NameN("test", 0, q1, q2, q3, q4, q, qlen);

        int[] quads = {1, 2, 3, 4, 5};
        int len = 5;

        nameN.equals(quads, len);
    }
}
