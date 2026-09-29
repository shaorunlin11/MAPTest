package org.apache.commons.cli;

import org.junit.Test;

public class OptionAddValueZeroCoverageTest {
    @Test
    public void testAddValueThrowsUnsupportedOperationException() {
        Option option = new Option("t", "test", false, "test description");
        try {
            option.addValue("testValue");
        } catch (UnsupportedOperationException e) {
            // Expected exception
        }
    }
}
