package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

public class NameNconstructTest {

    @Test
    public void testConstructWithQlenLessThan4() {
        try {
            NameN.construct("test", 0, new int[]{1, 2, 3}, 3);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testConstructWithQlenExactly4() throws Exception {
        int[] q = {0x12345678, 0x9ABCDEF0, 0x11223344, 0x55667788};
        NameN nameN = NameN.construct("test", 0, q, 4);

        Field q1Field = NameN.class.getDeclaredField("q1");
        Field q2Field = NameN.class.getDeclaredField("q2");
        Field q3Field = NameN.class.getDeclaredField("q3");
        Field q4Field = NameN.class.getDeclaredField("q4");
        Field qlenField = NameN.class.getDeclaredField("qlen");
        Field qField = NameN.class.getDeclaredField("q");

        q1Field.setAccessible(true);
        q2Field.setAccessible(true);
        q3Field.setAccessible(true);
        q4Field.setAccessible(true);
        qlenField.setAccessible(true);
        qField.setAccessible(true);

        Assert.assertEquals(0x12345678, q1Field.getInt(nameN));
        Assert.assertEquals(0x9ABCDEF0, q2Field.getInt(nameN));
        Assert.assertEquals(0x11223344, q3Field.getInt(nameN));
        Assert.assertEquals(0x55667788, q4Field.getInt(nameN));
        Assert.assertEquals(4, qlenField.getInt(nameN));
        Assert.assertNull(qField.get(nameN));
    }

    @Test
    public void testConstructWithQlenGreaterThan4() throws Exception {
        int[] q = {0x12345678, 0x9ABCDEF0, 0x11223344, 0x55667788, 0xAABBCCDD, 0x11223344};
        NameN nameN = NameN.construct("test", 0, q, 6);

        Field q1Field = NameN.class.getDeclaredField("q1");
        Field q2Field = NameN.class.getDeclaredField("q2");
        Field q3Field = NameN.class.getDeclaredField("q3");
        Field q4Field = NameN.class.getDeclaredField("q4");
        Field qlenField = NameN.class.getDeclaredField("qlen");
        Field qField = NameN.class.getDeclaredField("q");

        q1Field.setAccessible(true);
        q2Field.setAccessible(true);
        q3Field.setAccessible(true);
        q4Field.setAccessible(true);
        qlenField.setAccessible(true);
        qField.setAccessible(true);

        Assert.assertEquals(0x12345678, q1Field.getInt(nameN));
        Assert.assertEquals(0x9ABCDEF0, q2Field.getInt(nameN));
        Assert.assertEquals(0x11223344, q3Field.getInt(nameN));
        Assert.assertEquals(0x55667788, q4Field.getInt(nameN));
        Assert.assertEquals(6, qlenField.getInt(nameN));
        int[] buf = (int[]) qField.get(nameN);
        Assert.assertEquals(2, buf.length);
        Assert.assertEquals(0xAABBCCDD, buf[0]);
        Assert.assertEquals(0x11223344, buf[1]);
    }
}
