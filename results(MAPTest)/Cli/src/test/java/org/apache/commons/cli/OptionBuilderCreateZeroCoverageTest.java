package org.apache.commons.cli;

import org.junit.Test;

public class OptionBuilderCreateZeroCoverageTest {
    @Test
    public void testCreateWithNonNullOpt() throws IllegalArgumentException {
        // Execute target lines 371 through the selected target plan
        OptionBuilder.create("test");
    }
}
