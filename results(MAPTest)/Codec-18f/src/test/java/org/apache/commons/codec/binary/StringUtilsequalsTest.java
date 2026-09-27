package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;

public class StringUtilsequalsTest {

    @Test
    public void testEquals_SameObject() {
        CharSequence cs = "test";
        Assert.assertTrue(StringUtils.equals(cs, cs));
    }

    @Test
    public void testEquals_Nulls() {
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals((CharSequence) null, "test"));
        Assert.assertFalse(StringUtils.equals("test", (CharSequence) null));
    }

    @Test
    public void testEquals_StringVsString() {
        Assert.assertTrue(StringUtils.equals("test", "test"));
        Assert.assertFalse(StringUtils.equals("test", "Test"));
    }

    @Test
    public void testEquals_StringVsStringBuilder() {
        StringBuilder sb = new StringBuilder("test");
        Assert.assertTrue(StringUtils.equals("test", sb));
        Assert.assertFalse(StringUtils.equals("test", new StringBuilder("Test")));
    }

    @Test
    public void testEquals_LengthMismatch() {
        Assert.assertFalse(StringUtils.equals("test", "tests"));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("test"), new StringBuilder("tests")));
    }

    @Test
    public void testEquals_RegionMatch() {
        Assert.assertTrue(StringUtils.equals("hello world", "hello world"));
        Assert.assertTrue(StringUtils.equals("hello world", new StringBuilder("hello world")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("hello world"), "hello world"));
    }

    @Test
    public void testEquals_RegionMismatch() {
        Assert.assertFalse(StringUtils.equals("hello world", "hello world!"));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("hello world"), new StringBuilder("hello world!")));
    }
}
