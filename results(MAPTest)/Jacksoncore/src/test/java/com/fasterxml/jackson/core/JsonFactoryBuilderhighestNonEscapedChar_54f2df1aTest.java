package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonFactoryBuilderhighestNonEscapedChar_54f2df1aTest {

    @Test
    public void testHighestNonEscapedCharWithNegativeValue() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.highestNonEscapedChar(-5);
        Assert.assertEquals(0, builder._maximumNonEscapedChar);
    }

    @Test
    public void testHighestNonEscapedCharWithZeroValue() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.highestNonEscapedChar(0);
        Assert.assertEquals(0, builder._maximumNonEscapedChar);
    }

    @Test
    public void testHighestNonEscapedCharWithLessThan127() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.highestNonEscapedChar(100);
        Assert.assertEquals(127, builder._maximumNonEscapedChar);
    }

    @Test
    public void testHighestNonEscapedCharWithEqualTo127() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.highestNonEscapedChar(127);
        Assert.assertEquals(127, builder._maximumNonEscapedChar);
    }

    @Test
    public void testHighestNonEscapedCharWithGreaterThan127() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        builder.highestNonEscapedChar(200);
        Assert.assertEquals(200, builder._maximumNonEscapedChar);
    }
}
