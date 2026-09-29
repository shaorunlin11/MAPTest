package org.apache.commons.cli;

import org.junit.Test;

public class OptionBuilderHasArgZeroCoverageTest {
    @Test
    public void testHasArgTrue() {
        OptionBuilder.hasArg(true);
    }

    @Test
    public void testHasArgFalse() {
        OptionBuilder.hasArg(false);
    }

    @Test
    public void generatedBaselineCompiles() {
        // Zero-coverage baseline: keep the test class runnable before enhancement.
    }
}
