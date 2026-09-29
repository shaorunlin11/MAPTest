package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;

public class SerializedStringequalsTest {
    @Test
    public void testEqualsWithSameInstance() {
        SerializedString s = new SerializedString("test");
        Assert.assertTrue(s.equals(s));
    }

    @Test
    public void testEqualsWithNull() {
        SerializedString s = new SerializedString("test");
        Assert.assertFalse(s.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        SerializedString s = new SerializedString("test");
        Object other = new Object();
        Assert.assertFalse(s.equals(other));
    }

    @Test
    public void testEqualsWithSameValue() {
        SerializedString s1 = new SerializedString("test");
        SerializedString s2 = new SerializedString("test");
        Assert.assertTrue(s1.equals(s2));
    }

    @Test
    public void testEqualsWithDifferentValue() {
        SerializedString s1 = new SerializedString("test");
        SerializedString s2 = new SerializedString("different");
        Assert.assertFalse(s1.equals(s2));
    }
}
