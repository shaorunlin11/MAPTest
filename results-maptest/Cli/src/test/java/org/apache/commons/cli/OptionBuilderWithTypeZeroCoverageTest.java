package org.apache.commons.cli;

import org.junit.Test;

public class OptionBuilderWithTypeZeroCoverageTest {
    @Test
    public void testWithType() {
        // Execute the target method with a non-null type
        OptionBuilder.withType(String.class);
    }
}
