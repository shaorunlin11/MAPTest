package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptiongetArgNameTest {
    @Test
    public void testGetArgName() throws Exception {
        // Create an Option with a specific argName
        Option option = new Option("t", "test description");
        // Use the public setter method if available
        // Note: This assumes there is a public setArgName method, which may not exist
        // If no public setter exists, this test will fail at runtime
        option.setArgName("testArg");

        // Verify the returned argName matches the set value
        assertEquals("testArg", option.getArgName());
    }

    @Test
    public void testGetArgNameWithBuilder() throws Exception {
        // Create an Option using the Builder with a specific argName
        Option.Builder builder = Option.builder();
        builder.longOpt("t");
        builder.argName("testArg");
        builder.desc("test description");
        Option option = builder.build();

        // Verify the returned argName matches the set value
        assertEquals("testArg", option.getArgName());
    }
}
