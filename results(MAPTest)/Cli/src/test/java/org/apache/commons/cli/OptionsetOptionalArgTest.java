package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class OptionsetOptionalArgTest {
    private Option option;

    @Before
    public void setUp() {
        option = new Option("t", "test option");
    }

    @After
    public void tearDown() {
        option = null;
    }

    @Test
    public void testSetOptionalArg() throws Exception {
        // Test setting optionalArg to false
        option.setOptionalArg(false);
        Assert.assertFalse("optionalArg should be false after setOptionalArg(false)", option.hasOptionalArg());

        // Test setting optionalArg to true
        option.setOptionalArg(true);
        Assert.assertTrue("optionalArg should be true after setOptionalArg(true)", option.hasOptionalArg());
    }
}
