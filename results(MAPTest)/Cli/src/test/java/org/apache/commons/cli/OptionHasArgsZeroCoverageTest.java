package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

public class OptionHasArgsZeroCoverageTest {
    @Test
    public void testHasArgsWithUnlimitedValues() {
        Option option = new Option("t", "test", true, "test description");
        option.setArgs(Option.UNLIMITED_VALUES);
        Assert.assertTrue(option.hasArgs());
    }

    @Test
    public void testHasArgsWithNumberOfArgsGreaterThanOne() {
        Option option = new Option("t", "test", true, "test description");
        option.setArgs(2);
        Assert.assertTrue(option.hasArgs());
    }
}
