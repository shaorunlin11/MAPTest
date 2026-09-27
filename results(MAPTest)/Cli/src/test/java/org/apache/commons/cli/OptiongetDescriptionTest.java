package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptiongetDescriptionTest {

    @Test
    public void testGetDescription_returnsNullWhenNotSet() {
        Option option = new Option("t", "test description");
        assertEquals("test description", option.getDescription());
    }

    @Test
    public void testGetDescription_returnsSetDescription() {
        Option option = new Option("t", "another description");
        assertEquals("another description", option.getDescription());
    }

    @Test
    public void testGetDescription_withBuilder() throws Exception {
        Option option = new Option("t", "builder description");
        assertEquals("builder description", option.getDescription());
    }
}
