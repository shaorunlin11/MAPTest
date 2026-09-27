package org.apache.commons.cli;

import org.junit.Test;

public class OptionBuilderIsRequiredZeroCoverageTest {
    @Test
    public void testIsRequiredWithTrue() {
        OptionBuilder.isRequired(true);
    }

    @Test
    public void testIsRequiredWithFalse() {
        OptionBuilder.isRequired(false);
    }
}
