package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import org.junit.Assert;

public class Name3equals_28d79a65Test {
    @Test
    public void testEqualsWithTwoIntsAlwaysReturnsFalse() {
        Name3 name3 = new Name3("test", 0, 0, 0, 0);
        Assert.assertFalse(name3.equals(1, 2));
        Assert.assertFalse(name3.equals(3, 4));
        Assert.assertFalse(name3.equals(-1, 0));
    }
}
