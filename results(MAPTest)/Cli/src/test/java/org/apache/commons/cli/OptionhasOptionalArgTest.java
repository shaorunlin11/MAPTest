package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionhasOptionalArgTest {

    @Test
    public void testHasOptionalArgReturnsFalseByDefault() throws Exception {
        Option option = new Option("a", "description");
        assertFalse(option.hasOptionalArg());
    }

    @Test
    public void testHasOptionalArgReturnsTrueWhenSet() throws Exception {
        Option.Builder builder = Option.builder("a");
        builder.optionalArg(true);
        Option option = builder.build();
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testHasOptionalArgWithConstructor() throws Exception {
        Option option = new Option("a", true, "description");
        assertFalse(option.hasOptionalArg());
    }

    @Test
    public void testHasOptionalArgWithLongOptConstructor() throws Exception {
        Option option = new Option("a", "longOpt", true, "description");
        assertFalse(option.hasOptionalArg());
    }
}
